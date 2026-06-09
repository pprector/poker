package com.poker.server.module.pointslog.service;

import com.poker.server.framework.common.pojo.PageResult;
import com.poker.server.framework.common.util.object.BeanUtils;
import com.poker.server.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.poker.server.module.pointslog.controller.admin.vo.PointsLogPageReqVO;
import com.poker.server.module.pointslog.controller.admin.vo.PointsLogSaveReqVO;
import com.poker.server.module.pointslog.dal.dataobject.PointsLogDO;
import com.poker.server.module.pointslog.dal.mysql.PointsLogMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@Validated
public class PointsLogServiceImpl implements PointsLogService {

    @Resource
    private PointsLogMapper pointsLogMapper;

    @Override
    public Long createPointsLog(PointsLogSaveReqVO createReqVO) {
        PointsLogDO latest = pointsLogMapper.selectOne(new LambdaQueryWrapperX<PointsLogDO>()
                .eq(PointsLogDO::getUserId, createReqVO.getUserId())
                .eq(PointsLogDO::getClubId, createReqVO.getClubId())
                .orderByDesc(PointsLogDO::getId));
        Integer balanceBefore = (latest != null) ? latest.getBalanceAfter() : 0;
        PointsLogDO pointsLog = BeanUtils.toBean(createReqVO, PointsLogDO.class);
        pointsLog.setBalanceBefore(balanceBefore);
        pointsLog.setBalanceAfter(balanceBefore + createReqVO.getAmount());
        pointsLogMapper.insert(pointsLog);
        return pointsLog.getId();
    }

    @Override
    public PageResult<PointsLogDO> getPointsLogPage(PointsLogPageReqVO pageReqVO) {
        return pointsLogMapper.selectPage(pageReqVO);
    }

    @Override
    public Map<String, Object> getUserPointsSummary(Long userId, Long clubId) {
        PointsLogDO latest = pointsLogMapper.selectOne(new LambdaQueryWrapperX<PointsLogDO>()
                .eq(PointsLogDO::getUserId, userId)
                .eq(PointsLogDO::getClubId, clubId)
                .orderByDesc(PointsLogDO::getId));
        Integer currentBalance = (latest != null) ? latest.getBalanceAfter() : 0;
        Map<String, Object> summary = new HashMap<>();
        summary.put("userId", userId);
        summary.put("clubId", clubId);
        summary.put("currentBalance", currentBalance);
        return summary;
    }
}
