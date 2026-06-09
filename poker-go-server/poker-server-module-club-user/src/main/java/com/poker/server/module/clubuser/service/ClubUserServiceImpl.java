package com.poker.server.module.clubuser.service;

import com.poker.server.framework.common.exception.util.ServiceExceptionUtil;
import com.poker.server.framework.common.pojo.PageResult;
import com.poker.server.framework.common.util.object.BeanUtils;
import com.poker.server.module.clubuser.controller.admin.vo.ClubUserPageReqVO;
import com.poker.server.module.clubuser.controller.admin.vo.ClubUserSaveReqVO;
import com.poker.server.module.clubuser.dal.dataobject.ClubUserDO;
import com.poker.server.module.clubuser.dal.mysql.ClubUserMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static com.poker.server.module.clubuser.enums.ErrorCodeConstants.CLUB_USER_ALREADY_EXISTS;
import static com.poker.server.module.clubuser.enums.ErrorCodeConstants.CLUB_USER_NOT_EXISTS;

@Slf4j
@Service
@Validated
public class ClubUserServiceImpl implements ClubUserService {

    @Resource
    private ClubUserMapper clubUserMapper;

    @Override
    public Long createClubUser(ClubUserSaveReqVO createReqVO) {
        ClubUserDO clubUser = BeanUtils.toBean(createReqVO, ClubUserDO.class);
        clubUserMapper.insert(clubUser);
        return clubUser.getId();
    }

    @Override
    public void updateClubUser(ClubUserSaveReqVO updateReqVO) {
        ClubUserDO clubUser = clubUserMapper.selectById(updateReqVO.getId());
        if (clubUser == null) {
            throw ServiceExceptionUtil.exception(CLUB_USER_NOT_EXISTS);
        }
        ClubUserDO updateObj = BeanUtils.toBean(updateReqVO, ClubUserDO.class);
        clubUserMapper.updateById(updateObj);
    }

    @Override
    public void deleteClubUser(Long id) {
        ClubUserDO clubUser = clubUserMapper.selectById(id);
        if (clubUser == null) {
            throw ServiceExceptionUtil.exception(CLUB_USER_NOT_EXISTS);
        }
        clubUserMapper.deleteById(id);
    }

    @Override
    public ClubUserDO getClubUser(Long id) {
        return clubUserMapper.selectById(id);
    }

    @Override
    public PageResult<ClubUserDO> getClubUserPage(ClubUserPageReqVO pageReqVO) {
        return clubUserMapper.selectPage(pageReqVO);
    }

    @Override
    public List<ClubUserDO> getClubUsersByClubId(Long clubId) {
        return clubUserMapper.selectByClubId(clubId);
    }

    @Override
    public ClubUserDO getClubUserByUserId(Long userId) {
        return clubUserMapper.selectByUserId(userId);
    }
}
