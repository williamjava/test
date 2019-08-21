package com.tufire.user.vo.seller;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author it001hyl
 * @date 2019-01-22
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "树形结构对象信息")
public class TreeVo implements Serializable {
    private static final long serialVersionUID = -1462365751615946142L;

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value ="父节点ID")
    private Long parentId;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "URL")
    private String href;

    @ApiModelProperty(value = "子节点列表")
    private List<TreeVo> children;
}
