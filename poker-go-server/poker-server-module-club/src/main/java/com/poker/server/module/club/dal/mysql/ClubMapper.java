package com.poker.server.module.club.dal.mysql;

import com.poker.server.framework.common.pojo.PageResult;
import com.poker.server.framework.mybatis.core.mapper.BaseMapperX;
import com.poker.server.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.poker.server.module.club.controller.admin.vo.ClubPageReqVO;
import com.poker.server.module.club.dal.dataobject.ClubDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ClubMapper extends BaseMapperX<ClubDO> {

    default PageResult<ClubDO> selectPage(ClubPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ClubDO>()
                .likeIfPresent(ClubDO::getName, reqVO.getName())
                .eqIfPresent(ClubDO::getStatus, reqVO.getStatus())
                .orderByDesc(ClubDO::getId));
    }
}
