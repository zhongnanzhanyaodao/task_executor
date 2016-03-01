package com.kingdee.internet.repository;


import com.kingdee.internet.entity.CardBankUser;

import java.util.List;

public interface CardBankUserDao {

    /**
     * 存在时更新
     *
     * @param cardBankUser
     */
    void merge(CardBankUser cardBankUser);

    /**
     * 根据网银账户id 查询绑定的所有卡片
     *
     * @param bankUserId
     * @return
     */
    List<CardBankUser> queryCardsByBankUserId(String bankUserId);

    /**
     * 批量插入绑定卡片信息
     *
     * @param cardAndBankUserRelationVos
     */
    void batchInsert(List<CardBankUser> cardAndBankUserRelationVos);

    /**
     * 根据用户id,网银账户id,网银类型查询绑定卡片信息，其中用户id是必须的
     *
     * @param userid
     * @param bankUserId
     * @param bankType
     * @return
     */
    List<CardBankUser> queryCardsByIdsAndBankType(String userid, String bankUserId, String bankType);

    /**
     * 查询特定用户同步网银类型的 最新同步时间
     *
     * @param userId
     * @param bankType
     * @return
     */
    List<CardBankUser> queryLatestSyncDate(String userId, String bankUserId, String bankType);

}
