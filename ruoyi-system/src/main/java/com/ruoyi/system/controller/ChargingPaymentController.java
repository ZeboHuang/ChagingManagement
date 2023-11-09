package com.ruoyi.system.controller;

import java.util.List;
import java.util.UUID;
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
import com.ruoyi.system.domain.ChargingPayment;
import com.ruoyi.system.service.IChargingPaymentService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 支付订单Controller
 * 
 * @author ruoyi
 * @date 2023-01-12
 */
@RestController
@RequestMapping("/order/payment")
public class ChargingPaymentController extends BaseController
{
    @Autowired
    private IChargingPaymentService chargingPaymentService;

    /**
     * 查询支付订单列表
     */
    @PreAuthorize("@ss.hasPermi('order:payment:list')")
    @GetMapping("/list")
    public TableDataInfo list(ChargingPayment chargingPayment)
    {
        startPage();
        List<ChargingPayment> list = chargingPaymentService.selectChargingPaymentList(chargingPayment);
        return getDataTable(list);
    }

    /**
     * 导出支付订单列表
     */
    @PreAuthorize("@ss.hasPermi('order:payment:export')")
    @Log(title = "支付订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ChargingPayment chargingPayment)
    {
        List<ChargingPayment> list = chargingPaymentService.selectChargingPaymentList(chargingPayment);
        ExcelUtil<ChargingPayment> util = new ExcelUtil<ChargingPayment>(ChargingPayment.class);
        util.exportExcel(response, list, "支付订单数据");
    }

    /**
     * 获取支付订单详细信息
     */
    @PreAuthorize("@ss.hasPermi('order:payment:query')")
    @GetMapping(value = "/{sysId}")
    public AjaxResult getInfo(@PathVariable("sysId") Long sysId)
    {
        return success(chargingPaymentService.selectChargingPaymentBySysId(sysId));
    }

    /**
     * 新增支付订单
     */
    @PreAuthorize("@ss.hasPermi('order:payment:add')")
    @Log(title = "支付订单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ChargingPayment chargingPayment)
    {
        return toAjax(chargingPaymentService.insertChargingPayment(chargingPayment));
    }

    @PreAuthorize("@ss.hasPermi('order:payment:add')")
    @Log(title = "新增测试用例", businessType = BusinessType.INSERT)
    @PostMapping("/insert")
    public AjaxResult insert()
    {
        return toAjax(chargingPaymentService.insertChargingPayment(getRandomPayment()));
    }

    private ChargingPayment getRandomPayment(){
        double ramNum=Math.random();
        int num= (int) (ramNum*10);

        ChargingPayment payment=new ChargingPayment();
        payment.setUserId(UUID.randomUUID().toString());
        payment.setPaymentId(UUID.randomUUID().toString());

        payment.setOrderType(num%2+1);
        payment.setPaymentMode(num%2+1);
        payment.setOrderStatus(num%4+1);
        payment.setPaymentMethodTag(num%2+1);
        payment.setPaymentStatus(num%4+1);
        payment.setThirdPaymentId(UUID.randomUUID().toString());

        System.out.println(payment.toString());
        return payment;
    }

    /**
     * 修改支付订单
     */
    @PreAuthorize("@ss.hasPermi('order:payment:edit')")
    @Log(title = "支付订单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ChargingPayment chargingPayment)
    {
        return toAjax(chargingPaymentService.updateChargingPayment(chargingPayment));
    }

    /**
     * 删除支付订单
     */
    @PreAuthorize("@ss.hasPermi('order:payment:remove')")
    @Log(title = "支付订单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{sysIds}")
    public AjaxResult remove(@PathVariable Long[] sysIds)
    {
        return toAjax(chargingPaymentService.deleteChargingPaymentBySysIds(sysIds));
    }
}
