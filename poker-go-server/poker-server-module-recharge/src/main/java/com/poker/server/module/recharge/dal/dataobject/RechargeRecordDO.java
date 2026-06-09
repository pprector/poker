package com.poker.server.module.recharge.dal.dataobject;

import com.poker.server.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;

@TableName("recharge_record")
@KeySequence("recharge_record_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RechargeRecordDO extends BaseDO {

    @TableId
    private Long id;
    private Long clubId;
    private Long userId;
    private BigDecimal amount;
    private BigDecimal bonusAmount;
    private BigDecimal actualAmount;
    private Integer pointsAwarded;
    private String paymentMethod;
    private String status;
}
