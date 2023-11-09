package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.DiscountMapper;
import com.ruoyi.system.domain.Discount;
import com.ruoyi.system.service.IDiscountService;

/**
 * 优惠券Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-01-15
 */
@Service
public class DiscountServiceImpl implements IDiscountService 
{
    @Autowired
    private DiscountMapper discountMapper;

    /**
     * 查询优惠券
     * 
     * @param discountId 优惠券主键
     * @return 优惠券
     */
    @Override
    public Discount selectDiscountByDiscountId(Integer discountId)
    {
        return discountMapper.selectDiscountByDiscountId(discountId);
    }

    /**
     * 查询优惠券列表
     * 
     * @param discount 优惠券
     * @return 优惠券
     */
    @Override
    public List<Discount> selectDiscountList(Discount discount)
    {
        return discountMapper.selectDiscountList(discount);
    }

    /**
     * 新增优惠券
     * 
     * @param discount 优惠券
     * @return 结果
     */
    @Override
    public int insertDiscount(Discount discount)
    {
        return discountMapper.insertDiscount(discount);
    }

    /**
     * 修改优惠券
     * 
     * @param discount 优惠券
     * @return 结果
     */
    @Override
    public int updateDiscount(Discount discount)
    {
        return discountMapper.updateDiscount(discount);
    }

    /**
     * 批量删除优惠券
     * 
     * @param discountIds 需要删除的优惠券主键
     * @return 结果
     */
    @Override
    public int deleteDiscountByDiscountIds(Integer[] discountIds)
    {
        return discountMapper.deleteDiscountByDiscountIds(discountIds);
    }

    /**
     * 删除优惠券信息
     * 
     * @param discountId 优惠券主键
     * @return 结果
     */
    @Override
    public int deleteDiscountByDiscountId(Integer discountId)
    {
        return discountMapper.deleteDiscountByDiscountId(discountId);
    }
}
