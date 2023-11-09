package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.ChargeManageMapper;
import com.ruoyi.system.domain.ChargeManage;
import com.ruoyi.system.service.IChargeManageService;

/**
 * 计费管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-01-16
 */
@Service
public class ChargeManageServiceImpl implements IChargeManageService 
{
    @Autowired
    private ChargeManageMapper chargeManageMapper;

    /**
     * 查询计费管理
     * 
     * @param sysId 计费管理主键
     * @return 计费管理
     */
    @Override
    public ChargeManage selectChargeManageBySysId(Long sysId)
    {
        return chargeManageMapper.selectChargeManageBySysId(sysId);
    }

    /**
     * 查询计费管理列表
     * 
     * @param chargeManage 计费管理
     * @return 计费管理
     */
    @Override
    public List<ChargeManage> selectChargeManageList(ChargeManage chargeManage)
    {
        return chargeManageMapper.selectChargeManageList(chargeManage);
    }

    /**
     * 新增计费管理
     * 
     * @param chargeManage 计费管理
     * @return 结果
     */
    @Override
    public int insertChargeManage(ChargeManage chargeManage)
    {
        return chargeManageMapper.insertChargeManage(chargeManage);
    }

    /**
     * 修改计费管理
     * 
     * @param chargeManage 计费管理
     * @return 结果
     */
    @Override
    public int updateChargeManage(ChargeManage chargeManage)
    {
        return chargeManageMapper.updateChargeManage(chargeManage);
    }

    /**
     * 批量删除计费管理
     * 
     * @param sysIds 需要删除的计费管理主键
     * @return 结果
     */
    @Override
    public int deleteChargeManageBySysIds(Long[] sysIds)
    {
        return chargeManageMapper.deleteChargeManageBySysIds(sysIds);
    }

    /**
     * 删除计费管理信息
     * 
     * @param sysId 计费管理主键
     * @return 结果
     */
    @Override
    public int deleteChargeManageBySysId(Long sysId)
    {
        return chargeManageMapper.deleteChargeManageBySysId(sysId);
    }
}
