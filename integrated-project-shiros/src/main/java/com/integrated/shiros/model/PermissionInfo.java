package com.integrated.shiros.model;

import java.io.Serializable;

/**
 * entity:PermissionInfo
 * 
 * @author gencode
 */
public class PermissionInfo implements Serializable {
	
	private static final long serialVersionUID = 3987280895543055732L;
	
	private String	id;		
	private String	permissionName;		

	// Constructor
	public PermissionInfo() {
	}

	/**
	 * full Constructor
	 */
	public PermissionInfo(String id, String permissionName) {
		this.id = id;
		this.permissionName = permissionName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPermissionName() {
		return permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

	@Override
	public String toString() {
		return "PermissionInfo [" + "id=" + id+ ", permissionName=" + permissionName+  "]";
	}
}
