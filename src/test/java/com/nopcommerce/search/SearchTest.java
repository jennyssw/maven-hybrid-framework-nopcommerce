package com.nopcommerce.search;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.nopcommerce.data.DataTest;

import commons.BaseTest;
import factoryEnvironment.Environment;
import pageObjects.user.PageGeneratorManager;
import pageObjects.user.UserHomePageObject;
import pageObjects.user.footer.UserSearchPageObject;
import pageObjects.user.header.UserLoginPageObject;
import pageObjects.user.header.UserRegisterPageObject;

public class SearchTest extends BaseTest {
	WebDriver driver;
	Environment env;
	UserHomePageObject userHomePage;
	UserLoginPageObject userLoginPage;
	UserRegisterPageObject userRegisterPage;
	UserSearchPageObject userSearchPage;
	String registerEmail = DataTest.UserRegister.PRE_EMAIL_ADDRESS + getRandomNumber() + DataTest.UserRegister.WEB_EMAIL_SERVER;

	@Parameters({ "browser", "environment" })
	@BeforeClass
	public void beforeClass(String browserName, String environment) {
		ConfigFactory.setProperty("env", environment.toLowerCase());
		env = ConfigFactory.create(Environment.class);
		driver = getBrowserDriver(browserName, env.getUserUrl());

		userHomePage = PageGeneratorManager.getUserHomePage(driver);
		userHomePage.openLinkInHeaderByTitle(driver, "Register");
		userRegisterPage = PageGeneratorManager.getUserRegisterPage(driver);

		userRegisterPage.enterToTextboxByID(driver, DataTest.UserRegister.FIRST_NAME, "FirstName");
		userRegisterPage.enterToTextboxByID(driver, DataTest.UserRegister.LAST_NAME, "LastName");
		userRegisterPage.enterToTextboxByID(driver, registerEmail, "Email");
		userRegisterPage.enterToTextboxByID(driver, DataTest.UserRegister.PASSWORD, "Password");
		userRegisterPage.enterToTextboxByID(driver, DataTest.UserRegister.PASSWORD, "ConfirmPassword");
		userRegisterPage.clickToButtonByTitleText(driver, "Register");

		verifyEquals(userRegisterPage.getPageSuccessMessage(driver), "Your registration completed");

		if (userRegisterPage.getTextLogInLogOutInHeaderLink(driver).equals("Log out")) {
			userRegisterPage.clickToLinkByTitle(driver, "Log out");
			userHomePage = PageGeneratorManager.getUserHomePage(driver);
			userHomePage.openLinkInHeaderByTitle(driver, "Log in");
			userLoginPage = PageGeneratorManager.getUserLoginPage(driver);
		} else if (userRegisterPage.getTextLogInLogOutInHeaderLink(driver).equals("Log in")) {
			userLoginPage = userRegisterPage.clickToLoginLinkInHeader(driver);
		} else {
			throw new RuntimeException("Performance issues!");
		}

		userLoginPage.enterToTextboxByID(driver, registerEmail, "Email");
		userLoginPage.enterToTextboxByID(driver, DataTest.UserLogin.PASSWORD, "Password");
		userLoginPage.clickToButtonByTitleText(driver, "Log in");
		userHomePage = PageGeneratorManager.getUserHomePage(driver);

		verifyTrue(userHomePage.isTopicTitleUserHomePageDisplayed(driver));
	}

	@Test
	public void TC_01_Search_With_Empty_Data() {
		log.info("Search_01 - Step 01: Click to Search link in footer");
		userHomePage.openLinkInFooterByTitle(driver, "Search");
		userSearchPage = PageGeneratorManager.getUserSearchPage(driver);

		log.info("Search_01 - Step 02: Click to Search button");
		userSearchPage.clickToSearchButton(driver);

		log.info("Search_01 - Step 03: Verify error message is displayed: 'Search term minimum length is 3 characters'");
		verifyTrue(userSearchPage.isErrorMessageDisplayedByTitle(driver, "Search term minimum length is 3 characters"));
	}

	@Test
	public void TC_02_Search_With_Data_Not_Exist() {
		log.info("Search_02 - Step 01: Enter to Search Keyword textbox with value 'MacBook Pro 2050'");
		userSearchPage.enterToSearchKeywordTextbox(driver, "MacBook Pro 2050");

		log.info("Search_02 - Step 02: Click to Search button");
		userSearchPage.clickToSearchButton(driver);

		log.info("Search_02 - Step 03: Verify error message is displayed: 'No products were found that matched your criteria.'");
		verifyTrue(userSearchPage.isErrorMessageDisplayedByTitle(driver, "No products were found that matched your criteria."));
	}

	@Test
	public void TC_03_Search_Relatively_With_Product_Name() {
		log.info("Search_03 - Step 01: Enter to Search Keyword textbox with value 'Lenovo'");
		userSearchPage.enterToSearchKeywordTextbox(driver, "Lenovo");

		log.info("Search_03 - Step 02: Click to Search button");
		userSearchPage.clickToSearchButton(driver);

		log.info("Search_03 - Step 03: Verify product link with name 'Lenovo IdeaCentre 600 All-in-One PC' is displayed");
		verifyTrue(userSearchPage.isProductLinkDisplayedByTitle(driver, "Lenovo IdeaCentre 600 All-in-One PC"));

		log.info("Search_03 - Step 04: Verify product link with name 'Lenovo Thinkpad X1 Carbon Laptop' is displayed");
		verifyTrue(userSearchPage.isProductLinkDisplayedByTitle(driver, "Lenovo Thinkpad X1 Carbon Laptop"));
	}

	@Test
	public void TC_04_Search_Absolutely_With_Product_Name() {
		log.info("Search_04 - Step 01: Enter to Search Keyword textbox with value 'ThinkPad X1 Carbon'");
		userSearchPage.enterToSearchKeywordTextbox(driver, "ThinkPad X1 Carbon");

		log.info("Search_04 - Step 02: Click to Search button");
		userSearchPage.clickToSearchButton(driver);

		log.info("Search_04 - Step 03: Verify product link with name 'Lenovo Thinkpad X1 Carbon Laptop' is displayed");
		verifyTrue(userSearchPage.isProductLinkDisplayedByTitle(driver, "Lenovo Thinkpad X1 Carbon Laptop"));
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserDriver();
	}
}
