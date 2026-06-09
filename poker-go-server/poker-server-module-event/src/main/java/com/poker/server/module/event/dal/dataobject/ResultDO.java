package com.poker.server.module.event.dal.dataobject;

import com.poker.server.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;

@TableName("result")
@KeySequence("result_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResultDO extends BaseDO {

    @TableId
    private Long id;
    private Long eventId;
    private Long userId;
    private Integer rank;
    private BigDecimal prizeAmount;
    private Integer pointsEarned;
}
