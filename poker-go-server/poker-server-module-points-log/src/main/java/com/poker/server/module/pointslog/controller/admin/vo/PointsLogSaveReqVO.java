package com.poker.server.module.pointslog.controller.admin.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "管理后台 - 积分流水创建 Request VO")
@Data
public class PointsLogSaveReqVO {

    @Schema(description = "门店编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "门店编号不能为空")
    private Long clubId;

    @Schema(description = "用户编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "用户编号不能为空")
    private Long userId;

    @Schema(description = "积分类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "consume")
    @NotNull(message = "积分类型不能为空")
    private String type;

    @Schema(description = "变动数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "100")
    @NotNull(message = "变动数量不能为空")
    private Integer amount;

    @Schema(description = "来源类型", example = "consume")
    private String sourceType;

    @Schema(description = "来源编号", example = "1")
    private Long sourceId;

    @Schema(description = "备注", example = "消费赠送积分")
    private String remark;
}
