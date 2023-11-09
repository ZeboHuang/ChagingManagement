package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.ChargingGunMapper;
import com.ruoyi.system.domain.ChargingGun;
import com.ruoyi.system.service.IChargingGunService;

/**
 * 充电枪Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-01-14
 */
@Service
public class ChargingGunServiceImpl implements IChargingGunService 
{
    @Autowired
    private ChargingGunMapper chargingGunMapper;

    /**
     * 查询充电枪
     * 
     * @param id 充电枪主键
     * @return 充电枪
     */
    @Override
    public ChargingGun selectChargingGunById(Long id)
    {
        return chargingGunMapper.selectChargingGunById(id);
    }

    /**
     * 查询充电枪列表
     * 
     * @param chargingGun 充电枪
     * @return 充电枪
     */
    @Override
    public List<ChargingGun> selectChargingGunList(ChargingGun chargingGun)
    {
        return chargingGunMapper.selectChargingGunList(chargingGun);
    }

    /**
     * 新增充电枪
     * 
     * @param chargingGun 充电枪
     * @return 结果
     */
    @Override
    public int insertChargingGun(ChargingGun chargingGun)
    {
        chargingGun.setCreateTime(DateUtils.getNowDate());
        return chargingGunMapper.insertChargingGun(chargingGun);
    }

    /**
     * 修改充电枪
     * 
     * @param chargingGun 充电枪
     * @return 结果
     */
    @Override
    public int updateChargingGun(ChargingGun chargingGun)
    {
        chargingGun.setUpdateTime(DateUtils.getNowDate());
        return chargingGunMapper.updateChargingGun(chargingGun);
    }

    /**
     * 批量删除充电枪
     * 
     * @param ids 需要删除的充电枪主键
     * @return 结果
     */
    @Override
    public int deleteChargingGunByIds(Long[] ids)
    {
        return chargingGunMapper.deleteChargingGunByIds(ids);
    }

    /**
     * 删除充电枪信息
     * 
     * @param id 充电枪主键
     * @return 结果
     */
    @Override
    public int deleteChargingGunById(Long id)
    {
        return chargingGunMapper.deleteChargingGunById(id);
    }
}
