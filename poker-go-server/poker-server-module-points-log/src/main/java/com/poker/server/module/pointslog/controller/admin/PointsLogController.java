package com.poker.server.module.pointslog.controller.admin;

import com.poker.server.framework.common.pojo.CommonResult;
import com.poker.server.framework.common.pojo.PageResult;
import com.poker.server.framework.common.util.object.BeanUtils;
import com.poker.server.module.pointslog.controller.admin.vo.PointsLogPageReqVO;
import com.poker.server.module.pointslog.controller.admin.vo.PointsLogRespVO;
import com.poker.server.module.pointslog.controller.admin.vo.PointsLogSaveReqVO;
import com.poker.server.module.pointslog.dal.dataobject.PointsLogDO;
import com.poker.server.module.pointslog.service.PointsLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Tag(name = "管理后台 - 积分流水")
@RestController
@RequestMapping("/points-log")
public class PointsLogController {

    @Resource
    private PointsLogService pointsLogService;

    @PostMapping("/create")
    @Operation(summary = "创建积分流水")
    @PreAuthorize("@ss.hasPermission('points-log:create')")
    public CommonResult<Long> createPointsLog(@Valid @RequestBody PointsLogSaveReqVO createReqVO) {
        return CommonResult.success(pointsLogService.createPointsLog(createReqVO));
    }

    @GetMapping("/page")
    @Operation(summary = "获得积分流水分页")
    @PreAuthorize("@ss.hasPermission('points-log:query')")
    public CommonResult<PageResult<PointsLogRespVO>> getPointsLogPage(@Valid PointsLogPageReqVO pageReqVO) {
        PageResult<PointsLogDO> pageResult = pointsLogService.getPointsLogPage(pageReqVO);
        return CommonResult.success(BeanUtils.toBean(pageResult, PointsLogRespVO.class));
    }

    @GetMapping("/get-user-summary")
    @Operation(summary = "获得用户积分汇总")
    @PreAuthorize("@ss.hasPermission('points-log:query')")
    public CommonResult<Map<String, Object>> getUserPointsSummary(@RequestParam("userId") Long userId,
                                                                   @RequestParam("clubId") Long clubId) {
        return CommonResult.success(pointsLogService.getUserPointsSummary(userId, clubId));
    }
}
