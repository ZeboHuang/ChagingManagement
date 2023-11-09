package com.ruoyi.system.service.impl;

import java.util.Date;
import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.ChargingOrder;
import com.ruoyi.system.mapper.ChargingOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.ChargingReportDayMapper;
import com.ruoyi.system.domain.ChargingReportDay;
import com.ruoyi.system.service.IChargingReportDayService;

/**
 * 日报表Service业务层处理
 *
 * @author ruoyi
 * @date 2023-01-12
 */
@Service
public class ChargingReportDayServiceImpl implements IChargingReportDayService {
    @Autowired
    private ChargingReportDayMapper chargingReportDayMapper;

    @Autowired
    private ChargingOrderMapper chargingOrderMapper;

    /**
     * 查询日报表
     *
     * @param id 日报表主键
     * @return 日报表
     */
    @Override
    public ChargingReportDay selectChargingReportDayById(Long id) {
        return chargingReportDayMapper.selectChargingReportDayById(id);
    }

    /**
     * 查询日报表列表
     *
     * @param chargingReportDay 日报表
     * @return 日报表
     */
    @Override
    public List<ChargingReportDay> selectChargingReportDayList(ChargingReportDay chargingReportDay) {
        return chargingReportDayMapper.selectChargingReportDayList(chargingReportDay);
    }

    /**
     * 新增日报表
     *
     * @param chargingReportDay 日报表
     * @return 结果
     */
    @Override
    public int insertChargingReportDay(ChargingReportDay chargingReportDay) {
        chargingReportDay.setCreateTime(DateUtils.getNowDate());
        return chargingReportDayMapper.insertChargingReportDay(chargingReportDay);
    }

    /**
     * 修改日报表
     *
     * @param chargingReportDay 日报表
     * @return 结果
     */
    @Override
    public int updateChargingReportDay(ChargingReportDay chargingReportDay) {
        chargingReportDay.setUpdateTime(DateUtils.getNowDate());
        return chargingReportDayMapper.updateChargingReportDay(chargingReportDay);
    }

    /**
     * 批量删除日报表
     *
     * @param ids 需要删除的日报表主键
     * @return 结果
     */
    @Override
    public int deleteChargingReportDayByIds(Long[] ids) {
        return chargingReportDayMapper.deleteChargingReportDayByIds(ids);
    }

    /**
     * 删除日报表信息
     *
     * @param id 日报表主键
     * @return 结果
     */
    @Override
    public int deleteChargingReportDayById(Long id) {
        return chargingReportDayMapper.deleteChargingReportDayById(id);
    }

    @Override
    public void autoGenerate() {
        ChargingReportDay reportDay = generateReportDay(new Date());
        chargingReportDayMapper.insertChargingReportDay(reportDay);
    }


    /**
     * 生成日报表
     *
     * @return
     */

    private ChargingReportDay generateReportDay(Date date) {
        ChargingOrder order = new ChargingOrder();
//        order.setCreateTime(date);//today
        List<ChargingOrder> orderList = chargingOrderMapper.selectChargingOrderList(order);

        double totalCapacity;
        int totalElePrice;
        int totalServePrice;
        int totalDue;
        int totalActual;
        totalActual = totalDue = totalElePrice = totalServePrice = 0;
        totalCapacity = 0;
        for (int i = 0; i < orderList.size(); i++) {
            ChargingOrder t = orderList.get(i);
            totalActual += t.getActualPayment();
            totalDue += t.getDuePayment();
            totalCapacity += t.getChargingCapacity();
            totalElePrice += t.getElectricityPrice();
            totalServePrice += t.getServePrice();
        }

        ChargingReportDay reportDay = new ChargingReportDay();
        reportDay.setChargingCapacity(totalCapacity);
        reportDay.setActualPayment(totalActual);
        reportDay.setDuePayment(totalDue);
        reportDay.setElectoryPayment(totalElePrice);
        reportDay.setServePayment(totalServePrice);
        reportDay.setStationName("武科大站(测试名称)");
        return reportDay;
    }


}
