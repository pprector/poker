package com.poker.server.module.event.controller.admin;

import com.poker.server.framework.common.pojo.CommonResult;
import com.poker.server.framework.common.pojo.PageResult;
import com.poker.server.framework.common.util.object.BeanUtils;
import com.poker.server.module.event.controller.admin.vo.*;
import com.poker.server.module.event.dal.dataobject.EventDO;
import com.poker.server.module.event.dal.dataobject.RegistrationDO;
import com.poker.server.module.event.dal.dataobject.ResultDO;
import com.poker.server.module.event.service.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "管理后台 - 赛事活动")
@RestController
@RequestMapping("/event")
public class EventController {

    @Resource
    private EventService eventService;

    @PostMapping("/create")
    @Operation(summary = "创建赛事活动")
    @PreAuthorize("@ss.hasPermission('event:create')")
    public CommonResult<Long> createEvent(@Valid @RequestBody EventSaveReqVO createReqVO) {
        return CommonResult.success(eventService.createEvent(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新赛事活动")
    @PreAuthorize("@ss.hasPermission('event:update')")
    public CommonResult<Boolean> updateEvent(@Valid @RequestBody EventSaveReqVO updateReqVO) {
        eventService.updateEvent(updateReqVO);
        return CommonResult.success(true);
    }

    @PutMapping("/update-status")
    @Operation(summary = "更新赛事活动状态")
    @PreAuthorize("@ss.hasPermission('event:update')")
    public CommonResult<Boolean> updateEventStatus(@Valid @RequestBody EventStatusUpdateReqVO updateReqVO) {
        eventService.updateEventStatus(updateReqVO);
        return CommonResult.success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除赛事活动")
    @PreAuthorize("@ss.hasPermission('event:delete')")
    public CommonResult<Boolean> deleteEvent(@RequestParam("id") Long id) {
        eventService.deleteEvent(id);
        return CommonResult.success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得赛事活动")
    @PreAuthorize("@ss.hasPermission('event:query')")
    public CommonResult<EventRespVO> getEvent(@RequestParam("id") Long id) {
        EventDO event = eventService.getEvent(id);
        return CommonResult.success(BeanUtils.toBean(event, EventRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得赛事活动分页")
    @PreAuthorize("@ss.hasPermission('event:query')")
    public CommonResult<PageResult<EventRespVO>> getEventPage(@Valid EventPageReqVO pageReqVO) {
        PageResult<EventDO> pageResult = eventService.getEventPage(pageReqVO);
        return CommonResult.success(BeanUtils.toBean(pageResult, EventRespVO.class));
    }

    @PostMapping("/register")
    @Operation(summary = "报名参赛")
    @PreAuthorize("@ss.hasPermission('event:update')")
    public CommonResult<Long> registerPlayer(@RequestParam("eventId") Long eventId,
                                              @RequestParam("userId") Long userId) {
        return CommonResult.success(eventService.registerPlayer(eventId, userId));
    }

    @PostMapping("/cancel-registration")
    @Operation(summary = "取消报名")
    @PreAuthorize("@ss.hasPermission('event:update')")
    public CommonResult<Boolean> cancelRegistration(@RequestParam("eventId") Long eventId,
                                                     @RequestParam("userId") Long userId) {
        eventService.cancelRegistration(eventId, userId);
        return CommonResult.success(true);
    }

    @GetMapping("/{id}/registrations")
    @Operation(summary = "获得赛事活动报名列表")
    @PreAuthorize("@ss.hasPermission('event:query')")
    public CommonResult<List<RegistrationRespVO>> getRegistrations(@PathVariable("id") Long id) {
        List<RegistrationDO> list = eventService.getRegistrations(id);
        return CommonResult.success(BeanUtils.toBean(list, RegistrationRespVO.class));
    }

    @PostMapping("/finalize")
    @Operation(summary = "录入比赛结果")
    @PreAuthorize("@ss.hasPermission('event:update')")
    public CommonResult<List<ResultRespVO>> finalizeResults(@Valid @RequestBody EventFinalizeReqVO finalizeReqVO) {
        List<ResultDO> list = eventService.finalizeResults(finalizeReqVO);
        return CommonResult.success(BeanUtils.toBean(list, ResultRespVO.class));
    }
}
