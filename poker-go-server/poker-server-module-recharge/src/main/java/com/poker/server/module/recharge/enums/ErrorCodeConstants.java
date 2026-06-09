package com.poker.server.module.recharge.enums;

import com.poker.server.framework.common.exception.ErrorCode;

public interface ErrorCodeConstants {

    ErrorCode RECHARGE_NOT_EXISTS = new ErrorCode(1_010_002_001, "储值记录不存在");
    ErrorCode RECHARGE_ALREADY_CONFIRMED = new ErrorCode(1_010_002_002, "储值记录已确认，无法重复操作");
    ErrorCode RECHARGE_CANNOT_CONFIRM = new ErrorCode(1_010_002_003, "储值记录状态异常，无法确认");
}
