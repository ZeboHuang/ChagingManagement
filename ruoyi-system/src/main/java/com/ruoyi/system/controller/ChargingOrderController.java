package com.ruoyi.system.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.utils.uuid.UUID;
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
import com.ruoyi.system.domain.ChargingOrder;
import com.ruoyi.system.service.IChargingOrderService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 充电订单Controller
 *
 * @author ruoyi
 * @date 2023-01-14
 */
@RestController
@RequestMapping("/order/charging")
public class ChargingOrderController extends BaseController {
    @Autowired
    private IChargingOrderService chargingOrderService;


    /**
     * 查询充电订单列表
     */
    @PreAuthorize("@ss.hasPermi('order:charging:list')")
    @GetMapping("/list")
    public TableDataInfo list(ChargingOrder chargingOrder) {
        startPage();
        List<ChargingOrder> list = chargingOrderService.selectChargingOrderList(chargingOrder);
        return getDataTable(list);
    }

    /**
     * 导出充电订单列表
     */
    @PreAuthorize("@ss.hasPermi('order:charging:export')")
    @Log(title = "充电订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ChargingOrder chargingOrder) {
        List<ChargingOrder> list = chargingOrderService.selectChargingOrderList(chargingOrder);
        ExcelUtil<ChargingOrder> util = new ExcelUtil<ChargingOrder>(ChargingOrder.class);
        util.exportExcel(response, list, "充电订单数据");
    }

    /**
     * 获取充电订单详细信息
     */
    @PreAuthorize("@ss.hasPermi('order:charging:query')")
    @GetMapping(value = "/{sysId}")
    public AjaxResult getInfo(@PathVariable("sysId") Long sysId) {
        return success(chargingOrderService.selectChargingOrderBySysId(sysId));
    }

    /**
     * 新增充电订单
     */
    @PreAuthorize("@ss.hasPermi('order:charging:add')")
    @Log(title = "充电订单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ChargingOrder chargingOrder) {
        return toAjax(chargingOrderService.insertChargingOrder(chargingOrder));
    }

    /**
     * 充电订单插入测试
     */
    @PreAuthorize("@ss.hasPermi('order:charging:add')")
    @Log(title = "充电订单", businessType = BusinessType.INSERT)
    @PostMapping("/insert")
    public AjaxResult addTest() {
        return toAjax(chargingOrderService.insertChargingOrder(getRandomChargingOrder()));
    }

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmssS");
    private String[] cardIDs = {"6225 3652 7156 2822 858", "6222 6031 3000 0700 658", "6217 5820 0000 1498 278"};
    private String[] couponNames = {"春节优惠券", "老用户回馈", "新人优惠券", "中秋节优惠券"};
    private String[] stationNames={"凤城","武科大","光谷广场","楚河汉街"};

    private ChargingOrder getRandomChargingOrder() {
        double ramD = Math.random();
        int num = (int) (ramD * 10);
        System.out.println("num: "+num);
        Calendar calendar = new Calendar.Builder().build();
        calendar.setTimeInMillis(System.currentTimeMillis());

        ChargingOrder order = new ChargingOrder();

        order.setStationName(stationNames[num%4]);
        order.setChargingId(dateFormat.format(System.currentTimeMillis()));
        order.setPaymentId(dateFormat.format(System.currentTimeMillis()));
        order.setPileId(UUID.randomUUID().toString(true));
        order.setGunId(UUID.randomUUID().toString(true));
        order.setChargingCardId(cardIDs[num % 3]);
        order.setChargingMode(num % 3 + 1);
        order.setChargingStatus(num % 2 + 1);
        order.setTimeStart(calendar.getTime());
        calendar.add(Calendar.HOUR, num % 5 + 1);
        order.setTimeEnd(calendar.getTime());
        order.setFinishReason(num % 2 + 1);

        order.setUserId(UUID.randomUUID().toString());
        order.setUserType(num % 2 + 1);
        order.setVin(UUID.randomUUID().toString());

        order.setCouponName(couponNames[num % 4]);
        order.setCouponType(num % 3 + 1);
        order.setCouponQuota(num * 100);

        order.setPaymentChannelTag(num % 5 + 1);
        if (num == 5) {
            order.setPaymentChannel("其他渠道支付。");
        }
        //付款模式
        order.setPaymentMode(num%2+1);
        double chargingCapacity = (int)(ramD * 10000)/100;
        order.setChargingCapacity(chargingCapacity);
        order.setStationDiscount(0.9);
        order.setMemberDiscount(0.9);
        order.setMemberDiscountStatus(1);

        int timeType = num % 3 + 1;
        order.setTimeType(timeType);
        if (timeType == 1) {
            order.setElectricityPrice(80);
            order.setServePrice(120);
        } else if (timeType == 2) {
            order.setElectricityPrice(70);
            order.setServePrice(100);
        } else {
            order.setElectricityPrice(60);
            order.setElectricityPrice(60);
            order.setServePrice(90);
        }


        int duePay = (int) (chargingCapacity * order.getElectricityPrice() + order.getServePrice() * (num % 5 + 1));
        order.setDuePayment(duePay);
        order.setDiscountPayment(num * 100);
        int actualPay = duePay - num * 100;
        order.setActualPayment(actualPay);
        order.setAdvancePayment((int) (Math.ceil(duePay*1.0/100)*100));
        order.setRefundTag(num % 2 + 1);
        if (num % 2 + 1 == 1) {
            //无需退款
            order.setRefund(0);
        } else {
            order.setRefund(order.getAdvancePayment() - order.getActualPayment());
        }

        System.out.println(order.toString());

        return order;
    }

    /**
     * 修改充电订单
     */
    @PreAuthorize("@ss.hasPermi('order:charging:edit')")
    @Log(title = "充电订单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ChargingOrder chargingOrder) {
        return toAjax(chargingOrderService.updateChargingOrder(chargingOrder));
    }

    /**
     * 删除充电订单
     */
    @PreAuthorize("@ss.hasPermi('order:charging:remove')")
    @Log(title = "充电订单", businessType = BusinessType.DELETE)
    @DeleteMapping("/{sysIds}")
    public AjaxResult remove(@PathVariable Long[] sysIds) {
        return toAjax(chargingOrderService.deleteChargingOrderBySysIds(sysIds));
    }
}
