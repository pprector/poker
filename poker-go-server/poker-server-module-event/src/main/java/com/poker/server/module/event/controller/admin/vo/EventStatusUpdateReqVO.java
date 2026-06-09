package com.poker.server.module.event.controller.admin.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "管理后台 - 赛事活动状态更新 Request VO")
@Data
public class EventStatusUpdateReqVO {

    @Schema(description = "活动编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "活动编号不能为空")
    private Long id;

    @Schema(description = "目标状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "OPEN")
    @NotNull(message = "目标状态不能为空")
    private String status;
}
