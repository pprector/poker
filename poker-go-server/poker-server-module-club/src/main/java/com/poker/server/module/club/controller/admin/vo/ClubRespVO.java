package com.poker.server.module.club.controller.admin.vo;

import com.poker.server.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 门店 Response VO")
@Data
public class ClubRespVO {

    @Schema(description = "门店编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long id;

    @Schema(description = "门店名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "老友精酿扑克吧")
    private String name;

    @Schema(description = "门店地址", requiredMode = Schema.RequiredMode.REQUIRED, example = "朝阳区三里屯路88号")
    private String address;

    @Schema(description = "联系电话", example = "13812345678")
    private String phone;

    @Schema(description = "营业时间", example = "周一至周日 18:00-02:00")
    private String openHours;

    @Schema(description = "门店介绍", example = "这里不只是打牌")
    private String introText;

    @Schema(description = "状态", example = "1")
    private Integer status;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;
}
