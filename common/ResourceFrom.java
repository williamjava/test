package com.tufire.seller.form.user;

import com.tufire.seller.form.basic.BasicForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by it001hyl on 2019-02-28.
 */
@Data
@ApiModel
public class ResourceFrom  extends BasicForm {

    @ApiModelProperty(value = "id参数（更新时必传）",required = false)
    private  String id;

    @ApiModelProperty(value = "上级id",required = false)
    private  Long parentId;

    @ApiModelProperty(value = "名称",required = true)
    private String name;

    @ApiModelProperty(value = "链接地址",required = false)
    private String href;

    @ApiModelProperty(value = "权限",required = true)
    private String permission;

    @ApiModelProperty(value = "是否显示",required = true)
    private Boolean show;

    @ApiModelProperty(value = "图标",required = false, hidden = true)
    private String icon;

    @ApiModelProperty(value = "排序字段",required = true)
    private Integer sort;

}
