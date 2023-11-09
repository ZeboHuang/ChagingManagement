package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 充电枪对象 charging_gun
 * 
 * @author ruoyi
 * @date 2023-01-14
 */
public class ChargingGun extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 充电枪id */
    private Long id;

    /** 充电枪名称 */
    @Excel(name = "充电枪名称")
    private String name;

    /** 对应车位号 */
    @Excel(name = "对应车位号")
    private String parkingNumber;

    /** 省份 */
    @Excel(name = "省份")
    private String province;

    /** 城市 */
    @Excel(name = "城市")
    private String city;

    /** 区/县 */
    @Excel(name = "区/县")
    private String country;

    /** 充电站名称 */
    @Excel(name = "充电站名称")
    private String stationName;

    /** 站分区名称 */
    @Excel(name = "站分区名称")
    private String partitionName;

    /** 充电桩id */
    @Excel(name = "充电桩id")
    private String pileId;

    /** 充电桩名称 */
    @Excel(name = "充电桩名称")
    private String pileName;

    /** 充电桩序列号 */
    @Excel(name = "充电桩序列号")
    private String pileNumber;

    /** 充电桩类型/直流/交流 */
    @Excel(name = "充电桩类型/直流/交流")
    private Long pileType;

    /** 运营商名称 */
    @Excel(name = "运营商名称")
    private String operator;

    /** 枪状态/在线/离网 */
    @Excel(name = "枪状态/在线/离网")
    private Long gunStatus;

    /** 生产商 */
    @Excel(name = "生产商")
    private String producer;

    /** 二维码url */
    @Excel(name = "二维码url")
    private String url;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
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
    public void setParkingNumber(String parkingNumber) 
    {
        this.parkingNumber = parkingNumber;
    }

    public String getParkingNumber() 
    {
        return parkingNumber;
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
    public void setStationName(String stationName) 
    {
        this.stationName = stationName;
    }

    public String getStationName() 
    {
        return stationName;
    }
    public void setPartitionName(String partitionName) 
    {
        this.partitionName = partitionName;
    }

    public String getPartitionName() 
    {
        return partitionName;
    }
    public void setPileId(String pileId) 
    {
        this.pileId = pileId;
    }

    public String getPileId() 
    {
        return pileId;
    }
    public void setPileName(String pileName) 
    {
        this.pileName = pileName;
    }

    public String getPileName() 
    {
        return pileName;
    }
    public void setPileNumber(String pileNumber) 
    {
        this.pileNumber = pileNumber;
    }

    public String getPileNumber() 
    {
        return pileNumber;
    }
    public void setPileType(Long pileType) 
    {
        this.pileType = pileType;
    }

    public Long getPileType() 
    {
        return pileType;
    }
    public void setOperator(String operator) 
    {
        this.operator = operator;
    }

    public String getOperator() 
    {
        return operator;
    }
    public void setGunStatus(Long gunStatus) 
    {
        this.gunStatus = gunStatus;
    }

    public Long getGunStatus() 
    {
        return gunStatus;
    }
    public void setProducer(String producer) 
    {
        this.producer = producer;
    }

    public String getProducer() 
    {
        return producer;
    }
    public void setUrl(String url) 
    {
        this.url = url;
    }

    public String getUrl() 
    {
        return url;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("parkingNumber", getParkingNumber())
            .append("province", getProvince())
            .append("city", getCity())
            .append("country", getCountry())
            .append("stationName", getStationName())
            .append("partitionName", getPartitionName())
            .append("pileId", getPileId())
            .append("pileName", getPileName())
            .append("pileNumber", getPileNumber())
            .append("pileType", getPileType())
            .append("operator", getOperator())
            .append("gunStatus", getGunStatus())
            .append("producer", getProducer())
            .append("url", getUrl())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
