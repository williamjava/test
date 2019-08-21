package com.tufire.user.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.tufire.common.model.basic.BasicDto;
import com.tufire.common.util.ObjectUtil;
import com.tufire.common.util.StringUtils;
import com.tufire.user.client.ResourceClient;
import com.tufire.user.dal.entity.Resource;
import com.tufire.user.dal.mapper.ResourceMapper;
import com.tufire.user.dal.mapper.RoleResourceMapper;
import com.tufire.user.dto.ResourceDto;
import com.tufire.user.vo.seller.ResourceVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * Created by it001hyl on 2019-01-16.
 */
@RestController
@Slf4j
public class ResourceService extends ServiceImpl<ResourceMapper, Resource> implements ResourceClient {


    @Autowired
    private RoleResourceMapper roleResourceMapper;

    @Override
    public List<ResourceVo> findListByUserId(@RequestBody BasicDto basicDto) {
        List<Resource> resourceList = this.baseMapper.selectListAll(basicDto.getUserId(),Long.valueOf(basicDto.getId()),null);
        return ObjectUtil.sourceList2TargetList(resourceList,ResourceVo.class);
    }

    @Override
    public ResourceVo findById(@RequestParam(value = "id", required = false) Long id) {
        Resource resource = this.selectById(id);
        return ObjectUtil.source2Target(resource,ResourceVo.class);
    }

    /**
     * 菜单  相关逻辑 直接做物理删除
     * @param basicDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean deleteResourceById(@RequestBody BasicDto basicDto) {
        ResourceDto resourceDto = new ResourceDto();
        resourceDto.setParentId(Long.valueOf(basicDto.getId()));
        try {
            //先校验 是否还有子节点
            List<ResourceVo> resourceList = this.findAll(resourceDto);
            if (resourceList == null || resourceList.isEmpty()) {
                this.deleteById(Long.valueOf(basicDto.getId()));
                roleResourceMapper.deleteRoleResource(basicDto.getId());
            }
            return true;
        }catch (Exception e){
            log.error("deleteResourceById error: ",e);
            return false;
        }
    }

    @Override
    public List<ResourceVo> findAll(@RequestBody ResourceDto resourceDto) {
        Resource resource = ObjectUtil.source2Target(resourceDto, Resource.class);
        EntityWrapper<Resource> entityWrapper = new EntityWrapper<Resource>(resource);
        List<Resource> resourceList = this.selectList(entityWrapper);
        return ObjectUtil.sourceList2TargetList(resourceList, ResourceVo.class);
    }

    @Transactional
    @Override
    public Long  saveResource(@RequestBody ResourceDto resourceDto) {
        //简单再次检验参数
        if (StringUtils.isEmpty(resourceDto.getName()) || StringUtils.isEmpty(resourceDto.getPermission())) {
            return null;
        }
        try{
            Resource resource = ObjectUtil.source2Target(resourceDto, Resource.class);
            resource.setCreatedAt(new Date());
            resource.setCreatedBy(resourceDto.getUserId());
            resource.setDeleted(false);
            this.insert(resource);
            return resource.getId();
        }catch (Exception e){
            log.error("saveResource error: ",e);
            return null;
        }

    }

    @Transactional
    @Override
    public Boolean updateResource(@RequestBody ResourceDto resourceDto) {
        //简单再次检验参数
        if (StringUtils.isEmpty(resourceDto.getName()) ||
                StringUtils.isEmpty(resourceDto.getPermission()) || resourceDto.getId() == null) {
            return false;
        }
        Resource resource = ObjectUtil.source2Target(resourceDto, Resource.class);
        resource.setId(Long.valueOf(resourceDto.getId()));
        resource.setUpdatedBy(resourceDto.getUserId());
        resource.setUpdatedAt(new Date());
        return this.updateById(resource);
    }

    @Override
    public List<ResourceVo> findAllByRoleId(@RequestBody ResourceDto resourceDto) {
        List<Resource> resourceList = this.baseMapper.selectListAll(null,null,Long.valueOf(resourceDto.getId()));
        return ObjectUtil.sourceList2TargetList(resourceList,ResourceVo.class);
    }

    @Override
    public List<Long> findIdsByRoleId(@RequestParam(value = "roleId", required = false) Long roleId) {
        return roleResourceMapper.selectResourceIdsByRoleId(roleId);
    }
}
