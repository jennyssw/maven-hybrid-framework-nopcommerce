package com.nopcommerce.wishlist;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.nopcommerce.data.DataTest;

import commons.BaseTest;
import environmentConfig.PropertiesConfig;
import pageObjects.user.PageGeneratorManager;
import pageObjects.user.UserHomePageObject;
import pageObjects.user.UserInventoryPageObject;
import pageObjects.user.UserProductPageObject;
import pageObjects.user.footer.UserCompareProductsPageObject;
import pageObjects.user.header.UserLoginPageObject;
import pageObjects.user.header.UserRegisterPageObject;
import pageObjects.user.header.UserShoppingCartPageObject;
import pageObjects.user.header.UserWishlistPageObject;

public class WishlistTest extends BaseTest {
	WebDriver driver;
	PropertiesConfig propertiesConfig;
	UserHomePageObject userHomePage;
	UserLoginPageObject userLoginPage;
	UserRegisterPageObject userRegisterPage;
	UserProductPageObject userProductPage;
	UserWishlistPageObject userWishlistPage;
	UserShoppingCartPageObject userShoppingCartPage;
	UserInventoryPageObject userInventoryPage;
	UserCompareProductsPageObject userCompareProductsPage;
	String sku, productName, unitPrice, quantity;
	String registerEmail = DataTest.UserRegister.PRE_EMAIL_ADDRESS + getRandomNumber() + DataTest.UserRegister.WEB_EMAIL_SERVER;

