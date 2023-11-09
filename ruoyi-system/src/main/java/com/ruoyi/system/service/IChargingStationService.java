package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.ChargingStation;

/**
 * 充电站管理Service接口
 * 
 * @author ruoyi
 * @date 2023-01-15
 */
public interface IChargingStationService 
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
     * 批量删除充电站管理
     * 
     * @param sysIds 需要删除的充电站管理主键集合
     * @return 结果
     */
    public int deleteChargingStationBySysIds(Long[] sysIds);

    /**
     * 删除充电站管理信息
     * 
     * @param sysId 充电站管理主键
     * @return 结果
     */
    public int deleteChargingStationBySysId(Long sysId);
}
