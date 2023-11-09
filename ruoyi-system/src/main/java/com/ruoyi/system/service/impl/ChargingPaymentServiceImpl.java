package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.ChargingPaymentMapper;
import com.ruoyi.system.domain.ChargingPayment;
import com.ruoyi.system.service.IChargingPaymentService;

/**
 * 支付订单Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-01-12
 */
@Service
public class ChargingPaymentServiceImpl implements IChargingPaymentService 
{
    @Autowired
    private ChargingPaymentMapper chargingPaymentMapper;

    /**
     * 查询支付订单
     * 
     * @param sysId 支付订单主键
     * @return 支付订单
     */
    @Override
    public ChargingPayment selectChargingPaymentBySysId(Long sysId)
    {
        return chargingPaymentMapper.selectChargingPaymentBySysId(sysId);
    }

    /**
     * 查询支付订单列表
     * 
     * @param chargingPayment 支付订单
     * @return 支付订单
     */
    @Override
    public List<ChargingPayment> selectChargingPaymentList(ChargingPayment chargingPayment)
    {
        return chargingPaymentMapper.selectChargingPaymentList(chargingPayment);
    }

    /**
     * 新增支付订单
     * 
     * @param chargingPayment 支付订单
     * @return 结果
     */
    @Override
    public int insertChargingPayment(ChargingPayment chargingPayment)
    {
        chargingPayment.setCreateTime(DateUtils.getNowDate());
        return chargingPaymentMapper.insertChargingPayment(chargingPayment);
    }

    /**
     * 修改支付订单
     * 
     * @param chargingPayment 支付订单
     * @return 结果
     */
    @Override
    public int updateChargingPayment(ChargingPayment chargingPayment)
    {
        chargingPayment.setUpdateTime(DateUtils.getNowDate());
        return chargingPaymentMapper.updateChargingPayment(chargingPayment);
    }

    /**
     * 批量删除支付订单
     * 
     * @param sysIds 需要删除的支付订单主键
     * @return 结果
     */
    @Override
    public int deleteChargingPaymentBySysIds(Long[] sysIds)
    {
        return chargingPaymentMapper.deleteChargingPaymentBySysIds(sysIds);
    }

    /**
     * 删除支付订单信息
     * 
     * @param sysId 支付订单主键
     * @return 结果
     */
    @Override
    public int deleteChargingPaymentBySysId(Long sysId)
    {
        return chargingPaymentMapper.deleteChargingPaymentBySysId(sysId);
    }
}
