package com.kingdee.internet.service;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
public class TaskService {
    public static final Logger logger = LoggerFactory.getLogger(TaskService.class);

//    @Autowired
//    @Qualifier("ieWebDriver")
//    private WebDriver webDriver;
}
