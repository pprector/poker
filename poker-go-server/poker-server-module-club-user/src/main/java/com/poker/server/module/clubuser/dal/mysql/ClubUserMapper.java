package com.poker.server.module.clubuser.dal.mysql;

import com.poker.server.framework.common.pojo.PageResult;
import com.poker.server.framework.mybatis.core.mapper.BaseMapperX;
import com.poker.server.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.poker.server.module.clubuser.controller.admin.vo.ClubUserPageReqVO;
import com.poker.server.module.clubuser.dal.dataobject.ClubUserDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClubUserMapper extends BaseMapperX<ClubUserDO> {

    default List<ClubUserDO> selectByClubId(Long clubId) {
        return selectList(ClubUserDO::getClubId, clubId);
    }

    default ClubUserDO selectByUserId(Long userId) {
        return selectOne(ClubUserDO::getUserId, userId);
    }

    default ClubUserDO selectByOpenid(String openid) {
        return selectOne(ClubUserDO::getOpenid, openid);
    }

    default PageResult<ClubUserDO> selectPage(ClubUserPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ClubUserDO>()
                .eqIfPresent(ClubUserDO::getClubId, reqVO.getClubId())
                .eqIfPresent(ClubUserDO::getRole, reqVO.getRole())
                .eqIfPresent(ClubUserDO::getStatus, reqVO.getStatus())
                .likeIfPresent(ClubUserDO::getNickname, reqVO.getNickname())
                .orderByDesc(ClubUserDO::getId));
    }
}
