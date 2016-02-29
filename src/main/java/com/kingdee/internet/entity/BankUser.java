package com.kingdee.internet.entity;

import com.google.common.collect.Lists;
import com.kingdee.internet.util.IBuilder;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 统一银行登录账户信息 account; passwd;userName; customerNumber;确定唯一用户 ，不存在的字段留空
 */
public class BankUser implements Serializable {
	private static final long serialVersionUID = 6075789795670317252L;

	private String id;
	private String accountNum;
	private String passwd;
	private String userName;
	private String customerNum;
	private String extensionF1;
	private String extensionF2;
	private String extensionF3;
	private String isAuthorization;
	private String isUpdatedDaily;
	private String isValidity;
	private String bankType;
	private Date createDate;
	private Date updateDate;
	private String bankName;
	private List<CardBankUser> bankAccounts;

	public BankUser() {}

	private BankUser(Builder builder) {
		this.id = builder.id;
		this.accountNum = builder.accountNum;
		this.passwd = builder.passwd;
		this.userName = builder.userName;
		this.customerNum = builder.customerNum;
		this.extensionF1 = builder.extensionF1;
		this.extensionF2 = builder.extensionF2;
		this.extensionF3 = builder.extensionF3;
		this.isAuthorization = builder.isAuthorization;
		this.isUpdatedDaily = builder.isUpdatedDaily;
		this.isValidity = builder.isValidity;
		this.bankType = builder.bankType;
		this.createDate = builder.createDate;
		this.updateDate = builder.updateDate;
		this.bankName = builder.bankName;
		this.bankAccounts = builder.bankAccounts;
	}

	public List<CardBankUser> getBankAccounts() {
		return bankAccounts;
	}

	public void setBankAccounts(List<CardBankUser> bankAccounts) {
		this.bankAccounts = bankAccounts;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAccountNum() {
		return accountNum;
	}

	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCustomerNum() {
		return customerNum;
	}

	public void setCustomerNum(String customerNum) {
		this.customerNum = customerNum;
	}

	public String getExtensionF1() {
		return extensionF1;
	}

	public void setExtensionF1(String extensionF1) {
		this.extensionF1 = extensionF1;
	}

	public String getExtensionF2() {
		return extensionF2;
	}

	public void setExtensionF2(String extensionF2) {
		this.extensionF2 = extensionF2;
	}

	public String getExtensionF3() {
		return extensionF3;
	}

	public void setExtensionF3(String extensionF3) {
		this.extensionF3 = extensionF3;
	}

	public String getIsAuthorization() {
		return isAuthorization;
	}

	public void setIsAuthorization(String isAuthorization) {
		this.isAuthorization = isAuthorization;
	}

	public String getIsUpdatedDaily() {
		return isUpdatedDaily;
	}

	public void setIsUpdatedDaily(String isUpdatedDaily) {
		this.isUpdatedDaily = isUpdatedDaily;
	}

	public String getIsValidity() {
		return isValidity;
	}

	public void setIsValidity(String isValidity) {
		this.isValidity = isValidity;
	}

	public String getBankType() {
		return bankType;
	}

	public void setBankType(String bankType) {
		this.bankType = bankType;
	}

	public static class Builder implements IBuilder<BankUser> {
		private String id;
		private String accountNum;
		private String passwd;
		private String userName;
		private String customerNum;
		private String extensionF1;
		private String extensionF2;
		private String extensionF3;
		private String isAuthorization;
		private String isUpdatedDaily;
		private String isValidity;
		private String bankType;
		private Date createDate;
		private Date updateDate;
		private String bankName;
		private List<CardBankUser> bankAccounts;

		public Builder id(String id) {
			this.id = id;
			return this;
		}

		public Builder accountNum(String accountNum) {
			this.accountNum = accountNum;
			return this;
		}

		public Builder passwd(String passwd) {
			this.passwd = passwd;
			return this;
		}

		public Builder userName(String userName) {
			this.userName = userName;
			return this;
		}

		public Builder customerNum(String customerNum) {
			this.customerNum = customerNum;
			return this;
		}

		public Builder extensionF1(String extensionF1) {
			this.extensionF1 = extensionF1;
			return this;
		}

		public Builder extensionF2(String extensionF2) {
			this.extensionF2 = extensionF2;
			return this;
		}

		public Builder extensionF3(String extensionF3) {
			this.extensionF3 = extensionF3;
			return this;
		}

		public Builder isAuthorization(String isAuthorization) {
			this.isAuthorization = isAuthorization;
			return this;
		}

		public Builder isUpdatedDaily(String isUpdatedDaily) {
			this.isUpdatedDaily = isUpdatedDaily;
			return this;
		}

		public Builder isValidity(String isValidity) {
			this.isValidity = isValidity;
			return this;
		}

		public Builder bankType(String bankType) {
			this.bankType = bankType;
			return this;
		}

		public Builder createDate(Date createDate) {
			this.createDate = createDate;
			return this;
		}

		public Builder updateDate(Date updateDate) {
			this.updateDate = updateDate;
			return this;
		}

		public Builder bankName(String bankName) {
			this.bankName = bankName;
			return this;
		}

		public Builder bankAccount(CardBankUser bankAccount) {
			if(this.bankAccounts == null) {
				this.bankAccounts = Lists.newArrayList();
			}
			this.bankAccounts.add(bankAccount);
			return this;
		}

		public BankUser build() {
			return new BankUser(this);
		}
	}
}
