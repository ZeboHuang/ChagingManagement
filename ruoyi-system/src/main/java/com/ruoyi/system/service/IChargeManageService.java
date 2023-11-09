package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.ChargeManage;

/**
 * 计费管理Service接口
 * 
 * @author ruoyi
 * @date 2023-01-16
 */
public interface IChargeManageService 
{
    /**
     * 查询计费管理
     * 
     * @param sysId 计费管理主键
     * @return 计费管理
     */
    public ChargeManage selectChargeManageBySysId(Long sysId);

    /**
     * 查询计费管理列表
     * 
     * @param chargeManage 计费管理
     * @return 计费管理集合
     */
    public List<ChargeManage> selectChargeManageList(ChargeManage chargeManage);

    /**
     * 新增计费管理
     * 
     * @param chargeManage 计费管理
     * @return 结果
     */
    public int insertChargeManage(ChargeManage chargeManage);

    /**
     * 修改计费管理
     * 
     * @param chargeManage 计费管理
     * @return 结果
     */
    public int updateChargeManage(ChargeManage chargeManage);

    /**
     * 批量删除计费管理
     * 
     * @param sysIds 需要删除的计费管理主键集合
     * @return 结果
     */
    public int deleteChargeManageBySysIds(Long[] sysIds);

    /**
     * 删除计费管理信息
     * 
     * @param sysId 计费管理主键
     * @return 结果
     */
    public int deleteChargeManageBySysId(Long sysId);
}
