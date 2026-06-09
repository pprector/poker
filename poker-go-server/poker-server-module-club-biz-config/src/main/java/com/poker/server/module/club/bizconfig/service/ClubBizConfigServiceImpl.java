package com.poker.server.module.club.bizconfig.service;

import com.poker.server.framework.common.pojo.PageResult;
import com.poker.server.framework.common.util.object.BeanUtils;
import com.poker.server.framework.common.exception.util.ServiceExceptionUtil;
import com.poker.server.module.club.bizconfig.controller.admin.vo.ClubBizConfigPageReqVO;
import com.poker.server.module.club.bizconfig.controller.admin.vo.ClubBizConfigSaveReqVO;
import com.poker.server.module.club.bizconfig.dal.dataobject.ClubBizConfigDO;
import com.poker.server.module.club.bizconfig.dal.mysql.ClubBizConfigMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import static com.poker.server.module.club.bizconfig.enums.ErrorCodeConstants.BIZ_CONFIG_ALREADY_EXISTS;
import static com.poker.server.module.club.bizconfig.enums.ErrorCodeConstants.BIZ_CONFIG_NOT_EXISTS;

@Slf4j
@Service
@Validated
public class ClubBizConfigServiceImpl implements ClubBizConfigService {

    @Resource
    private ClubBizConfigMapper clubBizConfigMapper;

    @Override
    public Long createConfig(ClubBizConfigSaveReqVO createReqVO) {
        ClubBizConfigDO existing = clubBizConfigMapper.selectByClubId(createReqVO.getClubId());
        if (existing != null) {
            throw ServiceExceptionUtil.exception(BIZ_CONFIG_ALREADY_EXISTS);
        }
        ClubBizConfigDO config = BeanUtils.toBean(createReqVO, ClubBizConfigDO.class);
        if (config.getTemplateName() == null) {
            config.setTemplateName("social_light");
        }
        clubBizConfigMapper.insert(config);
        return config.getId();
    }

    @Override
    public void updateConfig(ClubBizConfigSaveReqVO updateReqVO) {
        ClubBizConfigDO config = clubBizConfigMapper.selectById(updateReqVO.getId());
        if (config == null) {
            throw ServiceExceptionUtil.exception(BIZ_CONFIG_NOT_EXISTS);
        }
        ClubBizConfigDO updateObj = BeanUtils.toBean(updateReqVO, ClubBizConfigDO.class);
        clubBizConfigMapper.updateById(updateObj);
    }

    @Override
    public void deleteConfig(Long id) {
        ClubBizConfigDO config = clubBizConfigMapper.selectById(id);
        if (config == null) {
            throw ServiceExceptionUtil.exception(BIZ_CONFIG_NOT_EXISTS);
        }
        clubBizConfigMapper.deleteById(id);
    }

    @Override
    public ClubBizConfigDO getConfigByClubId(Long clubId) {
        return clubBizConfigMapper.selectByClubId(clubId);
    }

    @Override
    public ClubBizConfigDO getConfig(Long id) {
        return clubBizConfigMapper.selectById(id);
    }

    @Override
    public PageResult<ClubBizConfigDO> getConfigPage(ClubBizConfigPageReqVO pageReqVO) {
        return clubBizConfigMapper.selectPage(pageReqVO, new LambdaQueryWrapper<ClubBizConfigDO>()
                .eq(pageReqVO.getClubId() != null, ClubBizConfigDO::getClubId, pageReqVO.getClubId())
                .eq(pageReqVO.getTemplateName() != null, ClubBizConfigDO::getTemplateName, pageReqVO.getTemplateName())
                .orderByDesc(ClubBizConfigDO::getId));
    }
}
