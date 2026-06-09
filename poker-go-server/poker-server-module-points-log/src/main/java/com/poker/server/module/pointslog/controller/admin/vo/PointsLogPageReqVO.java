package com.poker.server.module.pointslog.controller.admin.vo;

import com.poker.server.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Schema(description = "管理后台 - 积分流水分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class PointsLogPageReqVO extends PageParam {

    @Schema(description = "门店编号", example = "1")
    private Long clubId;

    @Schema(description = "用户编号", example = "1")
    private Long userId;

    @Schema(description = "积分类型", example = "consume")
    private String type;
}
