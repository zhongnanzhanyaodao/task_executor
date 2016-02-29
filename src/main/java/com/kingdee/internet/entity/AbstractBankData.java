package com.kingdee.internet.entity;

import com.kingdee.internet.util.DateUtils;
import com.kingdee.internet.util.IBuilder;
import com.kingdee.internet.util.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 第三方金融机构明细数据抽象类
 * @Author Yondy Zhou
 * @Since 2016/01/07
 */
abstract public class AbstractBankData<B extends AbstractBankData.Builder> {
    protected String id; // 主键
    protected String accountNo; // 账号
    protected String currency; // 交易币种
    protected String flag; // 借贷标志
    protected BigDecimal money; // 交易金额
    protected BigDecimal balance; // 账户当前余额
    protected String otherAccountNo; // 交易对方账号
    protected Date transferInTime; // 交易时间
    protected String otherOrgName; // 对方客户名称
    protected String channel; // 交易渠道
    protected String serialNo; // 交易流水号
    protected String accountType; // 账号类别
    protected String digest; // 摘要
    protected String otherBankName; // 对账

    public AbstractBankData() {  }

    protected AbstractBankData(B builder) {
        this.id = builder.id;
        this.accountNo = builder.accountNo;
        this.currency = builder.currency;
        this.flag = builder.flag;
        this.money = builder.money;
        this.balance = builder.balance;
        this.otherAccountNo = builder.otherAccountNo;
        this.transferInTime = builder.transferInTime;
        this.otherOrgName = builder.otherOrgName;
        this.channel = builder.channel;
        this.serialNo = builder.serialNo;
        this.accountType = builder.accountType;
        this.digest = builder.digest;
        this.otherBankName = builder.otherBankName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getOtherAccountNo() {
        return otherAccountNo;
    }

    public void setOtherAccountNo(String otherAccountNo) {
        this.otherAccountNo = otherAccountNo;
    }

    public Date getTransferInTime() {
        return transferInTime;
    }

    public void setTransferInTime(Date transferInTime) {
        this.transferInTime = transferInTime;
    }

    public String getOtherOrgName() {
        return otherOrgName;
    }

    public void setOtherOrgName(String otherOrgName) {
        this.otherOrgName = otherOrgName;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getOtherBankName() {
        return otherBankName;
    }

    public void setOtherBankName(String otherBankName) {
        this.otherBankName = otherBankName;
    }

    public abstract static class Builder<T extends AbstractBankData,
            B extends Builder<T, B>> implements IBuilder<T> {
        private static final Logger logger = LoggerFactory.getLogger(Builder.class);

        protected String id; // 主键
        protected String accountNo; // 账号
        protected String currency; // 交易币种
        protected String flag; // 借贷标志
        protected BigDecimal money; // 交易金额
        protected BigDecimal balance; // 账户当前余额
        protected String otherAccountNo; // 交易对方账号
        protected Date transferInTime; // 交易时间
        protected String otherOrgName; // 对方客户名称
        protected String channel; // 交易渠道
        protected String serialNo; // 交易流水号
        protected String accountType; // 账号类别
        protected String digest; // 摘要
        protected String otherBankName; // 对账

        private final B self = getSelf();

        public B id(String id) {
            self.id = id;
            return self;
        }

        public B accountNo(String accountNo) {
            self.accountNo = accountNo;
            return self;
        }

        public B currency(String currency) {
            self.currency = currency;
            return self;
        }

        public B flag(String flag) {
            self.flag = flag;
            return self;
        }

        public B money(BigDecimal money) {
            self.money = money;
            return self;
        }

        public B money(String money) {
            self.money = NumberUtils.getBigDecimal(money);
            return self;
        }

        public B balance(BigDecimal balance) {
            self.balance = balance;
            return self;
        }

        public B balance(String balance) {
            self.balance = NumberUtils.getBigDecimal(balance);
            return self;
        }

        public B otherAccountNo(String otherAccountNo) {
            self.otherAccountNo = otherAccountNo;
            return self;
        }

        public B transferInTime(Date transferInTime) {
            self.transferInTime = transferInTime;
            return self;
        }

        public B transferInTime(String transferInTime, String format) {
            self.transferInTime = DateUtils.parseDate(transferInTime, format);
            return self;
        }

        public B otherOrgName(String otherOrgName) {
            self.otherOrgName = otherOrgName;
            return self;
        }

        public B channel(String channel) {
            self.channel = channel;
            return self;
        }

        public B serialNo(String serialNo) {
            self.serialNo = serialNo;
            return self;
        }

        public B accountType(String accountType) {
            self.accountType = accountType;
            return self;
        }

        public B digest(String digest) {
            self.digest = digest;
            return self;
        }

        public B otherBankName(String otherBankName) {
            self.otherBankName = otherBankName;
            return self;
        }

        protected abstract B getSelf();
    }


}
