package com.poker.server.module.event.enums;

import com.poker.server.framework.common.exception.ErrorCode;

public interface ErrorCodeConstants {

    ErrorCode EVENT_NOT_EXISTS = new ErrorCode(1_010_002_001, "赛事活动不存在");
    ErrorCode EVENT_ALREADY_STARTED = new ErrorCode(1_010_002_002, "赛事活动已开始，无法操作");
    ErrorCode EVENT_ALREADY_FINISHED = new ErrorCode(1_010_002_003, "赛事活动已结束，无法操作");
    ErrorCode EVENT_FULL = new ErrorCode(1_010_002_004, "赛事活动报名人数已满");
    ErrorCode EVENT_REGISTRATION_CLOSED = new ErrorCode(1_010_002_005, "赛事活动报名已截止");
    ErrorCode EVENT_CANNOT_DELETE = new ErrorCode(1_010_002_006, "赛事活动已开始或已结束，无法删除");
    ErrorCode REGISTRATION_NOT_EXISTS = new ErrorCode(1_010_002_007, "报名记录不存在");
    ErrorCode REGISTRATION_ALREADY_EXISTS = new ErrorCode(1_010_002_008, "已报名该赛事活动，请勿重复报名");
    ErrorCode RESULT_ALREADY_EXISTS = new ErrorCode(1_010_002_009, "该用户排名结果已存在");
}
