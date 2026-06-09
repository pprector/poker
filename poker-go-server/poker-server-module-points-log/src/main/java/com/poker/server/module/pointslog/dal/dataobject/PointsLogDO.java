package com.poker.server.module.pointslog.dal.dataobject;

import com.poker.server.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

@TableName("points_log")
@KeySequence("points_log_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PointsLogDO extends BaseDO {

    @TableId
    private Long id;
    private Long clubId;
    private Long userId;
    private String type;
    private Integer amount;
    private Integer balanceBefore;
    private Integer balanceAfter;
    private String sourceType;
    private Long sourceId;
    private String remark;
}
