package com.poker.server.module.clubuser.enums;

import com.poker.server.framework.common.exception.ErrorCode;

public interface ErrorCodeConstants {

    ErrorCode CLUB_USER_NOT_EXISTS = new ErrorCode(1_010_003_001, "俱乐部用户不存在");
    ErrorCode CLUB_USER_ALREADY_EXISTS = new ErrorCode(1_010_003_002, "该用户已在该俱乐部中");
}
