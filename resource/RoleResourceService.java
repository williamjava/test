package com.tufire.user.service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.tufire.user.client.RoleResourceClient;
import com.tufire.user.dal.entity.RoleResource;
import com.tufire.user.dal.mapper.RoleResourceMapper;
import com.tufire.user.dto.RoleResourceDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by it001hyl on 2019-03-05.
 */
@RestController
@Slf4j
public class RoleResourceService extends ServiceImpl<RoleResourceMapper,RoleResource> implements RoleResourceClient {

    @Transactional
    @Override
    public Boolean saveRoleResource(@RequestBody RoleResourceDto roleResourceDto) {
        try {
            //删除原有的  添加当前的
            this.baseMapper.deleteByRoleId(Long.valueOf(roleResourceDto.getId()));
            List<RoleResource> roleResourceList = Lists.newArrayList();
            Long roleId  =Long.valueOf(roleResourceDto.getId());
            roleResourceDto.getResourceIds().forEach(v->{
                RoleResource roleResource = new RoleResource();
                roleResource.setResourceId(v);
                roleResource.setRoleId(roleId);
                roleResourceList.add(roleResource);
            });
           return this.insertOrUpdateBatch(roleResourceList);
        }catch (Exception e){
            log.error("saveRoleResource error: ",e);
            return false;
        }
    }


}
