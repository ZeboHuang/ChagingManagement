package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.ChargingStationMapper;
import com.ruoyi.system.domain.ChargingStation;
import com.ruoyi.system.service.IChargingStationService;

/**
 * 充电站管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-01-15
 */
@Service
public class ChargingStationServiceImpl implements IChargingStationService 
{
    @Autowired
    private ChargingStationMapper chargingStationMapper;

    /**
     * 查询充电站管理
     * 
     * @param sysId 充电站管理主键
     * @return 充电站管理
     */
    @Override
    public ChargingStation selectChargingStationBySysId(Long sysId)
    {
        return chargingStationMapper.selectChargingStationBySysId(sysId);
    }

    /**
     * 查询充电站管理列表
     * 
     * @param chargingStation 充电站管理
     * @return 充电站管理
     */
    @Override
    public List<ChargingStation> selectChargingStationList(ChargingStation chargingStation)
    {
        return chargingStationMapper.selectChargingStationList(chargingStation);
    }

    /**
     * 新增充电站管理
     * 
     * @param chargingStation 充电站管理
     * @return 结果
     */
    @Override
    public int insertChargingStation(ChargingStation chargingStation)
    {
        chargingStation.setCreateTime(DateUtils.getNowDate());
        return chargingStationMapper.insertChargingStation(chargingStation);
    }

    /**
     * 修改充电站管理
     * 
     * @param chargingStation 充电站管理
     * @return 结果
     */
    @Override
    public int updateChargingStation(ChargingStation chargingStation)
    {
        chargingStation.setUpdateTime(DateUtils.getNowDate());
        return chargingStationMapper.updateChargingStation(chargingStation);
    }

    /**
     * 批量删除充电站管理
     * 
     * @param sysIds 需要删除的充电站管理主键
     * @return 结果
     */
    @Override
    public int deleteChargingStationBySysIds(Long[] sysIds)
    {
        return chargingStationMapper.deleteChargingStationBySysIds(sysIds);
    }

    /**
     * 删除充电站管理信息
     * 
     * @param sysId 充电站管理主键
     * @return 结果
     */
    @Override
    public int deleteChargingStationBySysId(Long sysId)
    {
        return chargingStationMapper.deleteChargingStationBySysId(sysId);
    }
}
