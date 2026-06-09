package com.poker.server.module.event.dal.mysql;

import com.poker.server.framework.common.pojo.PageResult;
import com.poker.server.framework.mybatis.core.mapper.BaseMapperX;
import com.poker.server.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.poker.server.module.event.controller.admin.vo.EventPageReqVO;
import com.poker.server.module.event.dal.dataobject.EventDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EventMapper extends BaseMapperX<EventDO> {

    default PageResult<EventDO> selectPage(EventPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EventDO>()
                .eqIfPresent(EventDO::getClubId, reqVO.getClubId())
                .eqIfPresent(EventDO::getStatus, reqVO.getStatus())
                .eqIfPresent(EventDO::getType, reqVO.getType())
                .likeIfPresent(EventDO::getTitle, reqVO.getTitle())
                .orderByDesc(EventDO::getId));
    }
}
