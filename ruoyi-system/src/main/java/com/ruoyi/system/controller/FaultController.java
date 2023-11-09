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
import com.ruoyi.system.domain.Fault;
import com.ruoyi.system.service.IFaultService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 故障管理Controller
 * 
 * @author ruoyi
 * @date 2023-01-14
 */
@RestController
@RequestMapping("/charging/fault")
public class FaultController extends BaseController
{
    @Autowired
    private IFaultService faultService;

    /**
     * 查询故障管理列表
     */
    @PreAuthorize("@ss.hasPermi('charging:fault:list')")
    @GetMapping("/list")
    public TableDataInfo list(Fault fault)
    {
        startPage();
        List<Fault> list = faultService.selectFaultList(fault);
        return getDataTable(list);
    }

    /**
     * 导出故障管理列表
     */
    @PreAuthorize("@ss.hasPermi('charging:fault:export')")
    @Log(title = "故障管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Fault fault)
    {
        List<Fault> list = faultService.selectFaultList(fault);
        ExcelUtil<Fault> util = new ExcelUtil<Fault>(Fault.class);
        util.exportExcel(response, list, "故障管理数据");
    }

    /**
     * 获取故障管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('charging:fault:query')")
    @GetMapping(value = "/{faultId}")
    public AjaxResult getInfo(@PathVariable("faultId") Integer faultId)
    {
        return success(faultService.selectFaultByFaultId(faultId));
    }

    /**
     * 新增故障管理
     */
    @PreAuthorize("@ss.hasPermi('charging:fault:add')")
    @Log(title = "故障管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Fault fault)
    {
        return toAjax(faultService.insertFault(fault));
    }

    /**
     * 修改故障管理
     */
    @PreAuthorize("@ss.hasPermi('charging:fault:edit')")
    @Log(title = "故障管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Fault fault)
    {
        return toAjax(faultService.updateFault(fault));
    }

    /**
     * 删除故障管理
     */
    @PreAuthorize("@ss.hasPermi('charging:fault:remove')")
    @Log(title = "故障管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{faultIds}")
    public AjaxResult remove(@PathVariable Integer[] faultIds)
    {
        return toAjax(faultService.deleteFaultByFaultIds(faultIds));
    }
}
