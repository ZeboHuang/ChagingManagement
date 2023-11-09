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
import com.ruoyi.system.domain.ServerManage;
import com.ruoyi.system.service.IServerManageService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 运营商管理Controller
 * 
 * @author ruoyi
 * @date 2023-01-16
 */
@RestController
@RequestMapping("/server_system/server_manage")
public class ServerManageController extends BaseController
{
    @Autowired
    private IServerManageService serverManageService;

    /**
     * 查询运营商管理列表
     */
    @PreAuthorize("@ss.hasPermi('server_system:server_manage:list')")
    @GetMapping("/list")
    public TableDataInfo list(ServerManage serverManage)
    {
        startPage();
        List<ServerManage> list = serverManageService.selectServerManageList(serverManage);
        return getDataTable(list);
    }

    /**
     * 导出运营商管理列表
     */
    @PreAuthorize("@ss.hasPermi('server_system:server_manage:export')")
    @Log(title = "运营商管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ServerManage serverManage)
    {
        List<ServerManage> list = serverManageService.selectServerManageList(serverManage);
        ExcelUtil<ServerManage> util = new ExcelUtil<ServerManage>(ServerManage.class);
        util.exportExcel(response, list, "运营商管理数据");
    }

    /**
     * 获取运营商管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('server_system:server_manage:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(serverManageService.selectServerManageById(id));
    }

    /**
     * 新增运营商管理
     */
    @PreAuthorize("@ss.hasPermi('server_system:server_manage:add')")
    @Log(title = "运营商管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ServerManage serverManage)
    {
        return toAjax(serverManageService.insertServerManage(serverManage));
    }

    /**
     * 修改运营商管理
     */
    @PreAuthorize("@ss.hasPermi('server_system:server_manage:edit')")
    @Log(title = "运营商管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ServerManage serverManage)
    {
        return toAjax(serverManageService.updateServerManage(serverManage));
    }

    /**
     * 删除运营商管理
     */
    @PreAuthorize("@ss.hasPermi('server_system:server_manage:remove')")
    @Log(title = "运营商管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(serverManageService.deleteServerManageByIds(ids));
    }
}
