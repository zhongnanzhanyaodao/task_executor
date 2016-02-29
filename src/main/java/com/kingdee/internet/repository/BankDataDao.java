package com.kingdee.internet.repository;

import com.kingdee.internet.entity.AbstractBankData;
import org.springframework.data.repository.CrudRepository;

public interface BankDataDao<T extends AbstractBankData> extends CrudRepository<T, String> {

}
