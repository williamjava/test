package com.tufire.seller.api.common;

import com.tufire.common.model.basic.BasicDto;
import com.tufire.common.util.ObjectUtil;
import com.tufire.common.util.Result;
import com.tufire.seller.api.basic.BasicApi;
import com.tufire.seller.biz.user.ResourceBiz;
import com.tufire.seller.form.basic.BasicForm;
import com.tufire.seller.form.common.CommonForm;
import com.tufire.seller.form.user.ResourceFrom;
import com.tufire.user.client.ResourceClient;
import com.tufire.user.dto.ResourceDto;
import com.tufire.user.vo.seller.ResourceVo;
import com.tufire.user.vo.seller.TreeVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by it001hyl on 2019-01-17.
 */
@Api(value = "菜单相关接口", tags = "菜单相关接口 （HYL-1）")
@RestController
@RequestMapping("/authc/resource/")
@Slf4j
public class ResourceApi  extends BasicApi {

    @Autowired
    private ResourceBiz resourceBiz;

    @Autowired
    private ResourceClient resourceClient;

    @ApiOperation(value = "获取菜单资源", notes = "根据用户id获取用户所有菜单资源",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("sys:resource:view")
    @RequestMapping(value = "find_all_by_user_id",method = RequestMethod.POST)
    public Result<List<TreeVo>> findAllByUserId(@ModelAttribute BasicForm basicForm){
      List<TreeVo> treeVoList = resourceBiz.buildTreeVo(this.getUser());
      return Result.generateSuccess(treeVoList);
    }

    @ApiOperation(value = "获取资源", notes = "获取所有资源",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("sys:resource:view")
    @RequestMapping(value = "find_all",method = RequestMethod.POST)
    public Result<List<TreeVo>> findAll(@ModelAttribute BasicForm basicForm){
        List<TreeVo> treeVoList = resourceBiz.buildTreeVo();
        return Result.generateSuccess(treeVoList);
    }

    @ApiOperation(value = "获取单个资源", notes = "根据资源id获取资源",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("sys:resource:view")
    @RequestMapping(value = "view",method = RequestMethod.POST)
    public Result<ResourceVo> findById(@ModelAttribute CommonForm commenForm){
        return Result.generateSuccess(resourceClient.findById(Long.valueOf(commenForm.getId())));
    }

    @ApiOperation(value = "删除资源", notes = "根据菜单id删除资源" ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("sys:resource:edit")
    @RequestMapping(value = "delete",method = RequestMethod.POST)
    public Result<String> deleteById(@ModelAttribute CommonForm commenForm){
      BasicDto basicDto = ObjectUtil.source2Target(commenForm,BasicDto.class);
      basicDto.setUserId(this.getUser().getId());
      if(resourceClient.deleteResourceById(basicDto)){
          return Result.generateSuccessWithMsg("删除成功");
      }
      return Result.generateFailWithMsg("删除失败，请先删除子节点");
    }
    @ApiOperation(value = "添加资源", notes = "添加资源" ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("sys:resource:edit")
    @RequestMapping(value = "save",method = RequestMethod.POST)
    public Result<String> save(@ModelAttribute ResourceFrom resourceFrom){
        ResourceDto resourceDto = ObjectUtil.source2Target(resourceFrom,ResourceDto.class);
        return  resourceBiz.saveResource(resourceDto,this.getUser());
    }

    @ApiOperation(value = "更新资源", notes = "更新资源" ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("sys:resource:edit")
    @RequestMapping(value = "update",method = RequestMethod.POST)
    public Result<String> update(@ModelAttribute ResourceFrom resourceFrom){
        ResourceDto resourceDto = ObjectUtil.source2Target(resourceFrom,ResourceDto.class);
        return  resourceBiz.updateResource(resourceDto,this.getUser());
    }

    @ApiOperation(value = "查询角色拥有的资源", notes = "查询角色拥有的资源",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions(value = "sys:role:view")
    @RequestMapping(value = "find_role_resource",method = RequestMethod.POST)
    public Result<List<TreeVo>> findRoleResource(@ModelAttribute CommonForm commonForm){
        List<TreeVo> treeVoList = resourceBiz.buildTreeVoByRoleId(commonForm.getId());
        return Result.generateSuccess(treeVoList);
    }

    @ApiOperation(value = "根据角色ID查询资源ID列表", notes = "根据角色ID查询资源ID列表",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions(value = "sys:resource:view")
    @RequestMapping(value = "find_resource_ids_by_role_id",method = RequestMethod.POST)
    public Result<List<Long>> findResourceIdsByRoleId(@ModelAttribute CommonForm form){
        return Result.generateSuccess(resourceBiz.findResourceIdsByRoleId(Long.valueOf(form.getId())));
    }
}
