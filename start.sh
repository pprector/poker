#!/bin/bash

# ==========================================
#  PokerGo 项目快速启动/重启脚本
#  流程：停止服务 → 检查依赖 → 编译后端
#        → 启动 → 健康检查
#
#  优化说明：
#  - 保留 Maven 增量编译（去掉 mvn clean）
#  - 保留 Vite/Turbo 缓存（加快前端启动）
#  - 修复前端端口为实际值 5173
#  - 后端编译与前端依赖检查并行执行
# ==========================================

RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m'

PROJECT_ROOT="$(cd "$(dirname "$0")" && pwd)"
BACKEND_DIR="$PROJECT_ROOT/poker-go-server"
FRONTEND_DIR="$PROJECT_ROOT/poker-go-admin"
LOG_DIR="$PROJECT_ROOT/logs"

BACKEND_PORT=48080
FRONTEND_PORT=5666
HEALTH_CHECK_URL="http://localhost:48080/actuator/health"

MYSQL_PORT=3306
REDIS_PORT=6379

mkdir -p "$LOG_DIR"

print_banner() {
    echo -e "${BLUE}========================================${NC}"
    echo -e "${BLUE}  PokerGo 项目快速启动${NC}"
    echo -e "${BLUE}========================================${NC}"
    echo ""
}

# 杀死指定端口的进程
kill_port() {
    local port=$1
    local name=$2
    local pid
    pid=$(lsof -ti :"$port" 2>/dev/null || true)
    if [ -n "$pid" ]; then
        echo "  停止 ${name} (PID: $pid, 端口: $port)"
        kill -15 "$pid" 2>/dev/null || true
        sleep 2
        pid=$(lsof -ti :"$port" 2>/dev/null || true)
        if [ -n "$pid" ]; then
            kill -9 "$pid" 2>/dev/null || true
            echo "  ${name} 已强制停止"
        else
            echo "  ${name} 已优雅停止"
        fi
    else
        echo "  ${name} 未运行"
    fi
}

# ==========================================
# 步骤 1：停止现有服务
# ==========================================
stop_services() {
    echo -e "${YELLOW}[1/5] 停止现有服务...${NC}"
    kill_port $BACKEND_PORT "后端服务"
    kill_port $FRONTEND_PORT "前端服务"

    # 清理残留 Spring Boot / Vite 进程
    local pids
    pids=$(ps aux | grep -E "(vite.*dev|spring-boot:run)" | grep -v grep | awk '{print $2}' || true)
    if [ -n "$pids" ]; then
        kill -9 $pids 2>/dev/null || true
    fi
    echo ""
}

