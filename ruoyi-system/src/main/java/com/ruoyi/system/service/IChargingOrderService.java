package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.ChargingOrder;

/**
 * 充电订单Service接口
 * 
 * @author ruoyi
 * @date 2023-01-14
 */
public interface IChargingOrderService 
{
    /**
     * 查询充电订单
     * 
     * @param sysId 充电订单主键
     * @return 充电订单
     */
    public ChargingOrder selectChargingOrderBySysId(Long sysId);

    /**
     * 查询充电订单列表
     * 
     * @param chargingOrder 充电订单
     * @return 充电订单集合
     */
    public List<ChargingOrder> selectChargingOrderList(ChargingOrder chargingOrder);

    /**
     * 新增充电订单
     * 
     * @param chargingOrder 充电订单
     * @return 结果
     */
    public int insertChargingOrder(ChargingOrder chargingOrder);

    /**
     * 修改充电订单
     * 
     * @param chargingOrder 充电订单
     * @return 结果
     */
    public int updateChargingOrder(ChargingOrder chargingOrder);

    /**
     * 批量删除充电订单
     * 
     * @param sysIds 需要删除的充电订单主键集合
     * @return 结果
     */
    public int deleteChargingOrderBySysIds(Long[] sysIds);

    /**
     * 删除充电订单信息
     * 
     * @param sysId 充电订单主键
     * @return 结果
     */
    public int deleteChargingOrderBySysId(Long sysId);
}
