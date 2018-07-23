package com.integrated.shiros.model;

import java.io.Serializable;

/**
 * entity:RoleInfo
 * 
 * @author gencode
 */
public class RoleInfo implements Serializable {
	
	private static final long serialVersionUID = 6437189046017748343L;
	
	private String	id;		
	private String	roleName;		

	// Constructor
	public RoleInfo() {
	}

	/**
	 * full Constructor
	 */
	public RoleInfo(String id, String roleName) {
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
		return "RoleInfo [" + "id=" + id+ ", roleName=" + roleName+  "]";
	}
}
