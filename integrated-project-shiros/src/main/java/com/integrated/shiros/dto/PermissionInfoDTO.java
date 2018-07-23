package com.integrated.shiros.dto;

import java.io.Serializable;

/**
 * DTO:PermissionInfo
 * 
 * @author gencode
 * @date 2018-7-23
 */
public class PermissionInfoDTO implements Serializable {
	
	private static final long serialVersionUID = 2851045709759078177L;
	
	private String	id;
	private String	permissionName;

	// Constructor
	public PermissionInfoDTO() {
	}

	/**
	 * full Constructor
	 */
	public PermissionInfoDTO(String id, String permissionName) {
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
		return "PermissionInfoDTO [" + "id=" + id + ", permissionName=" + permissionName +  "]";
	}
}
