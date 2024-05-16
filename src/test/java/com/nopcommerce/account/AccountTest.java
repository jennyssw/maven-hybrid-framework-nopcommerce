package com.nopcommerce.account;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.nopcommerce.data.DataTest;

import commons.BaseTest;
import environmentConfig.PropertiesConfig;
import pageObjects.user.PageGeneratorManager;
import pageObjects.user.UserCustumerAddressAddPageObject;
import pageObjects.user.UserHomePageObject;
import pageObjects.user.UserProductPageObject;
import pageObjects.user.UserProductReviewPageObject;
import pageObjects.user.header.UserLoginPageObject;
import pageObjects.user.header.UserRegisterPageObject;
import pageObjects.user.sidebar.UserCustomerAddressesPageObject;
import pageObjects.user.sidebar.UserCustomerChangePasswordPageObject;
import pageObjects.user.sidebar.UserCustomerInfoPageObject;
import pageObjects.user.sidebar.UserCustomerProductReviewPageObject;

public class AccountTest extends BaseTest {
	WebDriver driver;
	PropertiesConfig propertiesConfig;
	UserHomePageObject userHomePage;
	UserLoginPageObject userLoginPage;
	UserRegisterPageObject userRegisterPage;
	UserCustomerInfoPageObject userCustomerInfoPage;
	UserCustomerAddressesPageObject userCustomerAddressesPage;
	UserCustomerChangePasswordPageObject userCustomerChangePasswordPage;
	UserCustomerProductReviewPageObject userCustomerProductReviewPage;
	UserCustumerAddressAddPageObject userCustomerAddressAddPage;
	UserProductReviewPageObject userProductReviewPage;
	UserProductPageObject appleMacBookProPage;
	String gender = DataTest.UserAccount.GENDER;
	String accountEmail = DataTest.UserAccount.PRE_EMAIL_ADDRESS + getRandomNumber() + DataTest.UserAccount.WEB_EMAIL_SERVER;
	String registerEmail = DataTest.UserRegister.PRE_EMAIL_ADDRESS + getRandomNumber() + DataTest.UserRegister.WEB_EMAIL_SERVER;
	String firstName = DataTest.UserAccount.FIRST_NAME;
	String lastName = DataTest.UserAccount.LAST_NAME;
	String password = DataTest.UserAccount.PASSWORD;
	String company = DataTest.UserAccount.COMPANY;
	String country = DataTest.UserAccount.COUNTRY;
	String state = DataTest.UserAccount.STATE;
	String city = DataTest.UserAccount.CITY;
	String address1 = DataTest.UserAccount.ADDRESS_1;
	String address2 = DataTest.UserAccount.ADDRESS_2;
	String zipCode = DataTest.UserAccount.ZIP_CODE;
	String phoneNumber = DataTest.UserAccount.PHONE_NUMBER;
	String faxNumber = DataTest.UserAccount.FAX_NUMBER;
	String day = DataTest.UserAccount.DATE_OF_BIRTH_DAY;
	String month = DataTest.UserAccount.DATE_OF_BIRTH_MONTH;
	String year = DataTest.UserAccount.DATE_OF_BIRTH_YEAR;

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

