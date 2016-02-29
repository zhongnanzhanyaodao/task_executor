package com.kingdee.internet.entity;

import java.io.Serializable;

public class Enterprise implements Serializable{
	/** serialVersionUID*/
	private static final long serialVersionUID = 1L;
	private String id;
	private String enterpriseName;
	private String accountNo;
	private String bankType;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEnterpriseName() {
		return enterpriseName;
	}
	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getBankType() {
		return bankType;
	}
	public void setBankType(String bankType) {
		this.bankType = bankType;
	}
	
}
