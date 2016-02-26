package com.kingdee.internet.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.service.DriverService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

import java.io.File;
import java.util.concurrent.Semaphore;

@Configuration
public class SeleniumConfig {
    @Value("${webdriver.ie.driver}")
    private String executableDriver4IE;

    @Value("${webdriver.chrome.driver}")
    private String executableDriver4Chrome;

    @Lazy(true)
    @Bean(name = "ieDriverService", initMethod = "start", destroyMethod = "stop")
    public DriverService ieDriverService() {
        return new InternetExplorerDriverService.Builder()
                .usingAnyFreePort()
                .usingDriverExecutable(new File(executableDriver4IE))
                .build();
    }

    @Lazy(true)
    @Bean(name = "chromeDriverService", initMethod = "start", destroyMethod = "stop")
    public DriverService chromeDriverService() {
        return new ChromeDriverService.Builder()
                .usingAnyFreePort()
                .usingDriverExecutable(new File(executableDriver4Chrome))
                .build();
    }

    @Scope(value = "prototype")
    @Bean(name = "ieRemoteWebDriver")
    public WebDriver ieRemoteWebDriver() {
        return new RemoteWebDriver(ieDriverService().getUrl(),
                DesiredCapabilities.internetExplorer());
    }

    @Scope(value = "prototype")
    @Bean(name = "chromeRemoteWebDriver")
    public WebDriver chromeRemoteWebDriver() {
        return new RemoteWebDriver(chromeDriverService().getUrl(),
                DesiredCapabilities.chrome());
    }

    @Scope(value = "prototype")
    @Bean(name = "chromeWebDriver", destroyMethod = "quit")
    @DependsOn("systemPrereqs")
    public WebDriver chromeWebDriver() {
        return new ChromeDriver();
    }

    @Scope(value = "prototype")
    @Bean(name = "ieWebDriver", destroyMethod = "quit")
    @DependsOn("systemPrereqs")
    public WebDriver ieWebDriver() {
        return new InternetExplorerDriver();
    }

    @Bean(name = "taskSemaphore")
    public Semaphore taskSemaphore(@Value("${task.pool.size}") int taskPoolSize) {
        return new Semaphore(taskPoolSize);
    }
}
