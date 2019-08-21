package com.tufire.user.client;


import com.tufire.common.model.basic.BasicDto;
import com.tufire.user.dto.ResourceDto;
import com.tufire.user.vo.seller.ResourceVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by it001hyl on 2019-01-16.
 */
@FeignClient(value = "huotu-user-service", contextId = "resource")
public interface ResourceClient {

    /**
     * 根据  用户id 和 appId 获取 用户的 权限
     * @param basicDto
     * @return
     */
    @PostMapping("resource/find_list")
    List<ResourceVo> findListByUserId(@RequestBody BasicDto basicDto);

    /**
     * 根据资源id获取资源
     * @param id
     * @return
     */
    @GetMapping("resource/find_by_id")
    ResourceVo findById(@RequestParam("id") Long id);

    /**
     * 根据 资源id 删除 对应资源
     * @param basicDto
     * @return
     */
    @PostMapping("resource/delete_resource_by_id")
    Boolean deleteResourceById(@RequestBody BasicDto basicDto);

    /**
     * 条件查询  所有的
     * @param resourceDto
     * @return
     */
    @PostMapping("resource/find_all")
    List<ResourceVo> findAll(@RequestBody  ResourceDto resourceDto);

    /**
     * 添加资源
     * @param resourceDto
     * @return
     */
    @PostMapping("resource/save_resource")
    Long saveResource(@RequestBody  ResourceDto resourceDto);

    /**
     * 更新资源
     * @param resourceDto
     * @return
     */
    @PostMapping("resource/update_resource")
    Boolean updateResource(@RequestBody ResourceDto resourceDto);

    /**
     * 权限id查询资源
     * @param resourceDto
     * @return
     */
    @PostMapping("resource/find_all_by_role_id")
    List<ResourceVo> findAllByRoleId(@RequestBody  ResourceDto resourceDto);

    /**
     * 根据角色id获取资源ID列表
     * @param roleId
     * @return
     */
    @GetMapping("resource/find_ids_by_role_id")
    List<Long> findIdsByRoleId(@RequestParam("roleId") Long roleId);
}
