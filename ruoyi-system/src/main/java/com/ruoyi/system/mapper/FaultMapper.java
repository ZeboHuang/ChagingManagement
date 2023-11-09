package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.Fault;

/**
 * 故障管理Mapper接口
 * 
 * @author ruoyi
 * @date 2023-01-14
 */
public interface FaultMapper 
{
    /**
     * 查询故障管理
     * 
     * @param faultId 故障管理主键
     * @return 故障管理
     */
    public Fault selectFaultByFaultId(Integer faultId);

    /**
     * 查询故障管理列表
     * 
     * @param fault 故障管理
     * @return 故障管理集合
     */
    public List<Fault> selectFaultList(Fault fault);

    /**
     * 新增故障管理
     * 
     * @param fault 故障管理
     * @return 结果
     */
    public int insertFault(Fault fault);

    /**
     * 修改故障管理
     * 
     * @param fault 故障管理
     * @return 结果
     */
    public int updateFault(Fault fault);

    /**
     * 删除故障管理
     * 
     * @param faultId 故障管理主键
     * @return 结果
     */
    public int deleteFaultByFaultId(Integer faultId);

    /**
     * 批量删除故障管理
     * 
     * @param faultIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFaultByFaultIds(Integer[] faultIds);
}
