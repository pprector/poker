-- ======================== PokerGo 业务表 ========================

-- 门店表
CREATE TABLE IF NOT EXISTS `club` (
    `id`            BIGINT PRIMARY KEY AUTO_INCREMENT,
    `name`          VARCHAR(50)   NOT NULL COMMENT '门店名称',
    `logo_url`      VARCHAR(500)  COMMENT 'Logo图片URL',
    `address`       VARCHAR(200)  NOT NULL COMMENT '地址',
    `phone`         VARCHAR(20)   NOT NULL COMMENT '联系电话',
    `open_hours`    VARCHAR(100)  COMMENT '营业时间',
    `photos`        JSON          COMMENT '门店照片URL数组',
    `intro_text`    VARCHAR(500)  COMMENT '门店介绍',
    `theme_color`   VARCHAR(20)   DEFAULT '#667eea' COMMENT '主题主色',
    `theme_config`  JSON          COMMENT '完整主题配置',
    `theme_template` VARCHAR(30)  DEFAULT 'night_purple' COMMENT '主题模板标识',
    `module_flags`  JSON          COMMENT '功能模块开关',
    `owner_id`      BIGINT        COMMENT '经营者用户ID',
    `status`        TINYINT       DEFAULT 1 COMMENT '1=正常 0=下线',
    `creator`       VARCHAR(64)   DEFAULT '' COMMENT '创建者',
    `create_time`   DATETIME      DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`       VARCHAR(64)   DEFAULT '' COMMENT '更新者',
    `update_time`   DATETIME      DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`       TINYINT       DEFAULT 0 COMMENT '是否删除',
    `tenant_id`     BIGINT        DEFAULT 0 COMMENT '租户编号',
    INDEX `idx_owner` (`owner_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='门店表';

-- 用户表
CREATE TABLE IF NOT EXISTS `club_user` (
    `id`            BIGINT PRIMARY KEY AUTO_INCREMENT,
    `openid`        VARCHAR(64)   COMMENT '微信OpenID',
    `unionid`       VARCHAR(64)   COMMENT '微信UnionID',
    `phone`         VARCHAR(20)   COMMENT '手机号',
    `nickname`      VARCHAR(50)   COMMENT '昵称',
    `avatar_url`    VARCHAR(500)  COMMENT '头像URL',
    `role`          TINYINT       DEFAULT 0 COMMENT '0=玩家 1=经营者 2=管理员',
    `total_points`  INT           DEFAULT 0 COMMENT '累计积分',
    `creator`       VARCHAR(64)   DEFAULT '' COMMENT '创建者',
    `create_time`   DATETIME      DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`       VARCHAR(64)   DEFAULT '' COMMENT '更新者',
    `update_time`   DATETIME      DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`       TINYINT       DEFAULT 0 COMMENT '是否删除',
    `tenant_id`     BIGINT        DEFAULT 0 COMMENT '租户编号',
    UNIQUE KEY `uk_openid` (`openid`),
    UNIQUE KEY `uk_phone` (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- 活动表
CREATE TABLE IF NOT EXISTS `event` (
    `id`              BIGINT PRIMARY KEY AUTO_INCREMENT,
    `club_id`         BIGINT        NOT NULL COMMENT '所属门店',
    `name`            VARCHAR(100)  NOT NULL COMMENT '活动名称',
    `event_type`      VARCHAR(20)   DEFAULT 'SOCIAL' COMMENT 'SOCIAL=社交赛 TOURNAMENT=锦标赛',
    `start_time`      DATETIME      NOT NULL COMMENT '开始时间',
    `entry_fee`       DECIMAL(10,2) COMMENT '报名费（元）',
    `max_players`     INT           DEFAULT 20 COMMENT '人数上限',
    `blind_template`  VARCHAR(50)   COMMENT '盲注模板标识',
    `prize_desc`      VARCHAR(200)  COMMENT '奖品描述',
    `status`          VARCHAR(20)   DEFAULT 'DRAFT' COMMENT 'DRAFT=草稿 OPEN=招募中 ONGOING=进行中 FINISHED=已结束 CANCELLED=已取消',
    `creator`         VARCHAR(64)   DEFAULT '' COMMENT '创建者',
    `create_time`     DATETIME      DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`         VARCHAR(64)   DEFAULT '' COMMENT '更新者',
    `update_time`     DATETIME      DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`         TINYINT       DEFAULT 0 COMMENT '是否删除',
    `tenant_id`       BIGINT        DEFAULT 0 COMMENT '租户编号',
    INDEX `idx_club_status` (`club_id`, `status`),
    INDEX `idx_start_time` (`start_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='活动表';

-- 报名记录表
CREATE TABLE IF NOT EXISTS `registration` (
    `id`          BIGINT PRIMARY KEY AUTO_INCREMENT,
    `event_id`    BIGINT    NOT NULL,
    `user_id`     BIGINT    NOT NULL,
    `name`        VARCHAR(20) COMMENT '报名姓名',
    `phone`       VARCHAR(20) COMMENT '报名手机号',
    `status`      VARCHAR(20) DEFAULT 'REGISTERED' COMMENT 'REGISTERED=已报名 CANCELLED=已取消',
    `creator`     VARCHAR(64) DEFAULT '' COMMENT '创建者',
    `create_time` DATETIME  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`     VARCHAR(64) DEFAULT '' COMMENT '更新者',
    `update_time` DATETIME  DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`     TINYINT   DEFAULT 0 COMMENT '是否删除',
    `tenant_id`   BIGINT    DEFAULT 0 COMMENT '租户编号',
    UNIQUE KEY `uk_event_user` (`event_id`, `user_id`),
    INDEX `idx_event` (`event_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='报名记录表';

-- 成绩表
CREATE TABLE IF NOT EXISTS `result` (
    `id`            BIGINT PRIMARY KEY AUTO_INCREMENT,
    `event_id`      BIGINT NOT NULL,
    `user_id`       BIGINT NOT NULL,
    `rank`          INT    NOT NULL COMMENT '名次',
    `points_earned` INT    DEFAULT 0 COMMENT '获得积分',
    `creator`       VARCHAR(64) DEFAULT '' COMMENT '创建者',
    `create_time`   DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`       VARCHAR(64) DEFAULT '' COMMENT '更新者',
    `update_time`   DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`       TINYINT  DEFAULT 0 COMMENT '是否删除',
    `tenant_id`     BIGINT   DEFAULT 0 COMMENT '租户编号',
    UNIQUE KEY `uk_event_user` (`event_id`, `user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='成绩表';

-- 门店经营模式配置表
CREATE TABLE IF NOT EXISTS `club_business_config` (
    `id`                      BIGINT PRIMARY KEY AUTO_INCREMENT,
    `club_id`                 BIGINT       NOT NULL COMMENT '所属门店',
    `template_name`           VARCHAR(30)  DEFAULT 'social_light' COMMENT '经营模式模板标识',
    `consume_points_enabled`  TINYINT      DEFAULT 1 COMMENT '消费送积分开关',
    `consume_points_ratio`    INT          DEFAULT 100 COMMENT '消费1元=X积分',
    `consume_points_threshold` DECIMAL(10,2) DEFAULT 0 COMMENT '消费满X元才送',
    `consume_points_fixed`    INT          DEFAULT 0 COMMENT '满额固定赠送积分',
    `event_points_enabled`    TINYINT      DEFAULT 1 COMMENT '赛事排名送积分开关',
    `event_points_template`   VARCHAR(20)  DEFAULT 'standard' COMMENT '排名积分模板',
    `event_points_custom`     JSON         COMMENT '自定义排名积分',
    `first_visit_bonus`       INT          DEFAULT 0 COMMENT '首次到店赠送积分',
    `daily_checkin_points`    INT          DEFAULT 0 COMMENT '每日签到积分',
    `recharge_enabled`        TINYINT      DEFAULT 0 COMMENT '储值功能开关',
    `recharge_mode`           VARCHAR(20)  DEFAULT 'FULL' COMMENT '储值模式',
    `recharge_discount_rate`  INT          DEFAULT 100 COMMENT '折扣到账比率',
    `recharge_bonus_rules`    JSON         COMMENT '充送阶梯',
    `points_usage`            JSON         COMMENT '积分用途',
    `exchange_rate`           INT          DEFAULT 1000 COMMENT '兑换比率',
    `deduct_rate`             INT          DEFAULT 0 COMMENT '抵扣比率',
    `entry_points_cost`       INT          DEFAULT 0 COMMENT '兑换入场券所需积分',
    `creator`                 VARCHAR(64)  DEFAULT '' COMMENT '创建者',
    `create_time`             DATETIME     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`                 VARCHAR(64)  DEFAULT '' COMMENT '更新者',
    `update_time`             DATETIME     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`                 TINYINT      DEFAULT 0 COMMENT '是否删除',
    `tenant_id`               BIGINT       DEFAULT 0 COMMENT '租户编号',
    UNIQUE KEY `uk_club` (`club_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='门店经营模式配置表';

-- 储值记录表
CREATE TABLE IF NOT EXISTS `recharge_record` (
    `id`            BIGINT PRIMARY KEY AUTO_INCREMENT,
    `club_id`       BIGINT        NOT NULL,
    `user_id`       BIGINT        NOT NULL,
    `amount`        DECIMAL(10,2) NOT NULL COMMENT '充值金额',
    `actual`        DECIMAL(10,2) NOT NULL COMMENT '实际到账金额',
    `bonus`         DECIMAL(10,2) DEFAULT 0 COMMENT '赠送金额',
    `points_awarded` INT           DEFAULT 0 COMMENT '充值赠送积分',
    `creator`       VARCHAR(64) DEFAULT '' COMMENT '创建者',
    `create_time`   DATETIME      DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`       VARCHAR(64) DEFAULT '' COMMENT '更新者',
    `update_time`   DATETIME      DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`       TINYINT       DEFAULT 0 COMMENT '是否删除',
    `tenant_id`     BIGINT        DEFAULT 0 COMMENT '租户编号',
    INDEX `idx_club_user` (`club_id`, `user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='储值记录表';

-- 积分流水表
CREATE TABLE IF NOT EXISTS `points_log` (
    `id`          BIGINT PRIMARY KEY AUTO_INCREMENT,
    `club_id`     BIGINT       NOT NULL,
    `user_id`     BIGINT       NOT NULL,
    `points`      INT          NOT NULL COMMENT '积分变动（正=获得，负=消耗）',
    `type`        VARCHAR(20)  NOT NULL COMMENT 'CONSUME/EVENT/RECHARGE/EXCHANGE/DEDUCT/CHECKIN/BONUS',
    `ref_id`      BIGINT       COMMENT '关联ID',
    `remark`      VARCHAR(100) COMMENT '备注说明',
    `balance`     INT          NOT NULL COMMENT '变动后余额',
    `creator`     VARCHAR(64) DEFAULT '' COMMENT '创建者',
    `create_time` DATETIME     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`     VARCHAR(64) DEFAULT '' COMMENT '更新者',
    `update_time` DATETIME     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`     TINYINT      DEFAULT 0 COMMENT '是否删除',
    `tenant_id`   BIGINT       DEFAULT 0 COMMENT '租户编号',
    INDEX `idx_club_user` (`club_id`, `user_id`),
    INDEX `idx_type` (`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='积分流水表';

-- 商品表
CREATE TABLE IF NOT EXISTS `product` (
    `id`          BIGINT PRIMARY KEY AUTO_INCREMENT,
    `club_id`     BIGINT       NOT NULL,
    `category_id` BIGINT       COMMENT '分类ID',
    `name`        VARCHAR(50)  NOT NULL COMMENT '商品名称',
    `image_url`   VARCHAR(500) COMMENT '商品图片',
    `price`       DECIMAL(10,2) NOT NULL COMMENT '价格',
    `description` VARCHAR(200) COMMENT '简介',
    `sort_order`  INT          DEFAULT 0 COMMENT '排序',
    `is_sold_out` TINYINT      DEFAULT 0 COMMENT '0=在售 1=售罄',
    `creator`     VARCHAR(64)  DEFAULT '' COMMENT '创建者',
    `create_time` DATETIME     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`     VARCHAR(64)  DEFAULT '' COMMENT '更新者',
    `update_time` DATETIME     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`     TINYINT      DEFAULT 0 COMMENT '是否删除',
    `tenant_id`   BIGINT       DEFAULT 0 COMMENT '租户编号',
    INDEX `idx_club_category` (`club_id`, `category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品表';

-- 商品分类表
CREATE TABLE IF NOT EXISTS `product_category` (
    `id`         BIGINT PRIMARY KEY AUTO_INCREMENT,
    `club_id`    BIGINT      NOT NULL,
    `name`       VARCHAR(30) NOT NULL COMMENT '分类名',
    `sort_order` INT         DEFAULT 0,
    `creator`    VARCHAR(64) DEFAULT '' COMMENT '创建者',
    `create_time` DATETIME   DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`    VARCHAR(64) DEFAULT '' COMMENT '更新者',
    `update_time` DATETIME   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`    TINYINT     DEFAULT 0 COMMENT '是否删除',
    `tenant_id`  BIGINT      DEFAULT 0 COMMENT '租户编号',
    INDEX `idx_club` (`club_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品分类表';

-- 订单表
CREATE TABLE IF NOT EXISTS `order` (
    `id`           BIGINT PRIMARY KEY AUTO_INCREMENT,
    `order_no`     VARCHAR(32)  NOT NULL COMMENT '订单号',
    `club_id`      BIGINT       NOT NULL,
    `user_id`      BIGINT       NOT NULL COMMENT '下单用户',
    `table_no`     VARCHAR(10)  COMMENT '桌号',
    `total_amount` DECIMAL(10,2) NOT NULL COMMENT '订单金额',
    `status`       VARCHAR(20)  DEFAULT 'PENDING' COMMENT 'PENDING=待确认 CONFIRMED=已确认 COMPLETED=已完成 CANCELLED=已取消',
    `remark`       VARCHAR(100) COMMENT '备注',
    `creator`      VARCHAR(64)  DEFAULT '' COMMENT '创建者',
    `create_time`  DATETIME     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`      VARCHAR(64)  DEFAULT '' COMMENT '更新者',
    `update_time`  DATETIME     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`      TINYINT      DEFAULT 0 COMMENT '是否删除',
    `tenant_id`    BIGINT       DEFAULT 0 COMMENT '租户编号',
    UNIQUE KEY `uk_order_no` (`order_no`),
    INDEX `idx_club_status` (`club_id`, `status`),
    INDEX `idx_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单表';

-- 订单明细表
CREATE TABLE IF NOT EXISTS `order_item` (
    `id`           BIGINT PRIMARY KEY AUTO_INCREMENT,
    `order_id`     BIGINT        NOT NULL,
    `product_id`   BIGINT        NOT NULL,
    `product_name` VARCHAR(50)   NOT NULL COMMENT '商品名称（快照）',
    `price`        DECIMAL(10,2) NOT NULL COMMENT '单价（快照）',
    `quantity`     INT           NOT NULL DEFAULT 1,
    `creator`      VARCHAR(64)   DEFAULT '' COMMENT '创建者',
    `create_time`  DATETIME      DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`      VARCHAR(64)   DEFAULT '' COMMENT '更新者',
    `update_time`  DATETIME      DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`      TINYINT       DEFAULT 0 COMMENT '是否删除',
    `tenant_id`    BIGINT        DEFAULT 0 COMMENT '租户编号',
    INDEX `idx_order` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单明细表';

-- 知识库文章表
CREATE TABLE IF NOT EXISTS `knowledge_article` (
    `id`           BIGINT PRIMARY KEY AUTO_INCREMENT,
    `title`        VARCHAR(100) NOT NULL COMMENT '标题',
    `category`     VARCHAR(30)  NOT NULL COMMENT '分类: RULES/EVENT/COCKTAIL/COMPLIANCE/BIZ',
    `cover_url`    VARCHAR(500) COMMENT '封面图',
    `summary`      VARCHAR(200) COMMENT '摘要',
    `content`      TEXT         NOT NULL COMMENT '正文（Markdown）',
    `read_count`   INT          DEFAULT 0 COMMENT '阅读量',
    `is_published` TINYINT      DEFAULT 1 COMMENT '0=草稿 1=已发布',
    `creator`      VARCHAR(64)  DEFAULT '' COMMENT '创建者',
    `create_time`  DATETIME     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`      VARCHAR(64)  DEFAULT '' COMMENT '更新者',
    `update_time`  DATETIME     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`      TINYINT      DEFAULT 0 COMMENT '是否删除',
    `tenant_id`    BIGINT       DEFAULT 0 COMMENT '租户编号',
    INDEX `idx_category` (`category`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='知识库文章表';

-- 合规文档表
CREATE TABLE IF NOT EXISTS `compliance_doc` (
    `id`         BIGINT PRIMARY KEY AUTO_INCREMENT,
    `title`      VARCHAR(100) NOT NULL,
    `category`   VARCHAR(30)  NOT NULL COMMENT 'REDLINE/POSITIONING/CHECKLIST/TEMPLATE',
    `content`    TEXT         NOT NULL,
    `sort_order` INT          DEFAULT 0,
    `creator`    VARCHAR(64)  DEFAULT '' COMMENT '创建者',
    `create_time` DATETIME    DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`    VARCHAR(64)  DEFAULT '' COMMENT '更新者',
    `update_time` DATETIME    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`    TINYINT      DEFAULT 0 COMMENT '是否删除',
    `tenant_id`  BIGINT       DEFAULT 0 COMMENT '租户编号',
    INDEX `idx_category` (`category`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='合规文档表';

-- ======================== 初始数据 ========================
-- 演示门店
INSERT IGNORE INTO `club` (`id`, `name`, `address`, `phone`, `open_hours`, `intro_text`, `status`, `owner_id`, `creator`, `tenant_id`)
VALUES (1, '老友精酿扑克吧', '朝阳区三里屯路88号 B1层', '13812345678', '周一至周日 18:00-02:00', '这里不只是打牌，更是一群人的社交客厅', 1, 1, '1', 0);
