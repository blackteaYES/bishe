package com.ruoyi.project.system.controller;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.system.domain.EquipmentName;
import com.ruoyi.project.system.service.IEquipmentNameService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 设备类别Controller
 * 
 * @author ruoyi
 * @date 2020-04-08
 */
@RestController
@RequestMapping("/system/name")
public class EquipmentNameController extends BaseController
{
    @Autowired
    private IEquipmentNameService equipmentNameService;

    /**
     * 查询设备类别列表
     */
    @PreAuthorize("@ss.hasPermi('system:name:list')")
    @GetMapping("/list")
    public TableDataInfo list(EquipmentName equipmentName)
    {
        startPage();
        List<EquipmentName> list = equipmentNameService.selectEquipmentNameList(equipmentName);
        return getDataTable(list);
    }

    /**
     * 导出设备类别列表
     */
    @PreAuthorize("@ss.hasPermi('system:name:export')")
    @Log(title = "设备类别", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(EquipmentName equipmentName)
    {
        List<EquipmentName> list = equipmentNameService.selectEquipmentNameList(equipmentName);
        ExcelUtil<EquipmentName> util = new ExcelUtil<EquipmentName>(EquipmentName.class);
        return util.exportExcel(list, "name");
    }

    /**
     * 获取设备类别详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:name:query')")
    @GetMapping(value = "/{enId}")
    public AjaxResult getInfo(@PathVariable("enId") Long enId)
    {
        return AjaxResult.success(equipmentNameService.selectEquipmentNameById(enId));
    }

    /**
     * 新增设备类别
     */
    @PreAuthorize("@ss.hasPermi('system:name:add')")
    @Log(title = "设备类别", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EquipmentName equipmentName)
    {
        return toAjax(equipmentNameService.insertEquipmentName(equipmentName));
    }

    /**
     * 修改设备类别
     */
    @PreAuthorize("@ss.hasPermi('system:name:edit')")
    @Log(title = "设备类别", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EquipmentName equipmentName)
    {
        return toAjax(equipmentNameService.updateEquipmentName(equipmentName));
    }

    /**
     * 删除设备类别
     */
    @PreAuthorize("@ss.hasPermi('system:name:remove')")
    @Log(title = "设备类别", businessType = BusinessType.DELETE)
	@DeleteMapping("/{enIds}")
    public AjaxResult remove(@PathVariable Long[] enIds)
    {
        return toAjax(equipmentNameService.deleteEquipmentNameByIds(enIds));
    }
}
