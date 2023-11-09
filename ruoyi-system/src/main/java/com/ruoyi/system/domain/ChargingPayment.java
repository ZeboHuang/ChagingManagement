package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 支付订单对象 charging_payment
 * 
 * @author ruoyi
 * @date 2023-01-12
 */
public class ChargingPayment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 系统编码 */
    private Long sysId;

    /** 用户编码 */
    @Excel(name = "用户编码")
    private String userId;

    /** 支付订单号 */
    @Excel(name = "支付订单号")
    private String paymentId;

    /** 订单类型 */
    @Excel(name = "订单类型")
    private Integer orderType;

    /** 支付模式 */
    @Excel(name = "支付模式")
    private Integer paymentMode;

    /** 订单状态 */
    @Excel(name = "订单状态")
    private Integer orderStatus;

    /** 支付方式 */
    @Excel(name = "支付方式")
    private String paymentMethod;

    /** 支付方式 */
    @Excel(name = "支付方式")
    private Integer paymentMethodTag;

    /** 支付状态 */
    @Excel(name = "支付状态")
    private Integer paymentStatus;

    /** 第三方支付编码 */
    @Excel(name = "第三方支付编码")
    private String thirdPaymentId;

    public void setSysId(Long sysId) 
    {
        this.sysId = sysId;
    }

    public Long getSysId() 
    {
        return sysId;
    }
    public void setUserId(String userId) 
    {
        this.userId = userId;
    }

    public String getUserId() 
    {
        return userId;
    }
    public void setPaymentId(String paymentId) 
    {
        this.paymentId = paymentId;
    }

    public String getPaymentId() 
    {
        return paymentId;
    }
    public void setOrderType(Integer orderType) 
    {
        this.orderType = orderType;
    }

    public Integer getOrderType() 
    {
        return orderType;
    }
    public void setPaymentMode(Integer paymentMode) 
    {
        this.paymentMode = paymentMode;
    }

    public Integer getPaymentMode() 
    {
        return paymentMode;
    }
    public void setOrderStatus(Integer orderStatus) 
    {
        this.orderStatus = orderStatus;
    }

    public Integer getOrderStatus() 
    {
        return orderStatus;
    }
    public void setPaymentMethod(String paymentMethod) 
    {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentMethod() 
    {
        return paymentMethod;
    }
    public void setPaymentMethodTag(Integer paymentMethodTag) 
    {
        this.paymentMethodTag = paymentMethodTag;
    }

    public Integer getPaymentMethodTag() 
    {
        return paymentMethodTag;
    }
    public void setPaymentStatus(Integer paymentStatus) 
    {
        this.paymentStatus = paymentStatus;
    }

    public Integer getPaymentStatus() 
    {
        return paymentStatus;
    }
    public void setThirdPaymentId(String thirdPaymentId) 
    {
        this.thirdPaymentId = thirdPaymentId;
    }

    public String getThirdPaymentId() 
    {
        return thirdPaymentId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("sysId", getSysId())
            .append("userId", getUserId())
            .append("paymentId", getPaymentId())
            .append("orderType", getOrderType())
            .append("paymentMode", getPaymentMode())
            .append("orderStatus", getOrderStatus())
            .append("paymentMethod", getPaymentMethod())
            .append("paymentMethodTag", getPaymentMethodTag())
            .append("paymentStatus", getPaymentStatus())
            .append("thirdPaymentId", getThirdPaymentId())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
