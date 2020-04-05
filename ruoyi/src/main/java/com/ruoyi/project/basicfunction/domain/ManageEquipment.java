package com.ruoyi.project.basicfunction.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 设备对象 manage_equipment
 * 
 * @author ruoyi
 * @date 2020-04-05
 */
@ApiModel("设备实体")
public class ManageEquipment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 设备ID */
    @ApiModelProperty("设备ID")
    private String meId;

    /** 设备位置 */
    @ApiModelProperty("设备位置")
    @Excel(name = "设备位置")
    private String mePosition;

    /** 设备状态 是否损坏 0完好 1损坏  */
    @ApiModelProperty("设备状态 是否损坏 0完好 1损坏")
    @Excel(name = "设备状态 是否损坏 0完好 1损坏 ")
    private Integer meState;

    /** 预约人数 */
    @ApiModelProperty("预约人数")
    @Excel(name = "预约人数")
    private Long maSize;

    /** 图片名称 */
    @ApiModelProperty("图片名称")
    @Excel(name = "图片名称")
    private String meImgName;

    /** 购入时间 */
    @ApiModelProperty("购入时间")
    @Excel(name = "购入时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date buyDate;

    /** 购入人 */
    @ApiModelProperty("购入人")
    @Excel(name = "购入人")
    private String buyName;

    /** 维修次数 */
    @ApiModelProperty("维修次数")
    @Excel(name = "维修次数")
    private Long pepairSize;

    public void setMeId(String meId) 
    {
        this.meId = meId;
    }

    public String getMeId() 
    {
        return meId;
    }
    public void setMePosition(String mePosition) 
    {
        this.mePosition = mePosition;
    }

    public String getMePosition() 
    {
        return mePosition;
    }
    public void setMeState(Integer meState) 
    {
        this.meState = meState;
    }

    public Integer getMeState() 
    {
        return meState;
    }
    public void setMaSize(Long maSize) 
    {
        this.maSize = maSize;
    }

    public Long getMaSize() 
    {
        return maSize;
    }
    public void setMeImgName(String meImgName) 
    {
        this.meImgName = meImgName;
    }

    public String getMeImgName() 
    {
        return meImgName;
    }
    public void setBuyDate(Date buyDate) 
    {
        this.buyDate = buyDate;
    }

    public Date getBuyDate() 
    {
        return buyDate;
    }
    public void setBuyName(String buyName) 
    {
        this.buyName = buyName;
    }

    public String getBuyName() 
    {
        return buyName;
    }
    public void setPepairSize(Long pepairSize) 
    {
        this.pepairSize = pepairSize;
    }

    public Long getPepairSize() 
    {
        return pepairSize;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("meId", getMeId())
            .append("mePosition", getMePosition())
            .append("meState", getMeState())
            .append("maSize", getMaSize())
            .append("meImgName", getMeImgName())
            .append("buyDate", getBuyDate())
            .append("buyName", getBuyName())
            .append("pepairSize", getPepairSize())
            .toString();
    }
}
