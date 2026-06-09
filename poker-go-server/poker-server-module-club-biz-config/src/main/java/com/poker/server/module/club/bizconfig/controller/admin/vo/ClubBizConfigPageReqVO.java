package com.poker.server.module.club.bizconfig.controller.admin.vo;

import com.poker.server.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Schema(description = "管理后台 - 经营配置分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class ClubBizConfigPageReqVO extends PageParam {

    @Schema(description = "门店编号", example = "1")
    private Long clubId;

    @Schema(description = "经营模式模板标识", example = "social_light")
    private String templateName;
}
