package com.integrated.shiros.dto;

import java.io.Serializable;

/**
 * DTO:RoleInfo
 * 
 * @author gencode
 * @date 2018-7-23
 */
public class RoleInfoDTO implements Serializable {
	
	private static final long serialVersionUID = -5120810039221573979L;
	
	private String	id;
	private String	roleName;

	// Constructor
	public RoleInfoDTO() {
	}

	/**
	 * full Constructor
	 */
	public RoleInfoDTO(String id, String roleName) {
		this.id = id;
		this.roleName = roleName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	@Override
	public String toString() {
		return "RoleInfoDTO [" + "id=" + id + ", roleName=" + roleName +  "]";
	}
}
