package com.poker.server.module.recharge.controller.admin.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 储值记录 Response VO")
@Data
public class RechargeRecordRespVO {

    @Schema(description = "储值记录编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long id;

    @Schema(description = "门店编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long clubId;

    @Schema(description = "用户编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long userId;

    @Schema(description = "储值金额", requiredMode = Schema.RequiredMode.REQUIRED, example = "1000.00")
    private BigDecimal amount;

    @Schema(description = "赠送金额", example = "100.00")
    private BigDecimal bonusAmount;

    @Schema(description = "实际到账金额", example = "1100.00")
    private BigDecimal actualAmount;

    @Schema(description = "赠送积分", example = "100")
    private Integer pointsAwarded;

    @Schema(description = "支付方式", example = "WECHAT")
    private String paymentMethod;

    @Schema(description = "状态", example = "PENDING")
    private String status;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;
}
