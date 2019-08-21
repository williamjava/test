package com.tufire.user.client;

import com.tufire.user.dto.RoleResourceDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Created by it001hyl on 2019-03-05.
 */
@FeignClient(value = "huotu-user-service", contextId = "roleResource")
public interface RoleResourceClient {

    /**
     * 编辑角色添加权限/资源
     * @param roleResourceDto
     * @return
     */
    @PostMapping("/role/save_role_resource")
    Boolean saveRoleResource(@RequestBody RoleResourceDto roleResourceDto);

}
