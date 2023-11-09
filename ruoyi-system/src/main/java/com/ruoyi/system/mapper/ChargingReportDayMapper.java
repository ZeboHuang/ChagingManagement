package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.ChargingReportDay;

/**
 * 日报表Mapper接口
 * 
 * @author ruoyi
 * @date 2023-01-12
 */
public interface ChargingReportDayMapper 
{
    /**
     * 查询日报表
     * 
     * @param id 日报表主键
     * @return 日报表
     */
    public ChargingReportDay selectChargingReportDayById(Long id);

    /**
     * 查询日报表列表
     * 
     * @param chargingReportDay 日报表
     * @return 日报表集合
     */
    public List<ChargingReportDay> selectChargingReportDayList(ChargingReportDay chargingReportDay);

    /**
     * 新增日报表
     * 
     * @param chargingReportDay 日报表
     * @return 结果
     */
    public int insertChargingReportDay(ChargingReportDay chargingReportDay);

    /**
     * 修改日报表
     * 
     * @param chargingReportDay 日报表
     * @return 结果
     */
    public int updateChargingReportDay(ChargingReportDay chargingReportDay);

    /**
     * 删除日报表
     * 
     * @param id 日报表主键
     * @return 结果
     */
    public int deleteChargingReportDayById(Long id);

    /**
     * 批量删除日报表
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteChargingReportDayByIds(Long[] ids);
}
