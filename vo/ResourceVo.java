package com.tufire.user.vo.seller;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by it001hyl on 2019-01-16.
 */
@Data
@ApiModel(value = "资源对象信息")
public class ResourceVo implements Serializable {

    private Long id;

    @ApiModelProperty(value = "父级ID")
    private Long parentId;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "连接地址")
    private String href;

    @ApiModelProperty(value = "权限标识")
    private String permission;

    @ApiModelProperty(value = "是否显示")
    private Boolean show;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "排序")
    private Integer sort;
}
