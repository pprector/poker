package com.poker.server.module.clubuser.service;

import com.poker.server.framework.common.pojo.PageResult;
import com.poker.server.module.clubuser.controller.admin.vo.ClubUserPageReqVO;
import com.poker.server.module.clubuser.controller.admin.vo.ClubUserSaveReqVO;
import com.poker.server.module.clubuser.dal.dataobject.ClubUserDO;

import java.util.List;

public interface ClubUserService {

    Long createClubUser(ClubUserSaveReqVO createReqVO);

    void updateClubUser(ClubUserSaveReqVO updateReqVO);

    void deleteClubUser(Long id);

    ClubUserDO getClubUser(Long id);

    PageResult<ClubUserDO> getClubUserPage(ClubUserPageReqVO pageReqVO);

    List<ClubUserDO> getClubUsersByClubId(Long clubId);

    ClubUserDO getClubUserByUserId(Long userId);
}
