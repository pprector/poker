package com.poker.server.module.clubuser.controller.admin.vo;

import com.poker.server.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Schema(description = "管理后台 - 俱乐部用户分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class ClubUserPageReqVO extends PageParam {

    @Schema(description = "俱乐部编号", example = "1")
    private Long clubId;

    @Schema(description = "角色", example = "PLAYER")
    private String role;

    @Schema(description = "状态", example = "1")
    private Integer status;

    @Schema(description = "昵称", example = "张三")
    private String nickname;
}
