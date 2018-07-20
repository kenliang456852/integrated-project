package com.integrated.shiros.model;

import java.io.Serializable;

/**
 * entity:BusiAcctInfoDTO
 *
 * @author gencode
 */
public class BusiAcctInfoDTO implements Serializable {

	private static final long serialVersionUID = 756840878989875125L;

	private String	id;
	private String	acctName;
	private String	password;

	// Constructor
	public BusiAcctInfoDTO() {
	}

	/**
	 * full Constructor
	 */
	public BusiAcctInfoDTO(String id, String acctName, String password) {
		this.id = id;
		this.acctName = acctName;
		this.password = password;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAcctName() {
		return acctName;
	}

	public void setAcctName(String acctName) {
		this.acctName = acctName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "BusiAcctInfoDTO [" + "id=" + id+ ", acctName=" + acctName+ ", password=" + password+  "]";
	}
}
