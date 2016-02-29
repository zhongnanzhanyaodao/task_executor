package com.kingdee.internet.entity;

import com.kingdee.internet.util.IBuilder;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

/**
 * <p>Title: CardBankUser</p>
 * <p>Description: 银行登录账号和银行账户号的绑定关系</p>
 * <p>Company: kingdee</p>
 *
 * @author dwj
 * @date 2015年12月1日
 */
public class CardBankUser implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    private String id;
    public String bankUserId;
    public String accountNo;
    public BigDecimal balance;
    public String accountName;
    public String bankType;
    public String bankName;
    /**
     * 最后同步时间
     */
    private Date latestSyncTime;

    public BigDecimal balance1; // 余额扩展项1

    public CardBankUser() {
    }

    private CardBankUser(Builder builder) {
        this.id = builder.id;
        this.bankUserId = builder.bankUserId;
        this.accountNo = builder.accountNo;
        this.balance = builder.balance;
        this.accountName = builder.accountName;
        this.bankType = builder.bankType;
        this.bankName = builder.bankName;
        this.latestSyncTime = builder.latestSyncTime;
        this.balance1 = builder.balance1;
    }

    public Date getLatestSyncTime() {
        return latestSyncTime;
    }

    public void setLatestSyncTime(Timestamp latestSyncTime) {
        this.latestSyncTime = latestSyncTime;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankType() {
        return bankType;
    }

    public void setBankType(String bankType) {
        this.bankType = bankType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBankLoginId() {
        return bankUserId;
    }

    public void setBankLoginId(String bankUserId) {
        this.bankUserId = bankUserId;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public BigDecimal getBalance1() {
        return balance1;
    }

    public void setBalance1(BigDecimal balance1) {
        this.balance1 = balance1;
    }

    public static class Builder implements IBuilder<CardBankUser> {
        private String id;
        private String bankUserId;
        private String accountNo;
        private BigDecimal balance;
        private String accountName;
        private String bankType;
        private String bankName;
        private Timestamp latestSyncTime;
        private BigDecimal balance1;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder bankUserId(String bankLoginId) {
            this.bankUserId = bankLoginId;
            return this;
        }

        public Builder accountNo(String accountNo) {
            this.accountNo = accountNo;
            return this;
        }

        public Builder balance(BigDecimal balance) {
            this.balance = balance;
            return this;
        }

        public Builder balance(String balance) {
            this.balance = new BigDecimal(balance);
            return this;
        }

        public Builder accountName(String accountName) {
            this.accountName = accountName;
            return this;
        }

        public Builder bankType(String bankType) {
            this.bankType = bankType;
            return this;
        }

        public Builder bankName(String bankName) {
            this.bankName = bankName;
            return this;
        }

        public Builder latestSyncTime(Timestamp latestSyncTime) {
            this.latestSyncTime = latestSyncTime;
            return this;
        }

        public Builder latestSyncTime(String latestSyncTime) {
            this.latestSyncTime = Timestamp.valueOf(latestSyncTime);
            return this;
        }

        public Builder balance1(String balance1) {
            if(StringUtils.isNotBlank(balance1)) {
                this.balance1 = new BigDecimal(balance1);
            }
            return this;
        }

        public Builder balance1(BigDecimal balance1) {
            this.balance1 = balance1;
            return this;
        }

        public CardBankUser build() {
            return new CardBankUser(this);
        }
    }
}
