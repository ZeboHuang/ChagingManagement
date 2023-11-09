package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Discount;

/**
 * 优惠券Service接口
 * 
 * @author ruoyi
 * @date 2023-01-15
 */
public interface IDiscountService 
{
    /**
     * 查询优惠券
     * 
     * @param discountId 优惠券主键
     * @return 优惠券
     */
    public Discount selectDiscountByDiscountId(Integer discountId);

    /**
     * 查询优惠券列表
     * 
     * @param discount 优惠券
     * @return 优惠券集合
     */
    public List<Discount> selectDiscountList(Discount discount);

    /**
     * 新增优惠券
     * 
     * @param discount 优惠券
     * @return 结果
     */
    public int insertDiscount(Discount discount);

    /**
     * 修改优惠券
     * 
     * @param discount 优惠券
     * @return 结果
     */
    public int updateDiscount(Discount discount);

    /**
     * 批量删除优惠券
     * 
     * @param discountIds 需要删除的优惠券主键集合
     * @return 结果
     */
    public int deleteDiscountByDiscountIds(Integer[] discountIds);

    /**
     * 删除优惠券信息
     * 
     * @param discountId 优惠券主键
     * @return 结果
     */
    public int deleteDiscountByDiscountId(Integer discountId);
}
