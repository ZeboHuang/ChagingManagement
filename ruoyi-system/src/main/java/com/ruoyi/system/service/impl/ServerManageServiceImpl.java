package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.ServerManageMapper;
import com.ruoyi.system.domain.ServerManage;
import com.ruoyi.system.service.IServerManageService;

/**
 * 运营商管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-01-16
 */
@Service
public class ServerManageServiceImpl implements IServerManageService 
{
    @Autowired
    private ServerManageMapper serverManageMapper;

    /**
     * 查询运营商管理
     * 
     * @param id 运营商管理主键
     * @return 运营商管理
     */
    @Override
    public ServerManage selectServerManageById(Long id)
    {
        return serverManageMapper.selectServerManageById(id);
    }

    /**
     * 查询运营商管理列表
     * 
     * @param serverManage 运营商管理
     * @return 运营商管理
     */
    @Override
    public List<ServerManage> selectServerManageList(ServerManage serverManage)
    {
        return serverManageMapper.selectServerManageList(serverManage);
    }

    /**
     * 新增运营商管理
     * 
     * @param serverManage 运营商管理
     * @return 结果
     */
    @Override
    public int insertServerManage(ServerManage serverManage)
    {
        return serverManageMapper.insertServerManage(serverManage);
    }

    /**
     * 修改运营商管理
     * 
     * @param serverManage 运营商管理
     * @return 结果
     */
    @Override
    public int updateServerManage(ServerManage serverManage)
    {
        return serverManageMapper.updateServerManage(serverManage);
    }

    /**
     * 批量删除运营商管理
     * 
     * @param ids 需要删除的运营商管理主键
     * @return 结果
     */
    @Override
    public int deleteServerManageByIds(Long[] ids)
    {
        return serverManageMapper.deleteServerManageByIds(ids);
    }

    /**
     * 删除运营商管理信息
     * 
     * @param id 运营商管理主键
     * @return 结果
     */
    @Override
    public int deleteServerManageById(Long id)
    {
        return serverManageMapper.deleteServerManageById(id);
    }
}
