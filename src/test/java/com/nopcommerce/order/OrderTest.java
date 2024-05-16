package com.nopcommerce.order;

import java.text.ParseException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.nopcommerce.data.DataTest;

import commons.BaseTest;
import environmentConfig.PropertiesConfig;
import pageObjects.user.PageGeneratorManager;
import pageObjects.user.UserCheckoutPageObject;
import pageObjects.user.UserHomePageObject;
import pageObjects.user.UserInventoryPageObject;
import pageObjects.user.UserOrderDetailsPageObject;
import pageObjects.user.UserProductPageObject;
import pageObjects.user.header.UserLoginPageObject;
import pageObjects.user.header.UserRegisterPageObject;
import pageObjects.user.header.UserShoppingCartPageObject;
import pageObjects.user.sidebar.UserCustomerInfoPageObject;
import pageObjects.user.sidebar.UserCustomerOrdersPageObject;

public class OrderTest extends BaseTest {
	WebDriver driver;
	PropertiesConfig propertiesConfig;
	UserHomePageObject userHomePage;
	UserLoginPageObject userLoginPage;
	UserProductPageObject userProductPage;
	UserInventoryPageObject userInventoryPage;
	UserRegisterPageObject userRegisterPage;
	UserShoppingCartPageObject userShoppingCartPage;
	UserCheckoutPageObject userCheckoutPage;
	UserCustomerInfoPageObject userMyAccountPage;
	UserCustomerOrdersPageObject userCustomerOrdersPage;
	UserOrderDetailsPageObject userOrderDetailsPage;
	String email = DataTest.UserRegister.PRE_EMAIL_ADDRESS + getRandomNumber() + DataTest.UserRegister.WEB_EMAIL_SERVER;
	String firstName = DataTest.UserOrderProduct.FIRST_NAME;
	String lastName = DataTest.UserOrderProduct.LAST_NAME;
	String fullName = DataTest.UserOrderProduct.FULL_NAME;
	String password = DataTest.UserOrderProduct.PASSWORD;

	String processor1 = DataTest.UserOrderProduct.PROCESSOR_1;
	String processor2 = DataTest.UserOrderProduct.PROCESSOR_2;
	String ram1 = DataTest.UserOrderProduct.RAM_1;
	String ram2 = DataTest.UserOrderProduct.RAM_2;
	String hdd1 = DataTest.UserOrderProduct.HDD_1;
	String hdd2 = DataTest.UserOrderProduct.HDD_2;
	String os1 = DataTest.UserOrderProduct.OS_1;
	String os2 = DataTest.UserOrderProduct.OS_2;
	String software1 = DataTest.UserOrderProduct.SOFTWARE_1;
	String software2 = DataTest.UserOrderProduct.SOFTWARE_2;
	String software3 = DataTest.UserOrderProduct.SOFTWARE_3;

	String productName1 = DataTest.UserOrderProduct.PRODUCT_NAME_1;
	String sku1 = DataTest.UserOrderProduct.SKU_1;
	String minPriceCombo1 = DataTest.UserOrderProduct.MIN_PRICE_WITH_ATTRIBUTES_1;
	String minPriceCombo2 = DataTest.UserOrderProduct.MIN_PRICE_WITH_ATTRIBUTES_2;

	String productName2 = DataTest.UserOrderProduct.PRODUCT_NAME_2;
	String sku2 = DataTest.UserOrderProduct.SKU_2;
	String minPrice2 = DataTest.UserOrderProduct.MIN_PRICE_2;

	String country = DataTest.UserOrderProduct.COUNTRY;
	String city1 = DataTest.UserOrderProduct.CITY_1;
	String city2 = DataTest.UserOrderProduct.CITY_2;
	String address1 = DataTest.UserOrderProduct.ADDRESS_1;
	String address2 = DataTest.UserOrderProduct.ADDRESS_2;
	String zipCode1 = DataTest.UserOrderProduct.ZIP_CODE_1;
	String zipCode2 = DataTest.UserOrderProduct.ZIP_CODE_2;
	String phoneNum = DataTest.UserOrderProduct.PHONE_NUM;
	String shippingPrice = DataTest.UserOrderProduct.SHIPPING_PRICE;
	String taxPrice = DataTest.UserOrderProduct.TAX_PRICE;

	@Parameters({ "browser", "environment" })
	@BeforeClass
	public void beforeClass(String browserName, String environment) {
		propertiesConfig = PropertiesConfig.getProperties(environment);
		driver = getBrowserDriver(browserName, propertiesConfig.getUserUrl());

		userHomePage = PageGeneratorManager.getUserHomePage(driver);
		userHomePage.openLinkInHeaderByTitle(driver, "Register");

		userRegisterPage = PageGeneratorManager.getUserRegisterPage(driver);
		userRegisterPage.enterToTextboxByID(driver, firstName, "FirstName");
		userRegisterPage.enterToTextboxByID(driver, lastName, "LastName");
		userRegisterPage.enterToTextboxByID(driver, email, "Email");
		userRegisterPage.enterToTextboxByID(driver, password, "Password");
		userRegisterPage.enterToTextboxByID(driver, password, "ConfirmPassword");
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

		userLoginPage = userRegisterPage.clickToLoginLinkInHeader(driver);
		userLoginPage.enterToTextboxByID(driver, email, "Email");
		userLoginPage.enterToTextboxByID(driver, password, "Password");
		userLoginPage.clickToButtonByTitleText(driver, "Log in");

		userHomePage = PageGeneratorManager.getUserHomePage(driver);
		verifyTrue(userHomePage.isTopicTitleUserHomePageDisplayed(driver));
	}

	@Test
	public void TC_01_Add_Product_To_Cart() {
		log.info("Order_01 - Step 01: Hover to Computers menu and click to Desktops submenu");
		userHomePage.hoverToMenuAndClickToSubmenuByTitle(driver, "Computers", "Desktops");
		userInventoryPage = PageGeneratorManager.getUserInventoryPage(driver);

		log.info("Order_01 - Step 02: Click to product link with value: " + productName1);
		userInventoryPage.clickToLinkByTitle(driver, productName1);
		userProductPage = PageGeneratorManager.getUserProductPage(driver);

		log.info("Order_01 - Step 03: Select Processor dropdown with value: " + processor1);
		userProductPage.selectItemInDefaultDropdownByID(driver, processor1, "product_attribute_1");

		log.info("Order_01 - Step 04: Select RAM dropdown with value: " + ram1);
		userProductPage.selectItemInDefaultDropdownByID(driver, ram1, "product_attribute_2");

		log.info("Order_01 - Step 05: Select HDD radio with value: " + hdd1);
		userProductPage.checkToCheckboxOrRadioByTitle(driver, hdd1);

		log.info("Order_01 - Step 06: Select OS radio with value: " + os1);
		userProductPage.checkToCheckboxOrRadioByTitle(driver, os1);

		log.info("Order_01 - Step 07: Select Software checkbox with value: " + software1);
		userProductPage.checkToCheckboxOrRadioByTitle(driver, software1);

		log.info("Order_01 - Step 08: Select Software checkbox with value: " + software2);
		userProductPage.checkToCheckboxOrRadioByTitle(driver, software2);

		log.info("Order_01 - Step 09: Select Software checkbox with value: " + software3);
		userProductPage.checkToCheckboxOrRadioByTitle(driver, software3);

		log.info("Order_01 - Step 10: Click to Add To Cart button");
		userProductPage.clickToButtonByTitleText(driver, "Add to cart");

		log.info("Order_01 - Step 11: Verify success message at bar notification is displayed: The product has been added to your shopping cart");
		verifyEquals(userProductPage.getBarNotiSuccessMessage(driver), "The product has been added to your shopping cart");

		log.info("Order_01 - Step 12: Click to close icon at bar notification");
		userProductPage.clickToCloseBarNotiSuccessMessageIcon(driver);

		log.info("Order_01 - Step 13: Verify product number added in Shopping Cart page is displayed: 1");
		verifyTrue(userProductPage.isProductNumberAddedInShoppingCartPageDisplayed(driver, "1"));

		log.info("Order_01 - Step 14: Hover mouse to Shopping Cart link in header link");
		userProductPage.scrollToShoppingCardInHeaderLink(driver);
		userProductPage.hoverMouseToShoppingCartInHeaderLink(driver);

		log.info("Order_01 - Step 15: Verify added product number in Mini Shopping Cart info is displayed: There are 1 item(s) in your cart.");
		verifyEquals(userProductPage.getTextAddedProductInMiniShoppingCart(driver, "count"), "There are 1 item(s) in your cart.");

		log.info("Order_01 - Step 16: Verify added product name in Mini Shopping Cart info is displayed: " + productName1);
		verifyEquals(userProductPage.getTextAddedProductInMiniShoppingCart(driver, "name"), productName1);

		log.info("Order_01 - Step 17: Verify added product attributes in Mini Shopping Cart info is displayed: "
				+ processor1.concat(", ").concat(ram1).concat(", ").concat(hdd1).concat(", ").concat(os1).concat(", ").concat(software1).concat(", ").concat(software2).concat(", ").concat(software3));
		String expectedProcessor1 = "Processor: "
				+ processor1.concat("\nRAM: ").concat(ram1).concat("\nHDD: ").concat(hdd1).concat("\nOS: ").concat(os1).concat("\nSoftware: ").concat(software1).concat("\nSoftware: ").concat(software2).concat("\nSoftware: ").concat(software3);
		String expectedProcessor2 = "Processor: "
				+ processor1.concat("RAM: ").concat(ram1).concat("HDD: ").concat(hdd1).concat("OS: ").concat(os1).concat("Software: ").concat(software1).concat("Software: ").concat(software2).concat("Software: ").concat(software3);
		verifyEquals(userProductPage.getTextAddedProductInMiniShoppingCart(driver, "attributes"), userProductPage.selectExpectedText(driver, expectedProcessor1, expectedProcessor2));
		log.info("Order_01 - Step 18: Verify added product totals in Mini Shopping Cart info is displayed: Sub-Total: " + minPriceCombo1);
		verifyEquals(userProductPage.getTextAddedProductInMiniShoppingCart(driver, "totals"), "Sub-Total: " + minPriceCombo1);
	}

