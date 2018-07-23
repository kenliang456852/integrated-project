package com.integrated.shiros.dto;

import java.io.Serializable;

/**
 * DTO:RolePermission
 * 
 * @author gencode
 * @date 2018-7-23
 */
public class RolePermissionDTO implements Serializable {
	
	private static final long serialVersionUID = 1063118977182027666L;
	
	private String	id;
	private String	roleId;
	private String	permissionId;

	// Constructor
	public RolePermissionDTO() {
	}

	/**
	 * full Constructor
	 */
	public RolePermissionDTO(String id, String roleId, String permissionId) {
		this.id = id;
		this.roleId = roleId;
		this.permissionId = permissionId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(String permissionId) {
		this.permissionId = permissionId;
	}
	@Override
	public String toString() {
		return "RolePermissionDTO [" + "id=" + id + ", roleId=" + roleId + ", permissionId=" + permissionId +  "]";
	}
}
