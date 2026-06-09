package com.poker.server.module.recharge.dal.mysql;

import com.poker.server.framework.common.pojo.PageResult;
import com.poker.server.framework.mybatis.core.mapper.BaseMapperX;
import com.poker.server.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.poker.server.module.recharge.controller.admin.vo.RechargeRecordPageReqVO;
import com.poker.server.module.recharge.dal.dataobject.RechargeRecordDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RechargeRecordMapper extends BaseMapperX<RechargeRecordDO> {

    default PageResult<RechargeRecordDO> selectPage(RechargeRecordPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<RechargeRecordDO>()
                .eqIfPresent(RechargeRecordDO::getClubId, reqVO.getClubId())
                .eqIfPresent(RechargeRecordDO::getUserId, reqVO.getUserId())
                .eqIfPresent(RechargeRecordDO::getStatus, reqVO.getStatus())
                .eqIfPresent(RechargeRecordDO::getPaymentMethod, reqVO.getPaymentMethod())
                .orderByDesc(RechargeRecordDO::getId));
    }
}
