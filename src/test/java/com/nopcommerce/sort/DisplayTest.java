package com.nopcommerce.sort;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import environmentConfig.PropertiesConfig;
import pageObjects.user.PageGeneratorManager;
import pageObjects.user.UserHomePageObject;
import pageObjects.user.UserInventoryPageObject;

public class DisplayTest extends BaseTest {
	WebDriver driver;
	PropertiesConfig propertiesConfig;
	UserHomePageObject userHomePage;
	UserInventoryPageObject userInventoryPage;

	@Parameters({ "browser", "environment" })
	@BeforeClass
	public void beforeClass(String browserName, String environment) {
		propertiesConfig = PropertiesConfig.getProperties(environment);
		driver = getBrowserDriver(browserName, propertiesConfig.getUserUrl());

		userHomePage = PageGeneratorManager.getUserHomePage(driver);
		userHomePage.hoverToMenuAndClickToSubmenuByTitle(driver, "Computers", "Notebooks");

		userInventoryPage = PageGeneratorManager.getUserInventoryPage(driver);
	}

	@Test
	public void TC_01_Display_With_3_Products_Per_Page() {
		log.info("Display_01 - Step 01: Select Display dropdown with value '3' products per page");
		userInventoryPage.selectDisplayDropdown(driver, "3");

		log.info("Display_01 - Step 02: Verify product name displayed with value '3' products per page");
		verifyTrue(userInventoryPage.isProductNumberDisplayed(driver, "3"));
		verifyTrue(userInventoryPage.isPagerIconByTitleDisplayed(driver, "Next"));

		userInventoryPage.clickToLinkByTitle(driver, "Next");

		verifyTrue(userInventoryPage.isPagerIconByTitleDisplayed(driver, "Previous"));
	}

	@Test
	public void TC_02_Display_With_6_Products_Per_Page() {
		log.info("Display_02 - Step 01: Select Display dropdown with value '6' products per page");
		userInventoryPage.selectDisplayDropdown(driver, "6");

		log.info("Display_02 - Step 02: Verify product name displayed with value '6' products per page");
		verifyTrue(userInventoryPage.isProductNumberDisplayed(driver, "6"));
		verifyTrue(userInventoryPage.isPagingAreaNotDisplayed(driver));
	}

	@Test
	public void TC_03_Display_With_9_Products_Per_Page() {
		log.info("Display_03 - Step 01: Select Display dropdown with value '9' products per page'");
		userInventoryPage.selectDisplayDropdown(driver, "9");

		log.info("Display_03 - Step 02: Verify product name displayed with value '9' products per page");
		verifyTrue(userInventoryPage.isProductNumberDisplayed(driver, "9"));
		verifyTrue(userInventoryPage.isPagingAreaNotDisplayed(driver));
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserDriver();
	}
}
