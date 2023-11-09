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
import com.ruoyi.system.domain.ChargingStation;
import com.ruoyi.system.service.IChargingStationService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 充电站管理Controller
 * 
 * @author ruoyi
 * @date 2023-01-15
 */
@RestController
@RequestMapping("/charging/station")
public class ChargingStationController extends BaseController
{
    @Autowired
    private IChargingStationService chargingStationService;

    /**
     * 查询充电站管理列表
     */
    @PreAuthorize("@ss.hasPermi('charging:station:list')")
    @GetMapping("/list")
    public TableDataInfo list(ChargingStation chargingStation)
    {
        startPage();
        List<ChargingStation> list = chargingStationService.selectChargingStationList(chargingStation);
        return getDataTable(list);
    }

    /**
     * 导出充电站管理列表
     */
    @PreAuthorize("@ss.hasPermi('charging:station:export')")
    @Log(title = "充电站管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ChargingStation chargingStation)
    {
        List<ChargingStation> list = chargingStationService.selectChargingStationList(chargingStation);
        ExcelUtil<ChargingStation> util = new ExcelUtil<ChargingStation>(ChargingStation.class);
        util.exportExcel(response, list, "充电站管理数据");
    }

    /**
     * 获取充电站管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('charging:station:query')")
    @GetMapping(value = "/{sysId}")
    public AjaxResult getInfo(@PathVariable("sysId") Long sysId)
    {
        return success(chargingStationService.selectChargingStationBySysId(sysId));
    }

    /**
     * 新增充电站管理
     */
    @PreAuthorize("@ss.hasPermi('charging:station:add')")
    @Log(title = "充电站管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ChargingStation chargingStation)
    {
        return toAjax(chargingStationService.insertChargingStation(chargingStation));
    }

    /**
     * 修改充电站管理
     */
    @PreAuthorize("@ss.hasPermi('charging:station:edit')")
    @Log(title = "充电站管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ChargingStation chargingStation)
    {
        return toAjax(chargingStationService.updateChargingStation(chargingStation));
    }

    /**
     * 删除充电站管理
     */
    @PreAuthorize("@ss.hasPermi('charging:station:remove')")
    @Log(title = "充电站管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{sysIds}")
    public AjaxResult remove(@PathVariable Long[] sysIds)
    {
        return toAjax(chargingStationService.deleteChargingStationBySysIds(sysIds));
    }
}
