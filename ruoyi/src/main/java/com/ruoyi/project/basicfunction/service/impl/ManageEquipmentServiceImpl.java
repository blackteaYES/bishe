package com.ruoyi.project.basicfunction.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.basicfunction.mapper.ManageEquipmentMapper;
import com.ruoyi.project.basicfunction.domain.ManageEquipment;
import com.ruoyi.project.basicfunction.service.IManageEquipmentService;

/**
 * 设备Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-04-05
 */
@Service
public class ManageEquipmentServiceImpl implements IManageEquipmentService 
{
    @Autowired
    private ManageEquipmentMapper manageEquipmentMapper;

    /**
     * 查询设备
     * 
     * @param meId 设备ID
     * @return 设备
     */
    @Override
    public ManageEquipment selectManageEquipmentById(String meId)
    {
        return manageEquipmentMapper.selectManageEquipmentById(meId);
    }

    /**
     * 查询设备列表
     * 
     * @param manageEquipment 设备
     * @return 设备
     */
    @Override
    public List<ManageEquipment> selectManageEquipmentList(ManageEquipment manageEquipment)
    {
        return manageEquipmentMapper.selectManageEquipmentList(manageEquipment);
    }

    /**
     * 新增设备
     * 
     * @param manageEquipment 设备
     * @return 结果
     */
    @Override
    public int insertManageEquipment(ManageEquipment manageEquipment)
    {
        return manageEquipmentMapper.insertManageEquipment(manageEquipment);
    }

    /**
     * 修改设备
     * 
     * @param manageEquipment 设备
     * @return 结果
     */
    @Override
    public int updateManageEquipment(ManageEquipment manageEquipment)
    {
        return manageEquipmentMapper.updateManageEquipment(manageEquipment);
    }

    /**
     * 批量删除设备
     * 
     * @param meIds 需要删除的设备ID
     * @return 结果
     */
    @Override
    public int deleteManageEquipmentByIds(String[] meIds)
    {
        return manageEquipmentMapper.deleteManageEquipmentByIds(meIds);
    }

    /**
     * 删除设备信息
     * 
     * @param meId 设备ID
     * @return 结果
     */
    @Override
    public int deleteManageEquipmentById(String meId)
    {
        return manageEquipmentMapper.deleteManageEquipmentById(meId);
    }
}
