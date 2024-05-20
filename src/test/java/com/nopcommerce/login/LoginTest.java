package com.nopcommerce.login;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.nopcommerce.data.DataTest;

import commons.BaseTest;
import config.environmentConfig.PropertiesConfig;
import pageObjects.user.PageGeneratorManager;
import pageObjects.user.UserHomePageObject;
import pageObjects.user.header.UserLoginPageObject;
import pageObjects.user.header.UserRegisterPageObject;

public class LoginTest extends BaseTest {
	WebDriver driver;
	PropertiesConfig propertiesConfig;
	UserHomePageObject userHomePage;
	UserLoginPageObject userLoginPage;
	UserRegisterPageObject userRegisterPage;
	String invalidEmail = "123@@456.789";
	String email = DataTest.UserRegister.PRE_EMAIL_ADDRESS + getRandomNumber() + DataTest.UserRegister.WEB_EMAIL_SERVER;
	String password = DataTest.UserLogin.PASSWORD;
	String firstName = DataTest.UserRegister.FIRST_NAME;
	String lastName = DataTest.UserRegister.LAST_NAME;

	@Parameters({ "browser", "environment" })
	@BeforeClass
	public void beforeClass(String browserName, String environment) {
		propertiesConfig = PropertiesConfig.getProperties(environment);
		driver = getBrowserDriver(browserName, propertiesConfig.getUserUrl());

		userHomePage = PageGeneratorManager.getUserHomePage(driver);
		userHomePage.openLinkInHeaderByTitle(driver, "Log in");
		userLoginPage = PageGeneratorManager.getUserLoginPage(driver);
	}

	@Test
	public void TC_01_Login_With_Empty_Data() {
		log.info("Login_01 - Step 01: Login with empty Email");
		userLoginPage.enterToTextboxByID(driver, "", "Email");

		log.info("Login_01 - Step 02: Login with empty Password");
		userLoginPage.enterToTextboxByID(driver, "", "Password");

		log.info("Login_01 - Step 03: Click to Login button");
		userLoginPage.clickToButtonByTitleText(driver, "Log in");

		log.info("Login_01 - Step 04: Verify error message is displayed: 'Please enter your email'");
		verifyEquals(userLoginPage.getFieldErrorMessageByClass(driver, "field-validation-error"), "Please enter your email");
	}

	@Test
	public void TC_02_Login_With_Invalid_Email() {
		log.info("Login_02 - Step 01: Click to Login link in header");
		if (userLoginPage.getTextLogInLogOutInHeaderLink(driver).equals("Log out")) {
			userLoginPage.clickToLinkByTitle(driver, "Log out");
			userHomePage = PageGeneratorManager.getUserHomePage(driver);
			userHomePage.openLinkInHeaderByTitle(driver, "Log in");
			userLoginPage = PageGeneratorManager.getUserLoginPage(driver);
		} else if (userLoginPage.getTextLogInLogOutInHeaderLink(driver).equals("Log in")) {
			userLoginPage.clickToLoginLinkInHeader(driver);
		} else {
			throw new RuntimeException("Performance issues!");
		}

		log.info("Login_02 - Step 02: Login with invalid Email: " + invalidEmail);
		userLoginPage.enterToTextboxByClassName(driver, invalidEmail, "email");

		log.info("Login_02 - Step 03: Login with Password: " + password);
		userLoginPage.enterToTextboxByClassName(driver, password, "password");

		log.info("Login_02 - Step 04: Click to Login button");
		userLoginPage.clickToButtonByTitleText(driver, "Log in");

		log.info("Login_02 - Step 05: Verify error message is displayed: 'Please enter a valid email address.'");
		verifyEquals(userLoginPage.getFieldErrorMessageByID(driver, "Email-error"), "Please enter a valid email address.");
	}

	@Test
	public void TC_03_Login_With_Unregistered_Email() {
		log.info("Login_03 - Step 01: Click to Login link in header");
		if (userLoginPage.getTextLogInLogOutInHeaderLink(driver).equals("Log out")) {
			userLoginPage.clickToLinkByTitle(driver, "Log out");
			userHomePage = PageGeneratorManager.getUserHomePage(driver);
			userHomePage.openLinkInHeaderByTitle(driver, "Log in");
			userLoginPage = PageGeneratorManager.getUserLoginPage(driver);
		} else if (userLoginPage.getTextLogInLogOutInHeaderLink(driver).equals("Log in")) {
			userLoginPage.clickToLoginLinkInHeader(driver);
		} else {
			throw new RuntimeException("Performance issues!");
		}

		log.info("Login_03 - Step 02: Login with unregistered Email: " + email);
		userLoginPage.enterToTextboxByClassName(driver, email, "email");

		log.info("Login_03 - Step 03: Login with Password: " + password);
		userLoginPage.enterToTextboxByClassName(driver, password, "password");

		log.info("Login_03 - Step 04: Click to Login button");
		userLoginPage.clickToButtonByTitleText(driver, "Log in");

		log.info("Login_03 - Step 05: Verify error message is displayed: 'Login was unsuccessful. Please correct the errors and try again. The credentials provided are incorrect'");
		verifyTrue(userLoginPage.isErrorMessageDisplayedInValidationSummaryErrors(driver, "Login was unsuccessful. Please correct the errors and try again. The credentials provided are incorrect"));
	}

