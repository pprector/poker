package com.poker.server.module.event.service;

import com.poker.server.framework.common.exception.util.ServiceExceptionUtil;
import com.poker.server.framework.common.pojo.PageResult;
import com.poker.server.framework.common.util.object.BeanUtils;
import com.poker.server.module.event.controller.admin.vo.EventFinalizeReqVO;
import com.poker.server.module.event.controller.admin.vo.EventPageReqVO;
import com.poker.server.module.event.controller.admin.vo.EventSaveReqVO;
import com.poker.server.module.event.controller.admin.vo.EventStatusUpdateReqVO;
import com.poker.server.module.event.dal.dataobject.EventDO;
import com.poker.server.module.event.dal.dataobject.RegistrationDO;
import com.poker.server.module.event.dal.dataobject.ResultDO;
import com.poker.server.module.event.dal.mysql.EventMapper;
import com.poker.server.module.event.dal.mysql.RegistrationMapper;
import com.poker.server.module.event.dal.mysql.ResultMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.List;

import static com.poker.server.module.event.enums.ErrorCodeConstants.*;

@Slf4j
@Service
@Validated
public class EventServiceImpl implements EventService {

    @Resource
    private EventMapper eventMapper;

    @Resource
    private RegistrationMapper registrationMapper;

    @Resource
    private ResultMapper resultMapper;

    @Override
    public Long createEvent(EventSaveReqVO createReqVO) {
        EventDO event = BeanUtils.toBean(createReqVO, EventDO.class);
        event.setCurrentPlayers(0);
        event.setStatus("DRAFT");
        eventMapper.insert(event);
        return event.getId();
    }

    @Override
    public void updateEvent(EventSaveReqVO updateReqVO) {
        EventDO event = eventMapper.selectById(updateReqVO.getId());
        if (event == null) {
            throw ServiceExceptionUtil.exception(EVENT_NOT_EXISTS);
        }
        EventDO updateObj = BeanUtils.toBean(updateReqVO, EventDO.class);
        eventMapper.updateById(updateObj);
    }

    @Override
    public void updateEventStatus(EventStatusUpdateReqVO updateReqVO) {
        EventDO event = eventMapper.selectById(updateReqVO.getId());
        if (event == null) {
            throw ServiceExceptionUtil.exception(EVENT_NOT_EXISTS);
        }
        String currentStatus = event.getStatus();
        String targetStatus = updateReqVO.getStatus();

        if ("DRAFT".equals(currentStatus) && "OPEN".equals(targetStatus)) {
            event.setStatus("OPEN");
        } else if ("OPEN".equals(currentStatus) && "ONGOING".equals(targetStatus)) {
            event.setStatus("ONGOING");
        } else if ("ONGOING".equals(currentStatus) && "FINISHED".equals(targetStatus)) {
            event.setStatus("FINISHED");
        } else if ("DRAFT".equals(currentStatus) && "CANCELLED".equals(targetStatus)) {
            event.setStatus("CANCELLED");
        } else if ("OPEN".equals(currentStatus) && "CANCELLED".equals(targetStatus)) {
            event.setStatus("CANCELLED");
        } else {
            throw ServiceExceptionUtil.exception(EVENT_ALREADY_FINISHED);
        }
        eventMapper.updateById(event);
    }

    @Override
    public void deleteEvent(Long id) {
        EventDO event = eventMapper.selectById(id);
        if (event == null) {
            throw ServiceExceptionUtil.exception(EVENT_NOT_EXISTS);
        }
        if (!"DRAFT".equals(event.getStatus()) && !"CANCELLED".equals(event.getStatus())) {
            throw ServiceExceptionUtil.exception(EVENT_CANNOT_DELETE);
        }
        eventMapper.deleteById(id);
    }

    @Override
    public EventDO getEvent(Long id) {
        return eventMapper.selectById(id);
    }

    @Override
    public PageResult<EventDO> getEventPage(EventPageReqVO pageReqVO) {
        return eventMapper.selectPage(pageReqVO);
    }

    @Override
    public Long registerPlayer(Long eventId, Long userId) {
        EventDO event = eventMapper.selectById(eventId);
        if (event == null) {
            throw ServiceExceptionUtil.exception(EVENT_NOT_EXISTS);
        }
        if (!"OPEN".equals(event.getStatus())) {
            throw ServiceExceptionUtil.exception(EVENT_REGISTRATION_CLOSED);
        }
        if (event.getMaxPlayers() != null && event.getCurrentPlayers() >= event.getMaxPlayers()) {
            throw ServiceExceptionUtil.exception(EVENT_FULL);
        }
        RegistrationDO existing = registrationMapper.selectByEventIdAndUserId(eventId, userId);
        if (existing != null) {
            throw ServiceExceptionUtil.exception(REGISTRATION_ALREADY_EXISTS);
        }

        RegistrationDO registration = RegistrationDO.builder()
                .eventId(eventId)
                .userId(userId)
                .registrationTime(LocalDateTime.now())
                .status("REGISTERED")
                .build();
        registrationMapper.insert(registration);

        event.setCurrentPlayers(event.getCurrentPlayers() + 1);
        eventMapper.updateById(event);

        return registration.getId();
    }

    @Override
    public void cancelRegistration(Long eventId, Long userId) {
        RegistrationDO registration = registrationMapper.selectByEventIdAndUserId(eventId, userId);
        if (registration == null) {
            throw ServiceExceptionUtil.exception(REGISTRATION_NOT_EXISTS);
        }
        if (!"REGISTERED".equals(registration.getStatus())) {
            throw ServiceExceptionUtil.exception(REGISTRATION_NOT_EXISTS);
        }

        registration.setStatus("CANCELLED");
        registrationMapper.updateById(registration);

        EventDO event = eventMapper.selectById(eventId);
        if (event != null && event.getCurrentPlayers() > 0) {
            event.setCurrentPlayers(event.getCurrentPlayers() - 1);
            eventMapper.updateById(event);
        }
    }

    @Override
    public List<RegistrationDO> getRegistrations(Long eventId) {
        return registrationMapper.selectByEventId(eventId);
    }

    @Override
    public List<ResultDO> finalizeResults(EventFinalizeReqVO finalizeReqVO) {
        EventDO event = eventMapper.selectById(finalizeReqVO.getEventId());
        if (event == null) {
            throw ServiceExceptionUtil.exception(EVENT_NOT_EXISTS);
        }
        if (!"ONGOING".equals(event.getStatus()) && !"FINISHED".equals(event.getStatus())) {
            throw ServiceExceptionUtil.exception(EVENT_ALREADY_FINISHED);
        }

        List<EventFinalizeReqVO.ResultItem> items = finalizeReqVO.getResults();
        for (EventFinalizeReqVO.ResultItem item : items) {
            ResultDO existing = resultMapper.selectByEventIdAndUserId(finalizeReqVO.getEventId(), item.getUserId());
            if (existing != null) {
                throw ServiceExceptionUtil.exception(RESULT_ALREADY_EXISTS);
            }
        }

        for (EventFinalizeReqVO.ResultItem item : items) {
            ResultDO result = ResultDO.builder()
                    .eventId(finalizeReqVO.getEventId())
                    .userId(item.getUserId())
                    .rank(item.getRank())
                    .prizeAmount(item.getPrizeAmount())
                    .pointsEarned(item.getPointsEarned())
                    .build();
            resultMapper.insert(result);
        }

        if (!"FINISHED".equals(event.getStatus())) {
            event.setStatus("FINISHED");
            eventMapper.updateById(event);
        }

        return resultMapper.selectByEventId(finalizeReqVO.getEventId());
    }
}
