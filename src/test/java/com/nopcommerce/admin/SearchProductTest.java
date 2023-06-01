package com.nopcommerce.admin;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.nopcommerce.data.DataTest;

import commons.BaseTest;
import factoryEnvironment.Environment;
import pageObjects.admin.AdminEditProductDetailsPageObject;
import pageObjects.admin.AdminLoginPageObject;
import pageObjects.admin.PageGeneratorManager;
import pageObjects.admin.menu.AdminDashboardPageObject;
import pageObjects.admin.menu.AdminProductsPageObject;

public class SearchProductTest extends BaseTest {
	WebDriver driver;
	Environment env;
	AdminLoginPageObject adminLoginPage;
	AdminDashboardPageObject adminDashboardPage;
	AdminProductsPageObject adminProductsPage;
	AdminEditProductDetailsPageObject adminEditProductDetailsPage;
	String productName = DataTest.AdminSearchProduct.PRODUCT_NAME;
	String sku = DataTest.AdminSearchProduct.SKU;
	String price = DataTest.AdminSearchProduct.PRICE;
	String stockQuantity = DataTest.AdminSearchProduct.STOCK_QUANTITY;

	@Parameters({ "browser", "environment" })
	@BeforeClass
	public void beforeClass(String browserName, String environment) {
		ConfigFactory.setProperty("env", environment.toLowerCase());
		env = ConfigFactory.create(Environment.class);
		driver = getBrowserDriver(browserName, env.getAdminUrl());

		adminLoginPage = PageGeneratorManager.getAdminLoginPage(driver);
		adminLoginPage.enterToTextboxByID(driver, DataTest.AdminLogin.ADMIN_EMAIL, "Email");
		adminLoginPage.enterToTextboxByID(driver, DataTest.AdminLogin.ADMIN_PASSWORD, "Password");
		adminLoginPage.clickToButtonByTitleText(driver, "Log in");
		adminDashboardPage = PageGeneratorManager.getAdminDashboardPage(driver);
	}

	@Test
	public void TC_01_Search_With_Product_Name() {
		log.info("Search_Product_01 - Step 01: Click to Catalog link in menu and Products link in sub menu");
		adminProductsPage = adminDashboardPage.openAdminProductsPage(driver, "Catalog", "Products");

		log.info("Search_Product_01 - Step 02: Enter to Product Name textbox: " + productName);
		adminProductsPage.openSearchProductCard(driver);
		adminProductsPage.enterToTextboxByID(driver, productName, "SearchProductName");

		log.info("Search_Product_01 - Step 03: Click to Search button");
		adminProductsPage.clickToSearchProductButton(driver);

		log.info("Search_Product_01 - Step 04: Verify only one item in table is displayed");
		verifyTrue(adminProductsPage.isProductInfoDisplayed(driver, "odd", productName, sku, price, stockQuantity));
		verifyTrue(adminProductsPage.isOneItemInTableDisplayed(driver));
	}

	@Test
	public void TC_02_Search_With_Product_Name_Parent_Category_Unchecked() {
		log.info("Search_Product_02 - Step 01: Enter to Product Name textbox: " + productName);
		adminProductsPage.openSearchProductCard(driver);
		adminProductsPage.enterToTextboxByID(driver, productName, "SearchProductName");

		log.info("Search_Product_02 - Step 02: Enter to Category dropdown with value 'Computers'");
		adminProductsPage.selectItemInDefaultDropdownByID(driver, "Computers", "SearchCategoryId");

		log.info("Search_Product_02 - Step 03: Uncheck Search Sub Categories checkbox");
		adminProductsPage.uncheckSearchSubCategoriesCheckbox(driver);

		log.info("Search_Product_02 - Step 04: Click to Search button");
		adminProductsPage.clickToSearchProductButton(driver);

		log.info("Search_Product_02 - Step 05: Verify no item in table is displayed with message 'No data available in table'");
		verifyTrue(adminProductsPage.isNoItemInTableDisplayed(driver));
	}