	@Test
	public void TC_04_Login_With_Registered_Email_And_Empty_Password() {
		log.info("Login_04 - Step 01: Click to Register link in header");
		userRegisterPage = userLoginPage.clickToRegisterLinkInHeader(driver);

		log.info("Login_04 - Step 02: Register with First Name: " + firstName);
		userRegisterPage.enterToTextboxByID(driver, firstName, "FirstName");

		log.info("Login_04 - Step 03: Register with Last Name: " + lastName);
		userRegisterPage.enterToTextboxByID(driver, lastName, "LastName");

		log.info("Login_04 - Step 04: Register with Email: " + email);
		userRegisterPage.enterToTextboxByID(driver, email, "Email");

		log.info("Login_04 - Step 05: Register with Password: " + password);
		userRegisterPage.enterToTextboxByID(driver, password, "Password");

		log.info("Login_04 - Step 06: Register with Confirm Password: " + password);
		userRegisterPage.enterToTextboxByID(driver, password, "ConfirmPassword");

		log.info("Login_04 - Step 07: Click to Register button");
		userRegisterPage.clickToButtonByTitleText(driver, "Register");

		log.info("Login_04 - Step 08: Verify success Message is displayed: 'Your registration completed'");
		verifyEquals(userRegisterPage.getPageSuccessMessage(driver), "Your registration completed");

		log.info("Login_04 - Step 09: Click to Login link in header");
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

		log.info("Login_04 - Step 10: Login with registered Email: " + email);
		userLoginPage.enterToTextboxByClassName(driver, email, "email");

		log.info("Login_04 - Step 11: Login with empty Password");
		userLoginPage.enterToTextboxByClassName(driver, "", "password");

		log.info("Login_04 - Step 12: Click to Login button");
		userLoginPage.clickToButtonByTitleText(driver, "Log in");

		log.info("Login_04 - Step 13: Verify error message is displayed: 'Login was unsuccessful. Please correct the errors and try again. The credentials provided are incorrect'");
		verifyTrue(userLoginPage.isErrorMessageDisplayedInValidationSummaryErrors(driver, "Login was unsuccessful. Please correct the errors and try again. The credentials provided are incorrect"));
	}

	@Test
	public void TC_05_Login_With_Registered_Email_And_Incorrect_Password() {
		log.info("Login_05 - Step 01: Click to Login link in header");
		if (userLoginPage.getTextLogInLogOutInHeaderLink(driver).equals("Log out")) {
			userLoginPage.clickToLinkByTitle(driver, "Log out");
			userHomePage = PageGeneratorManager.getUserHomePage(driver);
			userHomePage.openLinkInHeaderByTitle(driver, "Log in");
			userLoginPage = PageGeneratorManager.getUserLoginPage(driver);
		} else if (userLoginPage.getTextLogInLogOutInHeaderLink(driver).equals("Log in")) {
			userLoginPage.clickToLoginLinkInHeader(driver);
		} else {
			throw new RuntimeException("Performance issues!");
		}

		log.info("Login_05 - Step 02: Login with registered Email: " + email);
		userLoginPage.enterToTextboxByClassName(driver, email, "email");

		String incorrectPassword = getReverseString(driver, password);
		log.info("Login_05 - Step 03: Login with incorrect Password: " + incorrectPassword);
		userLoginPage.enterToTextboxByClassName(driver, incorrectPassword, "password");

		log.info("Login_05 - Step 04: Click to Login button");
		userLoginPage.clickToButtonByTitleText(driver, "Log in");

		log.info("Login_05 - Step 05: Verify error message is displayed: 'Login was unsuccessful. Please correct the errors and try again. The credentials provided are incorrect'");
		verifyTrue(userLoginPage.isErrorMessageDisplayedInValidationSummaryErrors(driver, "Login was unsuccessful. Please correct the errors and try again. The credentials provided are incorrect"));
	}

	@Test
	public void TC_06_Login_With_Registered_Email_And_Correct_Password() {
		log.info("Login_06 - Step 01: Click to Login link in header");
		if (userLoginPage.getTextLogInLogOutInHeaderLink(driver).equals("Log out")) {
			userLoginPage.clickToLinkByTitle(driver, "Log out");
			userHomePage = PageGeneratorManager.getUserHomePage(driver);
			userHomePage.openLinkInHeaderByTitle(driver, "Log in");
			userLoginPage = PageGeneratorManager.getUserLoginPage(driver);
		} else if (userLoginPage.getTextLogInLogOutInHeaderLink(driver).equals("Log in")) {
			userLoginPage.clickToLoginLinkInHeader(driver);
		} else {
			throw new RuntimeException("Performance issues!");
		}

		log.info("Login_06 - Step 02: Login with registered Email: " + email);
		userLoginPage.enterToTextboxByClassName(driver, email, "email");

		log.info("Login_06 - Step 03: Login with correct Password: " + password);
		userLoginPage.enterToTextboxByClassName(driver, password, "password");

		log.info("Login_06 - Step 04: Click to Login button");
		userLoginPage.clickToButtonByTitleText(driver, "Log in");
		userHomePage = PageGeneratorManager.getUserHomePage(driver);

		log.info("Login_06 - Step 05: Verify redirect 'Login Page to Home Page' after successfuly login");
		verifyTrue(userHomePage.isTopicTitleUserHomePageDisplayed(driver));
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserDriver();
	}
}
