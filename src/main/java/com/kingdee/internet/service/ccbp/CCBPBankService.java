package com.kingdee.internet.service.ccbp;

import com.google.common.collect.Lists;
import com.kingdee.internet.entity.ccbp.CCBP;
import com.kingdee.internet.repository.ccbp.CCBPDao;
import com.kingdee.internet.service.AbstractBankService;
import com.kingdee.internet.statemachine.Context;
import com.kingdee.internet.util.CommonUtils;
import com.kingdee.internet.util.ConfigUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service("ccbpBankService")
public class CCBPBankService extends AbstractBankService<CCBPDao, CCBP> {

    public static final Logger logger = LoggerFactory.getLogger(CCBPBankService.class);

    @Override
    protected List<CCBP> parseTxnDetails(Map<String, String> bankData, Context context) {
        List<CCBP> result = Lists.newArrayList();
        String filePath = downLoadFile(bankData, context);
        try {
            FileInputStream file = new FileInputStream(new File(filePath));
            HSSFWorkbook wb = new HSSFWorkbook(file);
            HSSFSheet sheet = wb.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            int count = 0;
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                if(++count < 7) continue;
                List<Cell> cells = Lists.newArrayList(row.cellIterator());
                if(StringUtils.startsWith(cells.get(0).getStringCellValue(), "以上数据仅供参考")) { // 最后一行
                    break;
                }
                double pay = cells.get(4).getNumericCellValue();
                double income = cells.get(5).getNumericCellValue();
                String amountStr = null;
                String flag = null;
                if(pay > 0) {
                    flag = ConfigUtils.FLAG_D;
                    amountStr = "" + pay;
                } else if(income > 0) {
                    flag = ConfigUtils.FLAG_C;
                    amountStr = "" + income;
                }
                CCBP ccbp = new CCBP.Builder()
                        .id(CommonUtils.uuid())
                        .accountNo(bankData.get("account"))
                        .currency(bankData.get("currency"))
                        .flag(flag)
                        .money(amountStr)
                        .balance(cells.get(6).getStringCellValue())
                        .otherAccountNo(cells.get(7).getStringCellValue())
                        .transferInTime(cells.get(1).getStringCellValue() + " " + cells.get(2).getStringCellValue(), "yyyyMMdd HH:mm:ss")
                        .otherOrgName(cells.get(7).getStringCellValue())
                        .channel(cells.get(3).getStringCellValue())
                        .digest(cells.get(11).getStringCellValue())
                        .build();
                result.add(ccbp);
            }
        } catch (Exception e) {
            logger.error("parse txn details failed.", e);
        }

        return result;
    }
}