	@Test
	public void TC_02_Edit_Product_In_Shopping_Cart() {
		log.info("Order_02 - Step 01: Click to Shopping Cart link in header");
		userProductPage.clickToLinkByClassName(driver, "ico-cart");
		userShoppingCartPage = PageGeneratorManager.getUserShoppingCartPage(driver);

		log.info("Order_02 - Step 02: Click to Edit link");
		userShoppingCartPage.clickToLinkByTitle(driver, "Edit");
		userProductPage = PageGeneratorManager.getUserProductPage(driver);

		log.info("Order_02 - Step 03: Select Processor dropdown with value: " + processor2);
		userProductPage.selectItemInDefaultDropdownByID(driver, processor2, "product_attribute_1");

		log.info("Order_02 - Step 04: Select RAM dropdown with value: " + ram2);
		userProductPage.selectItemInDefaultDropdownByID(driver, ram2, "product_attribute_2");

		log.info("Order_02 - Step 05: Select HDD radio with value: " + hdd2);
		userProductPage.checkToCheckboxOrRadioByTitle(driver, hdd2);

		log.info("Order_02 - Step 06: Select OS radio with value: " + os2);
		userProductPage.checkToCheckboxOrRadioByTitle(driver, os2);

		log.info("Order_02 - Step 07: Select Software checkbox with value: " + software1);
		userProductPage.checkToCheckboxOrRadioByTitle(driver, software1);

		log.info("Order_02 - Step 08: Deselect Software checkbox with value: " + software2);
		userProductPage.uncheckToCheckboxTitle(driver, software2);

		log.info("Order_02 - Step 09: Deselect Software checkbox with value: " + software3);
		userProductPage.uncheckToCheckboxTitle(driver, software3);

		log.info("Order_02 - Step 10: Verify product price is displayed: " + minPriceCombo2);
		verifyTrue(userProductPage.isProductPriceDisplayedByPrice(driver, minPriceCombo2));

		log.info("Order_02 - Step 12: Enter to product quantity with value: 2");
		userProductPage.enterToTextboxByID(driver, "2", "product_enteredQuantity_1");

		log.info("Order_02 - Step 13: Click to Update link");
		userProductPage.clickToButtonByTitleText(driver, "Update");

		log.info("Order_02 - Step 14: Verify success message at bar notification is displayed: The product has been added to your shopping cart");
		verifyEquals(userProductPage.getBarNotiSuccessMessage(driver), "The product has been added to your shopping cart");

		log.info("Order_02 - Step 15: Click to close icon at bar notification");
		userProductPage.clickToCloseBarNotiSuccessMessageIcon(driver);

		log.info("Order_02 - Step 16: Click to Shopping Cart link in header");
		userProductPage.clickToLinkByClassName(driver, "ico-cart");
		userShoppingCartPage = PageGeneratorManager.getUserShoppingCartPage(driver);

		log.info("Order_02 - Step 17: Verify product info in Shopping Cart page is displayed: " + processor2.concat(", ").concat(ram2).concat(", ").concat(hdd2).concat(", ").concat(os2).concat(", ").concat(software1));
		String expectedProcessor1 = "Processor: " + processor2.concat("\nRAM: ").concat(ram2).concat("\nHDD: ").concat(hdd2).concat("\nOS: ").concat(os2).concat("\nSoftware: ").concat(software1);
		String expectedProcessor2 = "Processor: " + processor2.concat("RAM: ").concat(ram2).concat("HDD: ").concat(hdd2).concat("OS: ").concat(os2).concat("Software: ").concat(software1);
		verifyEquals(userShoppingCartPage.getTextProductDetailToShoppingCartPageByProductName(driver, productName1), userShoppingCartPage.selectExpectedText(driver, expectedProcessor1, expectedProcessor2));

		log.info("Order_02 - Step 18: Verify product row info in Shopping Cart page is displayed: " + sku1 + ", " + productName1 + ", " + minPriceCombo2 + ", Qty = 2, Total = $2,640.00");
		verifyTrue(userShoppingCartPage.isProductAddedToShoppingCartPageDisplayed(driver, sku1, productName1, minPriceCombo2, "2", "$2,640.00"));
	}

	@Test
	public void TC_03_Remove_From_Cart() {
		log.info("Order_03 - Step 01: Click to Remove Product icon in Shopping Cart page");
		userShoppingCartPage.clickToRemoveProductIconInShoppingCartPage(driver, sku1, productName1, minPriceCombo2, "2", "$2,640.00");

		log.info("Order_03 - Step 02: Verify Shopping Cart message is displayed: Your Shopping Cart is empty!");
		verifyTrue(userShoppingCartPage.isShoppingCartMessageDisplayed(driver, "Your Shopping Cart is empty!"));

		log.info("Order_03 - Step 03: Verify product added to Shopping Cart page is undisplayed");
		verifyTrue(userShoppingCartPage.isProductAddedToShoppingCartPageUndisplayed(driver, sku1, productName1, minPriceCombo2, "2", "$2,640.00"));
	}

	@Test
	public void TC_04_Update_Shopping_Cart() {
		log.info("Order_04 - Step 01: Hover to Computers menu and click to Notebooks submenu");
		userShoppingCartPage.hoverToMenuAndClickToSubmenuByTitle(driver, "Computers", "Notebooks");
		userInventoryPage = PageGeneratorManager.getUserInventoryPage(driver);

		log.info("Order_04 - Step 02: Click to product link with value: " + productName2);
		userInventoryPage.clickToLinkByTitle(driver, productName2);
		userProductPage = PageGeneratorManager.getUserProductPage(driver);

		log.info("Order_04 - Step 03: Click to Add To Cart button");
		userProductPage.clickToButtonByID(driver, "add-to-cart-button-4");

		log.info("Order_04 - Step 04: Verify success message at bar notification is displayed: The product has been added to your shopping cart");
		verifyEquals(userProductPage.getBarNotiSuccessMessage(driver), "The product has been added to your shopping cart");

		log.info("Order_04 - Step 05: Click to close icon at bar notification");
		userProductPage.clickToCloseBarNotiSuccessMessageIcon(driver);

		log.info("Order_04 - Step 06: Click to Shopping Cart link in header");
		userProductPage.clickToLinkByClassName(driver, "ico-cart");
		userShoppingCartPage = PageGeneratorManager.getUserShoppingCartPage(driver);

		log.info("Order_04 - Step 07: Enter to Quantity textbox with value: 5");
		userShoppingCartPage.enterToQuantityTextbox(driver, "onchange", "5");
		userShoppingCartPage.pressEnterToQuantityTextbox(driver);

		log.info("Order_04 - Step 08: Verify product row info in Shopping Cart page is displayed: SKU: " + sku2 + ", " + productName2 + ", " + minPrice2 + ", Qty = 5, Total = $9,000.00");
		verifyTrue(userShoppingCartPage.isProductAddedToShoppingCartPageDisplayed(driver, sku2, productName2, minPrice2, "5", "$9,000.00"));
	}