	@Test
	public void TC_03_Search_With_Product_Name_Parent_Category_Checked() {
		log.info("Search_Product_03 - Step 01: Enter to Product Name textbox: " + productName);
		adminProductsPage.openSearchProductCard(driver);
		adminProductsPage.enterToTextboxByID(driver, productName, "SearchProductName");

		log.info("Search_Product_03 - Step 02: Enter to Category dropdown with value 'Computers'");
		adminProductsPage.selectItemInDefaultDropdownByID(driver, "Computers", "SearchCategoryId");

		log.info("Search_Product_03 - Step 03: Check Search Sub Categories checkbox");
		adminProductsPage.checkSearchSubCategoriesCheckbox(driver);

		log.info("Search_Product_03 - Step 04: Click to Search button");
		adminProductsPage.clickToSearchProductButton(driver);

		log.info("Search_Product_03 - Step 05: Verify only one item in table is displayed");
		verifyTrue(adminProductsPage.isProductInfoDisplayed(driver, "odd", productName, sku, price, stockQuantity));
		verifyTrue(adminProductsPage.isOneItemInTableDisplayed(driver));
	}

	@Test
	public void TC_04_Search_With_Product_Name_Child_Category_Unchecked() {
		log.info("Search_Product_04 - Step 01: Enter to Product Name textbox: " + productName);
		adminProductsPage.openSearchProductCard(driver);
		adminProductsPage.enterToTextboxByID(driver, productName, "SearchProductName");

		log.info("Search_Product_04 - Step 02: Enter to Category dropdown with value 'Computers >> Desktops'");
		adminProductsPage.selectItemInDefaultDropdownByID(driver, "Computers >> Desktops", "SearchCategoryId");

		log.info("Search_Product_04 - Step 03: Uncheck Search Sub Categories checkbox");
		adminProductsPage.uncheckSearchSubCategoriesCheckbox(driver);

		log.info("Search_Product_04 - Step 04: Click to Search button");
		adminProductsPage.clickToSearchProductButton(driver);

		log.info("Search_Product_04 - Step 05: Verify only one item in table is displayed");
		verifyTrue(adminProductsPage.isProductInfoDisplayed(driver, "odd", productName, sku, price, stockQuantity));
		verifyTrue(adminProductsPage.isOneItemInTableDisplayed(driver));
	}

	@Test
	public void TC_05_Search_With_Product_Name_Manufacturer() {
		log.info("Search_Product_05 - Step 01: Enter to Product Name textbox: " + productName);
		adminProductsPage.openSearchProductCard(driver);
		adminProductsPage.enterToTextboxByID(driver, productName, "SearchProductName");

		log.info("Search_Product_05 - Step 02: Enter to Category dropdown with value 'All'");
		adminProductsPage.selectItemInDefaultDropdownByID(driver, "All", "SearchCategoryId");

		log.info("Search_Product_05 - Step 03: Uncheck Search Sub Categories checkbox");
		adminProductsPage.uncheckSearchSubCategoriesCheckbox(driver);

		log.info("Search_Product_05 - Step 04: Enter to Manufacturer dropdown with value 'Apple'");
		adminProductsPage.selectItemInDefaultDropdownByID(driver, "Apple", "SearchManufacturerId");

		log.info("Search_Product_05 - Step 05: Click to Search button");
		adminProductsPage.clickToSearchProductButton(driver);

		log.info("Search_Product_05 - Step 06: Verify no item in table is displayed with message 'No data available in table'");
		verifyTrue(adminProductsPage.isNoItemInTableDisplayed(driver));
	}

	@Test
	public void TC_06_Go_Directly_To_Product_SKU() {
		log.info("Search_Product_06 - Step 01: Enter to Go Directly To Product SKU textbox: " + sku);
		adminProductsPage.enterToTextboxByID(driver, sku, "GoDirectlyToSku");

		log.info("Search_Product_06 - Step 02: Click to Go button");
		adminProductsPage.clickToButtonByID(driver, "go-to-product-by-sku");
		adminEditProductDetailsPage = PageGeneratorManager.getAdminEditProductDetailsPage(driver);

		log.info("Search_Product_06 - Step 03: Verify 'Edit Product Details' page is displayed");
		verifyTrue(adminEditProductDetailsPage.isPageTitleDisplayed(driver, "Edit product details"));

		log.info("Search_Product_06 - Step 04: Verify product information is displayed");
		verifyTrue(adminEditProductDetailsPage.isProductInfoDisplayed(driver, "Product name", productName));
		verifyTrue(adminEditProductDetailsPage.isProductInfoDisplayed(driver, "SKU", sku));
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserDriver();
	}
}
