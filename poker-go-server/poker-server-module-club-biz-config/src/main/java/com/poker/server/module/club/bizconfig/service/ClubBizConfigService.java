package com.poker.server.module.club.bizconfig.service;

import com.poker.server.framework.common.pojo.PageResult;
import com.poker.server.module.club.bizconfig.controller.admin.vo.ClubBizConfigPageReqVO;
import com.poker.server.module.club.bizconfig.controller.admin.vo.ClubBizConfigSaveReqVO;
import com.poker.server.module.club.bizconfig.dal.dataobject.ClubBizConfigDO;

public interface ClubBizConfigService {

    Long createConfig(ClubBizConfigSaveReqVO createReqVO);

    void updateConfig(ClubBizConfigSaveReqVO updateReqVO);

    void deleteConfig(Long id);

    ClubBizConfigDO getConfigByClubId(Long clubId);

    ClubBizConfigDO getConfig(Long id);

    PageResult<ClubBizConfigDO> getConfigPage(ClubBizConfigPageReqVO pageReqVO);
}
