package com.poker.server.module.club.service;

import com.poker.server.framework.common.exception.util.ServiceExceptionUtil;
import com.poker.server.framework.common.pojo.PageResult;
import com.poker.server.framework.common.util.object.BeanUtils;
import com.poker.server.module.club.controller.admin.vo.ClubSaveReqVO;
import com.poker.server.module.club.dal.dataobject.ClubDO;
import com.poker.server.module.club.dal.mysql.ClubMapper;
import com.poker.server.module.club.controller.admin.vo.ClubPageReqVO;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import static com.poker.server.module.club.enums.ErrorCodeConstants.CLUB_NOT_EXISTS;

@Slf4j
@Service
@Validated
public class ClubServiceImpl implements ClubService {

    @Resource
    private ClubMapper clubMapper;

    @Override
    public Long createClub(ClubSaveReqVO createReqVO) {
        ClubDO club = BeanUtils.toBean(createReqVO, ClubDO.class);
        clubMapper.insert(club);
        return club.getId();
    }

    @Override
    public void updateClub(ClubSaveReqVO updateReqVO) {
        ClubDO club = clubMapper.selectById(updateReqVO.getId());
        if (club == null) {
            throw ServiceExceptionUtil.exception(CLUB_NOT_EXISTS);
        }
        ClubDO updateObj = BeanUtils.toBean(updateReqVO, ClubDO.class);
        clubMapper.updateById(updateObj);
    }

    @Override
    public void deleteClub(Long id) {
        ClubDO club = clubMapper.selectById(id);
        if (club == null) {
            throw ServiceExceptionUtil.exception(CLUB_NOT_EXISTS);
        }
        clubMapper.deleteById(id);
    }

    @Override
    public ClubDO getClub(Long id) {
        return clubMapper.selectById(id);
    }

    @Override
    public PageResult<ClubDO> getClubPage(ClubPageReqVO pageReqVO) {
        return clubMapper.selectPage(pageReqVO);
    }
}
