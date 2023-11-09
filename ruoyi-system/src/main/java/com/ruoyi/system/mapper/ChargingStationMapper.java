package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.ChargingStation;

/**
 * 充电站管理Mapper接口
 * 
 * @author ruoyi
 * @date 2023-01-15
 */
public interface ChargingStationMapper 
{
    /**
     * 查询充电站管理
     * 
     * @param sysId 充电站管理主键
     * @return 充电站管理
     */
    public ChargingStation selectChargingStationBySysId(Long sysId);

    /**
     * 查询充电站管理列表
     * 
     * @param chargingStation 充电站管理
     * @return 充电站管理集合
     */
    public List<ChargingStation> selectChargingStationList(ChargingStation chargingStation);

    /**
     * 新增充电站管理
     * 
     * @param chargingStation 充电站管理
     * @return 结果
     */
    public int insertChargingStation(ChargingStation chargingStation);

    /**
     * 修改充电站管理
     * 
     * @param chargingStation 充电站管理
     * @return 结果
     */
    public int updateChargingStation(ChargingStation chargingStation);

    /**
     * 删除充电站管理
     * 
     * @param sysId 充电站管理主键
     * @return 结果
     */
    public int deleteChargingStationBySysId(Long sysId);

    /**
     * 批量删除充电站管理
     * 
     * @param sysIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteChargingStationBySysIds(Long[] sysIds);
}