	@Test
	public void TC_05_Checkout_Order_Payment_Method_By_Cheque() throws ParseException {
		log.info("Order_05 - Step 01: Select Gift Wrapping dropdown with value: No");
		userShoppingCartPage.selectItemInDefaultDropdownByID(driver, "No", "checkout_attribute_1");

		log.info("Order_05 - Step 02: Click to Term Of Service checkbox");
		userShoppingCartPage.clickToCheckboxByID(driver, "termsofservice");

		log.info("Order_05 - Step 03: Click to Checkout button");
		userShoppingCartPage.clickToButtonByID(driver, "checkout");
		userCheckoutPage = PageGeneratorManager.getUserCheckoutPage(driver);

		log.info("Order_05 - Step 04: Enter to Ship To Same Address checkbox");
		userCheckoutPage.checkToCheckboxOrRadioByTitle(driver, "Ship to the same address");

		log.info("Order_05 - Step 05: Enter to Country dropdown");
		userCheckoutPage.selectItemInDefaultDropdownByID(driver, country, "BillingNewAddress_CountryId");

		log.info("Order_05 - Step 06: Enter to City textbox");
		userCheckoutPage.enterToTextboxByID(driver, city1, "BillingNewAddress_City");

		log.info("Order_05 - Step 07: Enter to Address 1 textbox");
		userCheckoutPage.enterToTextboxByID(driver, address1, "BillingNewAddress_Address1");

		log.info("Order_05 - Step 08: Enter to Zip Code textbox");
		userCheckoutPage.enterToTextboxByID(driver, zipCode1, "BillingNewAddress_ZipPostalCode");

		log.info("Order_05 - Step 09: Enter to Phone Number textbox");
		userCheckoutPage.enterToTextboxByID(driver, phoneNum, "BillingNewAddress_PhoneNumber");

		log.info("Order_05 - Step 10: Enter to Continue button at Billing Buttons Container");
		userCheckoutPage.clickToBillingButtonByTitle(driver, "Continue");

		log.info("Order_05 - Step 11: Enter to Shipping Method radio: Ground ($0.00)");
		userCheckoutPage.checkToCheckboxOrRadioByTitle(driver, "Ground ($0.00)");

		log.info("Order_05 - Step 12: Enter to Continue button at Shipping Method Buttons Container");
		userCheckoutPage.clickToShippingMethodButtonByTitle(driver, "Continue");

		log.info("Order_05 - Step 13: Enter to Payment Method radio: Check / Money Order");
		userCheckoutPage.checkToCheckboxOrRadioByTitle(driver, "Check / Money Order");

		log.info("Order_05 - Step 14: Enter to Continue button at Payment Method Buttons Container");
		userCheckoutPage.clickToPaymentMethodButtonByTitle(driver, "Continue");

		log.info("Order_05 - Step 15: Verify Payment Information text is displayed");
		verifyTrue(userCheckoutPage.isPaymentInformationDisplayed(driver, "Mail Personal or Business Check, Cashier's Check or money order to:"));
		verifyTrue(userCheckoutPage.isPaymentInformationDisplayed(driver, "NOP SOLUTIONS"));
		verifyTrue(userCheckoutPage.isPaymentInformationDisplayed(driver, "your address here,"));
		verifyTrue(userCheckoutPage.isPaymentInformationDisplayed(driver, "New York, NY 10001 "));
		verifyTrue(userCheckoutPage.isPaymentInformationDisplayed(driver, "USA"));
		verifyTrue(userCheckoutPage.isPaymentInformationDisplayed(driver,
				"Notice that if you pay by Personal or Business Check, your order may be held for up to 10 days after we receive your check to allow enough time for the check to clear.  If you want us to ship faster upon receipt of your payment, then we recommend your send a money order or Cashier's check."));
		verifyTrue(userCheckoutPage.isPaymentInformationDisplayed(driver, "P.S. You can edit this text from admin panel."));

		log.info("Order_05 - Step 16: Enter to Continue button at Payment Info Buttons Container");
		userCheckoutPage.clickToPaymentInfoButtonByTitle(driver, "Continue");

		log.info("Order_05 - Step 17: Verify Full Name text at Billing Address is displayed: " + fullName);
		verifyEquals(userCheckoutPage.getTextBillingAddress(driver, "name"), fullName);

		log.info("Order_05 - Step 18: Verify Email text at Billing Address is displayed: Email: " + email);
		verifyEquals(userCheckoutPage.getTextBillingAddress(driver, "email"), "Email: " + email);

		log.info("Order_05 - Step 19: Verify Phone text at Billing Address is displayed: Phone: " + phoneNum);
		verifyEquals(userCheckoutPage.getTextBillingAddress(driver, "phone"), "Phone: " + phoneNum);

		log.info("Order_05 - Step 20: Verify Address 1 text at Billing Address is displayed: " + address1);
		verifyEquals(userCheckoutPage.getTextBillingAddress(driver, "address1"), address1);

		log.info("Order_05 - Step 21: Verify City text at Billing Address is displayed: " + city1);
		verifyEquals(userCheckoutPage.getTextBillingAddress(driver, "city"), city1);

		log.info("Order_05 - Step 22: Verify Zip Code text at Billing Address is displayed: " + zipCode1);
		verifyEquals(userCheckoutPage.getTextBillingAddress(driver, "zippostalcode"), zipCode1);

		log.info("Order_05 - Step 23: Verify Country text at Billing Address is displayed: " + country);
		verifyEquals(userCheckoutPage.getTextBillingAddress(driver, "country"), country);

		log.info("Order_05 - Step 24: Verify Payment Method text is displayed: Payment Method: Check / Money Order");
		verifyEquals(userCheckoutPage.getTextPaymentMethod(driver, "label", "value"), "Payment Method: Check / Money Order");

		log.info("Order_05 - Step 25: Verify Full Name text at Shipping Address is displayed: " + fullName);
		verifyEquals(userCheckoutPage.getTextShippingAddress(driver, "name"), fullName);

		log.info("Order_05 - Step 26: Verify Email text at Shipping Address is displayed: Email: " + email);
		verifyEquals(userCheckoutPage.getTextShippingAddress(driver, "email"), "Email: " + email);

		log.info("Order_05 - Step 27: Verify Phone text at Shipping Address is displayed: Phone: " + phoneNum);
		verifyEquals(userCheckoutPage.getTextShippingAddress(driver, "phone"), "Phone: " + phoneNum);

		log.info("Order_05 - Step 28: Verify Address 1 text at Shipping Address is displayed: " + address1);
		verifyEquals(userCheckoutPage.getTextShippingAddress(driver, "address1"), address1);

		log.info("Order_05 - Step 29: Verify City text at Shipping Address is displayed: " + city1);
		verifyEquals(userCheckoutPage.getTextShippingAddress(driver, "city"), city1);

		log.info("Order_05 - Step 30: Verify Zip Code text at Shipping Address is displayed: " + zipCode1);
		verifyEquals(userCheckoutPage.getTextShippingAddress(driver, "zippostalcode"), zipCode1);

		log.info("Order_05 - Step 31: Verify Country text at Shipping Address is displayed: " + country);
		verifyEquals(userCheckoutPage.getTextShippingAddress(driver, "country"), country);

		log.info("Order_05 - Step 32: Verify Shipping Method text is displayed: Shipping Method: Ground");
		verifyEquals(userCheckoutPage.getTextShippingMethod(driver, "label", "value"), "Shipping Method: Ground");

		log.info("Order_05 - Step 33: Verify product information row is displayed: SKU: " + sku2 + ", " + productName2 + ", " + minPrice2 + ", Qty = 5, Total = $9,000.00");
		verifyTrue(userCheckoutPage.isProductInfoRowDisplayed(driver, sku2, productName2, minPrice2, "5", "$9,000.00"));

		log.info("Order_05 - Step 33: Verify Gift Wrapping text is displayed: Gift wrapping: No");
		verifyEquals(userCheckoutPage.getTextGiftWrapping(driver), "Gift wrapping: No");

		log.info("Order_05 - Step 34: Verify Sub Total text at Total Info is displayed: $9,000.00");
		verifyEquals(userCheckoutPage.getTextSubTotalRowInTotalInfo(driver), "$9,000.00");

		log.info("Order_05 - Step 35: Verify Shipping text at Total Info is displayed: Shipping: (Ground) " + shippingPrice);
		verifyTrue(userCheckoutPage.isShippingRowInTotalInfoDisplayed(driver, "(Ground)", shippingPrice));

		log.info("Order_05 - Step 36: Verify Tax text at Total Info is displayed: " + taxPrice);
		verifyEquals(userCheckoutPage.getTextTaxRowInTotalInfo(driver), taxPrice);

		log.info("Order_05 - Step 37: Verify Total text at Total Info is displayed: $9,000.00");
		verifyEquals(userCheckoutPage.getTextTotalRowInTotalInfo(driver), "$9,000.00");

		log.info("Order_05 - Step 38: Verify You Will Earn text at Total Info is displayed: 900 points");
		verifyEquals(userCheckoutPage.getTextYouWillEarnRowInTotalInfo(driver), "900 points");

		log.info("Order_05 - Step 39: Click to Confirm button");
		userCheckoutPage.clickToButtonByTitleText(driver, "Confirm");

		log.info("Order_05 - Step 40: Verify Checkout Title is displayed: Thank you");
		verifyEquals(userCheckoutPage.getTextCheckoutTitle(driver), "Thank you");

		log.info("Order_05 - Step 41: Verify Section Order Title is displayed: Your order has been successfully processed!");
		verifyEquals(userCheckoutPage.getTextSectionOrderTitle(driver), "Your order has been successfully processed!");

		String orderNumberInCheckoutPage = userCheckoutPage.getTextOrderNumber(driver);
		String[] orderNum = orderNumberInCheckoutPage.split(":");
		String valueOrderNum = orderNum[1].trim();

		log.info("Order_05 - Step 42: Verify Order Number in Checkout Page is displayed: ORDER NUMBER: " + valueOrderNum);
		String expectedOrderNum1 = "ORDER NUMBER: " + valueOrderNum;
		String expectedOrderNum2 = "Order number: " + valueOrderNum;
		verifyEquals(userCheckoutPage.getTextOrderNumber(driver), userCheckoutPage.selectExpectedText(driver, expectedOrderNum1, expectedOrderNum2));

		log.info("Order_05 - Step 43: Open My Account link in header");
		userMyAccountPage = userCheckoutPage.openMyAccountLinkInHeader(driver);

		log.info("Order_05 - Step 44: Click to Orders link in sidebar");
		userCustomerOrdersPage = userMyAccountPage.clickToOrdersLinkInSidebar(driver);

		log.info("Order_05 - Step 45: Verify Order Number in Orders page is displayed: Order Number: " + valueOrderNum);
		verifyEquals(userCustomerOrdersPage.getTextOrderNumberInOrdersPage(driver), "Order Number: " + valueOrderNum);

		log.info("Order_05 - Step 46: Click to Details button");
		userOrderDetailsPage = userCustomerOrdersPage.clickToDetailsButton(driver);

		log.info("Order_05 - Step 47: Verify Order Number in Order Details page is displayed: ORDER #" + valueOrderNum);
		String expectedOrderNum1_1 = "ORDER #" + valueOrderNum;
		String expectedOrderNum2_2 = "Order #" + valueOrderNum;
		verifyEquals(userOrderDetailsPage.getTextOrderNumberInOrderDetailsPage(driver), userOrderDetailsPage.selectExpectedText(driver, expectedOrderNum1_1, expectedOrderNum2_2));

		String orderDate = userOrderDetailsPage.getCurrentDate(driver);
		log.info("Order_05 - Step 48: Verify Order Date in Order Details page is displayed: Order Date: " + orderDate);
		verifyEquals(userOrderDetailsPage.getTextOrderDateInOrderDetailsPage(driver), "Order Date: " + orderDate);

		log.info("Order_05 - Step 49: Verify Order Status in Order Details page is displayed: Order Status: Pending");
		verifyEquals(userOrderDetailsPage.getTextOrderStatusInOrderDetailsPage(driver), "Order Status: Pending");

		log.info("Order_05 - Step 50: Verify Order Total in Order Details page is displayed: Order Total: $9,000.00");
		verifyEquals(userOrderDetailsPage.getTextOrderTotalInOrderDetailsPage(driver), "Order Total: $9,000.00");

		log.info("Order_05 - Step 51: Verify Full Name text at Billing Address in Order Details page is displayed: " + fullName);
		verifyEquals(userOrderDetailsPage.getTextBillingAddress(driver, "name"), fullName);

		log.info("Order_05 - Step 52: Verify Email text at Billing Address in Order Details page is displayed: Email: " + email);
		verifyEquals(userOrderDetailsPage.getTextBillingAddress(driver, "email"), "Email: " + email);

		log.info("Order_05 - Step 53: Verify Phone Number text at Billing Address in Order Details page is displayed: Phone: " + phoneNum);
		verifyEquals(userOrderDetailsPage.getTextBillingAddress(driver, "phone"), "Phone: " + phoneNum);

		log.info("Order_05 - Step 54: Verify Address 1 text at Billing Address in Order Details page is displayed: " + address1);
		verifyEquals(userOrderDetailsPage.getTextBillingAddress(driver, "address1"), address1);

		log.info("Order_05 - Step 55: Verify City text at Billing Address in Order Details page is displayed: " + city1);
		verifyEquals(userOrderDetailsPage.getTextBillingAddress(driver, "city"), city1);

		log.info("Order_05 - Step 56: Verify Zip Code text at Billing Address in Order Details page is displayed: " + zipCode1);
		verifyEquals(userOrderDetailsPage.getTextBillingAddress(driver, "zippostalcode"), zipCode1);

		log.info("Order_05 - Step 57: Verify Country text at Billing Address in Order Details page is displayed: " + country);
		verifyEquals(userOrderDetailsPage.getTextBillingAddress(driver, "country"), country);

		log.info("Order_05 - Step 58: Verify Payment Method text at Payment Method in Order Details page is displayed: Payment Method: Check / Money Order");
		verifyEquals(userOrderDetailsPage.getTextPaymentMethod(driver, "label", "value"), "Payment Method: Check / Money Order");

		log.info("Order_05 - Step 59: Verify Full Name text at Shipping Address in Order Details page is displayed: " + fullName);
		verifyEquals(userOrderDetailsPage.getTextShippingAddress(driver, "name"), fullName);

		log.info("Order_05 - Step 60: Verify Email text at Shipping Address in Order Details page is displayed: Email: " + email);
		verifyEquals(userOrderDetailsPage.getTextShippingAddress(driver, "email"), "Email: " + email);

		log.info("Order_05 - Step 61: Verify Phone Number text at Shipping Address in Order Details page is displayed: Phone: " + phoneNum);
		verifyEquals(userOrderDetailsPage.getTextShippingAddress(driver, "phone"), "Phone: " + phoneNum);

		log.info("Order_05 - Step 62: Verify Address 1 text at Shipping Address in Order Details page is displayed: " + address1);
		verifyEquals(userOrderDetailsPage.getTextShippingAddress(driver, "address1"), address1);

		log.info("Order_05 - Step 63: Verify City text at Shipping Address in Order Details page is displayed: " + city1);
		verifyEquals(userOrderDetailsPage.getTextShippingAddress(driver, "city"), city1);

		log.info("Order_05 - Step 64: Verify Zip Code text at Shipping Address in Order Details page is displayed: " + zipCode1);
		verifyEquals(userOrderDetailsPage.getTextShippingAddress(driver, "zippostalcode"), zipCode1);

		log.info("Order_05 - Step 65: Verify Country text at Shipping Address in Order Details page is displayed: " + country);
		verifyEquals(userOrderDetailsPage.getTextShippingAddress(driver, "country"), country);

		log.info("Order_05 - Step 66: Verify Shipping Method text at Shipping Address in Order Details page is displayed: Shipping Method: Ground");
		verifyEquals(userOrderDetailsPage.getTextShippingMethod(driver, "label", "value"), "Shipping Method: Ground");

		log.info("Order_05 - Step 67: Verify product info row in Order Details page is displayed: " + sku2 + ", " + productName2 + ", " + minPrice2 + ", Qty = 5, Total = $9,000.00");
		verifyTrue(userOrderDetailsPage.isProductInfoRowDisplayed(driver, sku2, productName2, minPrice2, "5", "$9,000.00"));

		log.info("Order_05 - Step 68: Verify Gift Wrapping text in Order Details page is displayed: Gift wrapping: No");
		verifyEquals(userOrderDetailsPage.getTextGiftWrapping(driver), "Gift wrapping: No");

		log.info("Order_05 - Step 69: Verify Sub Total text at Total Info in Order Details page is displayed: $9,000.00");
		verifyEquals(userOrderDetailsPage.getTextSubTotalRowInTotalInfo(driver), "$9,000.00");

		log.info("Order_05 - Step 70: Verify Shipping text at Total Info in Order Details page is displayed: " + shippingPrice);
		verifyEquals(userOrderDetailsPage.getTextShippingRowAtTotalInfo(driver), shippingPrice);

		log.info("Order_05 - Step 71: Verify Tax text at Total Info in Order Details page is displayed: " + taxPrice);
		verifyEquals(userOrderDetailsPage.getTextTaxRowAtTotalInfo(driver), taxPrice);

		log.info("Order_05 - Step 72: Verify Total text at Total Info in Order Details page is displayed: $9,000.00");
		verifyEquals(userOrderDetailsPage.getTextTotalRowAtTotalInfo(driver), "$9,000.00");
	}

