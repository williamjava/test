package com.tufire.common.tree;

/**
 *
 * @author it001hyl
 * @date 2019-03-12
 */
public class TreeNode {
	private String id;
	private String parentId;
	private String inviteId;

	public String getInviteId() {
		return inviteId;
	}

	public void setInviteId(String inviteId) {
		this.inviteId = inviteId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

}