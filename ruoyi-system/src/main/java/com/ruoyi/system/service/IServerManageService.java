package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.ServerManage;

/**
 * 运营商管理Service接口
 * 
 * @author ruoyi
 * @date 2023-01-16
 */
public interface IServerManageService 
{
    /**
     * 查询运营商管理
     * 
     * @param id 运营商管理主键
     * @return 运营商管理
     */
    public ServerManage selectServerManageById(Long id);

    /**
     * 查询运营商管理列表
     * 
     * @param serverManage 运营商管理
     * @return 运营商管理集合
     */
    public List<ServerManage> selectServerManageList(ServerManage serverManage);

    /**
     * 新增运营商管理
     * 
     * @param serverManage 运营商管理
     * @return 结果
     */
    public int insertServerManage(ServerManage serverManage);

    /**
     * 修改运营商管理
     * 
     * @param serverManage 运营商管理
     * @return 结果
     */
    public int updateServerManage(ServerManage serverManage);

    /**
     * 批量删除运营商管理
     * 
     * @param ids 需要删除的运营商管理主键集合
     * @return 结果
     */
    public int deleteServerManageByIds(Long[] ids);

    /**
     * 删除运营商管理信息
     * 
     * @param id 运营商管理主键
     * @return 结果
     */
    public int deleteServerManageById(Long id);
}
