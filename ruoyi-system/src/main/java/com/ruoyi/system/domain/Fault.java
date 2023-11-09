package com.ruoyi.system.domain;

import com.ruoyi.common.utils.uuid.UUID;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 故障管理对象 fault
 * 
 * @author ruoyi
 * @date 2023-01-14
 */
public class Fault extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 故障编码 */
    @Excel(name = "故障编码")
    private Integer faultId;

    /** 故障原因/启动后不能充电/充电键不能被按下/充电电枪无法插入电动汽车/其他 */
    @Excel(name = "故障原因/启动后不能充电/充电键不能被按下/充电电枪无法插入电动汽车/其他")
    private Integer reason;

    /** 充电枪编码 */
    @Excel(name = "充电枪编码")
    private String gunId;

    /** 充电桩编码 */
    @Excel(name = "充电桩编码")
    private String pileId;

    /** 充电站编码 */
    @Excel(name = "充电站编码")
    private String stationId;

    /** 详细地址 */
    @Excel(name = "详细地址")
    private String address;

    /** 反馈用户编码 */
    @Excel(name = "反馈用户编码")
    private String userId;

    /** 联系人 */
    @Excel(name = "联系人")
    private String usre;

    /** 联系手机号 */
    @Excel(name = "联系手机号")
    private String phone;

    /** 联系邮箱 */
    @Excel(name = "联系邮箱")
    private String emali;

    public void setFaultId(Integer faultId) 
    {
        this.faultId = faultId;
    }

    public Integer getFaultId() 
    {
        return faultId;
    }
    public void setReason(Integer reason) 
    {
        this.reason = reason;
    }

    public Integer getReason() 
    {
        return reason;
    }
    public void setGunId(String gunId) 
    {
        this.gunId = gunId;
    }

    public String getGunId() 
    {
        return gunId;
    }
    public void setPileId(String pileId) 
    {
        this.pileId = pileId;
    }

    public String getPileId() 
    {
        return pileId;
    }
    public void setStationId(String stationId) 
    {
        this.stationId = stationId;
    }

    public String getStationId() 
    {
        return stationId;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }
    public void setUserId(String userId) 
    {
        this.userId = userId;
    }

    public String getUserId() 
    {
        return userId;
    }
    public void setUsre(String usre) 
    {
        this.usre = usre;
    }

    public String getUsre() 
    {
        return usre;
    }
    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }
    public void setEmali(String emali) 
    {
        this.emali = emali;
    }

    public String getEmali() 
    {
        return emali;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("faultId", getFaultId())
            .append("reason", getReason())
            .append("gunId", getGunId())
            .append("pileId", getPileId())
            .append("stationId", getStationId())
            .append("address", getAddress())
            .append("userId", getUserId())
            .append("usre", getUsre())
            .append("phone", getPhone())
            .append("emali", getEmali())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