		userHomePage.openLinkInHeaderByTitle(driver, "My account");
		userCustomerInfoPage = PageGeneratorManager.getUserCustomerInfoPage(driver);
	}

	@Test
	public void TC_01_Update_Account_Info() {
		log.info("Account_01 - Step 01: Update account with Gender: " + gender);
		userCustomerInfoPage.clickToRadioByClassName(driver, gender.toLowerCase());

		log.info("Account_01 - Step 02: Update account with new First Name: " + firstName);
		userCustomerInfoPage.enterToTextboxByID(driver, firstName, "FirstName");

		log.info("Account_01 - Step 03: Update account with new Last Name: " + lastName);
		userCustomerInfoPage.enterToTextboxByID(driver, lastName, "LastName");

		log.info("Account_01 - Step 04: Update account with Date Of Birth: " + day + "/" + month + "/" + year);
		userCustomerInfoPage.selectItemInDefaultDropdownByName(driver, day, "DateOfBirthDay");
		userCustomerInfoPage.selectItemInDefaultDropdownByName(driver, month, "DateOfBirthMonth");
		userCustomerInfoPage.selectItemInDefaultDropdownByName(driver, year, "DateOfBirthYear");

		log.info("Account_01 - Step 05: Update account with new Email: " + accountEmail);
		userCustomerInfoPage.enterToTextboxByID(driver, accountEmail, "Email");

		log.info("Account_01 - Step 06: Update account with Company: " + company);
		userCustomerInfoPage.enterToTextboxByID(driver, company, "Company");

		log.info("Account_01 - Step 07: Click to Save Info button");
		userCustomerInfoPage.clickToButtonByTitleText(driver, "Save");

		log.info("Account_01 - Step 08: Verify success message is displayed: 'The customer info has been updated successfully.'");
		verifyEquals(userCustomerInfoPage.getBarNotiSuccessMessage(driver), "The customer info has been updated successfully.");
	}

	@Test
	public void TC_02_Add_Addresses_Info() {
		log.info("Account_02 - Step 01: Click to Addresses Link in sidebar");
		userCustomerAddressesPage = userCustomerInfoPage.clickToAddressesLinkInSidebar(driver);

		log.info("Account_02 - Step 02: Click to Add New button");
		userCustomerAddressesPage.clickToButtonByTitleText(driver, "Add new");
		userCustomerAddressAddPage = PageGeneratorManager.getUserCustomerAddressAddPage(driver);

		log.info("Account_02 - Step 03: Add new address with First Name: " + firstName);
		userCustomerAddressAddPage.enterToTextboxByID(driver, firstName, "Address_FirstName");

		log.info("Account_02 - Step 04: Add new address with Last Name: " + lastName);
		userCustomerAddressAddPage.enterToTextboxByID(driver, lastName, "Address_LastName");

		log.info("Account_02 - Step 05: Add new address with Email: " + accountEmail);
		userCustomerAddressAddPage.enterToTextboxByID(driver, accountEmail, "Address_Email");

		log.info("Account_02 - Step 06: Add new address with Company: " + company);
		userCustomerAddressAddPage.enterToTextboxByID(driver, company, "Address_Company");

		log.info("Account_02 - Step 07: Add new address with Country: " + country);
		userCustomerAddressAddPage.selectItemInDefaultDropdownByID(driver, country, "Address_CountryId");

		log.info("Account_02 - Step 08: Add new address with State: " + state);
		userCustomerAddressAddPage.selectItemInDefaultDropdownByID(driver, state, "Address_StateProvinceId");

		log.info("Account_02 - Step 09: Add new address with City: " + city);
		userCustomerAddressAddPage.enterToTextboxByID(driver, city, "Address_City");

		log.info("Account_02 - Step 10: Add new address with Address 1: " + address1);
		userCustomerAddressAddPage.enterToTextboxByID(driver, address1, "Address_Address1");

		log.info("Account_02 - Step 11: Add new address with Address 2: " + address2);
		userCustomerAddressAddPage.enterToTextboxByID(driver, address2, "Address_Address2");

		log.info("Account_02 - Step 12: Add new address with Zip Code: " + zipCode);
		userCustomerAddressAddPage.enterToTextboxByID(driver, zipCode, "Address_ZipPostalCode");

		log.info("Account_02 - Step 13: Add new address with Phone Number: " + phoneNumber);
		userCustomerAddressAddPage.enterToTextboxByID(driver, phoneNumber, "Address_PhoneNumber");

		log.info("Account_02 - Step 14: Add new address with Fax Number: " + faxNumber);
		userCustomerAddressAddPage.enterToTextboxByID(driver, faxNumber, "Address_FaxNumber");

		log.info("Account_02 - Step 15: Click to Save Address button");
		userCustomerAddressAddPage.clickToButtonByTitleText(driver, "Save");
		userCustomerAddressesPage = PageGeneratorManager.getUserCustomerAddressesPage(driver);

		log.info("Account_02 - Step 16: Verify success message is displayed: 'The new address has been added successfully.'");
		verifyEquals(userCustomerAddressesPage.getBarNotiSuccessMessage(driver), "The new address has been added successfully.");
	}

	@Test
	public void TC_03_Change_Password() {
		log.info("Account_03 - Step 01: Click to Change Password link in sidebar");
		userCustomerChangePasswordPage = userCustomerAddressesPage.clickToChangePasswordLinkInSidebar(driver);

		String oldPassword = password;
		log.info("Account_03 - Step 02: Add Old Password with value: " + oldPassword);
		userCustomerChangePasswordPage.enterToTextboxByID(driver, oldPassword, "OldPassword");

		int newPw = getRandomNumber();
		String newPassword = Integer.toString(newPw);
		log.info("Account_03 - Step 03: Add New Password with value: " + newPassword);
		userCustomerChangePasswordPage.enterToTextboxByID(driver, newPassword, "NewPassword");

		log.info("Account_03 - Step 04: Add Confirm Password with value: " + newPassword);
		userCustomerChangePasswordPage.enterToTextboxByID(driver, newPassword, "ConfirmNewPassword");

		log.info("Account_03 - Step 05: Click to Change Password button");
		userCustomerChangePasswordPage.clickToButtonByTitleText(driver, "Change password");

		log.info("Account_03 - Step 06: Verify success message is displayed: 'Password was changed'");
		verifyEquals(userCustomerChangePasswordPage.getBarNotiSuccessMessage(driver), "Password was changed");

		log.info("Account_03 - Step 07: Close success message in bar notification");
		userCustomerChangePasswordPage.clickToCloseBarNotiSuccessMessageIcon(driver);

		log.info("Account_03 - Step 08: Click to Log Out link in header");
		userHomePage = userCustomerChangePasswordPage.clickToLogOutLinkInHeader(driver);

		log.info("Account_03 - Step 09: Click to Log In link in header");
		userHomePage.openLinkInHeaderByTitle(driver, "Log in");
		userLoginPage = PageGeneratorManager.getUserLoginPage(driver);

		log.info("Account_03 - Step 10: Enter to Email with value: " + accountEmail);
		userLoginPage.enterToTextboxByID(driver, accountEmail, "Email");

		String incorrectPassword = getReverseString(driver, newPassword);
		log.info("Account_03 - Step 11: Enter to Incorrect Password with value: " + incorrectPassword);
		userLoginPage.enterToTextboxByID(driver, incorrectPassword, "Password");

		log.info("Account_03 - Step 12: Click to Login button");
		userLoginPage.clickToButtonByTitleText(driver, "Log in");

		log.info("Account_03 - Step 13: Verify error message is displayed: 'Login was unsuccessful. Please correct the errors and try again. The credentials provided are incorrect'");
		verifyTrue(userLoginPage.isErrorMessageDisplayedInValidationSummaryErrors(driver, "Login was unsuccessful. Please correct the errors and try again. The credentials provided are incorrect"));

		log.info("Account_03 - Step 14: Enter to Email with value: " + accountEmail);
		userLoginPage.enterToTextboxByID(driver, accountEmail, "Email");

		log.info("Account_03 - Step 15: Enter to Correct Password with value: " + newPassword);
		userLoginPage.enterToTextboxByID(driver, newPassword, "Password");

		log.info("Account_03 - Step 16: Click to Login button");
		userLoginPage.clickToButtonByTitleText(driver, "Log in");
		userHomePage = PageGeneratorManager.getUserHomePage(driver);

		log.info("Account_03 - Step 17: Verify redirect 'Login Page to Home Page' after successfuly login");
		verifyTrue(userHomePage.isTopicTitleUserHomePageDisplayed(driver));
	}

	@Test
	public void TC_04_Review_Product() {
		log.info("Account_04 - Step 01: Click to Product Title link with product 'Apple MacBook Pro 13-inch'");
		userHomePage.clickToLinkByTitle(driver, "Apple MacBook Pro 13-inch");
		appleMacBookProPage = PageGeneratorManager.getUserProductPage(driver);

		log.info("Account_04 - Step 02: Click to Add Your Review link");
		appleMacBookProPage.clickToLinkByTitle(driver, "Add your review");
		userProductReviewPage = PageGeneratorManager.getUserProductReviewPage(driver);

		log.info("Account_04 - Step 03: Add Review Title textbox: 'Performance Review'");
		userProductReviewPage.enterToTextboxByID(driver, "Performance Review", "AddProductReview_Title");

		log.info("Account_04 - Step 04: Add Review Text textarea: 'It’s pricey, but it brings a pleasant performance boost to an already fantastic machine.'");
		userProductReviewPage.enterToTextareaByID(driver, "It’s pricey, but it brings a pleasant performance boost to an already fantastic machine.", "AddProductReview_ReviewText");

		log.info("Account_04 - Step 05: Click to Submit Review button");
		userProductReviewPage.clickToButtonByTitleText(driver, "Submit review");

		log.info("Account_04 - Step 06: Close success message in bar notification");
		userCustomerChangePasswordPage.clickToCloseBarNotiSuccessMessageIcon(driver);

		log.info("Account_04 - Step 07: Click to My Account link in header");
		userCustomerInfoPage = userProductReviewPage.clickToMyAccountLinkInHeader(driver, "My account");

		log.info("Account_04 - Step 08: Click to My Product Reviews link in sidebar");
		userCustomerProductReviewPage = userCustomerInfoPage.clickToProductReviewLinkInSidebar(driver);

		log.info("Account_04 - Step 09: Verify my product reviews is displayed");
		verifyTrue(userCustomerProductReviewPage.isProductReviewTitleDisplayed(driver, "Performance Review"));
		verifyTrue(userCustomerProductReviewPage.isProductReviewTextDisplayed(driver, "It’s pricey, but it brings a pleasant performance boost to an already fantastic machine."));
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserDriver();
	}
}
