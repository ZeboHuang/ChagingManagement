package com.ruoyi.system.controller;

import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.system.domain.ChargingOrder;
import com.ruoyi.system.service.IChargingOrderService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.ChargingReportDay;
import com.ruoyi.system.service.IChargingReportDayService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 日报表Controller
 *
 * @author ruoyi
 * @date 2023-01-12
 */
@RestController
@RequestMapping("/report/day")
public class ChargingReportDayController extends BaseController {
    @Autowired
    private IChargingReportDayService chargingReportDayService;
    private IChargingOrderService chargingOrderService;


    /**
     * 生成日报表
     * @return
     */

    private ChargingReportDay generateReportDay(Date date) {
        ChargingOrder order = new ChargingOrder();
        order.setCreateTime(date);//today
        List<ChargingOrder> orderList = chargingOrderService.selectChargingOrderList(order);

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

        ChargingReportDay reportDay=new ChargingReportDay();
        reportDay.setChargingCapacity(totalCapacity);
        reportDay.setActualPayment(totalActual);
        reportDay.setDuePayment(totalDue);
        reportDay.setElectoryPayment(totalElePrice);
        reportDay.setServePayment(totalServePrice);
        reportDay.setStationName("武科大站(测试名称)");
        return reportDay;
    }


    /**
     * 查询日报表列表
     */
    @PreAuthorize("@ss.hasPermi('report:day:list')")
    @GetMapping("/list")
    public TableDataInfo list(ChargingReportDay chargingReportDay) {
        startPage();
        List<ChargingReportDay> list = chargingReportDayService.selectChargingReportDayList(chargingReportDay);
        return getDataTable(list);
    }

    /**
     * 导出日报表列表
     */
    @PreAuthorize("@ss.hasPermi('report:day:export')")
    @Log(title = "日报表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ChargingReportDay chargingReportDay) {
        List<ChargingReportDay> list = chargingReportDayService.selectChargingReportDayList(chargingReportDay);
        ExcelUtil<ChargingReportDay> util = new ExcelUtil<ChargingReportDay>(ChargingReportDay.class);
        util.exportExcel(response, list, "日报表数据");
    }

    /**
     * 获取日报表详细信息
     */
    @PreAuthorize("@ss.hasPermi('report:day:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(chargingReportDayService.selectChargingReportDayById(id));
    }

    /**
     * 新增日报表
     */
    @PreAuthorize("@ss.hasPermi('report:day:add')")
    @Log(title = "日报表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ChargingReportDay chargingReportDay) {
        return toAjax(chargingReportDayService.insertChargingReportDay(chargingReportDay));
    }

    /**
     * 修改日报表
     */
    @PreAuthorize("@ss.hasPermi('report:day:edit')")
    @Log(title = "日报表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ChargingReportDay chargingReportDay) {
        return toAjax(chargingReportDayService.updateChargingReportDay(chargingReportDay));
    }

    /**
     * 删除日报表
     */
    @PreAuthorize("@ss.hasPermi('report:day:remove')")
    @Log(title = "日报表", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(chargingReportDayService.deleteChargingReportDayByIds(ids));
    }
}
