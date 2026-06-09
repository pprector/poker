package com.poker.server.module.event.service;

import com.poker.server.framework.common.pojo.PageResult;
import com.poker.server.module.event.controller.admin.vo.EventFinalizeReqVO;
import com.poker.server.module.event.controller.admin.vo.EventPageReqVO;
import com.poker.server.module.event.controller.admin.vo.EventSaveReqVO;
import com.poker.server.module.event.controller.admin.vo.EventStatusUpdateReqVO;
import com.poker.server.module.event.dal.dataobject.EventDO;
import com.poker.server.module.event.dal.dataobject.RegistrationDO;
import com.poker.server.module.event.dal.dataobject.ResultDO;

import java.util.List;

public interface EventService {

    Long createEvent(EventSaveReqVO createReqVO);

    void updateEvent(EventSaveReqVO updateReqVO);

    void updateEventStatus(EventStatusUpdateReqVO updateReqVO);

    void deleteEvent(Long id);

    EventDO getEvent(Long id);

    PageResult<EventDO> getEventPage(EventPageReqVO pageReqVO);

    Long registerPlayer(Long eventId, Long userId);

    void cancelRegistration(Long eventId, Long userId);

    List<RegistrationDO> getRegistrations(Long eventId);

    List<ResultDO> finalizeResults(EventFinalizeReqVO finalizeReqVO);
}
