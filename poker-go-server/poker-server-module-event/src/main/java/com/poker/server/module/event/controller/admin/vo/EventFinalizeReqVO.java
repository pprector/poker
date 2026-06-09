package com.poker.server.module.event.controller.admin.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Schema(description = "管理后台 - 赛事活动结果录入 Request VO")
@Data
public class EventFinalizeReqVO {

    @Schema(description = "活动编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "活动编号不能为空")
    private Long eventId;

    @Schema(description = "排名结果列表")
    private List<ResultItem> results;

    @Data
    public static class ResultItem {

        @Schema(description = "用户编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
        @NotNull(message = "用户编号不能为空")
        private Long userId;

        @Schema(description = "排名", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
        @NotNull(message = "排名不能为空")
        private Integer rank;

        @Schema(description = "奖金金额", example = "5000.00")
        private java.math.BigDecimal prizeAmount;

        @Schema(description = "获得积分", example = "100")
        private Integer pointsEarned;
    }
}
