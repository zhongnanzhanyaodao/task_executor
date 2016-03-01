package com.kingdee.internet.repository;


import com.kingdee.internet.entity.BankUser;
import com.kingdee.internet.entity.UserBankUser;

import java.util.List;

public interface UserBankUserDao {

    /**
     * 存在时更新(仅更新更新时间和密码)，不存在时插入
     *
     * @param userBankUser
     */
    void merge(UserBankUser userBankUser);

    /**
     * 用户添加网银账户同时插入用户网银关系表和网银用户表,仅支持添加一个网银用户
     *
     * @param userBankUser
     */
    void cascadeInsert(UserBankUser userBankUser, BankUser bankUser);


    /**
     * 根据userId 和 银行类型查询用户的绑定的网银账户
     *
     * @param userId
     * @param bankType
     * @return
     */
    List<BankUser> queryByUserIdAndBankType(String userId, String bankType);


}
