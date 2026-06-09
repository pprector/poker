package com.poker.server.module.club.bizconfig.service.calc;

import com.poker.server.module.club.bizconfig.dal.dataobject.ClubBizConfigDO;

public interface PointsStrategy {

    String getTemplateName();

    int calculateEventPoints(ClubBizConfigDO config, int rank, int playerCount);

    int calculateConsumePoints(ClubBizConfigDO config, int amountInCents);
}
