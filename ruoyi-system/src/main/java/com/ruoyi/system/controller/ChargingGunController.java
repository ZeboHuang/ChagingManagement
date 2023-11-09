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
import com.ruoyi.system.domain.ChargingGun;
import com.ruoyi.system.service.IChargingGunService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 充电枪Controller
 * 
 * @author ruoyi
 * @date 2023-01-14
 */
@RestController
@RequestMapping("/charging/gun")
public class ChargingGunController extends BaseController
{
    @Autowired
    private IChargingGunService chargingGunService;

    /**
     * 查询充电枪列表
     */
    @PreAuthorize("@ss.hasPermi('charging:gun:list')")
    @GetMapping("/list")
    public TableDataInfo list(ChargingGun chargingGun)
    {
        startPage();
        List<ChargingGun> list = chargingGunService.selectChargingGunList(chargingGun);
        return getDataTable(list);
    }

    /**
     * 导出充电枪列表
     */
    @PreAuthorize("@ss.hasPermi('charging:gun:export')")
    @Log(title = "充电枪", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ChargingGun chargingGun)
    {
        List<ChargingGun> list = chargingGunService.selectChargingGunList(chargingGun);
        ExcelUtil<ChargingGun> util = new ExcelUtil<ChargingGun>(ChargingGun.class);
        util.exportExcel(response, list, "充电枪数据");
    }

    /**
     * 获取充电枪详细信息
     */
    @PreAuthorize("@ss.hasPermi('charging:gun:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(chargingGunService.selectChargingGunById(id));
    }

    /**
     * 新增充电枪
     */
    @PreAuthorize("@ss.hasPermi('charging:gun:add')")
    @Log(title = "充电枪", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ChargingGun chargingGun)
    {
        return toAjax(chargingGunService.insertChargingGun(chargingGun));
    }

    /**
     * 修改充电枪
     */
    @PreAuthorize("@ss.hasPermi('charging:gun:edit')")
    @Log(title = "充电枪", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ChargingGun chargingGun)
    {
        return toAjax(chargingGunService.updateChargingGun(chargingGun));
    }

    /**
     * 删除充电枪
     */
    @PreAuthorize("@ss.hasPermi('charging:gun:remove')")
    @Log(title = "充电枪", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(chargingGunService.deleteChargingGunByIds(ids));
    }
}
