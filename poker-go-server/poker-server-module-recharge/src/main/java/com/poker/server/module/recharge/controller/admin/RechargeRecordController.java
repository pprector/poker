package com.poker.server.module.recharge.controller.admin;

import com.poker.server.framework.common.pojo.CommonResult;
import com.poker.server.framework.common.pojo.PageResult;
import com.poker.server.framework.common.util.object.BeanUtils;
import com.poker.server.module.recharge.controller.admin.vo.RechargeRecordPageReqVO;
import com.poker.server.module.recharge.controller.admin.vo.RechargeRecordRespVO;
import com.poker.server.module.recharge.controller.admin.vo.RechargeRecordSaveReqVO;
import com.poker.server.module.recharge.dal.dataobject.RechargeRecordDO;
import com.poker.server.module.recharge.service.RechargeRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Tag(name = "管理后台 - 储值记录")
@RestController
@RequestMapping("/recharge-record")
public class RechargeRecordController {

    @Resource
    private RechargeRecordService rechargeRecordService;

    @PostMapping("/create")
    @Operation(summary = "创建储值记录")
    @PreAuthorize("@ss.hasPermission('recharge:create')")
    public CommonResult<Long> createRechargeRecord(@Valid @RequestBody RechargeRecordSaveReqVO createReqVO) {
        return CommonResult.success(rechargeRecordService.createRechargeRecord(createReqVO));
    }

    @PutMapping("/confirm")
    @Operation(summary = "确认储值记录")
    @PreAuthorize("@ss.hasPermission('recharge:update')")
    public CommonResult<Boolean> confirmRecharge(@RequestParam("id") Long id) {
        rechargeRecordService.confirmRecharge(id);
        return CommonResult.success(true);
    }

    @GetMapping("/page")
    @Operation(summary = "获得储值记录分页")
    @PreAuthorize("@ss.hasPermission('recharge:query')")
    public CommonResult<PageResult<RechargeRecordRespVO>> getRechargeRecordPage(@Valid RechargeRecordPageReqVO pageReqVO) {
        PageResult<RechargeRecordDO> pageResult = rechargeRecordService.getRechargeRecordPage(pageReqVO);
        return CommonResult.success(BeanUtils.toBean(pageResult, RechargeRecordRespVO.class));
    }

    @GetMapping("/get-user-summary")
    @Operation(summary = "获得用户储值汇总")
    @PreAuthorize("@ss.hasPermission('recharge:query')")
    public CommonResult<BigDecimal> getUserRechargeSummary(@RequestParam("userId") Long userId,
                                                           @RequestParam("clubId") Long clubId) {
        return CommonResult.success(rechargeRecordService.getUserRechargeSummary(userId, clubId));
    }
}
