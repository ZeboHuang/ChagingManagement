package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 充电站管理对象 charging_station
 * 
 * @author ruoyi
 * @date 2023-01-15
 */
public class ChargingStation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 系统ID */
    @Excel(name = "系统ID")
    private Long sysId;

    /** 充电站ID */
    @Excel(name = "充电站ID")
    private String id;

    /** 站点名称 */
    @Excel(name = "站点名称")
    private String name;

    /** 站点类型/公共/个人 */
    @Excel(name = "站点类型")
    private Integer type;

    /** 省份 */
    @Excel(name = "省份")
    private String province;

    /** 城市 */
    @Excel(name = "城市")
    private String city;

    /** 区/县 */
    @Excel(name = "区/县")
    private String country;

    /** 详细地址 */
    @Excel(name = "详细地址")
    private String address;

    /** 建设场所 */
    @Excel(name = "建设场所")
    private String site;

    /** 可容纳量（个） */
    @Excel(name = "可容纳量", readConverterExp = "个=")
    private Integer capacity;

    /** 经度 */
    @Excel(name = "经度")
    private Double longitude;

    /** 纬度 */
    @Excel(name = "纬度")
    private Double latitude;

    /** 服务折扣费 */
    @Excel(name = "服务折扣费")
    private Double discount;

    /** 是否支持优惠券 */
    @Excel(name = "是否支持优惠券")
    private Integer couponStatus;

    /** 是否免费停车 */
    @Excel(name = "是否免费停车")
    private Integer parkingFreeStatus;

    /** 站点标签/免费停车/洗手间/餐饮/休息区/休闲娱乐/新能源车展厅/酒店住宿/换电站/服务区 */
    @Excel(name = "站点标签/免费停车/洗手间/餐饮/休息区/休闲娱乐/新能源车展厅/酒店住宿/换电站/服务区")
    private Integer tag;

    /** 运营商名称 */
    @Excel(name = "运营商名称")
    private String operator;

    /** 站点状态/正常/停用 */
    @Excel(name = "站点状态/正常/停用")
    private Integer stationStatus;

    /** 分区数量 */
    @Excel(name = "分区数量")
    private Integer regionNum;

    /** 营业周期(二进制表示) */
    private Integer businessCycle;

    /** 营业时间(起) */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "营业时间(起)", width = 30, dateFormat = "yyyy-MM-dd")
    private Date businessTimeStart;

    /** 营业时间(止) */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "营业时间(止)", width = 30, dateFormat = "yyyy-MM-dd")
    private Date businessTimeEnd;

    /** 联系人 */
    @Excel(name = "联系人")
    private String contact;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String phoneNumber;

    public void setSysId(Long sysId) 
    {
        this.sysId = sysId;
    }

    public Long getSysId() 
    {
        return sysId;
    }
    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setType(Integer type) 
    {
        this.type = type;
    }

    public Integer getType() 
    {
        return type;
    }
    public void setProvince(String province) 
    {
        this.province = province;
    }

    public String getProvince() 
    {
        return province;
    }
    public void setCity(String city) 
    {
        this.city = city;
    }

    public String getCity() 
    {
        return city;
    }
    public void setCountry(String country) 
    {
        this.country = country;
    }

    public String getCountry() 
    {
        return country;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }
    public void setSite(String site) 
    {
        this.site = site;
    }

    public String getSite() 
    {
        return site;
    }
    public void setCapacity(Integer capacity) 
    {
        this.capacity = capacity;
    }

    public Integer getCapacity() 
    {
        return capacity;
    }
    public void setLongitude(Double longitude) 
    {
        this.longitude = longitude;
    }

    public Double getLongitude() 
    {
        return longitude;
    }
    public void setLatitude(Double latitude) 
    {
        this.latitude = latitude;
    }

    public Double getLatitude() 
    {
        return latitude;
    }
    public void setDiscount(Double discount) 
    {
        this.discount = discount;
    }

    public Double getDiscount() 
    {
        return discount;
    }
    public void setCouponStatus(Integer couponStatus) 
    {
        this.couponStatus = couponStatus;
    }

    public Integer getCouponStatus() 
    {
        return couponStatus;
    }
    public void setParkingFreeStatus(Integer parkingFreeStatus) 
    {
        this.parkingFreeStatus = parkingFreeStatus;
    }

    public Integer getParkingFreeStatus() 
    {
        return parkingFreeStatus;
    }
    public void setTag(Integer tag) 
    {
        this.tag = tag;
    }

    public Integer getTag() 
    {
        return tag;
    }
    public void setOperator(String operator) 
    {
        this.operator = operator;
    }

    public String getOperator() 
    {
        return operator;
    }
    public void setStationStatus(Integer stationStatus) 
    {
        this.stationStatus = stationStatus;
    }

    public Integer getStationStatus() 
    {
        return stationStatus;
    }
    public void setRegionNum(Integer regionNum) 
    {
        this.regionNum = regionNum;
    }

    public Integer getRegionNum() 
    {
        return regionNum;
    }
    public void setBusinessCycle(Integer businessCycle) 
    {
        this.businessCycle = businessCycle;
    }

    public Integer getBusinessCycle() 
    {
        return businessCycle;
    }
    public void setBusinessTimeStart(Date businessTimeStart) 
    {
        this.businessTimeStart = businessTimeStart;
    }

    public Date getBusinessTimeStart() 
    {
        return businessTimeStart;
    }
    public void setBusinessTimeEnd(Date businessTimeEnd) 
    {
        this.businessTimeEnd = businessTimeEnd;
    }

    public Date getBusinessTimeEnd() 
    {
        return businessTimeEnd;
    }
    public void setContact(String contact) 
    {
        this.contact = contact;
    }

    public String getContact() 
    {
        return contact;
    }
    public void setPhoneNumber(String phoneNumber) 
    {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() 
    {
        return phoneNumber;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("sysId", getSysId())
            .append("id", getId())
            .append("name", getName())
            .append("type", getType())
            .append("province", getProvince())
            .append("city", getCity())
            .append("country", getCountry())
            .append("address", getAddress())
            .append("site", getSite())
            .append("capacity", getCapacity())
            .append("longitude", getLongitude())
            .append("latitude", getLatitude())
            .append("discount", getDiscount())
            .append("couponStatus", getCouponStatus())
            .append("parkingFreeStatus", getParkingFreeStatus())
            .append("tag", getTag())
            .append("operator", getOperator())
            .append("stationStatus", getStationStatus())
            .append("regionNum", getRegionNum())
            .append("businessCycle", getBusinessCycle())
            .append("businessTimeStart", getBusinessTimeStart())
            .append("businessTimeEnd", getBusinessTimeEnd())
            .append("contact", getContact())
            .append("phoneNumber", getPhoneNumber())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
