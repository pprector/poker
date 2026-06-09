package com.poker.server.module.club.bizconfig.controller.admin;

import com.poker.server.framework.common.pojo.CommonResult;
import com.poker.server.framework.common.pojo.PageResult;
import com.poker.server.framework.common.util.object.BeanUtils;
import com.poker.server.module.club.bizconfig.controller.admin.vo.ClubBizConfigPageReqVO;
import com.poker.server.module.club.bizconfig.controller.admin.vo.ClubBizConfigRespVO;
import com.poker.server.module.club.bizconfig.controller.admin.vo.ClubBizConfigSaveReqVO;
import com.poker.server.module.club.bizconfig.dal.dataobject.ClubBizConfigDO;
import com.poker.server.module.club.bizconfig.service.ClubBizConfigService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Tag(name = "管理后台 - 经营配置")
@RestController
@RequestMapping("/club-biz-config")
public class ClubBizConfigController {

    @Resource
    private ClubBizConfigService clubBizConfigService;

    @PostMapping("/create")
    @Operation(summary = "创建经营配置")
    @PreAuthorize("@ss.hasPermission('club-biz-config:create')")
    public CommonResult<Long> createConfig(@Valid @RequestBody ClubBizConfigSaveReqVO createReqVO) {
        return CommonResult.success(clubBizConfigService.createConfig(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新经营配置")
    @PreAuthorize("@ss.hasPermission('club-biz-config:update')")
    public CommonResult<Boolean> updateConfig(@Valid @RequestBody ClubBizConfigSaveReqVO updateReqVO) {
        clubBizConfigService.updateConfig(updateReqVO);
        return CommonResult.success(true);
    }

    @GetMapping("/get-by-club")
    @Operation(summary = "根据门店编号获得经营配置")
    @PreAuthorize("@ss.hasPermission('club-biz-config:query')")
    public CommonResult<ClubBizConfigRespVO> getConfigByClub(@RequestParam("clubId") Long clubId) {
        ClubBizConfigDO config = clubBizConfigService.getConfigByClubId(clubId);
        return CommonResult.success(BeanUtils.toBean(config, ClubBizConfigRespVO.class));
    }

    @GetMapping("/get")
    @Operation(summary = "获得经营配置")
    @PreAuthorize("@ss.hasPermission('club-biz-config:query')")
    public CommonResult<ClubBizConfigRespVO> getConfig(@RequestParam("id") Long id) {
        ClubBizConfigDO config = clubBizConfigService.getConfig(id);
        return CommonResult.success(BeanUtils.toBean(config, ClubBizConfigRespVO.class));
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除经营配置")
    @PreAuthorize("@ss.hasPermission('club-biz-config:delete')")
    public CommonResult<Boolean> deleteConfig(@RequestParam("id") Long id) {
        clubBizConfigService.deleteConfig(id);
        return CommonResult.success(true);
    }

    @GetMapping("/page")
    @Operation(summary = "获得经营配置分页")
    @PreAuthorize("@ss.hasPermission('club-biz-config:query')")
    public CommonResult<PageResult<ClubBizConfigRespVO>> getConfigPage(@Valid ClubBizConfigPageReqVO pageReqVO) {
        PageResult<ClubBizConfigDO> pageResult = clubBizConfigService.getConfigPage(pageReqVO);
        return CommonResult.success(BeanUtils.toBean(pageResult, ClubBizConfigRespVO.class));
    }
}
