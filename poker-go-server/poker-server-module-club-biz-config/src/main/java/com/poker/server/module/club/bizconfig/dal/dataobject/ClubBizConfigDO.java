package com.poker.server.module.club.bizconfig.dal.dataobject;

import com.poker.server.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

@TableName("club_business_config")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClubBizConfigDO extends BaseDO {

    @TableId
    private Long id;
    private Long clubId;
    private String templateName;
    private Integer consumePointsEnabled;
    private Integer consumePointsRatio;
    private String consumePointsThreshold;
    private Integer consumePointsFixed;
    private Integer eventPointsEnabled;
    private String eventPointsTemplate;
    private String eventPointsCustom;
    private Integer firstVisitBonus;
    private Integer dailyCheckinPoints;
    private Integer rechargeEnabled;
    private String rechargeMode;
    private Integer rechargeDiscountRate;
    private String rechargeBonusRules;
    private String pointsUsage;
    private Integer exchangeRate;
    private Integer deductRate;
    private Integer entryPointsCost;
}
