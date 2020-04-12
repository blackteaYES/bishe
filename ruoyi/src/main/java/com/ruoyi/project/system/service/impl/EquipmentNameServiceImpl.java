package com.ruoyi.project.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.EquipmentNameMapper;
import com.ruoyi.project.system.domain.EquipmentName;
import com.ruoyi.project.system.service.IEquipmentNameService;

/**
 * 设备类别Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-04-08
 */
@Service
public class EquipmentNameServiceImpl implements IEquipmentNameService 
{
    @Autowired
    private EquipmentNameMapper equipmentNameMapper;

    /**
     * 查询设备类别
     * 
     * @param enId 设备类别ID
     * @return 设备类别
     */
    @Override
    public EquipmentName selectEquipmentNameById(Long enId)
    {
        return equipmentNameMapper.selectEquipmentNameById(enId);
    }

    /**
     * 查询设备类别列表
     * 
     * @param equipmentName 设备类别
     * @return 设备类别
     */
    @Override
    public List<EquipmentName> selectEquipmentNameList(EquipmentName equipmentName)
    {
        return equipmentNameMapper.selectEquipmentNameList(equipmentName);
    }

    /**
     * 新增设备类别
     * 
     * @param equipmentName 设备类别
     * @return 结果
     */
    @Override
    public int insertEquipmentName(EquipmentName equipmentName)
    {
        return equipmentNameMapper.insertEquipmentName(equipmentName);
    }

    /**
     * 修改设备类别
     * 
     * @param equipmentName 设备类别
     * @return 结果
     */
    @Override
    public int updateEquipmentName(EquipmentName equipmentName)
    {
        return equipmentNameMapper.updateEquipmentName(equipmentName);
    }

    /**
     * 批量删除设备类别
     * 
     * @param enIds 需要删除的设备类别ID
     * @return 结果
     */
    @Override
    public int deleteEquipmentNameByIds(Long[] enIds)
    {
        return equipmentNameMapper.deleteEquipmentNameByIds(enIds);
    }

    /**
     * 删除设备类别信息
     * 
     * @param enId 设备类别ID
     * @return 结果
     */
    @Override
    public int deleteEquipmentNameById(Long enId)
    {
        return equipmentNameMapper.deleteEquipmentNameById(enId);
    }
}
