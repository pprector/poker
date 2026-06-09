package com.poker.server.module.clubuser.controller.admin;

import com.poker.server.framework.common.pojo.CommonResult;
import com.poker.server.framework.common.pojo.PageResult;
import com.poker.server.framework.common.util.object.BeanUtils;
import com.poker.server.module.clubuser.controller.admin.vo.ClubUserPageReqVO;
import com.poker.server.module.clubuser.controller.admin.vo.ClubUserRespVO;
import com.poker.server.module.clubuser.controller.admin.vo.ClubUserSaveReqVO;
import com.poker.server.module.clubuser.dal.dataobject.ClubUserDO;
import com.poker.server.module.clubuser.service.ClubUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "管理后台 - 俱乐部用户")
@RestController
@RequestMapping("/club-user")
public class ClubUserController {

    @Resource
    private ClubUserService clubUserService;

    @PostMapping("/create")
    @Operation(summary = "创建俱乐部用户")
    @PreAuthorize("@ss.hasPermission('club-user:create')")
    public CommonResult<Long> createClubUser(@Valid @RequestBody ClubUserSaveReqVO createReqVO) {
        return CommonResult.success(clubUserService.createClubUser(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新俱乐部用户")
    @PreAuthorize("@ss.hasPermission('club-user:update')")
    public CommonResult<Boolean> updateClubUser(@Valid @RequestBody ClubUserSaveReqVO updateReqVO) {
        clubUserService.updateClubUser(updateReqVO);
        return CommonResult.success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除俱乐部用户")
    @PreAuthorize("@ss.hasPermission('club-user:delete')")
    public CommonResult<Boolean> deleteClubUser(@RequestParam("id") Long id) {
        clubUserService.deleteClubUser(id);
        return CommonResult.success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得俱乐部用户")
    @PreAuthorize("@ss.hasPermission('club-user:query')")
    public CommonResult<ClubUserRespVO> getClubUser(@RequestParam("id") Long id) {
        ClubUserDO clubUser = clubUserService.getClubUser(id);
        return CommonResult.success(BeanUtils.toBean(clubUser, ClubUserRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得俱乐部用户分页")
    @PreAuthorize("@ss.hasPermission('club-user:query')")
    public CommonResult<PageResult<ClubUserRespVO>> getClubUserPage(@Valid ClubUserPageReqVO pageReqVO) {
        PageResult<ClubUserDO> pageResult = clubUserService.getClubUserPage(pageReqVO);
        return CommonResult.success(BeanUtils.toBean(pageResult, ClubUserRespVO.class));
    }

    @GetMapping("/get-by-club/{clubId}")
    @Operation(summary = "根据俱乐部编号获得俱乐部用户列表")
    @PreAuthorize("@ss.hasPermission('club-user:query')")
    public CommonResult<List<ClubUserRespVO>> getClubUsersByClubId(@PathVariable("clubId") Long clubId) {
        List<ClubUserDO> list = clubUserService.getClubUsersByClubId(clubId);
        return CommonResult.success(BeanUtils.toBeanList(list, ClubUserRespVO.class));
    }

    @GetMapping("/get-by-user/{userId}")
    @Operation(summary = "根据用户编号获得俱乐部用户")
    @PreAuthorize("@ss.hasPermission('club-user:query')")
    public CommonResult<ClubUserRespVO> getClubUserByUserId(@PathVariable("userId") Long userId) {
        ClubUserDO clubUser = clubUserService.getClubUserByUserId(userId);
        return CommonResult.success(BeanUtils.toBean(clubUser, ClubUserRespVO.class));
    }
}
