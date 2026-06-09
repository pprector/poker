package com.poker.server.module.club.enums;

import com.poker.server.framework.common.exception.ErrorCode;

public interface ErrorCodeConstants {

    ErrorCode CLUB_NOT_EXISTS = new ErrorCode(1_010_001_001, "门店不存在");
    ErrorCode CLUB_NOT_OWNER = new ErrorCode(1_010_001_002, "您不是该门店的经营者");
}
