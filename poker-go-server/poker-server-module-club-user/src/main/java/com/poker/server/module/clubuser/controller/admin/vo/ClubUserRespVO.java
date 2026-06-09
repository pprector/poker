package com.poker.server.module.clubuser.controller.admin.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 俱乐部用户 Response VO")
@Data
public class ClubUserRespVO {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long id;

    @Schema(description = "俱乐部编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
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

    @Schema(description = "总积分", example = "1000")
    private Integer totalPoints;

    @Schema(description = "当前积分", example = "500")
    private Integer currentPoints;

    @Schema(description = "累计充值金额", example = "1000.00")
    private BigDecimal totalRecharge;

    @Schema(description = "状态", example = "1")
    private Integer status;

    @Schema(description = "最后访问时间")
    private LocalDateTime lastVisitTime;

    @Schema(description = "访问次数", example = "10")
    private Integer visitCount;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;
}
