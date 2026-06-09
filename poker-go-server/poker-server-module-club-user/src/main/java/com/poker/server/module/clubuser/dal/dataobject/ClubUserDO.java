package com.poker.server.module.clubuser.dal.dataobject;

import com.poker.server.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@TableName("club_user")
@KeySequence("club_user_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClubUserDO extends BaseDO {

    @TableId
    private Long id;
    private Long clubId;
    private Long userId;
    private String openid;
    private String unionid;
    private String phone;
    private String nickname;
    private String avatar;
    private String role;
    private Integer totalPoints;
    private Integer currentPoints;
    private BigDecimal totalRecharge;
    private Integer status;
    private LocalDateTime lastVisitTime;
    private Integer visitCount;
}
