package com.poker.server.module.event.controller.admin.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 比赛结果 Response VO")
@Data
public class ResultRespVO {

    @Schema(description = "结果编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long id;

    @Schema(description = "活动编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long eventId;

    @Schema(description = "用户编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long userId;

    @Schema(description = "排名", example = "1")
    private Integer rank;

    @Schema(description = "奖金金额", example = "5000.00")
    private BigDecimal prizeAmount;

    @Schema(description = "获得积分", example = "100")
    private Integer pointsEarned;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;
}
