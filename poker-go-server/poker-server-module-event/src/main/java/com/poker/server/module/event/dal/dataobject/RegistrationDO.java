package com.poker.server.module.event.dal.dataobject;

import com.poker.server.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.time.LocalDateTime;

@TableName("registration")
@KeySequence("registration_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationDO extends BaseDO {

    @TableId
    private Long id;
    private Long eventId;
    private Long userId;
    private Integer seatNumber;
    private LocalDateTime registrationTime;
    private String status;
}
