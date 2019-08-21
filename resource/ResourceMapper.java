package com.tufire.user.dal.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.tufire.user.dal.entity.Resource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by it001hyl on 2019-01-16.
 */
public interface ResourceMapper extends BaseMapper<Resource> {

    List<Resource> selectListAll(@Param("userId") Long userId, @Param("appId") Long appId,@Param("roleId")Long roleId);

}
