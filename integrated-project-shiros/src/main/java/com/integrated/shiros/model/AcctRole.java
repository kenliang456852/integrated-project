package com.integrated.shiros.model;

import java.io.Serializable;

/**
 * entity:AcctRole
 * 
 * @author gencode
 */
public class AcctRole implements Serializable {
	
	private static final long serialVersionUID = 6043545257121265520L;
	
	private String	id;		
	private String	acctId;		
	private String	roleId;		

	// Constructor
	public AcctRole() {
	}

	/**
	 * full Constructor
	 */
	public AcctRole(String id, String acctId, String roleId) {
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
		return "AcctRole [" + "id=" + id+ ", acctId=" + acctId+ ", roleId=" + roleId+  "]";
	}
}
