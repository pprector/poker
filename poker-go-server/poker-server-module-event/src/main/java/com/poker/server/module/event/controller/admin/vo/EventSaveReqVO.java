package com.poker.server.module.event.controller.admin.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 赛事活动创建/修改 Request VO")
@Data
public class EventSaveReqVO {

    @Schema(description = "活动编号", example = "1")
    private Long id;

    @Schema(description = "所属门店编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "所属门店不能为空")
    private Long clubId;

    @Schema(description = "活动标题", requiredMode = Schema.RequiredMode.REQUIRED, example = "德州扑克周赛")
    @NotNull(message = "活动标题不能为空")
    private String title;

    @Schema(description = "活动类型", example = "TOURNAMENT")
    private String type;

    @Schema(description = "活动描述", example = "每周一次的德州扑克锦标赛")
    private String description;

    @Schema(description = "开始时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "开始时间不能为空")
    private LocalDateTime startTime;

    @Schema(description = "结束时间")
    private LocalDateTime endTime;

    @Schema(description = "报名截止时间")
    private LocalDateTime registrationDeadline;

    @Schema(description = "最大参赛人数", example = "100")
    private Integer maxPlayers;

    @Schema(description = "最小参赛人数", example = "2")
    private Integer minPlayers;

    @Schema(description = "报名费用", example = "100.00")
    private BigDecimal entryFee;

    @Schema(description = "报名积分", example = "100")
    private Integer entryPoints;

    @Schema(description = "奖池金额", example = "10000.00")
    private BigDecimal prizePool;

    @Schema(description = "奖金分配方案", example = "第一名50%,第二名30%,第三名20%")
    private String prizeDistribution;

    @Schema(description = "盲注结构", example = "25/50, 50/100, 100/200")
    private String blindStructure;

    @Schema(description = "级别时长(分钟)", example = "20")
    private Integer levelDuration;

    @Schema(description = "封面图片URL", example = "https://example.com/cover.jpg")
    private String coverImage;

    @Schema(description = "活动地点", example = "朝阳区三里屯路88号")
    private String location;
}
