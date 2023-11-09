package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.FaultMapper;
import com.ruoyi.system.domain.Fault;
import com.ruoyi.system.service.IFaultService;

/**
 * 故障管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-01-14
 */
@Service
public class FaultServiceImpl implements IFaultService 
{
    @Autowired
    private FaultMapper faultMapper;

    /**
     * 查询故障管理
     * 
     * @param faultId 故障管理主键
     * @return 故障管理
     */
    @Override
    public Fault selectFaultByFaultId(Integer faultId)
    {
        return faultMapper.selectFaultByFaultId(faultId);
    }

    /**
     * 查询故障管理列表
     * 
     * @param fault 故障管理
     * @return 故障管理
     */
    @Override
    public List<Fault> selectFaultList(Fault fault)
    {
        return faultMapper.selectFaultList(fault);
    }

    /**
     * 新增故障管理
     * 
     * @param fault 故障管理
     * @return 结果
     */
    @Override
    public int insertFault(Fault fault)
    {
        fault.setCreateTime(DateUtils.getNowDate());
        return faultMapper.insertFault(fault);
    }

    /**
     * 修改故障管理
     * 
     * @param fault 故障管理
     * @return 结果
     */
    @Override
    public int updateFault(Fault fault)
    {
        fault.setUpdateTime(DateUtils.getNowDate());
        return faultMapper.updateFault(fault);
    }

    /**
     * 批量删除故障管理
     * 
     * @param faultIds 需要删除的故障管理主键
     * @return 结果
     */
    @Override
    public int deleteFaultByFaultIds(Integer[] faultIds)
    {
        return faultMapper.deleteFaultByFaultIds(faultIds);
    }

    /**
     * 删除故障管理信息
     * 
     * @param faultId 故障管理主键
     * @return 结果
     */
    @Override
    public int deleteFaultByFaultId(Integer faultId)
    {
        return faultMapper.deleteFaultByFaultId(faultId);
    }
}
