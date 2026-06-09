package com.poker.server.module.event.dal.mysql;

import com.poker.server.framework.mybatis.core.mapper.BaseMapperX;
import com.poker.server.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.poker.server.module.event.dal.dataobject.ResultDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ResultMapper extends BaseMapperX<ResultDO> {

    default List<ResultDO> selectByEventId(Long eventId) {
        return selectList(new LambdaQueryWrapperX<ResultDO>()
                .eq(ResultDO::getEventId, eventId));
    }

    default ResultDO selectByEventIdAndUserId(Long eventId, Long userId) {
        return selectOne(new LambdaQueryWrapperX<ResultDO>()
                .eq(ResultDO::getEventId, eventId)
                .eq(ResultDO::getUserId, userId));
    }
}
