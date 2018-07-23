package com.integrated.shiros.model;

import java.io.Serializable;

/**
 * entity:RolePermission
 * 
 * @author gencode
 */
public class RolePermission implements Serializable {
	
	private static final long serialVersionUID = -7541875579653971270L;
	
	private String	id;		
	private String	roleId;		
	private String	permissionId;		

	// Constructor
	public RolePermission() {
	}

	/**
	 * full Constructor
	 */
	public RolePermission(String id, String roleId, String permissionId) {
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
		return "RolePermission [" + "id=" + id+ ", roleId=" + roleId+ ", permissionId=" + permissionId+  "]";
	}
}
