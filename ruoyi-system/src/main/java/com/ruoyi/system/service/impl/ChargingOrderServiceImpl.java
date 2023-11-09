package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.ChargingOrderMapper;
import com.ruoyi.system.domain.ChargingOrder;
import com.ruoyi.system.service.IChargingOrderService;

/**
 * 充电订单Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-01-14
 */
@Service
public class ChargingOrderServiceImpl implements IChargingOrderService 
{
    @Autowired
    private ChargingOrderMapper chargingOrderMapper;

    /**
     * 查询充电订单
     * 
     * @param sysId 充电订单主键
     * @return 充电订单
     */
    @Override
    public ChargingOrder selectChargingOrderBySysId(Long sysId)
    {
        return chargingOrderMapper.selectChargingOrderBySysId(sysId);
    }

    /**
     * 查询充电订单列表
     * 
     * @param chargingOrder 充电订单
     * @return 充电订单
     */
    @Override
    public List<ChargingOrder> selectChargingOrderList(ChargingOrder chargingOrder)
    {
        return chargingOrderMapper.selectChargingOrderList(chargingOrder);
    }

    /**
     * 新增充电订单
     * 
     * @param chargingOrder 充电订单
     * @return 结果
     */
    @Override
    public int insertChargingOrder(ChargingOrder chargingOrder)
    {
        chargingOrder.setCreateTime(DateUtils.getNowDate());
        return chargingOrderMapper.insertChargingOrder(chargingOrder);
    }

    /**
     * 修改充电订单
     * 
     * @param chargingOrder 充电订单
     * @return 结果
     */
    @Override
    public int updateChargingOrder(ChargingOrder chargingOrder)
    {
        chargingOrder.setUpdateTime(DateUtils.getNowDate());
        return chargingOrderMapper.updateChargingOrder(chargingOrder);
    }

    /**
     * 批量删除充电订单
     * 
     * @param sysIds 需要删除的充电订单主键
     * @return 结果
     */
    @Override
    public int deleteChargingOrderBySysIds(Long[] sysIds)
    {
        return chargingOrderMapper.deleteChargingOrderBySysIds(sysIds);
    }

    /**
     * 删除充电订单信息
     * 
     * @param sysId 充电订单主键
     * @return 结果
     */
    @Override
    public int deleteChargingOrderBySysId(Long sysId)
    {
        return chargingOrderMapper.deleteChargingOrderBySysId(sysId);
    }
}
