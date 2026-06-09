package com.poker.server.module.club.service;

import com.poker.server.framework.common.pojo.PageResult;
import com.poker.server.module.club.controller.admin.vo.ClubPageReqVO;
import com.poker.server.module.club.controller.admin.vo.ClubSaveReqVO;
import com.poker.server.module.club.dal.dataobject.ClubDO;

public interface ClubService {

    Long createClub(ClubSaveReqVO createReqVO);

    void updateClub(ClubSaveReqVO updateReqVO);

    void deleteClub(Long id);

    ClubDO getClub(Long id);

    PageResult<ClubDO> getClubPage(ClubPageReqVO pageReqVO);
}
