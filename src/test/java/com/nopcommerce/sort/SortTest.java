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

public class SortTest extends BaseTest {
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
	public void TC_01_Sort_With_Name_A_To_Z() {
		log.info("Sort_01 - Step 01: Select Sort dropdown with value 'Name: A to Z'");
		userInventoryPage.selectSortDropdown(driver, "Name: A to Z");

		log.info("Sort_01 - Step 02: Verify product name sorted with value 'Name: A to Z'");
		verifyTrue(userInventoryPage.isProductNameSortedAscending(driver));
	}

	@Test
	public void TC_02_Sort_With_Name_Z_To_A() {
		log.info("Sort_02 - Step 01: Select Sort dropdown with value 'Name: Z to A'");
		userInventoryPage.selectSortDropdown(driver, "Name: Z to A");

		log.info("Sort_02 - Step 02: Verify product name sorted with value 'Name: Z to A'");
		verifyTrue(userInventoryPage.isProductNameSortedDescending(driver));
	}

	@Test
	public void TC_03_Sort_With_Price_Low_To_High() {
		log.info("Sort_03 - Step 01: Select Sort dropdown with value 'Price: Low to High'");
		userInventoryPage.selectSortDropdown(driver, "Price: Low to High");

		log.info("Sort_03 - Step 02: Verify product name sorted with value 'Price: Low to High'");
		verifyTrue(userInventoryPage.isProductPriceSortedAscending(driver));
	}

	@Test
	public void TC_04_Sort_With_Price_High_To_Low() {
		log.info("Sort_04 - Step 01: Select Sort dropdown with value 'Price: High to Low'");
		userInventoryPage.selectSortDropdown(driver, "Price: High to Low");

		log.info("Sort_04 - Step 02: Verify product name sorted with value 'Price: High to Low'");
		verifyTrue(userInventoryPage.isProductPriceSortedDescending(driver));
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserDriver();
	}
}
