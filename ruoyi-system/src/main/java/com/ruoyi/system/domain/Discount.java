package com.ruoyi.system.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 优惠券对象 discount
 * 
 * @author ruoyi
 * @date 2023-01-15
 */
public class Discount extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 优惠券id */
    private Integer discountId;

    /** 优惠券名称 */
    @Excel(name = "优惠券名称")
    private String discountName;

    /** 优惠券类型 */
    @Excel(name = "优惠券类型")
    private Integer discountType;

    /** 优惠券额度 */
    @Excel(name = "优惠券额度")
    private BigDecimal discountQuota;

    /** 优惠券满减额度 */
    @Excel(name = "优惠券满减额度")
    private BigDecimal discountBeginQuota;

    /** 优惠券有效开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "优惠券有效开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date discountEfficientStartTime;

    /** 优惠券有效结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "优惠券有效结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date discountEfficientEndTime;

    /** 有效天数 */
    @Excel(name = "有效天数")
    private Integer discountDays;

    /** 优惠券数量 */
    @Excel(name = "优惠券数量")
    private Integer discountNumber;

    /** 优惠券状态 */
    @Excel(name = "优惠券状态")
    private Integer discountStatus;

    public void setDiscountId(Integer discountId) 
    {
        this.discountId = discountId;
    }

    public Integer getDiscountId() 
    {
        return discountId;
    }
    public void setDiscountName(String discountName) 
    {
        this.discountName = discountName;
    }

    public String getDiscountName() 
    {
        return discountName;
    }
    public void setDiscountType(Integer discountType) 
    {
        this.discountType = discountType;
    }

    public Integer getDiscountType() 
    {
        return discountType;
    }
    public void setDiscountQuota(BigDecimal discountQuota) 
    {
        this.discountQuota = discountQuota;
    }

    public BigDecimal getDiscountQuota() 
    {
        return discountQuota;
    }
    public void setDiscountBeginQuota(BigDecimal discountBeginQuota) 
    {
        this.discountBeginQuota = discountBeginQuota;
    }

    public BigDecimal getDiscountBeginQuota() 
    {
        return discountBeginQuota;
    }
    public void setDiscountEfficientStartTime(Date discountEfficientStartTime) 
    {
        this.discountEfficientStartTime = discountEfficientStartTime;
    }

    public Date getDiscountEfficientStartTime() 
    {
        return discountEfficientStartTime;
    }
    public void setDiscountEfficientEndTime(Date discountEfficientEndTime) 
    {
        this.discountEfficientEndTime = discountEfficientEndTime;
    }

    public Date getDiscountEfficientEndTime() 
    {
        return discountEfficientEndTime;
    }
    public void setDiscountDays(Integer discountDays) 
    {
        this.discountDays = discountDays;
    }

    public Integer getDiscountDays() 
    {
        return discountDays;
    }
    public void setDiscountNumber(Integer discountNumber) 
    {
        this.discountNumber = discountNumber;
    }

    public Integer getDiscountNumber() 
    {
        return discountNumber;
    }
    public void setDiscountStatus(Integer discountStatus) 
    {
        this.discountStatus = discountStatus;
    }

    public Integer getDiscountStatus() 
    {
        return discountStatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("discountId", getDiscountId())
            .append("discountName", getDiscountName())
            .append("discountType", getDiscountType())
            .append("discountQuota", getDiscountQuota())
            .append("discountBeginQuota", getDiscountBeginQuota())
            .append("discountEfficientStartTime", getDiscountEfficientStartTime())
            .append("discountEfficientEndTime", getDiscountEfficientEndTime())
            .append("discountDays", getDiscountDays())
            .append("discountNumber", getDiscountNumber())
            .append("discountStatus", getDiscountStatus())
            .toString();
    }
}
