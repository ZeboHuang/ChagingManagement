package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 计费管理对象 charge_manage
 * 
 * @author ruoyi
 * @date 2023-01-16
 */
public class ChargeManage extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 系统ID */
    private Long sysId;

    /** 电桩编号 */
    @Excel(name = "电桩编号")
    private String pileId;

    /** 站点名称 */
    @Excel(name = "站点名称")
    private String staname;

    /** 电桩位置 */
    @Excel(name = "电桩位置")
    private String loc;

    /** 每时单价 */
    @Excel(name = "每时单价")
    private Double unitprice;

    /** 计费时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "计费时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date duration;

    public void setSysId(Long sysId) 
    {
        this.sysId = sysId;
    }

    public Long getSysId() 
    {
        return sysId;
    }
    public void setPileId(String pileId) 
    {
        this.pileId = pileId;
    }

    public String getPileId() 
    {
        return pileId;
    }
    public void setStaname(String staname) 
    {
        this.staname = staname;
    }

    public String getStaname() 
    {
        return staname;
    }
    public void setLoc(String loc) 
    {
        this.loc = loc;
    }

    public String getLoc() 
    {
        return loc;
    }
    public void setUnitprice(Double unitprice) 
    {
        this.unitprice = unitprice;
    }

    public Double getUnitprice() 
    {
        return unitprice;
    }
    public void setDuration(Date duration) 
    {
        this.duration = duration;
    }

    public Date getDuration() 
    {
        return duration;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("sysId", getSysId())
            .append("pileId", getPileId())
            .append("staname", getStaname())
            .append("loc", getLoc())
            .append("unitprice", getUnitprice())
            .append("duration", getDuration())
            .append("remark", getRemark())
            .toString();
    }
}
