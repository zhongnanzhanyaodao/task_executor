package com.kingdee.internet.repository;

import com.kingdee.internet.entity.BankUser;

public interface BankUserDao {
    BankUser findByAccountNumAndBankType(String accountNum, String bankType);
}
