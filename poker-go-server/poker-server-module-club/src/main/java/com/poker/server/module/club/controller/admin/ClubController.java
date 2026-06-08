package com.poker.server.module.club.controller.admin;

import com.poker.server.framework.common.pojo.CommonResult;
import com.poker.server.framework.common.pojo.PageResult;
import com.poker.server.framework.common.util.object.BeanUtils;
import com.poker.server.module.club.controller.admin.vo.ClubPageReqVO;
import com.poker.server.module.club.controller.admin.vo.ClubRespVO;
import com.poker.server.module.club.controller.admin.vo.ClubSaveReqVO;
import com.poker.server.module.club.dal.dataobject.ClubDO;
import com.poker.server.module.club.service.ClubService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Tag(name = "管理后台 - 门店")
@RestController
@RequestMapping("/club")
public class ClubController {

    @Resource
    private ClubService clubService;

    @PostMapping("/create")
    @Operation(summary = "创建门店")
    @PreAuthorize("@ss.hasPermission('club:create')")
    public CommonResult<Long> createClub(@Valid @RequestBody ClubSaveReqVO createReqVO) {
        return CommonResult.success(clubService.createClub(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新门店")
    @PreAuthorize("@ss.hasPermission('club:update')")
    public CommonResult<Boolean> updateClub(@Valid @RequestBody ClubSaveReqVO updateReqVO) {
        clubService.updateClub(updateReqVO);
        return CommonResult.success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除门店")
    @PreAuthorize("@ss.hasPermission('club:delete')")
    public CommonResult<Boolean> deleteClub(@RequestParam("id") Long id) {
        clubService.deleteClub(id);
        return CommonResult.success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得门店")
    @PreAuthorize("@ss.hasPermission('club:query')")
    public CommonResult<ClubRespVO> getClub(@RequestParam("id") Long id) {
        ClubDO club = clubService.getClub(id);
        return CommonResult.success(BeanUtils.toBean(club, ClubRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得门店分页")
    @PreAuthorize("@ss.hasPermission('club:query')")
    public CommonResult<PageResult<ClubRespVO>> getClubPage(@Valid ClubPageReqVO pageReqVO) {
        PageResult<ClubDO> pageResult = clubService.getClubPage(pageReqVO);
        return CommonResult.success(BeanUtils.toBean(pageResult, ClubRespVO.class));
    }
}
