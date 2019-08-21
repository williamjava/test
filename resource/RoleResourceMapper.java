package com.tufire.user.dal.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.tufire.user.dal.entity.Role;
import com.tufire.user.dal.entity.RoleResource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by it001hyl on 2019-01-22.
 */
public interface RoleResourceMapper extends BaseMapper<RoleResource> {

    Boolean deleteRoleResource(@Param("resourceId") String resourceId);

    Boolean deleteByRoleId(@Param("roleId") Long roleId);

    List<Long> selectResourceIdsByRoleId(@Param("roleId") Long roleId);
}
