package com.acceptance.tests.selenium;

import com.paulhammant.ngwebdriver.NgWebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class SharedWebDriver {

    private static final int DEFAULT_WAIT_TIME = 10;
    private Logger log = LoggerFactory.getLogger(getClass());

    private static SharedWebDriver instance;

    private RemoteWebDriver driver;
    private NgWebDriver webDriver;

    enum Browser {CHROME, FIREFOX, HTMLUNIT}

    private String baseUrl;

    public static SharedWebDriver getInstance() {
        if (instance == null) {
            instance = new SharedWebDriver();
            instance.init();
        }

        return instance;
    }

    @PostConstruct//?
    public void init() {
        switch (getBrowser()) {

            case CHROME:
                WebDriverManager.chromedriver().setup();

                ChromeOptions options = new ChromeOptions();
                options.addArguments("disable-extensions");
                options.addArguments("window-size=1280,1024");
                options.addArguments("window-position=0,0");
                options.addArguments("no-sandbox");
                options.addArguments("disable-infobars");

                LoggingPreferences logPrefs = new LoggingPreferences();
                logPrefs.enable(LogType.BROWSER, Level.ALL);
                DesiredCapabilities caps = DesiredCapabilities.chrome();
                caps.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);

                caps.setCapability(ChromeOptions.CAPABILITY, options);
                driver = new ChromeDriver(caps);
                break;
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();

                break;
            case HTMLUNIT:
                break;
        }
        driver.manage().timeouts().implicitlyWait(DEFAULT_WAIT_TIME, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
//        driver.manage().window().setSize(new Dimension(1280, 1024));

        webDriver = new NgWebDriver(driver);
        // close selenium when all of the tests are finished
        attachShutDownHook();
    }

    public void cleanUp() {
        instance.getDriver().manage().deleteAllCookies();

        try {
            executeJavascript("javascript:localStorage.clear();");
        } catch (Exception e) {
            // ignore, cannot clear localStorage if the page hasn't been loaded
        }
        executeJavascript("console.clear()");
        // this clears the current console log store.
        getDriver().manage().logs().get(LogType.BROWSER);
    }

    private void executeJavascript(String javascript) {
        driver.executeScript(javascript);

    }

    public void createBaseUrl(int port) {
        baseUrl = "http://localhost:" + port + "/";
        log.info("running with base url: {}", baseUrl);
    }

    public void home() {
        navigate();
    }

    private void navigate() {
        getDriver().navigate().to(baseUrl + "");
    }

    private void shutDown() {
        log.info("shutting down selenium driver");
        try {

            if (driver != null) {
                driver.close();
                driver.quit();
            }
        } catch (Exception e) {
            log.error("could not close browser, close it by hand", e);
        }
    }

    public WebDriver getDriver() {
        return driver;
    }

    private Browser getBrowser() {
        Browser browser = Browser.CHROME;
        log.info("creating BROWSER of type: {}", browser.name());
        return browser;
    }

    private void attachShutDownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::shutDown));
        log.info("Shut Down Hook Attached.");
    }

    public static void main(String[] args) {
        SharedWebDriver.getInstance();
    }

    public void waitForAngular() {
        webDriver.waitForAngularRequestsToFinish();
    }

}
