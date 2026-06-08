package com.poker.server.module.club.controller.admin.vo;

import com.poker.server.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Schema(description = "管理后台 - 门店分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class ClubPageReqVO extends PageParam {

    @Schema(description = "门店名称", example = "老友")
    private String name;

    @Schema(description = "状态", example = "1")
    private Integer status;
}
