package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 充电订单对象 charging_order
 * 
 * @author ruoyi
 * @date 2023-01-14
 */
public class ChargingOrder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 系统ID */
    private Long sysId;

    /** 充电订单号 */
    @Excel(name = "充电订单号")
    private String chargingId;

    /** 支付订单号 */
    @Excel(name = "支付订单号")
    private String paymentId;

    /** 电桩编码 */
    @Excel(name = "电桩编码")
    private String pileId;

    /** 电枪编码 */
    @Excel(name = "电枪编码")
    private String gunId;

    /** 充电卡编码 */
    @Excel(name = "充电卡编码")
    private String chargingCardId;

    /** 充电模式 */
    @Excel(name = "充电模式")
    private Integer chargingMode;

    /** 充电站名称 */
    @Excel(name = "充电站名称")
    private String stationName;

    /** 充电状态 */
    @Excel(name = "充电状态")
    private Integer chargingStatus;

    /** 充电开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "充电开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date timeStart;

    /** 充电结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "充电结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date timeEnd;

    /** 支付渠道 */
    @Excel(name = "支付渠道")
    private Integer paymentChannelTag;

    /** 支付渠道 */
    private String paymentChannel;

    /** 付款模式 */
    @Excel(name = "付款模式")
    private Integer paymentMode;

    /** 充电量(度) */
    @Excel(name = "充电量(度)")
    private Double chargingCapacity;

    /** 优惠券名称 */
    private String couponName;

    /** 优惠券类型 */
    private Integer couponType;

    /** 优惠额度 */
    private Integer couponQuota;

    /** 预付款(分) */
    private Integer advancePayment;

    /** 应付款(分) */
    private Integer duePayment;

    /** 折扣金额(分) */
    private Integer discountPayment;

    /** 实付款(分) */
    @Excel(name = "实付款(分)")
    private Integer actualPayment;

    /** 退款(分) */
    private Integer refund;

    /** 退款表示 */
    @Excel(name = "退款表示")
    private Integer refundTag;

    /** 结束原因 */
    @Excel(name = "结束原因")
    private Integer finishReason;

    /** 用户ID */
    @Excel(name = "用户ID")
    private String userId;

    /** 车辆VIN */
    @Excel(name = "车辆VIN")
    private String vin;

    /** 用户类型 */
    private Integer userType;

    /** 时段类型 */
    private Integer timeType;

    /** 电价单价(分) */
    private Integer electricityPrice;

    /** 服务单价(分) */
    private Integer servePrice;

    /** 站点折扣 */
    private Double stationDiscount;

    /** 会员折扣 */
    private Double memberDiscount;

    /** 会员折扣开启 */
    private Integer memberDiscountStatus;

    public void setSysId(Long sysId) 
    {
        this.sysId = sysId;
    }

    public Long getSysId() 
    {
        return sysId;
    }
    public void setChargingId(String chargingId) 
    {
        this.chargingId = chargingId;
    }

    public String getChargingId() 
    {
        return chargingId;
    }
    public void setPaymentId(String paymentId) 
    {
        this.paymentId = paymentId;
    }

    public String getPaymentId() 
    {
        return paymentId;
    }
    public void setPileId(String pileId) 
    {
        this.pileId = pileId;
    }

    public String getPileId() 
    {
        return pileId;
    }
    public void setGunId(String gunId) 
    {
        this.gunId = gunId;
    }

    public String getGunId() 
    {
        return gunId;
    }
    public void setChargingCardId(String chargingCardId) 
    {
        this.chargingCardId = chargingCardId;
    }

    public String getChargingCardId() 
    {
        return chargingCardId;
    }
    public void setChargingMode(Integer chargingMode) 
    {
        this.chargingMode = chargingMode;
    }

    public Integer getChargingMode() 
    {
        return chargingMode;
    }
    public void setStationName(String stationName) 
    {
        this.stationName = stationName;
    }

    public String getStationName() 
    {
        return stationName;
    }
    public void setChargingStatus(Integer chargingStatus) 
    {
        this.chargingStatus = chargingStatus;
    }

    public Integer getChargingStatus() 
    {
        return chargingStatus;
    }
    public void setTimeStart(Date timeStart) 
    {
        this.timeStart = timeStart;
    }

    public Date getTimeStart() 
    {
        return timeStart;
    }
    public void setTimeEnd(Date timeEnd) 
    {
        this.timeEnd = timeEnd;
    }

    public Date getTimeEnd() 
    {
        return timeEnd;
    }
    public void setPaymentChannelTag(Integer paymentChannelTag) 
    {
        this.paymentChannelTag = paymentChannelTag;
    }

    public Integer getPaymentChannelTag() 
    {
        return paymentChannelTag;
    }
    public void setPaymentChannel(String paymentChannel) 
    {
        this.paymentChannel = paymentChannel;
    }

    public String getPaymentChannel() 
    {
        return paymentChannel;
    }
    public void setPaymentMode(Integer paymentMode) 
    {
        this.paymentMode = paymentMode;
    }

    public Integer getPaymentMode() 
    {
        return paymentMode;
    }
    public void setChargingCapacity(Double chargingCapacity) 
    {
        this.chargingCapacity = chargingCapacity;
    }

    public Double getChargingCapacity() 
    {
        return chargingCapacity;
    }
    public void setCouponName(String couponName) 
    {
        this.couponName = couponName;
    }

    public String getCouponName() 
    {
        return couponName;
    }
    public void setCouponType(Integer couponType) 
    {
        this.couponType = couponType;
    }

    public Integer getCouponType() 
    {
        return couponType;
    }
    public void setCouponQuota(Integer couponQuota) 
    {
        this.couponQuota = couponQuota;
    }

    public Integer getCouponQuota() 
    {
        return couponQuota;
    }
    public void setAdvancePayment(Integer advancePayment) 
    {
        this.advancePayment = advancePayment;
    }

    public Integer getAdvancePayment() 
    {
        return advancePayment;
    }
    public void setDuePayment(Integer duePayment) 
    {
        this.duePayment = duePayment;
    }

    public Integer getDuePayment() 
    {
        return duePayment;
    }
    public void setDiscountPayment(Integer discountPayment) 
    {
        this.discountPayment = discountPayment;
    }

    public Integer getDiscountPayment() 
    {
        return discountPayment;
    }
    public void setActualPayment(Integer actualPayment) 
    {
        this.actualPayment = actualPayment;
    }

    public Integer getActualPayment() 
    {
        return actualPayment;
    }
    public void setRefund(Integer refund) 
    {
        this.refund = refund;
    }

    public Integer getRefund() 
    {
        return refund;
    }
    public void setRefundTag(Integer refundTag) 
    {
        this.refundTag = refundTag;
    }

    public Integer getRefundTag() 
    {
        return refundTag;
    }
    public void setFinishReason(Integer finishReason) 
    {
        this.finishReason = finishReason;
    }

    public Integer getFinishReason() 
    {
        return finishReason;
    }
    public void setUserId(String userId) 
    {
        this.userId = userId;
    }

    public String getUserId() 
    {
        return userId;
    }
    public void setVin(String vin) 
    {
        this.vin = vin;
    }

    public String getVin() 
    {
        return vin;
    }
    public void setUserType(Integer userType) 
    {
        this.userType = userType;
    }

    public Integer getUserType() 
    {
        return userType;
    }
    public void setTimeType(Integer timeType) 
    {
        this.timeType = timeType;
    }

    public Integer getTimeType() 
    {
        return timeType;
    }
    public void setElectricityPrice(Integer electricityPrice) 
    {
        this.electricityPrice = electricityPrice;
    }

    public Integer getElectricityPrice() 
    {
        return electricityPrice;
    }
    public void setServePrice(Integer servePrice) 
    {
        this.servePrice = servePrice;
    }

    public Integer getServePrice() 
    {
        return servePrice;
    }
    public void setStationDiscount(Double stationDiscount) 
    {
        this.stationDiscount = stationDiscount;
    }

    public Double getStationDiscount() 
    {
        return stationDiscount;
    }
    public void setMemberDiscount(Double memberDiscount) 
    {
        this.memberDiscount = memberDiscount;
    }

    public Double getMemberDiscount() 
    {
        return memberDiscount;
    }
    public void setMemberDiscountStatus(Integer memberDiscountStatus) 
    {
        this.memberDiscountStatus = memberDiscountStatus;
    }

    public Integer getMemberDiscountStatus() 
    {
        return memberDiscountStatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("sysId", getSysId())
            .append("chargingId", getChargingId())
            .append("paymentId", getPaymentId())
            .append("pileId", getPileId())
            .append("gunId", getGunId())
            .append("chargingCardId", getChargingCardId())
            .append("chargingMode", getChargingMode())
            .append("stationName", getStationName())
            .append("chargingStatus", getChargingStatus())
            .append("timeStart", getTimeStart())
            .append("timeEnd", getTimeEnd())
            .append("paymentChannelTag", getPaymentChannelTag())
            .append("paymentChannel", getPaymentChannel())
            .append("paymentMode", getPaymentMode())
            .append("chargingCapacity", getChargingCapacity())
            .append("couponName", getCouponName())
            .append("couponType", getCouponType())
            .append("couponQuota", getCouponQuota())
            .append("advancePayment", getAdvancePayment())
            .append("duePayment", getDuePayment())
            .append("discountPayment", getDiscountPayment())
            .append("actualPayment", getActualPayment())
            .append("refund", getRefund())
            .append("refundTag", getRefundTag())
            .append("finishReason", getFinishReason())
            .append("userId", getUserId())
            .append("vin", getVin())
            .append("userType", getUserType())
            .append("timeType", getTimeType())
            .append("electricityPrice", getElectricityPrice())
            .append("servePrice", getServePrice())
            .append("stationDiscount", getStationDiscount())
            .append("memberDiscount", getMemberDiscount())
            .append("memberDiscountStatus", getMemberDiscountStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
