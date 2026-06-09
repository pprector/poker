package com.poker.server.module.event.controller.admin.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 报名记录 Response VO")
@Data
public class RegistrationRespVO {

    @Schema(description = "报名编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long id;

    @Schema(description = "活动编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long eventId;

    @Schema(description = "用户编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long userId;

    @Schema(description = "座位号", example = "5")
    private Integer seatNumber;

    @Schema(description = "报名时间")
    private LocalDateTime registrationTime;

    @Schema(description = "报名状态", example = "REGISTERED")
    private String status;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;
}
