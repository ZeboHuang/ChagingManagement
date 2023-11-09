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
import com.ruoyi.system.domain.Discount;
import com.ruoyi.system.service.IDiscountService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 优惠券Controller
 * 
 * @author ruoyi
 * @date 2023-01-15
 */
@RestController
@RequestMapping("/discount/voucher")
public class DiscountController extends BaseController
{
    @Autowired
    private IDiscountService discountService;

    /**
     * 查询优惠券列表
     */
    @PreAuthorize("@ss.hasPermi('discount:voucher:list')")
    @GetMapping("/list")
    public TableDataInfo list(Discount discount)
    {
        startPage();
        List<Discount> list = discountService.selectDiscountList(discount);
        return getDataTable(list);
    }

    /**
     * 导出优惠券列表
     */
    @PreAuthorize("@ss.hasPermi('discount:voucher:export')")
    @Log(title = "优惠券", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Discount discount)
    {
        List<Discount> list = discountService.selectDiscountList(discount);
        ExcelUtil<Discount> util = new ExcelUtil<Discount>(Discount.class);
        util.exportExcel(response, list, "优惠券数据");
    }

    /**
     * 获取优惠券详细信息
     */
    @PreAuthorize("@ss.hasPermi('discount:voucher:query')")
    @GetMapping(value = "/{discountId}")
    public AjaxResult getInfo(@PathVariable("discountId") Integer discountId)
    {
        return success(discountService.selectDiscountByDiscountId(discountId));
    }

    /**
     * 新增优惠券
     */
    @PreAuthorize("@ss.hasPermi('discount:voucher:add')")
    @Log(title = "优惠券", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Discount discount)
    {
        return toAjax(discountService.insertDiscount(discount));
    }

    /**
     * 修改优惠券
     */
    @PreAuthorize("@ss.hasPermi('discount:voucher:edit')")
    @Log(title = "优惠券", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Discount discount)
    {
        return toAjax(discountService.updateDiscount(discount));
    }

    /**
     * 删除优惠券
     */
    @PreAuthorize("@ss.hasPermi('discount:voucher:remove')")
    @Log(title = "优惠券", businessType = BusinessType.DELETE)
	@DeleteMapping("/{discountIds}")
    public AjaxResult remove(@PathVariable Integer[] discountIds)
    {
        return toAjax(discountService.deleteDiscountByDiscountIds(discountIds));
    }
}
