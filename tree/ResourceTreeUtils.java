package com.tufire.seller.util;

import com.google.common.collect.Lists;
import com.tufire.common.util.ObjectUtil;
import com.tufire.user.vo.seller.ResourceVo;
import com.tufire.user.vo.seller.TreeVo;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by it001hyl on 2019-01-22.
 */
public class ResourceTreeUtils {


    /**
     * 将资源格式化成 树状结构
     * @param resourceVoList 源数据
     * @param flag 是否 只格式化菜单资源
     * @return
     */
    public static List<TreeVo> formatResource(List<ResourceVo> resourceVoList, boolean flag) {
        if(resourceVoList == null){
            return null;
        }
        //判断过滤  菜单
        if(flag){
            resourceVoList = resourceVoList.stream()
                              .filter(v->v.getShow()!=null&&v.getShow()).collect(Collectors.toList());
        }
        //根节点
        List<ResourceVo> resourceVoRootList = resourceVoList.stream().filter(
                                               v->v.getParentId()==null).collect(Collectors.toList());
        //找出根节点 以外的其他节点
        Map<Long, List<ResourceVo>> resourceVoChildrentMap = resourceVoList.stream().filter(
                v->v.getParentId()!=null).collect(Collectors.groupingBy(ResourceVo::getParentId));
        List<TreeVo> treeVoList = Lists.newArrayList();
        resourceVoRootList.forEach(v->{
            TreeVo treeVo = ObjectUtil.source2Target(v,TreeVo.class);
            buildTerrVo(resourceVoChildrentMap,treeVo);
            treeVoList.add(treeVo);
        });
        return treeVoList;
    }


    /**
     * 递归组建  treeVo
     * @param resourceVoChildrentMap
     * @param treeVo
     */
    private static void buildTerrVo(Map<Long, List<ResourceVo>> resourceVoChildrentMap, TreeVo treeVo) {
        List<ResourceVo> resourceVoList = resourceVoChildrentMap.get(treeVo.getId());
        if(resourceVoList== null || resourceVoList.isEmpty()){
            return ;
        }
        treeVo.setChildren(ObjectUtil.sourceList2TargetList(resourceVoList,TreeVo.class));
        treeVo.getChildren().forEach(v->{
            buildTerrVo(resourceVoChildrentMap,v);
        });
    }
}
