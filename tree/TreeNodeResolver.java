package com.tufire.common.tree;

import java.util.function.Function;
/**
 *
 * @author it001hyl
 * @date 2019-03-12
 */
@FunctionalInterface
public interface TreeNodeResolver<T> extends Function<T, TreeNode> {
	
}