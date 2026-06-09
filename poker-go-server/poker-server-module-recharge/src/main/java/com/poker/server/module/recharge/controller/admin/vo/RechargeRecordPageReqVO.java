package com.poker.server.module.recharge.controller.admin.vo;

import com.poker.server.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Schema(description = "管理后台 - 储值记录分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class RechargeRecordPageReqVO extends PageParam {

    @Schema(description = "门店编号", example = "1")
    private Long clubId;

    @Schema(description = "用户编号", example = "1")
    private Long userId;

    @Schema(description = "状态", example = "PENDING")
    private String status;

    @Schema(description = "支付方式", example = "WECHAT")
    private String paymentMethod;
}
