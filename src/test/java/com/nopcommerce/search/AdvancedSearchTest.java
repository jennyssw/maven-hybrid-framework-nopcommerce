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

public class AdvancedSearchTest extends BaseTest {

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
	public void TC_01_Advanced_Search_With_Parent_Categories() {
		log.info("Advanced_Search_01 - Step 01: Click to Search link in footer");
		userHomePage.openLinkInFooterByTitle(driver, "Search");
		userSearchPage = PageGeneratorManager.getUserSearchPage(driver);

		log.info("Advanced_Search_01 - Step 02: Enter to Search Keyword textbox with value 'Apple MacBook Pro'");
		userSearchPage.enterToSearchKeywordTextbox(driver, "Apple MacBook Pro");

		log.info("Advanced_Search_01 - Step 03: Select 'Advanced search' checkbox");
		userSearchPage.checkToCheckboxOrRadioByTitle(driver, "Advanced search");

		log.info("Advanced_Search_01 - Step 04: Enter to Category dropdown with value 'Computers'");
		userSearchPage.enterToDropdownByTitle(driver, "Computers", "Category:");

		log.info("Advanced_Search_01 - Step 05: Click to Search button");
		userSearchPage.clickToSearchButton(driver);

		log.info("Advanced_Search_01 - Step 06: Verify error message is displayed: 'No products were found that matched your criteria.'");
		verifyTrue(userSearchPage.isErrorMessageDisplayedByTitle(driver, "No products were found that matched your criteria."));
	}

	@Test
	public void TC_02_Advanced_Search_With_Sub_Categories() {
		log.info("Advanced_Search_02 - Step 01: Enter to Search Keyword textbox with value 'Apple MacBook Pro'");
		userSearchPage.enterToSearchKeywordTextbox(driver, "Apple MacBook Pro");

		log.info("Advanced_Search_02 - Step 02: Select 'Advanced search' checkbox");
		userSearchPage.checkToCheckboxOrRadioByTitle(driver, "Advanced search");

		log.info("Advanced_Search_02 - Step 03: Enter to Category dropdown with value 'Computers'");
		userSearchPage.enterToDropdownByTitle(driver, "Computers", "Category:");

		log.info("Advanced_Search_02 - Step 04: Select 'Automatically search sub categories' checkbox");
		userSearchPage.checkToCheckboxOrRadioByTitle(driver, "Automatically search sub categories");

		log.info("Advanced_Search_02 - Step 05: Click to Search button");
		userSearchPage.clickToSearchButton(driver);

		log.info("Advanced_Search_02 - Step 06: Verify product link with name 'Apple MacBook Pro 13-inch' is displayed");
		userSearchPage.isProductLinkDisplayedByTitle(driver, "Apple MacBook Pro 13-inch");
	}

	@Test
	public void TC_03_Advanced_Search_With_Incorrect_Manufacturer() {
		log.info("Advanced_Search_03 - Step 01: Enter to Search Keyword textbox with value 'Apple MacBook Pro'");
		userSearchPage.enterToSearchKeywordTextbox(driver, "Apple MacBook Pro");

		log.info("Advanced_Search_03 - Step 02: Select 'Advanced search' checkbox");
		userSearchPage.checkToCheckboxOrRadioByTitle(driver, "Advanced search");

		log.info("Advanced_Search_03 - Step 03: Enter to Category dropdown with value 'Computers'");
		userSearchPage.enterToDropdownByTitle(driver, "Computers", "Category:");

		log.info("Advanced_Search_03 - Step 04: Select 'Automatically search sub categories' checkbox");
		userSearchPage.checkToCheckboxOrRadioByTitle(driver, "Automatically search sub categories");

		log.info("Advanced_Search_03 - Step 05: Enter to Manufacturer dropdown with value 'HP'");
		userSearchPage.enterToDropdownByTitle(driver, "HP", "Manufacturer:");

		log.info("Advanced_Search_03 - Step 06: Click to Search button");
		userSearchPage.clickToSearchButton(driver);

		log.info("Advanced_Search_03 - Step 07: Verify error message is displayed: 'No products were found that matched your criteria.'");
		verifyTrue(userSearchPage.isErrorMessageDisplayedByTitle(driver, "No products were found that matched your criteria."));
	}

	@Test
	public void TC_04_Advanced_Search_With_Correct_Manufacturer() {
		log.info("Advanced_Search_04 - Step 01: Enter to Search Keyword textbox with value 'Apple MacBook Pro'");
		userSearchPage.enterToSearchKeywordTextbox(driver, "Apple MacBook Pro");

		log.info("Advanced_Search_04 - Step 02: Select 'Advanced search' checkbox");
		userSearchPage.checkToCheckboxOrRadioByTitle(driver, "Advanced search");

		log.info("Advanced_Search_04 - Step 03: Enter to Category dropdown with value 'Computers'");
		userSearchPage.enterToDropdownByTitle(driver, "Computers", "Category:");

		log.info("Advanced_Search_04 - Step 04: Select 'Automatically search sub categories' checkbox");
		userSearchPage.checkToCheckboxOrRadioByTitle(driver, "Automatically search sub categories");

		log.info("Advanced_Search_04 - Step 05: Enter to Manufacturer dropdown with value 'Apple'");
		userSearchPage.enterToDropdownByTitle(driver, "Apple", "Manufacturer:");

		log.info("Advanced_Search_04 - Step 06: Click to Search button");
		userSearchPage.clickToSearchButton(driver);

		log.info("Advanced_Search_04 - Step 07: Verify product link with name 'Apple MacBook Pro 13-inch' is displayed");
		userSearchPage.isProductLinkDisplayedByTitle(driver, "Apple MacBook Pro 13-inch");
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserDriver();
	}
}
