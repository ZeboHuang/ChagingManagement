package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 客户对象 user
 * 
 * @author ruoyi
 * @date 2023-01-15
 */
public class User extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 用户编号 */
    private Integer userId;

    /** 用户名 */
    @Excel(name = "用户名")
    private String userName;

    /** 用户密码 */
    @Excel(name = "用户密码")
    private String userPassword;

    /** 性别 */
    @Excel(name = "性别")
    private String userSex;

    /** 出生日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "出生日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date userBirthday;

    /** 邮箱地址 */
    @Excel(name = "邮箱地址")
    private String userEmail;

    /** 手机号 */
    @Excel(name = "手机号")
    private String userPhonenumber;

    /** 用户类型 */
    @Excel(name = "用户类型")
    private Integer userType;

    /** 用户状态 */
    @Excel(name = "用户状态")
    private Integer userStatus;

    public void setUserId(Integer userId) 
    {
        this.userId = userId;
    }

    public Integer getUserId() 
    {
        return userId;
    }
    public void setUserName(String userName) 
    {
        this.userName = userName;
    }

    public String getUserName() 
    {
        return userName;
    }
    public void setUserPassword(String userPassword) 
    {
        this.userPassword = userPassword;
    }

    public String getUserPassword() 
    {
        return userPassword;
    }
    public void setUserSex(String userSex) 
    {
        this.userSex = userSex;
    }

    public String getUserSex() 
    {
        return userSex;
    }
    public void setUserBirthday(Date userBirthday) 
    {
        this.userBirthday = userBirthday;
    }

    public Date getUserBirthday() 
    {
        return userBirthday;
    }
    public void setUserEmail(String userEmail) 
    {
        this.userEmail = userEmail;
    }

    public String getUserEmail() 
    {
        return userEmail;
    }
    public void setUserPhonenumber(String userPhonenumber) 
    {
        this.userPhonenumber = userPhonenumber;
    }

    public String getUserPhonenumber() 
    {
        return userPhonenumber;
    }
    public void setUserType(Integer userType) 
    {
        this.userType = userType;
    }

    public Integer getUserType() 
    {
        return userType;
    }
    public void setUserStatus(Integer userStatus) 
    {
        this.userStatus = userStatus;
    }

    public Integer getUserStatus() 
    {
        return userStatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("userId", getUserId())
            .append("userName", getUserName())
            .append("userPassword", getUserPassword())
            .append("userSex", getUserSex())
            .append("userBirthday", getUserBirthday())
            .append("userEmail", getUserEmail())
            .append("userPhonenumber", getUserPhonenumber())
            .append("userType", getUserType())
            .append("userStatus", getUserStatus())
            .toString();
    }
}
