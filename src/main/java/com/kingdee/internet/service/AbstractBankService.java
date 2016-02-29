package com.kingdee.internet.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.kingdee.internet.entity.AbstractBankData;
import com.kingdee.internet.repository.BankDataDao;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

public abstract class AbstractBankService<DAO extends BankDataDao<T>,
        T extends AbstractBankData> implements BankService {
    private static final Logger logger = LoggerFactory.getLogger(AbstractBankService.class);

    protected DAO bankDataDao;

    @Override
    @Transactional(readOnly = false)
    public int saveBankData(String bankDataJson) {
        if(StringUtils.isBlank(bankDataJson)) {
            throw new RuntimeException("网银数据为空!");
        }
        String bankUserId = null;
        List<Map<String, String>> bankDataList = JSON.parseObject(bankDataJson, new TypeReference<List<Map<String, String>>>(){});
        for(Map<String, String> bankData : bankDataList) {
            if(StringUtils.isBlank(bankUserId)) {
                bankUserId = saveUserBankUser(bankData); // maintenance user-bank relation
            }
            bankData.put("bankUserId", bankUserId);
            bankData.put("accountId", saveBankAccount(bankData));
            saveUserCardPermission(bankData);
        }

        return 0;
    }

    private void saveUserCardPermission(Map<String, String> bankData) {

    }

    private String saveBankAccount(Map<String, String> bankData) {
        return null;
    }

    private String saveUserBankUser(Map<String, String> bankData) {
        return null;
    }

    abstract protected List<T> parseTxnDetails(Map<String, String> bankData);
}
