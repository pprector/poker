package com.poker.server.module.club.bizconfig.dal.mysql;

import com.poker.server.framework.mybatis.core.mapper.BaseMapperX;
import com.poker.server.module.club.bizconfig.dal.dataobject.ClubBizConfigDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ClubBizConfigMapper extends BaseMapperX<ClubBizConfigDO> {

    default ClubBizConfigDO selectByClubId(Long clubId) {
        return selectOne(ClubBizConfigDO::getClubId, clubId);
    }
}
