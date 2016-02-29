package com.kingdee.internet.service.ccbp;

import com.kingdee.internet.entity.ccbp.CCBP;
import com.kingdee.internet.repository.ccbp.CCBPDao;
import com.kingdee.internet.service.AbstractBankService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("ccbpBankService")
public class CCBPBankService extends AbstractBankService<CCBPDao, CCBP> {

    @Override
    protected List<CCBP> parseTxnDetails(Map<String, String> bankData) {
        return null;
    }
}
