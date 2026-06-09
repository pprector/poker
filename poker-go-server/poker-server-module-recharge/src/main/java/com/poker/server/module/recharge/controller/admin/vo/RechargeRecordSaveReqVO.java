package com.poker.server.module.recharge.controller.admin.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 储值记录创建/修改 Request VO")
@Data
public class RechargeRecordSaveReqVO {

    @Schema(description = "储值记录编号", example = "1")
    private Long id;

    @Schema(description = "门店编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "门店编号不能为空")
    private Long clubId;

    @Schema(description = "用户编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "用户编号不能为空")
    private Long userId;

    @Schema(description = "储值金额", requiredMode = Schema.RequiredMode.REQUIRED, example = "1000.00")
    @NotNull(message = "储值金额不能为空")
    private BigDecimal amount;

    @Schema(description = "赠送金额", example = "100.00")
    private BigDecimal bonusAmount;

    @Schema(description = "实际到账金额", example = "1100.00")
    private BigDecimal actualAmount;

    @Schema(description = "赠送积分", example = "100")
    private Integer pointsAwarded;

    @Schema(description = "支付方式", example = "WECHAT")
    private String paymentMethod;
}
