package com.kingdee.internet.repository;


import com.kingdee.internet.entity.BankUser;

public interface BankUserDao {
    /**
     * @param bankUser
     */
    void insert(BankUser bankUser);

    /**
     * 存在时更新(仅更新更新时间和密码)，不存在时插入
     *
     * @param bankUser
     */
    void merge(BankUser bankUser);

    /**
     * 根据AccountNum与BankType查找BankUser
     *
     * @param bankUser
     * @return
     */
    BankUser findByAccountNumAndBankType(BankUser bankUser);
}
