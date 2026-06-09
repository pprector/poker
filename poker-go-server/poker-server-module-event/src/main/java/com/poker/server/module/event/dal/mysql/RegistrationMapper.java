package com.poker.server.module.event.dal.mysql;

import com.poker.server.framework.mybatis.core.mapper.BaseMapperX;
import com.poker.server.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.poker.server.module.event.dal.dataobject.RegistrationDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RegistrationMapper extends BaseMapperX<RegistrationDO> {

    default List<RegistrationDO> selectByEventId(Long eventId) {
        return selectList(new LambdaQueryWrapperX<RegistrationDO>()
                .eq(RegistrationDO::getEventId, eventId));
    }

    default RegistrationDO selectByEventIdAndUserId(Long eventId, Long userId) {
        return selectOne(new LambdaQueryWrapperX<RegistrationDO>()
                .eq(RegistrationDO::getEventId, eventId)
                .eq(RegistrationDO::getUserId, userId));
    }

    default Long countByEventId(Long eventId) {
        return selectCount(new LambdaQueryWrapperX<RegistrationDO>()
                .eq(RegistrationDO::getEventId, eventId));
    }
}
