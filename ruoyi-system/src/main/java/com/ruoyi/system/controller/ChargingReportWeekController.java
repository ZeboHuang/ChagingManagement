package com.ruoyi.system.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import com.ruoyi.system.domain.ChargingReportWeek;
import com.ruoyi.system.service.IChargingReportWeekService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 周报表Controller
 * 
 * @author ruoyi
 * @date 2023-01-12
 */
@RestController
@RequestMapping("/report/week")
public class ChargingReportWeekController extends BaseController
{
    @Autowired
    private IChargingReportWeekService chargingReportWeekService;

    /**
     * 查询周报表列表
     */
    @PreAuthorize("@ss.hasPermi('report:week:list')")
    @GetMapping("/list")
    public TableDataInfo list(ChargingReportWeek chargingReportWeek)
    {
        startPage();
        List<ChargingReportWeek> list = chargingReportWeekService.selectChargingReportWeekList(chargingReportWeek);
        return getDataTable(list);
    }

    /**
     * 导出周报表列表
     */
    @PreAuthorize("@ss.hasPermi('report:week:export')")
    @Log(title = "周报表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ChargingReportWeek chargingReportWeek)
    {
        List<ChargingReportWeek> list = chargingReportWeekService.selectChargingReportWeekList(chargingReportWeek);
        ExcelUtil<ChargingReportWeek> util = new ExcelUtil<ChargingReportWeek>(ChargingReportWeek.class);
        util.exportExcel(response, list, "周报表数据");
    }

    /**
     * 获取周报表详细信息
     */
    @PreAuthorize("@ss.hasPermi('report:week:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(chargingReportWeekService.selectChargingReportWeekById(id));
    }

    /**
     * 新增周报表
     */
    @PreAuthorize("@ss.hasPermi('report:week:add')")
    @Log(title = "周报表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ChargingReportWeek chargingReportWeek)
    {
        return toAjax(chargingReportWeekService.insertChargingReportWeek(chargingReportWeek));
    }

    /**
     * 修改周报表
     */
    @PreAuthorize("@ss.hasPermi('report:week:edit')")
    @Log(title = "周报表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ChargingReportWeek chargingReportWeek)
    {
        return toAjax(chargingReportWeekService.updateChargingReportWeek(chargingReportWeek));
    }

    /**
     * 删除周报表
     */
    @PreAuthorize("@ss.hasPermi('report:week:remove')")
    @Log(title = "周报表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(chargingReportWeekService.deleteChargingReportWeekByIds(ids));
    }
}
