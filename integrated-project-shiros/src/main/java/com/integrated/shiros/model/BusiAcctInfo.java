package com.integrated.shiros.model;

import java.io.Serializable;

/**
 * entity:BusiAcctInfo
 * 
 * @author gencode
 */
public class BusiAcctInfo implements Serializable {
	
	private static final long serialVersionUID = 756840878989875125L;
	
	private String	id;		
	private String	userName;		
	private String	password;		

	// Constructor
	public BusiAcctInfo() {
	}

	/**
	 * full Constructor
	 */
	public BusiAcctInfo(String id, String userName, String password) {
		this.id = id;
		this.userName = userName;
		this.password = password;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "BusiAcctInfo [" + "id=" + id+ ", userName=" + userName+ ", password=" + password+  "]";
	}
}
