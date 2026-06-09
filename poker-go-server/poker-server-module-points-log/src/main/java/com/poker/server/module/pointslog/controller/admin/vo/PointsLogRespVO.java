package com.poker.server.module.pointslog.controller.admin.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 积分流水 Response VO")
@Data
public class PointsLogRespVO {

    @Schema(description = "流水编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long id;

    @Schema(description = "门店编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long clubId;

    @Schema(description = "用户编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long userId;

    @Schema(description = "积分类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "consume")
    private String type;

    @Schema(description = "变动数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "100")
    private Integer amount;

    @Schema(description = "变动前余额", example = "500")
    private Integer balanceBefore;

    @Schema(description = "变动后余额", example = "600")
    private Integer balanceAfter;

    @Schema(description = "来源类型", example = "consume")
    private String sourceType;

    @Schema(description = "来源编号", example = "1")
    private Long sourceId;

    @Schema(description = "备注", example = "消费赠送积分")
    private String remark;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;
}
