package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.ChargingGun;

/**
 * 充电枪Mapper接口
 * 
 * @author ruoyi
 * @date 2023-01-14
 */
public interface ChargingGunMapper 
{
    /**
     * 查询充电枪
     * 
     * @param id 充电枪主键
     * @return 充电枪
     */
    public ChargingGun selectChargingGunById(Long id);

    /**
     * 查询充电枪列表
     * 
     * @param chargingGun 充电枪
     * @return 充电枪集合
     */
    public List<ChargingGun> selectChargingGunList(ChargingGun chargingGun);

    /**
     * 新增充电枪
     * 
     * @param chargingGun 充电枪
     * @return 结果
     */
    public int insertChargingGun(ChargingGun chargingGun);

    /**
     * 修改充电枪
     * 
     * @param chargingGun 充电枪
     * @return 结果
     */
    public int updateChargingGun(ChargingGun chargingGun);

    /**
     * 删除充电枪
     * 
     * @param id 充电枪主键
     * @return 结果
     */
    public int deleteChargingGunById(Long id);

    /**
     * 批量删除充电枪
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteChargingGunByIds(Long[] ids);
}
