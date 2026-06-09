package com.poker.server.module.recharge.service;

import com.poker.server.framework.common.exception.util.ServiceExceptionUtil;
import com.poker.server.framework.common.pojo.PageResult;
import com.poker.server.framework.common.util.object.BeanUtils;
import com.poker.server.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.poker.server.module.recharge.controller.admin.vo.RechargeRecordPageReqVO;
import com.poker.server.module.recharge.controller.admin.vo.RechargeRecordSaveReqVO;
import com.poker.server.module.recharge.dal.dataobject.RechargeRecordDO;
import com.poker.server.module.recharge.dal.mysql.RechargeRecordMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import static com.poker.server.module.recharge.enums.ErrorCodeConstants.RECHARGE_ALREADY_CONFIRMED;
import static com.poker.server.module.recharge.enums.ErrorCodeConstants.RECHARGE_CANNOT_CONFIRM;
import static com.poker.server.module.recharge.enums.ErrorCodeConstants.RECHARGE_NOT_EXISTS;

@Slf4j
@Service
@Validated
public class RechargeRecordServiceImpl implements RechargeRecordService {

    @Resource
    private RechargeRecordMapper rechargeRecordMapper;

    @Override
    public Long createRechargeRecord(RechargeRecordSaveReqVO createReqVO) {
        RechargeRecordDO record = BeanUtils.toBean(createReqVO, RechargeRecordDO.class);
        if (record.getBonusAmount() == null) {
            record.setBonusAmount(BigDecimal.ZERO);
        }
        record.setActualAmount(record.getAmount().add(record.getBonusAmount()));
        record.setStatus("PENDING");
        rechargeRecordMapper.insert(record);
        return record.getId();
    }

    @Override
    public void confirmRecharge(Long id) {
        RechargeRecordDO record = rechargeRecordMapper.selectById(id);
        if (record == null) {
            throw ServiceExceptionUtil.exception(RECHARGE_NOT_EXISTS);
        }
        if (!Objects.equals(record.getStatus(), "PENDING")) {
            if (Objects.equals(record.getStatus(), "CONFIRMED")) {
                throw ServiceExceptionUtil.exception(RECHARGE_ALREADY_CONFIRMED);
            }
            throw ServiceExceptionUtil.exception(RECHARGE_CANNOT_CONFIRM);
        }
        RechargeRecordDO updateObj = new RechargeRecordDO();
        updateObj.setId(id);
        updateObj.setStatus("CONFIRMED");
        rechargeRecordMapper.updateById(updateObj);
    }

    @Override
    public PageResult<RechargeRecordDO> getRechargeRecordPage(RechargeRecordPageReqVO pageReqVO) {
        return rechargeRecordMapper.selectPage(pageReqVO);
    }

    @Override
    public BigDecimal getUserRechargeSummary(Long userId, Long clubId) {
        List<RechargeRecordDO> records = rechargeRecordMapper.selectList(new LambdaQueryWrapperX<RechargeRecordDO>()
                .eq(RechargeRecordDO::getUserId, userId)
                .eq(RechargeRecordDO::getClubId, clubId)
                .eq(RechargeRecordDO::getStatus, "CONFIRMED"));
        return records.stream()
                .map(RechargeRecordDO::getActualAmount)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