# ==========================================
# 步骤 2：检查依赖（MySQL + Redis）
# ==========================================
check_dependencies() {
    echo -e "${YELLOW}[2/5] 检查依赖服务...${NC}"

    local mysql_ok=false
    local redis_ok=false

    check_port() {
        local host=$1 port=$2
        if command -v nc &>/dev/null; then
            nc -z -w3 "$host" "$port" 2>/dev/null && return 0
        fi
        (echo > /dev/tcp/"$host"/"$port") 2>/dev/null && return 0
        return 1
    }

    # ----- MySQL -----
    if check_port 127.0.0.1 $MYSQL_PORT; then
        mysql_ok=true
    fi

    if [ "$mysql_ok" = false ] && command -v docker &>/dev/null; then
        if docker ps -a --format '{{.Names}}' | grep -q "pokergo-mysql"; then
            echo "  检测到 Docker 容器 pokergo-mysql，正在启动..."
            docker start pokergo-mysql > /dev/null
            for i in $(seq 1 15); do
                check_port 127.0.0.1 $MYSQL_PORT && { mysql_ok=true; break; }
                sleep 2
            done
        else
            echo "  ${YELLOW}  Docker 容器 pokergo-mysql 不存在，尝试 docker compose 启动...${NC}"
            cd "$BACKEND_DIR/script/docker" 2>/dev/null && docker compose up -d mysql 2>/dev/null && {
                for i in $(seq 1 15); do
                    check_port 127.0.0.1 $MYSQL_PORT && { mysql_ok=true; break; }
                    sleep 2
                done
            }
        fi
    fi

    if [ "$mysql_ok" = true ]; then
        echo -e "  MySQL  端口 ${MYSQL_PORT}  ${GREEN}✓ 运行中${NC}"
    else
        echo -e "  MySQL  端口 ${MYSQL_PORT}  ${RED}✗ 无法连接${NC}"
    fi

    # ----- Redis -----
    if check_port 127.0.0.1 $REDIS_PORT; then
        redis_ok=true
    fi

    if [ "$redis_ok" = false ] && command -v docker &>/dev/null; then
        if docker ps -a --format '{{.Names}}' | grep -q "pokergo-redis"; then
            echo "  检测到 Docker 容器 pokergo-redis，正在启动..."
            docker start pokergo-redis > /dev/null
            for i in $(seq 1 10); do
                check_port 127.0.0.1 $REDIS_PORT && { redis_ok=true; break; }
                sleep 2
            done
        else
            echo "  ${YELLOW}  Docker 容器 pokergo-redis 不存在，尝试 docker compose 启动...${NC}"
            cd "$BACKEND_DIR/script/docker" 2>/dev/null && docker compose up -d redis 2>/dev/null && {
                for i in $(seq 1 10); do
                    check_port 127.0.0.1 $REDIS_PORT && { redis_ok=true; break; }
                    sleep 2
                done
            }
        fi
    fi

    if [ "$redis_ok" = true ]; then
        echo -e "  Redis  端口 ${REDIS_PORT}  ${GREEN}✓ 运行中${NC}"
    else
        echo -e "  Redis  端口 ${REDIS_PORT}  ${RED}✗ 无法连接${NC}"
    fi

    echo ""
    if [ "$mysql_ok" = false ]; then
        echo -e "${RED}  ⚠ MySQL 未就绪${NC}"
        echo -e "     ${YELLOW}docker start pokergo-mysql${NC}"
    fi
    if [ "$redis_ok" = false ]; then
        echo -e "${RED}  ⚠ Redis 未就绪${NC}"
        echo -e "     ${YELLOW}docker start pokergo-redis${NC}"
    fi
    if [ "$mysql_ok" = false ] || [ "$redis_ok" = false ]; then
        exit 1
    fi
}

# ==========================================
# 步骤 3：编译后端 + 检查前端依赖（并行）
# ==========================================
compile_and_check_deps() {
    echo -e "${YELLOW}[3/5] 编译后端 + 检查前端依赖（并行执行）...${NC}"

    # 并行：后端编译 & 前端依赖检查
    (
        echo "  [后端] 增量编译 poker-server-server..."
        cd "$BACKEND_DIR" || exit 1
        mvn compile -pl poker-server-server -am -q > "$LOG_DIR/backend-compile.log" 2>&1
        local exit_code=$?
        if [ $exit_code -eq 0 ]; then
            echo -e "  [后端] ${GREEN}✓ 编译成功${NC}"
        else
            echo -e "  [后端] ${RED}✗ 编译失败 (退出码: $exit_code)${NC}"
            tail -40 "$LOG_DIR/backend-compile.log"
            exit 1
        fi
    ) &

    (
        echo "  [前端] 检查 node_modules..."
        cd "$FRONTEND_DIR" || exit 1
        if [ ! -d "node_modules" ]; then
            echo "  [前端] node_modules 不存在，执行 pnpm install..."
            pnpm install > "$LOG_DIR/frontend-install.log" 2>&1
            local exit_code=$?
            if [ $exit_code -eq 0 ]; then
                echo -e "  [前端] ${GREEN}✓ 依赖安装成功${NC}"
            else
                echo -e "  [前端] ${RED}✗ 依赖安装失败${NC}"
                tail -40 "$LOG_DIR/frontend-install.log"
                exit 1
            fi
        else
            echo -e "  [前端] ${GREEN}✓ node_modules 已存在${NC}"
        fi
    ) &

    wait
    echo ""
}

