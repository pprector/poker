package com.poker.server.module.event.controller.admin.vo;

import com.poker.server.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Schema(description = "管理后台 - 赛事活动分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class EventPageReqVO extends PageParam {

    @Schema(description = "所属门店编号", example = "1")
    private Long clubId;

    @Schema(description = "活动状态", example = "DRAFT")
    private String status;

    @Schema(description = "活动类型", example = "TOURNAMENT")
    private String type;

    @Schema(description = "活动标题", example = "周赛")
    private String title;
}
