package com.poker.server.module.club.bizconfig.service.calc.impl;

import com.poker.server.module.club.bizconfig.dal.dataobject.ClubBizConfigDO;
import com.poker.server.module.club.bizconfig.service.calc.PointsStrategy;
import org.springframework.stereotype.Component;

@Component
public class SocialLightStrategy implements PointsStrategy {

    @Override
    public String getTemplateName() {
        return "social_light";
    }

    @Override
    public int calculateEventPoints(ClubBizConfigDO config, int rank, int playerCount) {
        if (config.getEventPointsEnabled() != 1) {
            return 0;
        }
        if (rank <= 0 || rank > playerCount) {
            return 0;
        }
        int base = playerCount - rank + 1;
        return Math.max(base, 1);
    }

    @Override
    public int calculateConsumePoints(ClubBizConfigDO config, int amountInCents) {
        if (config.getConsumePointsEnabled() != 1) {
            return 0;
        }
        int threshold = 0;
        if (config.getConsumePointsThreshold() != null) {
            threshold = (int) (Double.parseDouble(config.getConsumePointsThreshold()) * 100);
        }
        if (amountInCents < threshold) {
            return 0;
        }
        if (config.getConsumePointsFixed() != null && config.getConsumePointsFixed() > 0) {
            return config.getConsumePointsFixed();
        }
        int ratio = config.getConsumePointsRatio() != null ? config.getConsumePointsRatio() : 100;
        return (amountInCents / 100) * ratio;
    }
}
