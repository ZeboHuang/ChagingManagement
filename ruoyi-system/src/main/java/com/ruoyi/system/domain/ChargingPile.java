package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 充电桩对象 charging_pile
 * 
 * @author ruoyi
 * @date 2023-01-14
 */
public class ChargingPile extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 充电桩编号 */
    private Long id;

    /** 充电桩名称 */
    @Excel(name = "充电桩名称")
    private String name;

    /** 充电桩型号 */
    @Excel(name = "充电桩型号")
    private String model;

    /** 充电桩功率 */
    @Excel(name = "充电桩功率")
    private Long power;

    /** 充电桩序列号 */
    @Excel(name = "充电桩序列号")
    private String serialNumber;

    /** 充电桩类型/直流/交流 */
    @Excel(name = "充电桩类型/直流/交流")
    private Long type;

    /** 充电桩电压 */
    @Excel(name = "充电桩电压")
    private Long voltage;

    /** 充电枪个数 */
    @Excel(name = "充电枪个数")
    private Long gunNumber;

    /** 辅助电源数量 */
    @Excel(name = "辅助电源数量")
    private Long supplyNumber;

    /** 充电站名称 */
    @Excel(name = "充电站名称")
    private String stationName;

    /** 站分区名称 */
    @Excel(name = "站分区名称")
    private String partitionName;

    /** 充电桩状态/正常/停用 */
    @Excel(name = "充电桩状态/正常/停用")
    private Long status;

    /** 运营商名称 */
    @Excel(name = "运营商名称")
    private String operator;

    /** 所属对象 */
    @Excel(name = "所属对象")
    private String belongs;

    /** 车位编号 */
    @Excel(name = "车位编号")
    private String parkingNumber;

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
    public void setModel(String model) 
    {
        this.model = model;
    }

    public String getModel() 
    {
        return model;
    }
    public void setPower(Long power) 
    {
        this.power = power;
    }

    public Long getPower() 
    {
        return power;
    }
    public void setSerialNumber(String serialNumber) 
    {
        this.serialNumber = serialNumber;
    }

    public String getSerialNumber() 
    {
        return serialNumber;
    }
    public void setType(Long type) 
    {
        this.type = type;
    }

    public Long getType() 
    {
        return type;
    }
    public void setVoltage(Long voltage) 
    {
        this.voltage = voltage;
    }

    public Long getVoltage() 
    {
        return voltage;
    }
    public void setGunNumber(Long gunNumber) 
    {
        this.gunNumber = gunNumber;
    }

    public Long getGunNumber() 
    {
        return gunNumber;
    }
    public void setSupplyNumber(Long supplyNumber) 
    {
        this.supplyNumber = supplyNumber;
    }

    public Long getSupplyNumber() 
    {
        return supplyNumber;
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
    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }
    public void setOperator(String operator) 
    {
        this.operator = operator;
    }

    public String getOperator() 
    {
        return operator;
    }
    public void setBelongs(String belongs) 
    {
        this.belongs = belongs;
    }

    public String getBelongs() 
    {
        return belongs;
    }
    public void setParkingNumber(String parkingNumber) 
    {
        this.parkingNumber = parkingNumber;
    }

    public String getParkingNumber() 
    {
        return parkingNumber;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("model", getModel())
            .append("power", getPower())
            .append("serialNumber", getSerialNumber())
            .append("type", getType())
            .append("voltage", getVoltage())
            .append("gunNumber", getGunNumber())
            .append("supplyNumber", getSupplyNumber())
            .append("stationName", getStationName())
            .append("partitionName", getPartitionName())
            .append("status", getStatus())
            .append("operator", getOperator())
            .append("belongs", getBelongs())
            .append("parkingNumber", getParkingNumber())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
