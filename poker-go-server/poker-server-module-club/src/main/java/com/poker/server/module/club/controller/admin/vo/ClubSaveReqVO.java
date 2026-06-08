package com.poker.server.module.club.controller.admin.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "管理后台 - 门店创建/修改 Request VO")
@Data
public class ClubSaveReqVO {

    @Schema(description = "门店编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long id;

    @Schema(description = "门店名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "老友精酿扑克吧")
    @NotNull(message = "门店名称不能为空")
    private String name;

    @Schema(description = "门店地址", requiredMode = Schema.RequiredMode.REQUIRED, example = "朝阳区三里屯路88号")
    @NotNull(message = "门店地址不能为空")
    private String address;

    @Schema(description = "联系电话", requiredMode = Schema.RequiredMode.REQUIRED, example = "13812345678")
    @NotNull(message = "联系电话不能为空")
    private String phone;

    @Schema(description = "营业时间", example = "周一至周日 18:00-02:00")
    private String openHours;

    @Schema(description = "门店介绍", example = "这里不只是打牌，更是一群人的社交客厅")
    private String introText;
}
