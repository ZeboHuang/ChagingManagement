package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 运营商管理对象 server_manage
 * 
 * @author ruoyi
 * @date 2023-01-16
 */
public class ServerManage extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 运营商编号 */
    private Long id;

    /** 运营商名称 */
    @Excel(name = "运营商名称")
    private String name;

    /** 省市 */
    @Excel(name = "省市")
    private String province;

    /** 电桩数量 */
    @Excel(name = "电桩数量")
    private Long quantity;

    /** 负责人 */
    @Excel(name = "负责人")
    private String person;

    /** 手机号 */
    @Excel(name = "手机号")
    private String phone;

    /** 邮箱 */
    @Excel(name = "邮箱")
    private String email;

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
    public void setProvince(String province) 
    {
        this.province = province;
    }

    public String getProvince() 
    {
        return province;
    }
    public void setQuantity(Long quantity) 
    {
        this.quantity = quantity;
    }

    public Long getQuantity() 
    {
        return quantity;
    }
    public void setPerson(String person) 
    {
        this.person = person;
    }

    public String getPerson() 
    {
        return person;
    }
    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }
    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("province", getProvince())
            .append("quantity", getQuantity())
            .append("person", getPerson())
            .append("phone", getPhone())
            .append("email", getEmail())
            .toString();
    }
}
