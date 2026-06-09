package com.poker.server.module.pointslog.dal.mysql;

import com.poker.server.framework.common.pojo.PageResult;
import com.poker.server.framework.mybatis.core.mapper.BaseMapperX;
import com.poker.server.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.poker.server.module.pointslog.controller.admin.vo.PointsLogPageReqVO;
import com.poker.server.module.pointslog.dal.dataobject.PointsLogDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PointsLogMapper extends BaseMapperX<PointsLogDO> {

    default PageResult<PointsLogDO> selectPage(PointsLogPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<PointsLogDO>()
                .eqIfPresent(PointsLogDO::getClubId, reqVO.getClubId())
                .eqIfPresent(PointsLogDO::getUserId, reqVO.getUserId())
                .eqIfPresent(PointsLogDO::getType, reqVO.getType())
                .orderByDesc(PointsLogDO::getId));
    }
}
