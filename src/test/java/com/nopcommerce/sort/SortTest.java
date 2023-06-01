package com.nopcommerce.sort;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import factoryEnvironment.Environment;
import pageObjects.user.PageGeneratorManager;
import pageObjects.user.UserHomePageObject;
import pageObjects.user.UserInventoryPageObject;

public class SortTest extends BaseTest {
	WebDriver driver;
	Environment env;
	UserHomePageObject userHomePage;
	UserInventoryPageObject userInventoryPage;

	@Parameters({ "browser", "environment" })
	@BeforeClass
	public void beforeClass(String browserName, String environment) {
		ConfigFactory.setProperty("env", environment.toLowerCase());
		env = ConfigFactory.create(Environment.class);
		driver = getBrowserDriver(browserName, env.getUserUrl());
		userHomePage = PageGeneratorManager.getUserHomePage(driver);
		userHomePage.hoverToMenuAndClickToSubmenuByTitle(driver, "Computers", "Notebooks");

		userInventoryPage = PageGeneratorManager.getUserInventoryPage(driver);
	}

	@Test
	public void TC_01_Sort_With_Name_A_To_Z() {
		log.info("Sort_01 - Step 01: Select Sort dropdown with value 'Name: A to Z'");
		userInventoryPage.selectSortDropdown(driver, "Name: A to Z");

		log.info("Sort_01 - Step 02: Verify product name sorted with value 'Name: A to Z'");
		verifyTrue(userInventoryPage.isProductNameSortAscending(driver));
	}

	@Test
	public void TC_02_Sort_With_Name_Z_To_A() {
		log.info("Sort_02 - Step 01: Select Sort dropdown with value 'Name: Z to A'");
		userInventoryPage.selectSortDropdown(driver, "Name: Z to A");

		log.info("Sort_02 - Step 02: Verify product name sorted with value 'Name: Z to A'");
		verifyTrue(userInventoryPage.isProductNameSortDescending(driver));
	}

	@Test
	public void TC_03_Sort_With_Price_Low_To_High() {
		log.info("Sort_03 - Step 01: Select Sort dropdown with value 'Price: Low to High'");
		userInventoryPage.selectSortDropdown(driver, "Price: Low to High");

		log.info("Sort_03 - Step 02: Verify product name sorted with value 'Price: Low to High'");
		verifyTrue(userInventoryPage.isProductPriceSortAscending(driver));
	}

	@Test
	public void TC_04_Sort_With_Price_High_To_Low() {
		log.info("Sort_04 - Step 01: Select Sort dropdown with value 'Price: High to Low'");
		userInventoryPage.selectSortDropdown(driver, "Price: High to Low");

		log.info("Sort_04 - Step 02: Verify product name sorted with value 'Price: High to Low'");
		verifyTrue(userInventoryPage.isProductPriceSortDescending(driver));
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserDriver();
	}
}
