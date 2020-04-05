package com.ruoyi.project.basicfunction.controller;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.basicfunction.domain.ManageEquipment;
import com.ruoyi.project.basicfunction.service.IManageEquipmentService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

import javax.validation.Valid;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 设备Controller
 * 
 * @author ruoyi
 * @date 2020-04-05
 */
@RestController
@RequestMapping("/basicfunction/equipment")
@Api("设备")
public class ManageEquipmentController extends BaseController
{
    @Autowired
    private IManageEquipmentService manageEquipmentService;

    /**
     * 查询设备列表
     */
    @PreAuthorize("@ss.hasPermi('basicfunction:equipment:list')")
    @GetMapping("/list")
    @ApiOperation("获取设备列表")
    public TableDataInfo list()
    {
        startPage();
        ManageEquipment manageEquipment = new ManageEquipment();
        List<ManageEquipment> list = manageEquipmentService.selectManageEquipmentList(manageEquipment);
        return getDataTable(list);
    }

    /**
     * 导出设备列表
     */
    @PreAuthorize("@ss.hasPermi('basicfunction:equipment:export')")
    @Log(title = "设备", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ManageEquipment manageEquipment)
    {
        List<ManageEquipment> list = manageEquipmentService.selectManageEquipmentList(manageEquipment);
        ExcelUtil<ManageEquipment> util = new ExcelUtil<ManageEquipment>(ManageEquipment.class);
        return util.exportExcel(list, "equipment");
    }

    /**
     * 获取设备详细信息
     */
    @ApiOperation("获取设备详细")
    @ApiImplicitParam(name = "meId", value = "设备ID", required = true, dataType = "String", paramType = "path")
    @PreAuthorize("@ss.hasPermi('basicfunction:equipment:query')")
    @GetMapping(value = "/{meId}")
    public AjaxResult getInfo(@PathVariable("meId") String meId)
    {
        return AjaxResult.success(manageEquipmentService.selectManageEquipmentById(meId));
    }

    /**
     * 新增设备
     */
    @ApiOperation("新增设备")
    @ApiImplicitParam(name = "manageEquipment", value = "新增设备信息", dataType = "ManageEquipment")
    @PreAuthorize("@ss.hasPermi('basicfunction:equipment:add')")
    @Log(title = "设备", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ManageEquipment manageEquipment)
    {
        return toAjax(manageEquipmentService.insertManageEquipment(manageEquipment));
    }

    /**
     * 修改设备
     */
    @ApiOperation("更新设备信息")
    @ApiImplicitParam(name = "manageEquipment", value = "更新设备信息", dataType = "ManageEquipment")
    @PreAuthorize("@ss.hasPermi('basicfunction:equipment:edit')")
    @Log(title = "设备", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ManageEquipment manageEquipment)
    {
        return toAjax(manageEquipmentService.updateManageEquipment(manageEquipment));
    }

    /**
     * 删除设备
     */
    @ApiOperation("删除设备组信息")
    @ApiImplicitParam(name = "meIds", value = "设备数组", required = true, dataType = "String[]", paramType = "path")
    @PreAuthorize("@ss.hasPermi('basicfunction:equipment:remove')")
    @Log(title = "设备", businessType = BusinessType.DELETE)
	@DeleteMapping("/{meIds}")
    public AjaxResult remove(@PathVariable String[] meIds)
    {
        return toAjax(manageEquipmentService.deleteManageEquipmentByIds(meIds));
    }
}
