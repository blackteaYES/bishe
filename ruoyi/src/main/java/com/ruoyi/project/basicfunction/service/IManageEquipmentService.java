package com.ruoyi.project.basicfunction.service;

import java.util.List;
import com.ruoyi.project.basicfunction.domain.ManageEquipment;

/**
 * 设备Service接口
 * 
 * @author ruoyi
 * @date 2020-04-05
 */
public interface IManageEquipmentService 
{
    /**
     * 查询设备
     * 
     * @param meId 设备ID
     * @return 设备
     */
    public ManageEquipment selectManageEquipmentById(String meId);

    /**
     * 查询设备列表
     * 
     * @param manageEquipment 设备
     * @return 设备集合
     */
    public List<ManageEquipment> selectManageEquipmentList(ManageEquipment manageEquipment);

    /**
     * 新增设备
     * 
     * @param manageEquipment 设备
     * @return 结果
     */
    public int insertManageEquipment(ManageEquipment manageEquipment);

    /**
     * 修改设备
     * 
     * @param manageEquipment 设备
     * @return 结果
     */
    public int updateManageEquipment(ManageEquipment manageEquipment);

    /**
     * 批量删除设备
     * 
     * @param meIds 需要删除的设备ID
     * @return 结果
     */
    public int deleteManageEquipmentByIds(String[] meIds);

    /**
     * 删除设备信息
     * 
     * @param meId 设备ID
     * @return 结果
     */
    public int deleteManageEquipmentById(String meId);
}
