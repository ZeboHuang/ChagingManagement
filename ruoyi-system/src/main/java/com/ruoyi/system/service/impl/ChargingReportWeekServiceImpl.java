package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.ChargingReportWeekMapper;
import com.ruoyi.system.domain.ChargingReportWeek;
import com.ruoyi.system.service.IChargingReportWeekService;

/**
 * 周报表Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-01-12
 */
@Service
public class ChargingReportWeekServiceImpl implements IChargingReportWeekService 
{
    @Autowired
    private ChargingReportWeekMapper chargingReportWeekMapper;

    /**
     * 查询周报表
     * 
     * @param id 周报表主键
     * @return 周报表
     */
    @Override
    public ChargingReportWeek selectChargingReportWeekById(Long id)
    {
        return chargingReportWeekMapper.selectChargingReportWeekById(id);
    }

    /**
     * 查询周报表列表
     * 
     * @param chargingReportWeek 周报表
     * @return 周报表
     */
    @Override
    public List<ChargingReportWeek> selectChargingReportWeekList(ChargingReportWeek chargingReportWeek)
    {
        return chargingReportWeekMapper.selectChargingReportWeekList(chargingReportWeek);
    }

    /**
     * 新增周报表
     * 
     * @param chargingReportWeek 周报表
     * @return 结果
     */
    @Override
    public int insertChargingReportWeek(ChargingReportWeek chargingReportWeek)
    {
        chargingReportWeek.setCreateTime(DateUtils.getNowDate());
        return chargingReportWeekMapper.insertChargingReportWeek(chargingReportWeek);
    }

    /**
     * 修改周报表
     * 
     * @param chargingReportWeek 周报表
     * @return 结果
     */
    @Override
    public int updateChargingReportWeek(ChargingReportWeek chargingReportWeek)
    {
        chargingReportWeek.setUpdateTime(DateUtils.getNowDate());
        return chargingReportWeekMapper.updateChargingReportWeek(chargingReportWeek);
    }

    /**
     * 批量删除周报表
     * 
     * @param ids 需要删除的周报表主键
     * @return 结果
     */
    @Override
    public int deleteChargingReportWeekByIds(Long[] ids)
    {
        return chargingReportWeekMapper.deleteChargingReportWeekByIds(ids);
    }

    /**
     * 删除周报表信息
     * 
     * @param id 周报表主键
     * @return 结果
     */
    @Override
    public int deleteChargingReportWeekById(Long id)
    {
        return chargingReportWeekMapper.deleteChargingReportWeekById(id);
    }
}
