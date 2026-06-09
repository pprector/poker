package com.poker.server.module.club.bizconfig.controller.admin.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 经营配置 Response VO")
@Data
public class ClubBizConfigRespVO {

    @Schema(description = "配置编号", example = "1")
    private Long id;

    @Schema(description = "门店编号", example = "1")
    private Long clubId;

    @Schema(description = "经营模式模板标识", example = "social_light")
    private String templateName;

    @Schema(description = "消费送积分开关", example = "1")
    private Integer consumePointsEnabled;

    @Schema(description = "消费1元=X积分", example = "100")
    private Integer consumePointsRatio;

    @Schema(description = "消费满X元才送", example = "0")
    private String consumePointsThreshold;

    @Schema(description = "满额固定赠送积分", example = "0")
    private Integer consumePointsFixed;

    @Schema(description = "赛事排名送积分开关", example = "1")
    private Integer eventPointsEnabled;

    @Schema(description = "赛事积分模板", example = "rank_based")
    private String eventPointsTemplate;

    @Schema(description = "赛事积分自定义规则")
    private String eventPointsCustom;

    @Schema(description = "首次到店赠送积分", example = "0")
    private Integer firstVisitBonus;

    @Schema(description = "每日签到积分", example = "0")
    private Integer dailyCheckinPoints;

    @Schema(description = "储值功能开关", example = "0")
    private Integer rechargeEnabled;

    @Schema(description = "充值模式", example = "fixed_rate")
    private String rechargeMode;

    @Schema(description = "充值折扣率", example = "80")
    private Integer rechargeDiscountRate;

    @Schema(description = "充值赠送规则")
    private String rechargeBonusRules;

    @Schema(description = "积分用途", example = "all")
    private String pointsUsage;

    @Schema(description = "兑换汇率", example = "100")
    private Integer exchangeRate;

    @Schema(description = "抵扣比例", example = "50")
    private Integer deductRate;

    @Schema(description = "兑换入场券所需积分", example = "0")
    private Integer entryPointsCost;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;
}
