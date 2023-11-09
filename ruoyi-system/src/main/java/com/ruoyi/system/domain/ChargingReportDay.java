package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 日报表对象 charging_report_day
 * 
 * @author ruoyi
 * @date 2023-01-12
 */
public class ChargingReportDay extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 日报表ID */
    private Long id;

    /** 站点名称 */
    @Excel(name = "站点名称")
    private String stationName;

    /** 交易日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "交易日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date transDate;

    /** 累计充电量(度) */
    @Excel(name = "累计充电量(度)")
    private Double chargingCapacity;

    /** 累计电费(分) */
    @Excel(name = "累计电费(分)")
    private Integer electoryPayment;

    /** 累计服务费(分) */
    @Excel(name = "累计服务费(分)")
    private Integer servePayment;

    /** 累计应付款(分) */
    @Excel(name = "累计应付款(分)")
    private Integer duePayment;

    /** 实际应付款(分) */
    @Excel(name = "实际应付款(分)")
    private Integer actualPayment;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setStationName(String stationName) 
    {
        this.stationName = stationName;
    }

    public String getStationName() 
    {
        return stationName;
    }
    public void setTransDate(Date transDate) 
    {
        this.transDate = transDate;
    }

    public Date getTransDate() 
    {
        return transDate;
    }
    public void setChargingCapacity(Double chargingCapacity) 
    {
        this.chargingCapacity = chargingCapacity;
    }

    public Double getChargingCapacity() 
    {
        return chargingCapacity;
    }
    public void setElectoryPayment(Integer electoryPayment) 
    {
        this.electoryPayment = electoryPayment;
    }

    public Integer getElectoryPayment() 
    {
        return electoryPayment;
    }
    public void setServePayment(Integer servePayment) 
    {
        this.servePayment = servePayment;
    }

    public Integer getServePayment() 
    {
        return servePayment;
    }
    public void setDuePayment(Integer duePayment) 
    {
        this.duePayment = duePayment;
    }

    public Integer getDuePayment() 
    {
        return duePayment;
    }
    public void setActualPayment(Integer actualPayment) 
    {
        this.actualPayment = actualPayment;
    }

    public Integer getActualPayment() 
    {
        return actualPayment;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("stationName", getStationName())
            .append("transDate", getTransDate())
            .append("chargingCapacity", getChargingCapacity())
            .append("electoryPayment", getElectoryPayment())
            .append("servePayment", getServePayment())
            .append("duePayment", getDuePayment())
            .append("actualPayment", getActualPayment())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
