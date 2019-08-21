package com.tufire.common.tree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author it001hyl
 * @date 2019-03-12
 */
public class TreeHelper {

	/**
	 * 根据parent关系获取团队成员
	 * @param entities
	 * @param parentId
	 * @param treeNodeResolver
	 * @param <T>
     * @return
     */
	public static <T> List<T> sortBreadth(Collection<T> entities, String parentId, TreeNodeResolver<T> treeNodeResolver) {
		List<T> result = new ArrayList<>();
		Map<String, List<T>> childrenMap = entities.stream().collect(Collectors.groupingBy(v -> treeNodeResolver.apply(v).getParentId() == null ? "DEFAULT_NULL_KEY" : treeNodeResolver.apply(v).getParentId()));
		sortBreadth(childrenMap, result, parentId, treeNodeResolver);
		return result;
	}

	private static <T> void sortBreadth(Map<String, List<T>> childrenMap, Collection<T> result, String parentId, TreeNodeResolver<T> treeNodeResolver) {
		parentId = parentId == null ? "DEFAULT_NULL_KEY" : parentId;
		List<T> plist = childrenMap.get(parentId);
		if (plist == null) {
			return;
		}
		result.addAll(plist);
		for (T p : plist) {
			sortBreadth(childrenMap, result, treeNodeResolver.apply(p).getId(), treeNodeResolver);
		}
	}

	/**
	 * 根据invite关系获取团队成员
	 * @param entities
	 * @param inviteId
	 * @param treeNodeResolver
	 * @param <T>
     * @return
     */
	public static <T> List<T> sortBreadthByInviteId(Collection<T> entities, String inviteId, TreeNodeResolver<T> treeNodeResolver) {
		List<T> result = new ArrayList<>();
		Map<String, List<T>> childrenMap = entities.stream().collect(Collectors.groupingBy(v -> treeNodeResolver.apply(v).getInviteId() == null ? "DEFAULT_NULL_KEY" : treeNodeResolver.apply(v).getInviteId()));
		sortBreadth(childrenMap, result, inviteId, treeNodeResolver);
		return result;
	}
}
