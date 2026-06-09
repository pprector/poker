package com.poker.server.module.club.bizconfig.enums;

import com.poker.server.framework.common.exception.ErrorCode;

public interface ErrorCodeConstants {

    ErrorCode BIZ_CONFIG_NOT_EXISTS = new ErrorCode(1_010_002_001, "经营配置不存在");
    ErrorCode BIZ_CONFIG_ALREADY_EXISTS = new ErrorCode(1_010_002_002, "该门店经营配置已存在");
}
