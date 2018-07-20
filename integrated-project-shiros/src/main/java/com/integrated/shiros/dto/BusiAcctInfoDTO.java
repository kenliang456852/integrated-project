package com.integrated.shiros.dto;

import java.io.Serializable;

/**
 * DTO:BusiAcctInfo
 * 
 * @author gencode
 * @date 2018-7-21
 */
public class BusiAcctInfoDTO implements Serializable {
	
	private static final long serialVersionUID = 370662215636554307L;
	
	private String	id;
	private String	userName;
	private String	password;

	// Constructor
	public BusiAcctInfoDTO() {
	}

	/**
	 * full Constructor
	 */
	public BusiAcctInfoDTO(String id, String userName, String password) {
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
		return "BusiAcctInfoDTO [" + "id=" + id + ", userName=" + userName + ", password=" + password +  "]";
	}
}
