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
import com.ruoyi.system.domain.ChargeManage;
import com.ruoyi.system.service.IChargeManageService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 计费管理Controller
 * 
 * @author ruoyi
 * @date 2023-01-16
 */
@RestController
@RequestMapping("/charge_system/charge_manage")
public class ChargeManageController extends BaseController
{
    @Autowired
    private IChargeManageService chargeManageService;

    /**
     * 查询计费管理列表
     */
    @PreAuthorize("@ss.hasPermi('charge_system:charge_manage:list')")
    @GetMapping("/list")
    public TableDataInfo list(ChargeManage chargeManage)
    {
        startPage();
        List<ChargeManage> list = chargeManageService.selectChargeManageList(chargeManage);
        return getDataTable(list);
    }

    /**
     * 导出计费管理列表
     */
    @PreAuthorize("@ss.hasPermi('charge_system:charge_manage:export')")
    @Log(title = "计费管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ChargeManage chargeManage)
    {
        List<ChargeManage> list = chargeManageService.selectChargeManageList(chargeManage);
        ExcelUtil<ChargeManage> util = new ExcelUtil<ChargeManage>(ChargeManage.class);
        util.exportExcel(response, list, "计费管理数据");
    }

    /**
     * 获取计费管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('charge_system:charge_manage:query')")
    @GetMapping(value = "/{sysId}")
    public AjaxResult getInfo(@PathVariable("sysId") Long sysId)
    {
        return success(chargeManageService.selectChargeManageBySysId(sysId));
    }

    /**
     * 新增计费管理
     */
    @PreAuthorize("@ss.hasPermi('charge_system:charge_manage:add')")
    @Log(title = "计费管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ChargeManage chargeManage)
    {
        return toAjax(chargeManageService.insertChargeManage(chargeManage));
    }

    /**
     * 修改计费管理
     */
    @PreAuthorize("@ss.hasPermi('charge_system:charge_manage:edit')")
    @Log(title = "计费管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ChargeManage chargeManage)
    {
        return toAjax(chargeManageService.updateChargeManage(chargeManage));
    }

    /**
     * 删除计费管理
     */
    @PreAuthorize("@ss.hasPermi('charge_system:charge_manage:remove')")
    @Log(title = "计费管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{sysIds}")
    public AjaxResult remove(@PathVariable Long[] sysIds)
    {
        return toAjax(chargeManageService.deleteChargeManageBySysIds(sysIds));
    }
}
