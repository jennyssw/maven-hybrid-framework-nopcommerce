package com.nopcommerce.wishlist;

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
import pageObjects.user.UserInventoryPageObject;
import pageObjects.user.footer.UserRecentlyViewedProductsPageObject;
import pageObjects.user.header.UserLoginPageObject;
import pageObjects.user.header.UserRegisterPageObject;

public class RecentlyViewedProductTest extends BaseTest {
	WebDriver driver;
	Environment env;
	UserHomePageObject userHomePage;
	UserLoginPageObject userLoginPage;
	UserRegisterPageObject userRegisterPage;
	UserInventoryPageObject userInventoryPage;
	UserRecentlyViewedProductsPageObject userRecentlyViewedProductsPage;
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
	public void TC_01_Recently_Viewed_Products() {
		log.info("Wishlist_01 - Step 01: Click to product link with title: Samsung Series 9 NP900X4C Premium Ultrabook");
		userHomePage.hoverToMenuAndClickToSubmenuByTitle(driver, "Computers", "Notebooks");
		userInventoryPage = PageGeneratorManager.getUserInventoryPage(driver);

		userInventoryPage.clickToLinkByTitleInInventoryPage(driver, "Samsung Series 9 NP900X4C Premium Ultrabook");
		userInventoryPage.backToPage(driver);

		log.info("Wishlist_01 - Step 02: Click to product link with title: Asus N551JK-XO076H Laptop");
		userInventoryPage.clickToLinkByTitleInInventoryPage(driver, "Asus N551JK-XO076H Laptop");
		userInventoryPage.backToPage(driver);

		String recentlyViewedProduct1 = "HP Envy 6-1180ca 15.6-Inch Sleekbook";
		log.info("Wishlist_01 - Step 03: Click to product link with title: " + recentlyViewedProduct1);
		userInventoryPage.clickToLinkByTitleInInventoryPage(driver, recentlyViewedProduct1);
		userInventoryPage.backToPage(driver);

		String recentlyViewedProduct2 = "HP Spectre XT Pro UltraBook";
		log.info("Wishlist_01 - Step 04: Click to product link with title: " + recentlyViewedProduct2);
		userInventoryPage.clickToLinkByTitleInInventoryPage(driver, recentlyViewedProduct2);
		userInventoryPage.backToPage(driver);

		String recentlyViewedProduct3 = "Lenovo Thinkpad X1 Carbon Laptop";
		log.info("Wishlist_01 - Step 05: Click to product link with title: " + recentlyViewedProduct3);
		userInventoryPage.clickToLinkByTitleInInventoryPage(driver, recentlyViewedProduct3);

		log.info("Wishlist_01 - Step 06: Click to footer link with title 'Recently viewed products'");
		userInventoryPage.clickToLinkByTitle(driver, "Recently viewed products");
		userRecentlyViewedProductsPage = PageGeneratorManager.getUserRecentlyViewedProductsPage(driver);

		log.info("Wishlist_01 - Step 07: Verify 03 products viewed recently is diplayed");
		verifyTrue(userRecentlyViewedProductsPage.isProductAddedToRecentlyViewedProductsPageDisplayed(driver, recentlyViewedProduct1));
		verifyTrue(userRecentlyViewedProductsPage.isProductAddedToRecentlyViewedProductsPageDisplayed(driver, recentlyViewedProduct2));
		verifyTrue(userRecentlyViewedProductsPage.isProductAddedToRecentlyViewedProductsPageDisplayed(driver, recentlyViewedProduct3));
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserDriver();
	}
}
