package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.ChargingReportWeek;

/**
 * 周报表Service接口
 * 
 * @author ruoyi
 * @date 2023-01-12
 */
public interface IChargingReportWeekService 
{
    /**
     * 查询周报表
     * 
     * @param id 周报表主键
     * @return 周报表
     */
    public ChargingReportWeek selectChargingReportWeekById(Long id);

    /**
     * 查询周报表列表
     * 
     * @param chargingReportWeek 周报表
     * @return 周报表集合
     */
    public List<ChargingReportWeek> selectChargingReportWeekList(ChargingReportWeek chargingReportWeek);

    /**
     * 新增周报表
     * 
     * @param chargingReportWeek 周报表
     * @return 结果
     */
    public int insertChargingReportWeek(ChargingReportWeek chargingReportWeek);

    /**
     * 修改周报表
     * 
     * @param chargingReportWeek 周报表
     * @return 结果
     */
    public int updateChargingReportWeek(ChargingReportWeek chargingReportWeek);

    /**
     * 批量删除周报表
     * 
     * @param ids 需要删除的周报表主键集合
     * @return 结果
     */
    public int deleteChargingReportWeekByIds(Long[] ids);

    /**
     * 删除周报表信息
     * 
     * @param id 周报表主键
     * @return 结果
     */
    public int deleteChargingReportWeekById(Long id);
}
