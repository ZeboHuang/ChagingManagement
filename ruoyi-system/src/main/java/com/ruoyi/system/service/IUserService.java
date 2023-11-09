package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.User;

/**
 * 客户Service接口
 * 
 * @author ruoyi
 * @date 2023-01-15
 */
public interface IUserService 
{
    /**
     * 查询客户
     * 
     * @param userId 客户主键
     * @return 客户
     */
    public User selectUserByUserId(Integer userId);

    /**
     * 查询客户列表
     * 
     * @param user 客户
     * @return 客户集合
     */
    public List<User> selectUserList(User user);

    /**
     * 新增客户
     * 
     * @param user 客户
     * @return 结果
     */
    public int insertUser(User user);

    /**
     * 修改客户
     * 
     * @param user 客户
     * @return 结果
     */
    public int updateUser(User user);

    /**
     * 批量删除客户
     * 
     * @param userIds 需要删除的客户主键集合
     * @return 结果
     */
    public int deleteUserByUserIds(Integer[] userIds);

    /**
     * 删除客户信息
     * 
     * @param userId 客户主键
     * @return 结果
     */
    public int deleteUserByUserId(Integer userId);
}
