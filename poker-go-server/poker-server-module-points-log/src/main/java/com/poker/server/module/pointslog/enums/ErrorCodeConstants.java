package com.poker.server.module.pointslog.enums;

import com.poker.server.framework.common.exception.ErrorCode;

public interface ErrorCodeConstants {

    ErrorCode POINTS_LOG_NOT_EXISTS = new ErrorCode(1_010_003_001, "积分流水不存在");
}
