package com.kingdee.internet.repository;

import com.kingdee.internet.entity.Enterprise;
import com.kingdee.internet.entity.TxnDetail;

import java.util.List;

public interface TxnDetailDao<T extends TxnDetail> {
    /**
     * Mybatis批量Insert
     * @param dataList
     * @return 实际插入记录条数
     */
    int insertAll(List<T> dataList);

    int batchEnterpriseInsert(List<Enterprise> list);
}
