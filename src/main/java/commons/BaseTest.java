package commons;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.Reporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	private WebDriver driver;
	protected final Log log;
	String projectPath = GlobalConstants.PROJECT_PATH;

	public BaseTest() {
		log = LogFactory.getLog(getClass());
	}

	protected WebDriver getBrowserDriver(String browserName, String urlValue) {
		BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());
		switch (browserList) {
		case FIREFOX:
			WebDriverManager.firefoxdriver().driverVersion("0.31.0").setup();
			driver = new FirefoxDriver();
			break;
		case FIREFOX_HEADLESS:
			FirefoxOptions ffHeadOptions = new FirefoxOptions();
			ffHeadOptions.addArguments("-headless");
			ffHeadOptions.addArguments("window-size=1920x1080");
			driver = WebDriverManager.firefoxdriver().capabilities(ffHeadOptions).create();
			break;
		case CHROME:
			// driver = WebDriverManager.chromedriver().create();
			ChromeOptions chromeOptions = new ChromeOptions();
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("autofill.profile_enabled", false);
			chromeOptions.setExperimentalOption("prefs", prefs);
			driver = WebDriverManager.chromedriver().capabilities(chromeOptions).create();
			break;
		case CHROME_HEADLESS:
			ChromeOptions chromeHeadOptions = new ChromeOptions();
			chromeHeadOptions.addArguments("-headless");
			chromeHeadOptions.addArguments("window-size=1920x1080");
			driver = WebDriverManager.chromedriver().capabilities(chromeHeadOptions).create();
			break;
		case COC_COC:
			WebDriverManager.chromedriver().driverVersion(browserName).setup();
			ChromeOptions cocCocOptions = new ChromeOptions();
			cocCocOptions.setBinary("C:\\Users\\Admin\\AppData\\Local\\CocCoc\\Browser\\Application\\browser.exe");
			driver = new ChromeDriver(cocCocOptions);
		case EDGE:
			driver = WebDriverManager.edgedriver().create();
			break;
		case OPERA:
			driver = WebDriverManager.operadriver().create();
			break;
		case SAFARI:
			driver = new SafariDriver();
			break;
		default:
			throw new RuntimeException("Browser name is not valid!");
		}

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(urlValue);

		return driver;
	}

	protected static int getRandomNumber() {
		Random rand = new Random();
		return 100000 + rand.nextInt(900000);
	}

	protected String getCurrentTime() {
		Date date = new Date();
		return date.toString();
	}

	protected String getReverseString(WebDriver driver, String str) {
		StringBuilder sb = new StringBuilder(str);
		sb.reverse();
		return sb.toString();
	}

	protected String getReverseString(WebDriver driver, int num) {
		StringBuilder sb = new StringBuilder(num);
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
			} else if (driverInstanceName.contains("opera")) {
				browserDriverName = "operadriver";
			} else {
				browserDriverName = "safaridriver";
			}

			if (osName.contains("Windows")) {
				cmd = "taskkill /F /FI \"IMAGENAME eq " + browserDriverName + "*\"";
			} else {
				cmd = "pkill " + browserDriverName;
			}

			if (driver != null) {
				driver.manage().deleteAllCookies();
				driver.quit();
			}
		} catch (

		Exception e) {
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