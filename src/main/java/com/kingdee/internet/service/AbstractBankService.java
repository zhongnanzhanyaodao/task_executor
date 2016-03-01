package com.kingdee.internet.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.kingdee.internet.entity.*;
import com.kingdee.internet.repository.*;
import com.kingdee.internet.statemachine.Context;
import com.kingdee.internet.util.CommonUtils;
import com.kingdee.internet.util.DateUtils;
import com.kingdee.internet.util.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.CookieStore;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

public abstract class AbstractBankService<DAO extends TxnDetailDao<T>,
        T extends TxnDetail> implements BankService {
    private static final Logger logger = LoggerFactory.getLogger(AbstractBankService.class);

    protected static final int BATCH_INSERT_SIZE = 500;

    @Autowired
    private BankUserDao bankUserDao;

    @Autowired
    private CardBankUserDao cardBankUserDao;

    @Autowired
    private UserBankUserDao userBankUserDao;

    @Autowired
    private UserCardPermissionDao userCardPermissionDao;

    protected DAO bankDataDao;

    @Value("${download.base.dir}")
    private String downloadBaseDir;

    @Override
    @Transactional(readOnly = true)
    public String getStartDate(Map<String, String> params) {
        BankUser param = new BankUser.Builder()
                .accountNum(params.get("bankLoginNo"))
                .bankType(params.get("bankType"))
                .build();
        BankUser bankUser = bankUserDao.findByAccountNumAndBankType(param);
        if(bankUser!= null && bankUser.getBankAccounts() != null) {
            List<CardBankUser> cardBankUsers = bankUser.getBankAccounts();
            if(cardBankUsers.size() > 0) {
                long minTimeMillis = Long.MAX_VALUE;
                for(CardBankUser cardBankUser : cardBankUsers) {
                    if(cardBankUser.getLatestSyncTime() != null) {
                        minTimeMillis = Math.min(minTimeMillis, cardBankUser.getLatestSyncTime().getTime());
                    }
                }
                if(minTimeMillis < Long.MAX_VALUE) {
                    return DateUtils.date2str(new Date(minTimeMillis), DateUtils.DateFormat.YYYYMMDD);
                }
            }
        }
        return DateUtils.getYearDiffFromNow(-1, DateUtils.DateFormat.YYYYMMDD);
    }

    @Override
    @Transactional(readOnly = false)
    public int saveBankData(String bankDataJson, Context context) {
        if(StringUtils.isBlank(bankDataJson)) {
            throw new RuntimeException("网银数据为空!");
        }
        String bankUserId = StringUtils.EMPTY;
        List<Map<String, String>> bankDataList = JSON.parseObject(bankDataJson, new TypeReference<List<Map<String, String>>>(){});
        for(Map<String, String> bankData : bankDataList) {
            if(StringUtils.isBlank(bankUserId)) {
                bankUserId = saveUserBankUser(bankData, context); // maintenance user-bank relation
            }
            bankData.put("bankUserId", bankUserId);
            bankData.put("accountId", saveBankAccount(bankData, context));
            saveUserCardPermission(bankData, context);
            List<T> dataList = parseTxnDetails(bankData, context);
            if(!dataList.isEmpty()) {
                // 保存明细
                batchInsert(dataList);
                //去重
                dataList = dereplicationList(dataList);
                // 保存客户列表
                saveEnterpriseName(dataList, context.task().getBankType());
            }
        }
        return 0;
    }

    private void saveUserCardPermission(Map<String, String> bankData, Context context) {
        Map<String, String> taskParams = context.task().getParams().get("respData");
        UserCardPermission userCardPermission = new UserCardPermission.Builder()
                .id(CommonUtils.uuid())
                .createBy(taskParams.get("userId"))
                .userId(taskParams.get("userId"))
                .cardBankUser(new CardBankUser.Builder().id(bankData.get("accountId")).build())
                .username(bankData.get("accountName"))
                .build();

        List<UserCardPermission> authorisedCardPermissions = userCardPermissionDao.findAuthorisedCardPermission(userCardPermission);
        userCardPermissionDao.delUserCardPermission(userCardPermission); // 删除原有权限
        for(UserCardPermission authorised : authorisedCardPermissions) {
            String createBy = authorised.getCreateBy(); // 获取权限赋予人
            userCardPermissionDao.delAllPermissionsIfNotExists(createBy, userCardPermission.getUserId());
            userCardPermissionDao.delAllUserPerEnterpriseIfNotExists(createBy, userCardPermission.getUserId());
        }
        userCardPermissionDao.insert(userCardPermission); // 插入用户-卡绑定关系
    }

    private String saveBankAccount(Map<String, String> bankData, Context context) {
        Map<String, String> taskParams = context.task().getParams().get("respData");
        CardBankUser cardBankUser = new CardBankUser.Builder()
                .id(CommonUtils.uuid())
                .bankUserId(bankData.get("bankUserId"))
                .accountNo(bankData.get("account"))
                .accountName(bankData.get("accountName"))
                .balance(NumberUtils.getBigDecimal(bankData.get("balance"), "0"))
                .bankType(taskParams.get("bankType"))
                .bankName(bankData.get("bankName"))
                .latestSyncTime(new Timestamp(System.currentTimeMillis()))
                .balance1(NumberUtils.getBigDecimal(bankData.get("balance1")))
                .build();
        cardBankUserDao.merge(cardBankUser);
        return cardBankUser.getId();
    }

    private String saveUserBankUser(Map<String, String> bankData, Context context) {
        Date now = new Date();
        Map<String, String> taskParams = context.task().getParams().get("respData");
        BankUser bankUser = new BankUser.Builder()
                .id(CommonUtils.uuid())
                .passwd(taskParams.get("bankPassword"))
                .accountNum(taskParams.get("bankLoginNo"))
                .userName(taskParams.get("userName"))
                .customerNum(taskParams.get("bankCustomNo"))
                .isAuthorization(taskParams.get("isAgreeProtocol"))
                .isUpdatedDaily(taskParams.get("isUpdate"))
                .bankType(taskParams.get("bankType"))
                .createDate(now)
                .updateDate(now)
                .bankName(bankData.get("bankName"))
                .build();
        bankUserDao.merge(bankUser); // Merge银行登陆信息表

        UserBankUser userBankUser = new UserBankUser.Builder()
                .id(CommonUtils.uuid())
                .userId(taskParams.get("userId"))
                .bankUserId(bankUser.getId())
                .createDate(now)
                .updateDate(now)
                .isValidity("1")
                .bankUserPasswd(bankUser.getPasswd()) // 最后一次登陆密码
                .build();
        userBankUserDao.merge(userBankUser); // Merge用户-银行登陆信息关系表

        return bankUser.getId();
    }

    abstract protected List<T> parseTxnDetails(Map<String, String> bankData, Context context);

    protected void batchInsert(List<T> dataList) {
        final int total = dataList.size();
        final int pageSize = (total % BATCH_INSERT_SIZE) == 0 ?
                total / BATCH_INSERT_SIZE : total / BATCH_INSERT_SIZE + 1;
        int totalInsert = 0;
        for (int i = 0; i < pageSize; i++) {
            final int fromIndex = i * BATCH_INSERT_SIZE;
            final int toIndex = Math.min(fromIndex + BATCH_INSERT_SIZE, total);
            totalInsert += bankDataDao.insertAll(dataList.subList(fromIndex, toIndex));
        }
        logger.info("bank data sync: retrieved {} records from pab, and inserted {} records.", total, totalInsert);
    }

    private List<T> dereplicationList(List<T> list) {
        Map<String,T> map = Maps.newHashMap();
        for(T t :list){
            if(StringUtils.isNotBlank(t.getOtherOrgName())&&!map.containsKey(t.getOtherOrgName()+t.getAccountNo())){
                map.put(t.getOtherOrgName()+t.getAccountNo(), t);
            }
        }
        list.clear();
        for (String key : map.keySet()) {
            list.add(map.get(key));
        }
        return list;
    }

    protected List<Enterprise> getEnterpriseList(List<T> dataList, String bankType) {
        List<Enterprise> enterprises = Lists.newArrayList();
        for(TxnDetail<?> bankData : dataList) {
            Enterprise enterprise = new Enterprise();
            enterprise.setAccountNo(bankData.getAccountNo());
            enterprise.setEnterpriseName(bankData.getOtherOrgName());
            enterprise.setBankType(bankType);
            enterprise.setId(CommonUtils.uuid());
            enterprises.add(enterprise);
        }
        return enterprises;
    }


    private void saveEnterpriseName(List<T> dataList, String bankType) {
        List<Enterprise> enterpriseList = getEnterpriseList(dataList, bankType);
        if(enterpriseList!=null && !enterpriseList.isEmpty()){
            bankDataDao.batchEnterpriseInsert(enterpriseList);
        }
    }

    protected String downLoadFile(Map<String, String> bankData, Context context) {
        String downloadUrl = bankData.get("download");
        CookieStore cookieStore = (CookieStore)context.extra().get("cookieStore");
        String filePath = downloadBaseDir + context.task().getBankType()
                + File.separator + DateUtils.now2str(DateUtils.DateFormat.YYYYMMDD)
                + File.separator + StringUtils.substringBetween(downloadUrl, "clientFileName=", "&");
        try {
            Executor.newInstance()
                    .use(cookieStore)
                    .execute(Request.Post(downloadUrl))
                    .saveContent(new File(filePath));
            return filePath;
        } catch (IOException e) {
            logger.error("failed to download file.", e);
            throw new RuntimeException("failed to download file.");
        }
    }
}
