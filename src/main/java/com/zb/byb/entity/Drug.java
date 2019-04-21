package com.zb.byb.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Data
@ApiModel("药品信息")
public class Drug implements Serializable {

    @ApiModelProperty("主键id")
    private String id;
    @ApiModelProperty("物料id")
    private String materialId;
    @ApiModelProperty("物料编号")
    private String materialNo;
    @ApiModelProperty("物料名称")
    private String materialName;
    @ApiModelProperty("批次id")
    private String batchId;
    @ApiModelProperty("批次")
    private String batchNo;

    @ApiModelProperty("数量")
    private Integer userQty;
    @ApiModelProperty("单价")
    private Double price;
    @ApiModelProperty("金额")
    private Double payment;



}