	@Parameters({ "browser", "environment" })
	@BeforeClass
	public void beforeClass(String browserName, String environment) {
		propertiesConfig = PropertiesConfig.getProperties(environment);
		driver = getBrowserDriver(browserName, propertiesConfig.getUserUrl());

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
	public void TC_01_Add_To_Wishlist() {
		log.info("Wishlist_01 - Step 01: Click to Product Title link with product 'Apple MacBook Pro 13-inch'");
		userHomePage.clickToLinkByTitle(driver, "Apple MacBook Pro 13-inch");
		userProductPage = PageGeneratorManager.getUserProductPage(driver);

		log.info("Wishlist_01 - Step 02: Click to Add To Wishlist button");
		userProductPage.clickToAddToWishlistButton(driver);

		log.info("Wishlist_01 - Step 03: Verify success message is displayed: 'The product has been added to your wishlist'");
		verifyEquals(userProductPage.getBarNotiSuccessMessage(driver), "The product has been added to your wishlist");

		log.info("Wishlist_01 - Step 04: Close success message in bar notification");
		userProductPage.clickToCloseBarNotiSuccessMessageIcon(driver);

		log.info("Wishlist_01 - Step 05: Get Product Name text");
		productName = userProductPage.getProductNameText(driver).trim();

		log.info("Wishlist_01 - Step 06: Get SKU text");
		sku = userProductPage.getSKUText(driver).trim();

		log.info("Wishlist_01 - Step 07: Get Product Price text");
		unitPrice = userProductPage.getProductPriceText(driver).trim();

		log.info("Wishlist_01 - Step 08: Get Product Quantity text by attribute 'value'");
		quantity = userProductPage.getProductQuantityTextByAttribute(driver, "value").trim();

		log.info("Wishlist_01 - Step 09: Click to Wishlist link in header");
		userWishlistPage = userProductPage.clickToLinkInHeaderByClassName(driver, "ico-wishlist");

		log.info("Wishlist_01 - Step 10: Verify the product added to wishlist page is displayed with correct values");
		verifyTrue(userWishlistPage.isProductAddedToWishlistPageDisplayed(driver, sku, productName, unitPrice, quantity, "$3,600.00"));

		log.info("Wishlist_01 - Step 11: Click to sharing link: 'Your wishlist URL for sharing'");
		userWishlistPage.clickToSharingLink(driver);

		log.info("Wishlist_01 - Step 12: Verify the product added to wishlist page from sharing link is displayed with correct values");
		verifyEquals(userWishlistPage.getWishlistOfPersonTitle(driver), "Wishlist of " + DataTest.UserRegister.FULL_NAME);
		verifyTrue(userWishlistPage.isProductAddedToWishlistSharingLinkDisplayed(driver, sku, productName, unitPrice, quantity, "$3,600.00"));
	}

	@Test
	public void TC_02_Add_Product_To_Cart_From_Wishlist_Page() {
		log.info("Wishlist_02 - Step 01: Back to Wishlist page");
		userWishlistPage.backToPage(driver);

		log.info("Wishlist_02 - Step 02: Click 'Add to cart:' checkbox");
		userWishlistPage.checkToCheckboxOrRadioByTitle(driver, "Add to cart:");

		log.info("Wishlist_02 - Step 03: Click 'Add to cart' button");
		userWishlistPage.clickToButtonByTitleText(driver, "Add to cart");

		log.info("Wishlist_02 - Step 04: Click to Shopping Cart link");
		userWishlistPage.clickToLinkByClassName(driver, "ico-cart");
		userShoppingCartPage = PageGeneratorManager.getUserShoppingCartPage(driver);

		log.info("Wishlist_02 - Step 05: Verify the product added to shopping cart page is displayed with correct values");
		verifyTrue(userShoppingCartPage.isProductAddedToShoppingCartPageDisplayed(driver, sku, productName, unitPrice, quantity, "$3,600.00"));

		log.info("Wishlist_02 - Step 06: Click to Wishlist link");
		userWishlistPage.clickToLinkByClassName(driver, "ico-wishlist");
		userWishlistPage = PageGeneratorManager.getUserWishlistPage(driver);

		log.info("Wishlist_02 - Step 07: Verify the product deleted in wishlist page with message 'The wishlist is empty!' is displayed");
		verifyTrue(userWishlistPage.isPageMessageDisplayed(driver, "The wishlist is empty!"));
	}

	@Test
	public void TC_03_Remove_Product_In_Wishlist_Page() {
		log.info("Wishlist_03 - Step 01: Back to Wishlist page");
		userWishlistPage.backToPage(driver);

		log.info("Wishlist_03 - Step 02: Back to Product page");
		userWishlistPage.backToPage(driver);
		userProductPage = PageGeneratorManager.getUserProductPage(driver);

		log.info("Wishlist_03 - Step 03: Back to Home page");
		userProductPage.backToPage(driver);
		userProductPage.backToPage(driver);
		userHomePage = PageGeneratorManager.getUserHomePage(driver);

		log.info("Wishlist_03 - Step 04: Click to Product Title link with product 'HTC One M8 Android L 5.0 Lollipop'");
		userHomePage.clickToLinkByTitle(driver, "HTC One M8 Android L 5.0 Lollipop");
		userProductPage = PageGeneratorManager.getUserProductPage(driver);

		log.info("Wishlist_03 - Step 05: Click to Add To Wishlist button");
		userProductPage.clickToAddToWishlistButton(driver);

		log.info("Wishlist_03 - Step 06: Verify success message is displayed: 'The product has been added to your wishlist'");
		verifyEquals(userProductPage.getBarNotiSuccessMessage(driver), "The product has been added to your wishlist");

		log.info("Wishlist_03 - Step 07: Close success message in bar notification");
		userProductPage.clickToCloseBarNotiSuccessMessageIcon(driver);

		log.info("Wishlist_03 - Step 08: Get Product Name text");
		productName = userProductPage.getProductNameText(driver).trim();

		log.info("Wishlist_03 - Step 09: Get SKU text");
		sku = userProductPage.getSKUText(driver).trim();

		log.info("Wishlist_03 - Step 10: Get Product Price text");
		unitPrice = userProductPage.getProductPriceText(driver).trim();

		log.info("Wishlist_03 - Step 11: Get Product Quantity text by attribute 'value'");
		quantity = userProductPage.getProductQuantityTextByAttribute(driver, "value").trim();

		log.info("Wishlist_03 - Step 12: Click to Wishlist link in header");
		userWishlistPage = userProductPage.clickToLinkInHeaderByClassName(driver, "ico-wishlist");

		log.info("Wishlist_03 - Step 13: Verify the product added to wishlist page is displayed with correct values");
		verifyTrue(userWishlistPage.isProductAddedToWishlistPageDisplayed(driver, sku, productName, unitPrice, quantity, "$245.00"));

		log.info("Wishlist_03 - Step 14: Click to Remove icon");
		userWishlistPage.clickToRemoveProductIcon(driver, sku, productName, unitPrice, quantity, "$245.00");

		log.info("Wishlist_03 - Step 15: Verify the product deleted in wishlist page with message 'The wishlist is empty!' is displayed");
		verifyTrue(userWishlistPage.isPageMessageDisplayed(driver, "The wishlist is empty!"));

		verifyTrue(userWishlistPage.isProductAddedToWishlistPageUndisplayed(driver, sku, productName, unitPrice, quantity, "$245.00"));
	}

	@Test
	public void TC_04_Add_Product_To_Compare() {
		log.info("Wishlist_04 - Step 01: Add 'Asus N551JK-XO076H Laptop' product to compare list");
		userWishlistPage.hoverToMenuAndClickToSubmenuByTitle(driver, "Computers", "Notebooks");
		userInventoryPage = PageGeneratorManager.getUserInventoryPage(driver);

		userInventoryPage.clickToLinkByTitle(driver, "Asus N551JK-XO076H Laptop");
		userProductPage = PageGeneratorManager.getUserProductPage(driver);
		userProductPage.clickToAddProductToCompareListByTitle(driver, "Asus N551JK-XO076H Laptop");

		String productName1 = userProductPage.getProductNameText(driver).trim();
		String unitPrice1 = userProductPage.getProductPriceText(driver).trim();

		log.info("Wishlist_04 - Step 02: Verify success message is displayed: 'The product has been added to your product comparison");
		verifyEquals(userProductPage.getBarNotiSuccessMessage(driver), "The product has been added to your product comparison");

		log.info("Wishlist_04 - Step 03: Close success message in bar notification");
		userProductPage.clickToCloseBarNotiSuccessMessageIcon(driver);

		log.info("Wishlist_04 - Step 04: Add 'HP Spectre XT Pro UltraBook' product to compare list");
		userProductPage.backToPage(driver);
		userInventoryPage = PageGeneratorManager.getUserInventoryPage(driver);

		userInventoryPage.clickToLinkByTitle(driver, "HP Spectre XT Pro UltraBook");
		userProductPage = PageGeneratorManager.getUserProductPage(driver);
		userProductPage.clickToAddProductToCompareListByTitle(driver, "HP Spectre XT Pro UltraBook");

		String productName2 = userProductPage.getProductNameText(driver).trim();
		String unitPrice2 = userProductPage.getProductPriceText(driver).trim();

		log.info("Wishlist_04 - Step 05: Verify success message is displayed: 'The product has been added to your product comparison");
		verifyEquals(userProductPage.getBarNotiSuccessMessage(driver), "The product has been added to your product comparison");

		log.info("Wishlist_04 - Step 06: Click to footer link with name 'Compare products list'");
		userProductPage.clickToLinkInFooterByTitle(driver, "Compare products list");
		userCompareProductsPage = PageGeneratorManager.getUserCompareProductsPage(driver);

		log.info("Wishlist_04 - Step 07: Verify product information is displayed");
		verifyTrue(userCompareProductsPage.isProductInformationDisplayed(driver, productName2, productName1, unitPrice2, unitPrice1));

		log.info("Wishlist_04 - Step 08: Click to Clear List link");
		userCompareProductsPage.clickToLinkByTitle(driver, "Clear list");

		log.info("Wishlist_04 - Step 09: Verify message is displayed: 'You have no items to compare.'");
		verifyTrue(userCompareProductsPage.isPageMessageDisplayed(driver, "You have no items to compare."));

		log.info("Wishlist_04 - Step 10: Verify 02 products is undisplayed in Compare Products page");
		verifyTrue(userCompareProductsPage.isProductAddedToCompareProductsPageUndisplayed(driver, productName2, productName1, unitPrice2, unitPrice1));
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserDriver();
	}
}
