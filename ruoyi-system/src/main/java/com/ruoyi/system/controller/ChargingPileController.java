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
import com.ruoyi.system.domain.ChargingPile;
import com.ruoyi.system.service.IChargingPileService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 充电桩Controller
 * 
 * @author ruoyi
 * @date 2023-01-14
 */
@RestController
@RequestMapping("/charging/pile")
public class ChargingPileController extends BaseController
{
    @Autowired
    private IChargingPileService chargingPileService;

    /**
     * 查询充电桩列表
     */
    @PreAuthorize("@ss.hasPermi('charging:pile:list')")
    @GetMapping("/list")
    public TableDataInfo list(ChargingPile chargingPile)
    {
        startPage();
        List<ChargingPile> list = chargingPileService.selectChargingPileList(chargingPile);
        return getDataTable(list);
    }

    /**
     * 导出充电桩列表
     */
    @PreAuthorize("@ss.hasPermi('charging:pile:export')")
    @Log(title = "充电桩", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ChargingPile chargingPile)
    {
        List<ChargingPile> list = chargingPileService.selectChargingPileList(chargingPile);
        ExcelUtil<ChargingPile> util = new ExcelUtil<ChargingPile>(ChargingPile.class);
        util.exportExcel(response, list, "充电桩数据");
    }

    /**
     * 获取充电桩详细信息
     */
    @PreAuthorize("@ss.hasPermi('charging:pile:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(chargingPileService.selectChargingPileById(id));
    }

    /**
     * 新增充电桩
     */
    @PreAuthorize("@ss.hasPermi('charging:pile:add')")
    @Log(title = "充电桩", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ChargingPile chargingPile)
    {
        return toAjax(chargingPileService.insertChargingPile(chargingPile));
    }

    /**
     * 修改充电桩
     */
    @PreAuthorize("@ss.hasPermi('charging:pile:edit')")
    @Log(title = "充电桩", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ChargingPile chargingPile)
    {
        return toAjax(chargingPileService.updateChargingPile(chargingPile));
    }

    /**
     * 删除充电桩
     */
    @PreAuthorize("@ss.hasPermi('charging:pile:remove')")
    @Log(title = "充电桩", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(chargingPileService.deleteChargingPileByIds(ids));
    }
}
