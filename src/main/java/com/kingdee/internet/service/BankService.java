package com.kingdee.internet.service;

import java.util.Map;

public interface BankService {
    /**
     * 设置后台抓取数据起始时间
     * @param params
     * @return
     */
    String getStartDate(Map<String, String> params);

    /**
     * 保存抓取结果
     * @param bankDataJson
     * @return
     */
    int saveBankData(String bankDataJson);
}
