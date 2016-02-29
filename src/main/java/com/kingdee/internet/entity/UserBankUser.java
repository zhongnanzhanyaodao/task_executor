package com.kingdee.internet.entity;

import com.kingdee.internet.util.IBuilder;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 系统用户与网银账户对应关系
 * 
 */
public class UserBankUser implements Serializable {
	private static final long serialVersionUID = 3887716355134929529L;
	private String id;
	private String userId;
	private String bankUserId;
	private Date updateDate;
	private Date createDate;
	private String isValidity;
	private String bankUserPasswd;//最近一次同步时输入的密码
	private List<BankUser> bankUsers;

	public UserBankUser() {}

	private UserBankUser(Builder builder) {
		this.id = builder.id;
		this.userId = builder.userId;
		this.bankUserId = builder.bankUserId;
		this.updateDate = builder.updateDate;
		this.createDate = builder.createDate;
		this.isValidity = builder.isValidity;
		this.bankUserPasswd = builder.bankUserPasswd;
	}

	public String getBankUserPasswd() {
		return bankUserPasswd;
	}

	public void setBankUserPasswd(String bankUserPasswd) {
		this.bankUserPasswd = bankUserPasswd;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getBankUserId() {
		return bankUserId;
	}

	public void setBankUserId(String bankUserId) {
		this.bankUserId = bankUserId;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getIsValidity() {
		return isValidity;
	}

	public void setIsValidity(String isValidity) {
		this.isValidity = isValidity;
	}

	public List<BankUser> getBankUsers() {
		return bankUsers;
	}

	public void setBankUsers(List<BankUser> bankUsers) {
		this.bankUsers = bankUsers;
	}

	public static class Builder implements IBuilder<UserBankUser> {
		private String id;
		private String userId;
		private String bankUserId;
		private Date updateDate;
		private Date createDate;
		private String isValidity;
		private String bankUserPasswd;//最近一次同步时输入的密码

		public Builder id(String id) { this.id = id; return this; }
		public Builder userId(String userId) { this.userId = userId; return this; }
		public Builder bankUserId(String bankUserId) { this.bankUserId = bankUserId; return this; }
		public Builder updateDate(Date updateDate) { this.updateDate = updateDate; return this; }
		public Builder createDate(Date createDate) { this.createDate = createDate; return this; }
		public Builder isValidity(String isValidity) { this.isValidity = isValidity; return this; }
		public Builder bankUserPasswd(String bankUserPasswd) { this.bankUserPasswd = bankUserPasswd; return this; }

		public UserBankUser build() {
			return new UserBankUser(this);
		}
	}
}
