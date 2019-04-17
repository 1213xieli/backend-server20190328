package com.zb.byb.entity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ApiModel("设备领用申请")
public class EquipmentApply {
    @ApiModelProperty("记录id")
    private String rcordId;
    @ApiModelProperty("number")
    private String number;
    @ApiModelProperty("状态")
    private int billStatusIndex;
    @ApiModelProperty("状态")
    private String billStatus;
    @ApiModelProperty("日期")
    private Date bizDate;
    @ApiModelProperty("serviceId")
    private String serviceId;
    @ApiModelProperty("serviceName")
    private String serviceName;
    @ApiModelProperty("养户id")
    private String custId;
    @ApiModelProperty("equipAmt")
    private String equipAmt;
    @ApiModelProperty("养户姓名")
    private String custName;
    @ApiModelProperty("申请日期")
    private Date applyDate;
    @ApiModelProperty("累计已领取设备金额")
    private Double totalPayment;
    @ApiModelProperty("设备申请明细")
    private List<Equipment> applyList;
    @ApiModelProperty("设备实际领用明细")
    private List<Equipment>  actualList;
    @ApiModelProperty("设备领用明细")
    private List<Equipment> entrys;

    @ApiModelProperty("是否委托他人领取")
    private Boolean isEntrust;
    @ApiModelProperty("被委托领取人姓名")
    private String entrustorName;
    @ApiModelProperty("被委托领取人身份证号")
    private String entrustorId;
    private String description;

    public int pageNumber = 1;
    public int pageSize = 1000;
}
