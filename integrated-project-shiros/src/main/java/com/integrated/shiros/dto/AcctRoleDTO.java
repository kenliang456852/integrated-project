package com.integrated.shiros.dto;

import java.io.Serializable;

/**
 * DTO:AcctRole
 * 
 * @author gencode
 * @date 2018-7-23
 */
public class AcctRoleDTO implements Serializable {
	
	private static final long serialVersionUID = 2976558809190657543L;
	
	private String	id;
	private String	acctId;
	private String	roleId;

	// Constructor
	public AcctRoleDTO() {
	}

	/**
	 * full Constructor
	 */
	public AcctRoleDTO(String id, String acctId, String roleId) {
		this.id = id;
		this.acctId = acctId;
		this.roleId = roleId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	public String getAcctId() {
		return acctId;
	}

	public void setAcctId(String acctId) {
		this.acctId = acctId;
	}
	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	@Override
	public String toString() {
		return "AcctRoleDTO [" + "id=" + id + ", acctId=" + acctId + ", roleId=" + roleId +  "]";
	}
}
