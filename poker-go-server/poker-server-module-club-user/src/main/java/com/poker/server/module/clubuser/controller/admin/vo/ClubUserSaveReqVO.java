package com.poker.server.module.clubuser.controller.admin.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "管理后台 - 俱乐部用户创建/修改 Request VO")
@Data
public class ClubUserSaveReqVO {

    @Schema(description = "编号", example = "1")
    private Long id;

    @Schema(description = "俱乐部编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "俱乐部编号不能为空")
    private Long clubId;

    @Schema(description = "用户编号", example = "1")
    private Long userId;

    @Schema(description = "微信openid", example = "oXXXXX")
    private String openid;

    @Schema(description = "微信unionid", example = "uXXXXX")
    private String unionid;

    @Schema(description = "手机号", example = "13812345678")
    private String phone;

    @Schema(description = "昵称", example = "张三")
    private String nickname;

    @Schema(description = "头像", example = "https://example.com/avatar.png")
    private String avatar;

    @Schema(description = "角色", example = "PLAYER")
    private String role;

    @Schema(description = "状态", example = "1")
    private Integer status;
}
