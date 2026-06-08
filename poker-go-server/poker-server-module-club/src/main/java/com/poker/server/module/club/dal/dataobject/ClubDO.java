package com.poker.server.module.club.dal.dataobject;

import com.poker.server.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

@TableName("club")
@KeySequence("club_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClubDO extends BaseDO {

    @TableId
    private Long id;
    private String name;
    private String logoUrl;
    private String address;
    private String phone;
    private String openHours;
    private String photos;
    private String introText;
    private String themeColor;
    private String themeConfig;
    private String themeTemplate;
    private String moduleFlags;
    private Long ownerId;
    private Integer status;
}
