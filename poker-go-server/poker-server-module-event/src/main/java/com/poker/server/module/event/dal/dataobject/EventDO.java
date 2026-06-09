package com.poker.server.module.event.dal.dataobject;

import com.poker.server.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@TableName("event")
@KeySequence("event_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventDO extends BaseDO {

    @TableId
    private Long id;
    private Long clubId;
    private String title;
    private String type;
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalDateTime registrationDeadline;
    private Integer maxPlayers;
    private Integer minPlayers;
    private Integer currentPlayers;
    private BigDecimal entryFee;
    private Integer entryPoints;
    private BigDecimal prizePool;
    private String prizeDistribution;
    private String blindStructure;
    private Integer levelDuration;
    private String status;
    private String coverImage;
    private String location;
}