	@Test
	public void TC_06_Checkout_Order_Payment_Method_By_Cart() throws ParseException {
		log.info("Order_06 - Step 01: Hover to Computers menu and click to Notebooks submenu");
		userOrderDetailsPage.ClickToSubMenuByJS(driver);
		userInventoryPage = PageGeneratorManager.getUserInventoryPage(driver);

		log.info("Order_06 - Step 02: Click to product link with value: " + productName2);
		userInventoryPage.clickToLinkByTitle(driver, productName2);
		userProductPage = PageGeneratorManager.getUserProductPage(driver);

		log.info("Order_06 - Step 03: Click to Add To Cart button");
		userProductPage.clickToButtonByID(driver, "add-to-cart-button-4");

		log.info("Order_06 - Step 04: Verify success message at bar notification is displayed: The product has been added to your shopping cart");
		verifyEquals(userProductPage.getBarNotiSuccessMessage(driver), "The product has been added to your shopping cart");

		log.info("Order_06 - Step 05: Click to close icon at bar notification");
		userProductPage.clickToCloseBarNotiSuccessMessageIcon(driver);

		log.info("Order_06 - Step 06: Click to Shopping Cart link in header");
		userProductPage.clickToLinkByClassName(driver, "ico-cart");
		userShoppingCartPage = PageGeneratorManager.getUserShoppingCartPage(driver);

		log.info("Order_06 - Step 07: Enter to Quantity textbox with value: 5");
		userShoppingCartPage.enterToQuantityTextbox(driver, "onchange", "5");
		userShoppingCartPage.pressEnterToQuantityTextbox(driver);

		log.info("Order_06 - Step 08: Verify product row info in Shopping Cart page is displayed: SKU: " + sku2 + ", " + productName2 + ", " + minPrice2 + ", Qty = 5, Total = $9,000.00");
		verifyTrue(userShoppingCartPage.isProductAddedToShoppingCartPageDisplayed(driver, sku2, productName2, minPrice2, "5", "$9,000.00"));

		log.info("Order_06 - Step 09: Select Gift Wrapping dropdown with value: No");
		userShoppingCartPage.selectItemInDefaultDropdownByID(driver, "No", "checkout_attribute_1");

		log.info("Order_06 - Step 10: Click to Term Of Service checkbox");
		userShoppingCartPage.clickToCheckboxByID(driver, "termsofservice");

		log.info("Order_06 - Step 11: Click to Checkout button");
		userShoppingCartPage.clickToButtonByID(driver, "checkout");
		userCheckoutPage = PageGeneratorManager.getUserCheckoutPage(driver);

		log.info("Order_06 - Step 12: Enter to Ship To Same Address checkbox");
		userCheckoutPage.checkToCheckboxOrRadioByTitle(driver, "Ship to the same address");

		log.info("Order_06 - Step 13: Enter to Continue button at Billing Buttons Container");
		userCheckoutPage.clickToBillingButtonByTitle(driver, "Continue");

		log.info("Order_06 - Step 14: Enter to Shipping Method radio: Ground ($0.00)");
		userCheckoutPage.checkToCheckboxOrRadioByTitle(driver, "Ground ($0.00)");

		log.info("Order_06 - Step 15: Enter to Continue button at Shipping Method Buttons Container");
		userCheckoutPage.clickToShippingMethodButtonByTitle(driver, "Continue");

		log.info("Order_06 - Step 16: Enter to Payment Method radio: Credit Card");
		userCheckoutPage.checkToCheckboxOrRadioByTitle(driver, "Credit Card");

		log.info("Order_06 - Step 17: Enter to Continue button at Payment Method Buttons Container");
		userCheckoutPage.clickToPaymentMethodButtonByTitle(driver, "Continue");

		log.info("Order_06 - Step 18: Select Credit Card dropdown with value: Visa");
		userCheckoutPage.selectItemInDefaultDropdownByID(driver, "Visa", "CreditCardType");

		log.info("Order_06 - Step 19: Enter to Card Holder Name textbox: " + fullName);
		userCheckoutPage.enterToTextboxByID(driver, fullName, "CardholderName");

		log.info("Order_06 - Step 20: Enter to Card Number textbox: 4192474942851269");
		userCheckoutPage.enterToTextboxByID(driver, "4192474942851269", "CardNumber");

		log.info("Order_06 - Step 21: Select Expire Month dropdown with value: 06");
		userCheckoutPage.selectItemInDefaultDropdownByID(driver, "06", "ExpireMonth");

		log.info("Order_06 - Step 22: Select Expire Year dropdown with value: 2025");
		userCheckoutPage.selectItemInDefaultDropdownByID(driver, "2025", "ExpireYear");

		log.info("Order_06 - Step 23: Enter to Cart Code textbox: 123");
		userCheckoutPage.enterToTextboxByID(driver, "123", "CardCode");

		log.info("Order_06 - Step 24: Enter to Continue button at Payment Info Buttons Container");
		userCheckoutPage.clickToPaymentInfoButtonByTitle(driver, "Continue");

		log.info("Order_06 - Step 25: Verify Full Name text at Billing Address is displayed: " + fullName);
		verifyEquals(userCheckoutPage.getTextBillingAddress(driver, "name"), fullName);

		log.info("Order_06 - Step 26: Verify Email text at Billing Address is displayed: Email: " + email);
		verifyEquals(userCheckoutPage.getTextBillingAddress(driver, "email"), "Email: " + email);

		log.info("Order_06 - Step 27: Verify Phone text at Billing Address is displayed: Phone: " + phoneNum);
		verifyEquals(userCheckoutPage.getTextBillingAddress(driver, "phone"), "Phone: " + phoneNum);

		log.info("Order_06 - Step 28: Verify Address 1 text at Billing Address is displayed: " + address1);
		verifyEquals(userCheckoutPage.getTextBillingAddress(driver, "address1"), address1);

		log.info("Order_06 - Step 29: Verify City text at Billing Address is displayed: " + city1);
		verifyEquals(userCheckoutPage.getTextBillingAddress(driver, "city"), city1);

		log.info("Order_06 - Step 30: Verify Zip Code text at Billing Address is displayed: " + zipCode1);
		verifyEquals(userCheckoutPage.getTextBillingAddress(driver, "zippostalcode"), zipCode1);

		log.info("Order_06 - Step 31: Verify Country text at Billing Address is displayed: " + country);
		verifyEquals(userCheckoutPage.getTextBillingAddress(driver, "country"), country);

		log.info("Order_06 - Step 32: Verify Payment Method text is displayed: Payment Method: Credit Card");
		verifyEquals(userCheckoutPage.getTextPaymentMethod(driver, "label", "value"), "Payment Method: Credit Card");

		log.info("Order_06 - Step 33: Verify Full Name text at Shipping Address is displayed: " + fullName);
		verifyEquals(userCheckoutPage.getTextShippingAddress(driver, "name"), fullName);

		log.info("Order_06 - Step 34: Verify Email text at Shipping Address is displayed: Email: " + email);
		verifyEquals(userCheckoutPage.getTextShippingAddress(driver, "email"), "Email: " + email);

		log.info("Order_06 - Step 35: Verify Phone text at Shipping Address is displayed: Phone: " + phoneNum);
		verifyEquals(userCheckoutPage.getTextShippingAddress(driver, "phone"), "Phone: " + phoneNum);

		log.info("Order_06 - Step 36: Verify Address 1 text at Shipping Address is displayed: " + address1);
		verifyEquals(userCheckoutPage.getTextShippingAddress(driver, "address1"), address1);

		log.info("Order_06 - Step 37: Verify City 1 text at Shipping Address is displayed: " + city1);
		verifyEquals(userCheckoutPage.getTextShippingAddress(driver, "city"), city1);

		log.info("Order_06 - Step 38: Verify Zip Code 1 text at Shipping Address is displayed: " + zipCode1);
		verifyEquals(userCheckoutPage.getTextShippingAddress(driver, "zippostalcode"), zipCode1);

		log.info("Order_06 - Step 39: Verify Country text at Shipping Address is displayed: " + country);
		verifyEquals(userCheckoutPage.getTextShippingAddress(driver, "country"), country);

		log.info("Order_06 - Step 40: Verify Shipping Method text is displayed: Shipping Method: Ground");
		verifyEquals(userCheckoutPage.getTextShippingMethod(driver, "label", "value"), "Shipping Method: Ground");

		log.info("Order_06 - Step 41: Verify product information row is displayed: SKU: " + sku2 + ", " + productName2 + ", " + minPrice2 + ", Qty = 5, Total = $9,000.00");
		verifyTrue(userCheckoutPage.isProductInfoRowDisplayed(driver, sku2, productName2, minPrice2, "5", "$9,000.00"));

		log.info("Order_06 - Step 42: Verify Gift Wrapping text is displayed: Gift wrapping: No");
		verifyEquals(userCheckoutPage.getTextGiftWrapping(driver), "Gift wrapping: No");

		log.info("Order_06 - Step 43: Verify Sub Total text at Total Info is displayed: $9,000.00");
		verifyEquals(userCheckoutPage.getTextSubTotalRowInTotalInfo(driver), "$9,000.00");

		log.info("Order_06 - Step 44: Verify Shipping text at Total Info is displayed: Shipping: (Ground) " + shippingPrice);
		verifyTrue(userCheckoutPage.isShippingRowInTotalInfoDisplayed(driver, "(Ground)", shippingPrice));

		log.info("Order_06 - Step 45: Verify Tax text at Total Info is displayed: " + taxPrice);
		verifyEquals(userCheckoutPage.getTextTaxRowInTotalInfo(driver), taxPrice);

		log.info("Order_06 - Step 46: Verify Total text at Total Info is displayed: $9,000.00");
		verifyEquals(userCheckoutPage.getTextTotalRowInTotalInfo(driver), "$9,000.00");

		log.info("Order_06 - Step 47: Verify You Will Earn text at Total Info is displayed: 900 points");
		verifyEquals(userCheckoutPage.getTextYouWillEarnRowInTotalInfo(driver), "900 points");

		log.info("Order_06 - Step 48: Click to Confirm button");
		userCheckoutPage.sleepInSecond(20);
		userCheckoutPage.clickToButtonByTitleText(driver, "Confirm");

		log.info("Order_06 - Step 49: Verify Checkout Title is displayed: Thank you");
		verifyEquals(userCheckoutPage.getTextCheckoutTitle(driver), "Thank you");

		log.info("Order_06 - Step 50: Verify Section Order Title is displayed: Your order has been successfully processed!");
		verifyEquals(userCheckoutPage.getTextSectionOrderTitle(driver), "Your order has been successfully processed!");

		String orderNumberInCheckoutPage = userCheckoutPage.getTextOrderNumber(driver);
		String[] orderNum = orderNumberInCheckoutPage.split(":");
		String valueOrderNum = orderNum[1].trim();

		log.info("Order_06 - Step 51: Verify Order Number in Checkout Page is displayed: ORDER NUMBER: " + valueOrderNum);
		String expectedOrderNum1 = "ORDER NUMBER: " + valueOrderNum;
		String expectedOrderNum2 = "Order number: " + valueOrderNum;
		verifyEquals(userCheckoutPage.getTextOrderNumber(driver), userCheckoutPage.selectExpectedText(driver, expectedOrderNum1, expectedOrderNum2));

		log.info("Order_06 - Step 52: Open My Account link in header");
		userMyAccountPage = userCheckoutPage.openMyAccountLinkInHeader(driver);

		log.info("Order_06 - Step 53: Click to Orders link in sidebar");
		userCustomerOrdersPage = userMyAccountPage.clickToOrdersLinkInSidebar(driver);

		log.info("Order_06 - Step 54: Verify Order Number in Orders page is displayed: Order Number: " + valueOrderNum);
		verifyEquals(userCustomerOrdersPage.getTextOrderNumberInOrdersPage(driver), "Order Number: " + valueOrderNum);

		log.info("Order_06 - Step 55: Click to Details button");
		userOrderDetailsPage = userCustomerOrdersPage.clickToDetailsButton(driver);

		log.info("Order_06 - Step 56: Verify Order Number in Order Details page is displayed: ORDER #" + valueOrderNum);
		String expectedOrderNum1_1 = "ORDER #" + valueOrderNum;
		String expectedOrderNum2_2 = "Order #" + valueOrderNum;
		verifyEquals(userOrderDetailsPage.getTextOrderNumberInOrderDetailsPage(driver), userOrderDetailsPage.selectExpectedText(driver, expectedOrderNum1_1, expectedOrderNum2_2));

		String orderDate = userOrderDetailsPage.getCurrentDate(driver);
		log.info("Order_06 - Step 57: Verify Order Date in Order Details page is displayed: Order Date: " + orderDate);
		verifyEquals(userOrderDetailsPage.getTextOrderDateInOrderDetailsPage(driver), "Order Date: " + orderDate);

		log.info("Order_06 - Step 58: Verify Order Status in Order Details page is displayed: Order Status: Pending");
		verifyEquals(userOrderDetailsPage.getTextOrderStatusInOrderDetailsPage(driver), "Order Status: Pending");

		log.info("Order_06 - Step 59: Verify Order Total in Order Details page is displayed: Order Total: $9,000.00");
		verifyEquals(userOrderDetailsPage.getTextOrderTotalInOrderDetailsPage(driver), "Order Total: $9,000.00");

		log.info("Order_06 - Step 60: Verify Full Name text at Billing Address in Order Details page is displayed: " + fullName);
		verifyEquals(userOrderDetailsPage.getTextBillingAddress(driver, "name"), fullName);

		log.info("Order_06 - Step 61: Verify Email text at Billing Address in Order Details page is displayed: Email: " + email);
		verifyEquals(userOrderDetailsPage.getTextBillingAddress(driver, "email"), "Email: " + email);

		log.info("Order_06 - Step 62: Verify Phone Number text at Billing Address in Order Details page is displayed: Phone: " + phoneNum);
		verifyEquals(userOrderDetailsPage.getTextBillingAddress(driver, "phone"), "Phone: " + phoneNum);

		log.info("Order_06 - Step 63: Verify Address 1 text at Billing Address in Order Details page is displayed: " + address1);
		verifyEquals(userOrderDetailsPage.getTextBillingAddress(driver, "address1"), address1);

		log.info("Order_06 - Step 64: Verify City text at Billing Address in Order Details page is displayed: " + city1);
		verifyEquals(userOrderDetailsPage.getTextBillingAddress(driver, "city"), city1);

		log.info("Order_06 - Step 65: Verify Zip Code text at Billing Address in Order Details page is displayed: " + zipCode1);
		verifyEquals(userOrderDetailsPage.getTextBillingAddress(driver, "zippostalcode"), zipCode1);

		log.info("Order_06 - Step 66: Verify Country text at Billing Address in Order Details page is displayed: " + country);
		verifyEquals(userOrderDetailsPage.getTextBillingAddress(driver, "country"), country);

		log.info("Order_06 - Step 67: Verify Payment Method text at Payment Method in Order Details page is displayed: Payment Method: Credit Card");
		verifyEquals(userOrderDetailsPage.getTextPaymentMethod(driver, "label", "value"), "Payment Method: Credit Card");

		log.info("Order_06 - Step 68: Verify Full Name text at Shipping Address in Order Details page is displayed: " + fullName);
		verifyEquals(userOrderDetailsPage.getTextShippingAddress(driver, "name"), fullName);

		log.info("Order_06 - Step 69: Verify Email text at Shipping Address in Order Details page is displayed: Email: " + email);
		verifyEquals(userOrderDetailsPage.getTextShippingAddress(driver, "email"), "Email: " + email);

		log.info("Order_06 - Step 70: Verify Phone Number text at Shipping Address in Order Details page is displayed: Phone: " + phoneNum);
		verifyEquals(userOrderDetailsPage.getTextShippingAddress(driver, "phone"), "Phone: " + phoneNum);

		log.info("Order_06 - Step 71: Verify Address 1 text at Shipping Address in Order Details page is displayed: " + address1);
		verifyEquals(userOrderDetailsPage.getTextShippingAddress(driver, "address1"), address1);

		log.info("Order_06 - Step 72: Verify City text at Shipping Address in Order Details page is displayed: " + city1);
		verifyEquals(userOrderDetailsPage.getTextShippingAddress(driver, "city"), city1);

		log.info("Order_06 - Step 73: Verify Zip Code text at Shipping Address in Order Details page is displayed: " + zipCode1);
		verifyEquals(userOrderDetailsPage.getTextShippingAddress(driver, "zippostalcode"), zipCode1);

		log.info("Order_06 - Step 74: Verify Country text at Shipping Address in Order Details page is displayed: " + country);
		verifyEquals(userOrderDetailsPage.getTextShippingAddress(driver, "country"), country);

		log.info("Order_06 - Step 75: Verify Shipping Method text at Shipping Address in Order Details page is displayed: Shipping Method: Ground");
		verifyEquals(userOrderDetailsPage.getTextShippingMethod(driver, "label", "value"), "Shipping Method: Ground");

		log.info("Order_06 - Step 76: Verify product info row in Order Details page is displayed: " + sku2 + ", " + productName2 + ", " + minPrice2 + ", Qty = 5, Total = $9,000.00");
		verifyTrue(userOrderDetailsPage.isProductInfoRowDisplayed(driver, sku2, productName2, minPrice2, "5", "$9,000.00"));

		log.info("Order_06 - Step 77: Verify Gift Wrapping text in Order Details page is displayed: Gift wrapping: No");
		verifyEquals(userOrderDetailsPage.getTextGiftWrapping(driver), "Gift wrapping: No");

		log.info("Order_06 - Step 78: Verify Sub Total text at Total Info in Order Details page is displayed: $9,000.00");
		verifyEquals(userOrderDetailsPage.getTextSubTotalRowInTotalInfo(driver), "$9,000.00");

		log.info("Order_06 - Step 79: Verify Shipping text at Total Info in Order Details page is displayed: " + shippingPrice);
		verifyEquals(userOrderDetailsPage.getTextShippingRowAtTotalInfo(driver), shippingPrice);

		log.info("Order_06 - Step 80: Verify Tax text at Total Info in Order Details page is displayed: " + taxPrice);
		verifyEquals(userOrderDetailsPage.getTextTaxRowAtTotalInfo(driver), taxPrice);

		log.info("Order_06 - Step 81: Verify Total text at Total Info in Order Details page is displayed: $9,000.00");
		verifyEquals(userOrderDetailsPage.getTextTotalRowAtTotalInfo(driver), "$9,000.00");
	}

