package commons;

import java.io.IOException;
import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.Reporter;

public class BaseTest {
	private WebDriver driver;
	protected final Log log;
	String projectPath = GlobalConstants.PROJECT_PATH;

	protected BaseTest() {
		log = LogFactory.getLog(getClass());
	}

	protected WebDriver getBrowserDriver(String browserName, String urlValue) {
		BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());
		switch (browserList) {
		case FIREFOX:
			driver = new FirefoxDriver();
			break;
		case FIREFOX_HEADLESS:
			FirefoxOptions ffHeadOptions = new FirefoxOptions();
			ffHeadOptions.addArguments("-headless");
			ffHeadOptions.addArguments("window-size=1920x1080");
			driver = new FirefoxDriver(ffHeadOptions);
			break;
		case CHROME:
			ChromeOptions chromeOptions = new ChromeOptions();
			Map<String, Object> chromePrefs = new HashMap<String, Object>();
			chromePrefs.put("profile.default_content_setting_values.notifications", 2);
			chromePrefs.put("credentials_enable_service", false);
			chromePrefs.put("profile.password_manager_enabled", false);
			chromePrefs.put("autofill.profile_enabled", false);
			chromePrefs.put("autofill.credit_card_enabled", false);
			chromeOptions.setExperimentalOption("prefs", chromePrefs);
			chromeOptions.setExperimentalOption("useAutomationExtension", false);
			chromeOptions.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
			chromeOptions.addArguments("--disable-notifications");
			driver = new ChromeDriver(chromeOptions);
			break;
		case CHROME_HEADLESS:
			ChromeOptions chromeHeadOptions = new ChromeOptions();
			chromeHeadOptions.addArguments("--headless");
			chromeHeadOptions.addArguments("window-size=1920x1080");
			driver = new ChromeDriver(chromeHeadOptions);
			break;
		case EDGE:
			EdgeOptions edgeOptions = new EdgeOptions();
			Map<String, Object> edgePrefs = new HashMap<String, Object>();
			edgePrefs.put("profile.default_content_setting_values.notifications", 2);
			edgePrefs.put("credentials_enable_service", false);
			edgePrefs.put("profile.password_manager_enabled", false);
			edgePrefs.put("autofill.profile_enabled", false);
			edgePrefs.put("autofill.credit_card_enabled", false);
			edgeOptions.setExperimentalOption("prefs", edgePrefs);
			edgeOptions.setExperimentalOption("useAutomationExtension", false);
			edgeOptions.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
			edgeOptions.addArguments("--disable-notifications");
			driver = new EdgeDriver(edgeOptions);
			break;
		case SAFARI:
			driver = new SafariDriver();
			break;
		default:
			throw new RuntimeException("Browser name is not valid!");
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
		driver.manage().window().maximize();
		driver.get(urlValue);

		return driver;
	}

	protected static int getRandomNumber() {
		Random rand = new Random();
		return 100000 + rand.nextInt(900000);
	}

	protected String getReverseString(WebDriver driver, String str) {
		StringBuilder sb = new StringBuilder(str);
		sb.reverse();
		return sb.toString();
	}

	protected boolean verifyTrue(boolean condition) {
		boolean status = true;
		try {
			Assert.assertTrue(condition);
			log.info("------------------------ PASSED ------------------------");
		} catch (Throwable e) {
			status = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
			log.info("------------------------ FAILED ------------------------");
		}
		return status;
	}

	protected boolean verifyFalse(boolean condition) {
		boolean status = true;
		try {
			Assert.assertFalse(condition);
			log.info("------------------------ PASSED ------------------------");
		} catch (Throwable e) {
			status = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
			log.info("------------------------ FAILED ------------------------");
		}
		return status;
	}

	protected boolean verifyEquals(Object actual, Object expected) {
		boolean status = true;
		try {
			Assert.assertEquals(actual, expected);
			log.info("------------------------ PASSED ------------------------");
		} catch (Throwable e) {
			status = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
			log.info("------------------------ FAILED ------------------------");
		}
		return status;
	}

	public WebDriver getDriver() {
		return this.driver;
	}

	protected void closeBrowserDriver() {
		String cmd = null;
		try {
			String osName = GlobalConstants.OS_NAME;
			log.info("OS name = " + osName);

			String driverInstanceName = driver.toString().toLowerCase();
			log.info("Driver instance name = " + driverInstanceName);

			String browserDriverName = null;

			if (driverInstanceName.contains("chrome")) {
				browserDriverName = "chromedriver";
			} else if (driverInstanceName.contains("firefox")) {
				browserDriverName = "geckodriver";
			} else if (driverInstanceName.contains("edge")) {
				browserDriverName = "msedgedriver";
			} else {
				browserDriverName = "safaridriver";
			}

			if (osName.contains("windows")) {
				cmd = "taskkill /F /FI \"IMAGENAME eq " + browserDriverName + "*\"";
			} else {
				cmd = "pkill " + browserDriverName;
			}

			if (driver != null) {
				driver.manage().deleteAllCookies();
				driver.quit();
			}
		} catch (Exception e) {
			log.info(e.getMessage());
		} finally {
			try {
				Process process = Runtime.getRuntime().exec(cmd);
				process.waitFor();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}