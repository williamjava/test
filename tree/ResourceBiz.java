package com.tufire.seller.biz.user;

import com.tufire.common.model.basic.BasicDto;
import com.tufire.common.util.Result;
import com.tufire.common.constants.SysConstants;
import com.tufire.seller.util.ResourceTreeUtils;
import com.tufire.user.client.ResourceClient;
import com.tufire.user.dto.ResourceDto;
import com.tufire.user.vo.seller.ResourceVo;
import com.tufire.user.vo.seller.SellerUserVo;
import com.tufire.user.vo.seller.TreeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.tufire.common.util.ValidateUtils.NOT_BLANK;
import static com.tufire.common.util.ValidateUtils.NOT_NULL;
import static com.tufire.common.util.ValidateUtils.validate;

/**
 * Created by it001hyl on 2019-01-22.
 */
@Component
public class ResourceBiz {

    @Autowired
    private ResourceClient resourceClient;


    /**
     * 组建菜单树
     * @param sellerUserVo
     * @return
     */
    public List<TreeVo> buildTreeVo(SellerUserVo sellerUserVo){

        //从api获取  用户所有资源  在根据应用层需求转成自己的格式
        List<ResourceVo> resourceVoList = resourceClient.findListByUserId(
                BasicDto.builder().id(SysConstants.APP_ID).userId(sellerUserVo.getUserId()).build());
        return ResourceTreeUtils.formatResource(resourceVoList,true);
    }

    /**
     * 添加  资源
     * @param resourceDto
     * @return
     */
    public Result<String> saveResource(ResourceDto resourceDto,SellerUserVo sellerUserVo) {
        validate(resourceDto.getName(),NOT_BLANK,"请填写资源名称");
        validate(resourceDto.getPermission(),NOT_BLANK,"请填写权限名称");
        resourceDto.setUserId(sellerUserVo.getUserId());
        if (resourceClient.saveResource(resourceDto)!=null){
            return Result.generateSuccessWithMsg("添加资源成功");
        }
        return Result.generateFailWithMsg("添加资源失败");
    }

    /**
     * 更新   资源
     * @param resourceDto
     * @param sellerUserVo
     * @return
     */
    public Result<String> updateResource(ResourceDto resourceDto, SellerUserVo sellerUserVo) {
        validate(resourceDto.getName(),NOT_BLANK,"请填写资源名称");
        validate(resourceDto.getPermission(),NOT_BLANK,"请填写权限名称");
        validate(resourceDto.getId(),NOT_NULL,"数据参数异常");
        //置入操作人
        resourceDto.setUserId(sellerUserVo.getUserId());
        if (resourceClient.updateResource(resourceDto)){
            return Result.generateSuccessWithMsg("更新资源成功");
        }
        return Result.generateFailWithMsg("更新资源失败");
    }

    /**
     * 获取所有资源
     * @return
     */
    public List<TreeVo> buildTreeVo() {
        List<ResourceVo> resourceVoList = resourceClient.findAll(new ResourceDto());
        return ResourceTreeUtils.formatResource(resourceVoList,false);
    }

    public List<TreeVo> buildTreeVoByRoleId(String id) {
        ResourceDto resourceDto = new ResourceDto();
        resourceDto.setId(id);
        List<ResourceVo> resourceVoList = resourceClient.findAllByRoleId(resourceDto);
        return ResourceTreeUtils.formatResource(resourceVoList,false);
    }

    public List<Long> findResourceIdsByRoleId(Long roleId){
        return resourceClient.findIdsByRoleId(roleId);
    }
}
