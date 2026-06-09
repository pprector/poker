package com.poker.server.module.pointslog.service;

import com.poker.server.framework.common.pojo.PageResult;
import com.poker.server.module.pointslog.controller.admin.vo.PointsLogPageReqVO;
import com.poker.server.module.pointslog.controller.admin.vo.PointsLogSaveReqVO;
import com.poker.server.module.pointslog.dal.dataobject.PointsLogDO;

import java.util.Map;

public interface PointsLogService {

    Long createPointsLog(PointsLogSaveReqVO createReqVO);

    PageResult<PointsLogDO> getPointsLogPage(PointsLogPageReqVO pageReqVO);

    Map<String, Object> getUserPointsSummary(Long userId, Long clubId);
}
