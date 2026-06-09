package com.poker.server.module.club.bizconfig.service.calc;

import com.poker.server.module.club.bizconfig.dal.dataobject.ClubBizConfigDO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class PointsStrategyManager {

    private final Map<String, PointsStrategy> strategyMap;

    public PointsStrategyManager(List<PointsStrategy> strategies) {
        this.strategyMap = strategies.stream()
                .collect(Collectors.toMap(PointsStrategy::getTemplateName, s -> s));
    }

    public PointsStrategy getStrategy(ClubBizConfigDO config) {
        PointsStrategy strategy = strategyMap.get(config.getTemplateName());
        if (strategy == null) {
            strategy = strategyMap.get("social_light");
        }
        return strategy;
    }

    public int calculateEventPoints(ClubBizConfigDO config, int rank, int playerCount) {
        return getStrategy(config).calculateEventPoints(config, rank, playerCount);
    }

    public int calculateConsumePoints(ClubBizConfigDO config, int amountInCents) {
        return getStrategy(config).calculateConsumePoints(config, amountInCents);
    }
}
