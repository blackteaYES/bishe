package com.ruoyi.project.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 设备类别对象 equipment_name
 * 
 * @author ruoyi
 * @date 2020-04-08
 */
public class EquipmentName extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 设备类别 */
    private Long enId;

    /** 设备名称 */
    @Excel(name = "设备名称")
    private String enName;

    public void setEnId(Long enId) 
    {
        this.enId = enId;
    }

    public Long getEnId() 
    {
        return enId;
    }
    public void setEnName(String enName) 
    {
        this.enName = enName;
    }

    public String getEnName() 
    {
        return enName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("enId", getEnId())
            .append("enName", getEnName())
            .toString();
    }
}