	@Test
	public void TC_07_Re_Order() throws ParseException {
		log.info("Order_07 - Step 01: Click to Re-order button");
		userShoppingCartPage = userOrderDetailsPage.clickToReorderButton(driver);

		log.info("Order_07 - Step 02: Enter to Quantity textbox with value: 10");
		userShoppingCartPage.enterToQuantityTextbox(driver, "onchange", "10");
		userShoppingCartPage.pressEnterToQuantityTextbox(driver);

		log.info("Order_07 - Step 03: Verify product row info in Shopping Cart page is displayed: SKU: " + sku2 + ", " + productName2 + ", " + minPrice2 + ", Qty = 10, Total = $18,000.00");
		verifyTrue(userShoppingCartPage.isProductAddedToShoppingCartPageDisplayed(driver, sku2, productName2, minPrice2, "10", "$18,000.00"));

		log.info("Order_07 - Step 04: Select Gift Wrapping dropdown with value: No");
		userShoppingCartPage.selectItemInDefaultDropdownByID(driver, "No", "checkout_attribute_1");

		log.info("Order_07 - Step 05: Click to Term Of Service checkbox");
		userShoppingCartPage.clickToCheckboxByID(driver, "termsofservice");

		log.info("Order_07 - Step 06: Click to Checkout button");
		userShoppingCartPage.clickToButtonByID(driver, "checkout");
		userCheckoutPage = PageGeneratorManager.getUserCheckoutPage(driver);

		log.info("Order_07 - Step 07: Enter to Ship To Same Address checkbox");
		userCheckoutPage.checkToCheckboxOrRadioByTitle(driver, "Ship to the same address");

		log.info("Order_07 - Step 08: Enter to Edit button at Billing Address Section");
		userCheckoutPage.clickToBillingButtonWhenReorderingByTitle(driver, "Edit");

		log.info("Order_07 - Step 09: Enter to Country dropdown");
		userCheckoutPage.selectItemInDefaultDropdownByID(driver, country, "BillingNewAddress_CountryId");

		log.info("Order_07 - Step 10: Enter to City textbox");
		userCheckoutPage.enterToTextboxByID(driver, city2, "BillingNewAddress_City");

		log.info("Order_07 - Step 11: Enter to Address 1 textbox");
		userCheckoutPage.enterToTextboxByID(driver, address2, "BillingNewAddress_Address1");

		log.info("Order_07 - Step 12: Enter to Zip Code textbox");
		userCheckoutPage.enterToTextboxByID(driver, zipCode2, "BillingNewAddress_ZipPostalCode");

		log.info("Order_07 - Step 13: Enter to Phone Number textbox");
		userCheckoutPage.enterToTextboxByID(driver, phoneNum, "BillingNewAddress_PhoneNumber");

		log.info("Order_07 - Step 14: Enter to Save button at Billing Buttons Container");
		userCheckoutPage.clickToBillingButtonByTitle(driver, "Save");

		log.info("Order_07 - Step 15: Enter to Continue button at Billing Buttons Container");
		userCheckoutPage.clickToBillingButtonByTitle(driver, "Continue");

		log.info("Order_07 - Step 16: Enter to Shipping Method radio: Next Day Air ($0.00)");
		userCheckoutPage.checkToCheckboxOrRadioByTitle(driver, "Next Day Air ($0.00)");

		log.info("Order_07 - Step 17: Enter to Continue button at Shipping Method Buttons Container");
		userCheckoutPage.clickToShippingMethodButtonByTitle(driver, "Continue");

		log.info("Order_07 - Step 18: Enter to Payment Method radio: Check / Money Order");
		userCheckoutPage.checkToCheckboxOrRadioByTitle(driver, "Check / Money Order");

		log.info("Order_07 - Step 19: Enter to Continue button at Payment Method Buttons Container");
		userCheckoutPage.clickToPaymentMethodButtonByTitle(driver, "Continue");

		log.info("Order_07 - Step 20: Verify Payment Information text is displayed");
		verifyTrue(userCheckoutPage.isPaymentInformationDisplayed(driver, "Mail Personal or Business Check, Cashier's Check or money order to:"));
		verifyTrue(userCheckoutPage.isPaymentInformationDisplayed(driver, "NOP SOLUTIONS"));
		verifyTrue(userCheckoutPage.isPaymentInformationDisplayed(driver, "your address here,"));
		verifyTrue(userCheckoutPage.isPaymentInformationDisplayed(driver, "New York, NY 10001 "));
		verifyTrue(userCheckoutPage.isPaymentInformationDisplayed(driver, "USA"));
		verifyTrue(userCheckoutPage.isPaymentInformationDisplayed(driver,
				"Notice that if you pay by Personal or Business Check, your order may be held for up to 10 days after we receive your check to allow enough time for the check to clear.  If you want us to ship faster upon receipt of your payment, then we recommend your send a money order or Cashier's check."));
		verifyTrue(userCheckoutPage.isPaymentInformationDisplayed(driver, "P.S. You can edit this text from admin panel."));

		log.info("Order_07 - Step 21: Enter to Continue button at Payment Info Buttons Container");
		userCheckoutPage.clickToPaymentInfoButtonByTitle(driver, "Continue");

		log.info("Order_07 - Step 22: Verify Full Name text at Billing Address is displayed: " + fullName);
		verifyEquals(userCheckoutPage.getTextBillingAddress(driver, "name"), fullName);

		log.info("Order_07 - Step 23: Verify Email text at Billing Address is displayed: Email: " + email);
		verifyEquals(userCheckoutPage.getTextBillingAddress(driver, "email"), "Email: " + email);

		log.info("Order_07 - Step 24: Verify Phone text at Billing Address is displayed: Phone: " + phoneNum);
		verifyEquals(userCheckoutPage.getTextBillingAddress(driver, "phone"), "Phone: " + phoneNum);

		log.info("Order_07 - Step 25: Verify Address 1 text at Billing Address is displayed: " + address2);
		verifyEquals(userCheckoutPage.getTextBillingAddress(driver, "address1"), address2);

		log.info("Order_07 - Step 26: Verify City text at Billing Address is displayed: " + city2);
		verifyEquals(userCheckoutPage.getTextBillingAddress(driver, "city"), city2);

		log.info("Order_07 - Step 27: Verify Zip Code text at Billing Address is displayed: " + zipCode2);
		verifyEquals(userCheckoutPage.getTextBillingAddress(driver, "zippostalcode"), zipCode2);

		log.info("Order_07 - Step 28: Verify Country text at Billing Address is displayed: " + country);
		verifyEquals(userCheckoutPage.getTextBillingAddress(driver, "country"), country);

		log.info("Order_07 - Step 29: Verify Payment Method text is displayed: Payment Method: Check / Money Order");
		verifyEquals(userCheckoutPage.getTextPaymentMethod(driver, "label", "value"), "Payment Method: Check / Money Order");

		log.info("Order_07 - Step 30: Verify Full Name text at Shipping Address is displayed: " + fullName);
		verifyEquals(userCheckoutPage.getTextShippingAddress(driver, "name"), fullName);

		log.info("Order_07 - Step 31: Verify Email text at Shipping Address is displayed: Email: " + email);
		verifyEquals(userCheckoutPage.getTextShippingAddress(driver, "email"), "Email: " + email);

		log.info("Order_07 - Step 32: Verify Phone text at Shipping Address is displayed: Phone: " + phoneNum);
		verifyEquals(userCheckoutPage.getTextShippingAddress(driver, "phone"), "Phone: " + phoneNum);

		log.info("Order_07 - Step 33: Verify Address 1 text at Shipping Address is displayed: " + address2);
		verifyEquals(userCheckoutPage.getTextShippingAddress(driver, "address1"), address2);

		log.info("Order_07 - Step 34: Verify City text at Shipping Address is displayed: " + city2);
		verifyEquals(userCheckoutPage.getTextShippingAddress(driver, "city"), city2);

		log.info("Order_07 - Step 35: Verify Zip Code text at Shipping Address is displayed: " + zipCode2);
		verifyEquals(userCheckoutPage.getTextShippingAddress(driver, "zippostalcode"), zipCode2);

		log.info("Order_07 - Step 36: Verify Country text at Shipping Address is displayed: " + country);
		verifyEquals(userCheckoutPage.getTextShippingAddress(driver, "country"), country);

		log.info("Order_07 - Step 37: Verify Shipping Method text is displayed: Shipping Method: Next Day Air");
		verifyEquals(userCheckoutPage.getTextShippingMethod(driver, "label", "value"), "Shipping Method: Next Day Air");

		log.info("Order_07 - Step 38: Verify product information row is displayed: SKU: " + sku2 + ", " + productName2 + ", " + minPrice2 + ", Qty = 10, Total = $18,000.00");
		verifyTrue(userCheckoutPage.isProductInfoRowDisplayed(driver, sku2, productName2, minPrice2, "10", "$18,000.00"));

		log.info("Order_07 - Step 39: Verify Gift Wrapping text is displayed: Gift wrapping: No");
		verifyEquals(userCheckoutPage.getTextGiftWrapping(driver), "Gift wrapping: No");

		log.info("Order_07 - Step 40: Verify Sub Total text at Total Info is displayed: $18,000.00");
		verifyEquals(userCheckoutPage.getTextSubTotalRowInTotalInfo(driver), "$18,000.00");

		log.info("Order_07 - Step 41: Verify Shipping text at Total Info is displayed: Shipping: (Next Day Air) " + shippingPrice);
		verifyTrue(userCheckoutPage.isShippingRowInTotalInfoDisplayed(driver, "(Next Day Air)", shippingPrice));

		log.info("Order_07 - Step 42: Verify Tax text at Total Info is displayed: " + taxPrice);
		verifyEquals(userCheckoutPage.getTextTaxRowInTotalInfo(driver), taxPrice);

		log.info("Order_07 - Step 43: Verify Total text at Total Info is displayed: $18,000.00");
		verifyEquals(userCheckoutPage.getTextTotalRowInTotalInfo(driver), "$18,000.00");

		log.info("Order_07 - Step 44: Verify You Will Earn text at Total Info is displayed: 1800 points");
		verifyEquals(userCheckoutPage.getTextYouWillEarnRowInTotalInfo(driver), "1800 points");

		log.info("Order_07 - Step 45: Click to Confirm button");
		userCheckoutPage.sleepInSecond(30);
		userCheckoutPage.clickToButtonByTitleText(driver, "Confirm");

		log.info("Order_07 - Step 46: Verify Checkout Title is displayed: Thank you");
		verifyEquals(userCheckoutPage.getTextCheckoutTitle(driver), "Thank you");

		log.info("Order_07 - Step 47: Verify Section Order Title is displayed: Your order has been successfully processed!");
		verifyEquals(userCheckoutPage.getTextSectionOrderTitle(driver), "Your order has been successfully processed!");

		String orderNumberInCheckoutPage = userCheckoutPage.getTextOrderNumber(driver);
		String[] orderNum = orderNumberInCheckoutPage.split(":");
		String valueOrderNum = orderNum[1].trim();

		log.info("Order_07 - Step 48: Verify Order Number in Checkout Page is displayed: ORDER NUMBER: " + valueOrderNum);
		String expectedOrderNum1 = "ORDER NUMBER: " + valueOrderNum;
		String expectedOrderNum2 = "Order number: " + valueOrderNum;
		verifyEquals(userCheckoutPage.getTextOrderNumber(driver), userCheckoutPage.selectExpectedText(driver, expectedOrderNum1, expectedOrderNum2));

		log.info("Order_07 - Step 49: Open My Account link in header");
		userMyAccountPage = userCheckoutPage.openMyAccountLinkInHeader(driver);

		log.info("Order_07 - Step 50: Click to Orders link in sidebar");
		userCustomerOrdersPage = userMyAccountPage.clickToOrdersLinkInSidebar(driver);

		log.info("Order_07 - Step 51: Verify Order Number in Orders page is displayed: Order Number: " + valueOrderNum);
		verifyEquals(userCustomerOrdersPage.getTextOrderNumberInOrdersPage(driver), "Order Number: " + valueOrderNum);

		log.info("Order_07 - Step 52: Click to Details button");
		userOrderDetailsPage = userCustomerOrdersPage.clickToDetailsButton(driver);

		log.info("Order_07 - Step 53: Verify Order Number in Order Details page is displayed: ORDER #" + valueOrderNum);
		String expectedOrderNum1_1 = "ORDER #" + valueOrderNum;
		String expectedOrderNum2_2 = "Order #" + valueOrderNum;
		verifyEquals(userOrderDetailsPage.getTextOrderNumberInOrderDetailsPage(driver), userOrderDetailsPage.selectExpectedText(driver, expectedOrderNum1_1, expectedOrderNum2_2));

		String orderDate = userOrderDetailsPage.getCurrentDate(driver);
		log.info("Order_07 - Step 54: Verify Order Date in Order Details page is displayed: Order Date: " + orderDate);
		verifyEquals(userOrderDetailsPage.getTextOrderDateInOrderDetailsPage(driver), "Order Date: " + orderDate);

		log.info("Order_07 - Step 55: Verify Order Status in Order Details page is displayed: Order Status: Pending");
		verifyEquals(userOrderDetailsPage.getTextOrderStatusInOrderDetailsPage(driver), "Order Status: Pending");

		log.info("Order_07 - Step 56: Verify Order Total in Order Details page is displayed: Order Total: $18,000.00");
		verifyEquals(userOrderDetailsPage.getTextOrderTotalInOrderDetailsPage(driver), "Order Total: $18,000.00");

		log.info("Order_07 - Step 57: Verify Full Name text at Billing Address in Order Details page is displayed: " + fullName);
		verifyEquals(userOrderDetailsPage.getTextBillingAddress(driver, "name"), fullName);

		log.info("Order_07 - Step 58: Verify Email text at Billing Address in Order Details page is displayed: Email: " + email);
		verifyEquals(userOrderDetailsPage.getTextBillingAddress(driver, "email"), "Email: " + email);

		log.info("Order_07 - Step 59: Verify Phone Number text at Billing Address in Order Details page is displayed: Phone: " + phoneNum);
		verifyEquals(userOrderDetailsPage.getTextBillingAddress(driver, "phone"), "Phone: " + phoneNum);

		log.info("Order_07 - Step 60: Verify Address 1 text at Billing Address in Order Details page is displayed: " + address2);
		verifyEquals(userOrderDetailsPage.getTextBillingAddress(driver, "address1"), address2);

		log.info("Order_07 - Step 61: Verify City text at Billing Address in Order Details page is displayed: " + city2);
		verifyEquals(userOrderDetailsPage.getTextBillingAddress(driver, "city"), city2);

		log.info("Order_07 - Step 62: Verify Zip Code text at Billing Address in Order Details page is displayed: " + zipCode2);
		verifyEquals(userOrderDetailsPage.getTextBillingAddress(driver, "zippostalcode"), zipCode2);

		log.info("Order_07 - Step 63: Verify Country text at Billing Address in Order Details page is displayed: " + country);
		verifyEquals(userOrderDetailsPage.getTextBillingAddress(driver, "country"), country);

		log.info("Order_07 - Step 64: Verify Payment Method text at Payment Method in Order Details page is displayed: Payment Method: Check / Money Order");
		verifyEquals(userOrderDetailsPage.getTextPaymentMethod(driver, "label", "value"), "Payment Method: Check / Money Order");

		log.info("Order_07 - Step 65: Verify Full Name text at Shipping Address in Order Details page is displayed: " + fullName);
		verifyEquals(userOrderDetailsPage.getTextShippingAddress(driver, "name"), fullName);

		log.info("Order_07 - Step 66: Verify Email text at Shipping Address in Order Details page is displayed: Email: " + email);
		verifyEquals(userOrderDetailsPage.getTextShippingAddress(driver, "email"), "Email: " + email);

		log.info("Order_07 - Step 67: Verify Phone Number text at Shipping Address in Order Details page is displayed: Phone: " + phoneNum);
		verifyEquals(userOrderDetailsPage.getTextShippingAddress(driver, "phone"), "Phone: " + phoneNum);

		log.info("Order_07 - Step 68: Verify Address 1 text at Shipping Address in Order Details page is displayed: " + address2);
		verifyEquals(userOrderDetailsPage.getTextShippingAddress(driver, "address1"), address2);

		log.info("Order_07 - Step 69: Verify City text at Shipping Address in Order Details page is displayed: " + city2);
		verifyEquals(userOrderDetailsPage.getTextShippingAddress(driver, "city"), city2);

		log.info("Order_07 - Step 70: Verify Zip Code text at Shipping Address in Order Details page is displayed: " + zipCode2);
		verifyEquals(userOrderDetailsPage.getTextShippingAddress(driver, "zippostalcode"), zipCode2);

		log.info("Order_07 - Step 71: Verify Country text at Shipping Address in Order Details page is displayed: " + country);
		verifyEquals(userOrderDetailsPage.getTextShippingAddress(driver, "country"), country);

		log.info("Order_07 - Step 72: Verify Shipping Method text at Shipping Address in Order Details page is displayed: Shipping Method: Next Day Air");
		verifyEquals(userOrderDetailsPage.getTextShippingMethod(driver, "label", "value"), "Shipping Method: Next Day Air");

		log.info("Order_07 - Step 73: Verify product info row in Order Details page is displayed: " + sku2 + ", " + productName2 + ", " + minPrice2 + ", Qty = 10, Total = $18,000.00");
		verifyTrue(userOrderDetailsPage.isProductInfoRowDisplayed(driver, sku2, productName2, minPrice2, "10", "$18,000.00"));

		log.info("Order_07 - Step 74: Verify Gift Wrapping text in Order Details page is displayed: Gift wrapping: No");
		verifyEquals(userOrderDetailsPage.getTextGiftWrapping(driver), "Gift wrapping: No");

		log.info("Order_07 - Step 75: Verify Sub Total text at Total Info in Order Details page is displayed: $18,000.00");
		verifyEquals(userOrderDetailsPage.getTextSubTotalRowInTotalInfo(driver), "$18,000.00");

		log.info("Order_07 - Step 76: Verify Shipping text at Total Info in Order Details page is displayed: " + shippingPrice);
		verifyEquals(userOrderDetailsPage.getTextShippingRowAtTotalInfo(driver), shippingPrice);

		log.info("Order_07 - Step 77: Verify Tax text at Total Info in Order Details page is displayed: " + taxPrice);
		verifyEquals(userOrderDetailsPage.getTextTaxRowAtTotalInfo(driver), taxPrice);

		log.info("Order_07 - Step 78: Verify Total text at Total Info in Order Details page is displayed: $18,000.00");
		verifyEquals(userOrderDetailsPage.getTextTotalRowAtTotalInfo(driver), "$18,000.00");
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserDriver();
	}
}