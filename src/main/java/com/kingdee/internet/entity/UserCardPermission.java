package com.kingdee.internet.entity;

import com.kingdee.internet.util.IBuilder;

/**
 * 用户-银行卡权限绑定关系表
 */
public class UserCardPermission {
    private String id;
    private String createBy;
    private String userId;
    private CardBankUser cardBankUser;
    private String username;
    private String remark;

    private UserCardPermission(Builder builder) {
        this.id = builder.id;
        this.createBy = builder.createBy;
        this.userId = builder.userId;
        this.cardBankUser = builder.cardBankUser;
        this.username = builder.username;
        this.remark = builder.remark;
    }

    public UserCardPermission() {  }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public CardBankUser getCardBankUser() {
        return cardBankUser;
    }

    public void setCardBankUser(CardBankUser cardBankUser) {
        this.cardBankUser = cardBankUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public static class Builder implements IBuilder<UserCardPermission> {
        private String id;
        private String createBy;
        private String userId;
        private CardBankUser cardBankUser;
        private String username;
        private String remark;

        public Builder id(String id) { this.id = id; return this; }
        public Builder createBy(String createBy) { this.createBy = createBy; return this; }
        public Builder userId(String userId) { this.userId = userId; return this; }
        public Builder cardBankUser(CardBankUser cardBankUser) { this.cardBankUser = cardBankUser; return this; }
        public Builder username(String username) { this.username = username; return this; }
        public Builder remark(String remark) { this.remark = remark; return this; }

        @Override
        public UserCardPermission build() {
            return new UserCardPermission(this);
        }
    }
}
