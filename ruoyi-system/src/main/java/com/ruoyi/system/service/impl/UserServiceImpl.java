package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.UserMapper;
import com.ruoyi.system.domain.User;
import com.ruoyi.system.service.IUserService;

/**
 * 客户Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-01-15
 */
@Service
public class UserServiceImpl implements IUserService 
{
    @Autowired
    private UserMapper userMapper;

    /**
     * 查询客户
     * 
     * @param userId 客户主键
     * @return 客户
     */
    @Override
    public User selectUserByUserId(Integer userId)
    {
        return userMapper.selectUserByUserId(userId);
    }

    /**
     * 查询客户列表
     * 
     * @param user 客户
     * @return 客户
     */
    @Override
    public List<User> selectUserList(User user)
    {
        return userMapper.selectUserList(user);
    }

    /**
     * 新增客户
     * 
     * @param user 客户
     * @return 结果
     */
    @Override
    public int insertUser(User user)
    {
        return userMapper.insertUser(user);
    }

    /**
     * 修改客户
     * 
     * @param user 客户
     * @return 结果
     */
    @Override
    public int updateUser(User user)
    {
        return userMapper.updateUser(user);
    }

    /**
     * 批量删除客户
     * 
     * @param userIds 需要删除的客户主键
     * @return 结果
     */
    @Override
    public int deleteUserByUserIds(Integer[] userIds)
    {
        return userMapper.deleteUserByUserIds(userIds);
    }

    /**
     * 删除客户信息
     * 
     * @param userId 客户主键
     * @return 结果
     */
    @Override
    public int deleteUserByUserId(Integer userId)
    {
        return userMapper.deleteUserByUserId(userId);
    }
}