# ==========================================
# 步骤 4：启动服务（并行）
# ==========================================
start_services() {
    echo -e "${YELLOW}[4/5] 启动服务...${NC}"

    # 启动后端
    echo "  启动后端服务 (端口: $BACKEND_PORT)..."
    cd "$BACKEND_DIR" || exit 1
    nohup mvn spring-boot:run \
        -pl poker-server-server \
        -Dspring-boot.run.profiles=local \
        > "$LOG_DIR/backend.log" 2>&1 &
    BACKEND_PID=$!
    echo "  后端 PID: $BACKEND_PID"

    # 启动前端
    echo "  启动前端服务 (端口: $FRONTEND_PORT)..."
    cd "$FRONTEND_DIR" || exit 1
    nohup pnpm dev:antd > "$LOG_DIR/frontend.log" 2>&1 &
    FRONTEND_PID=$!
    echo "  前端 PID: $FRONTEND_PID"
    echo ""
}

# ==========================================
# 步骤 5：健康检查
# ==========================================
health_check() {
    echo -e "${YELLOW}[5/5] 健康检查...${NC}"

    # 后端健康检查（最长等待 120 秒）
    echo -n "  等待后端服务启动"
    local backend_ok=false
    for i in $(seq 1 60); do
        if curl -s -o /dev/null -w "%{http_code}" "$HEALTH_CHECK_URL" 2>/dev/null | grep -q "200"; then
            backend_ok=true
            echo ""
            echo -e "  ${GREEN}后端服务启动成功 ✓${NC}"
            break
        fi
        echo -n "."
        sleep 2
    done

    if [ "$backend_ok" = false ]; then
        echo ""
        echo -e "  ${RED}后端服务启动超时，请查看日志: ${LOG_DIR}/backend.log${NC}"
        tail -20 "$LOG_DIR/backend.log"
    fi

    # 前端检查（最长等待 60 秒）
    echo -n "  等待前端服务启动"
    local frontend_ok=false
    for i in $(seq 1 30); do
        if lsof -ti :"$FRONTEND_PORT" > /dev/null 2>&1; then
            frontend_ok=true
            echo ""
            echo -e "  ${GREEN}前端服务启动成功 ✓${NC}"
            break
        fi
        echo -n "."
        sleep 2
    done
    if [ "$frontend_ok" = false ]; then
        echo ""
        echo -e "  ${RED}前端服务启动超时，请查看日志: ${LOG_DIR}/frontend.log${NC}"
        tail -10 "$LOG_DIR/frontend.log"
    fi
    echo ""
}

# ==========================================
# 打印结果
# ==========================================
print_result() {
    local backend_running=false
    local frontend_running=false

    lsof -ti :"$BACKEND_PORT" > /dev/null 2>&1 && backend_running=true
    lsof -ti :"$FRONTEND_PORT" > /dev/null 2>&1 && frontend_running=true

    echo -e "${BLUE}========================================${NC}"
    if [ "$backend_running" = true ] && [ "$frontend_running" = true ]; then
        echo -e "${GREEN}  🎉 PokerGo 项目启动完成！${NC}"
    elif [ "$backend_running" = true ]; then
        echo -e "${YELLOW}  ⚠️  仅后端服务启动成功${NC}"
    elif [ "$frontend_running" = true ]; then
        echo -e "${YELLOW}  ⚠️  仅前端服务启动成功${NC}"
    else
        echo -e "${RED}  ❌ 所有服务启动失败${NC}"
    fi
    echo -e "${BLUE}========================================${NC}"
    echo -e "  前端地址: ${GREEN}http://localhost:${FRONTEND_PORT}${NC}"
    echo -e "  后端地址: ${GREEN}http://localhost:${BACKEND_PORT}${NC}"
    echo -e "  API 地址: ${GREEN}http://localhost:${BACKEND_PORT}/admin-api${NC}"
    echo -e "  后端日志: ${YELLOW}${LOG_DIR}/backend.log${NC}"
    echo -e "  前端日志: ${YELLOW}${LOG_DIR}/frontend.log${NC}"
    echo ""
    echo -e "  查看后端实时日志: ${YELLOW}tail -f ${LOG_DIR}/backend.log${NC}"
    echo -e "  查看前端实时日志: ${YELLOW}tail -f ${LOG_DIR}/frontend.log${NC}"
    echo -e "${BLUE}========================================${NC}"
}

# ==========================================
# 主流程
# ==========================================
print_banner
stop_services
check_dependencies
compile_and_check_deps
start_services
health_check
print_result
