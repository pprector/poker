package com.poker.server.module.recharge.service;

import com.poker.server.framework.common.pojo.PageResult;
import com.poker.server.module.recharge.controller.admin.vo.RechargeRecordPageReqVO;
import com.poker.server.module.recharge.controller.admin.vo.RechargeRecordSaveReqVO;
import com.poker.server.module.recharge.dal.dataobject.RechargeRecordDO;

import java.math.BigDecimal;

public interface RechargeRecordService {

    Long createRechargeRecord(RechargeRecordSaveReqVO createReqVO);

    void confirmRecharge(Long id);

    PageResult<RechargeRecordDO> getRechargeRecordPage(RechargeRecordPageReqVO pageReqVO);

    BigDecimal getUserRechargeSummary(Long userId, Long clubId);
}
