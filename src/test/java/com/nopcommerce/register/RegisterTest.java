package com.nopcommerce.register;

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
import pageObjects.user.header.UserRegisterPageObject;

public class RegisterTest extends BaseTest {
	WebDriver driver;
	Environment env;
	UserHomePageObject userHomePage;
	UserRegisterPageObject userRegisterPage;
	String invalidEmail = "123@@456.789";
	String invalidPassword = "123";
	String email = DataTest.UserRegister.PRE_EMAIL_ADDRESS + getRandomNumber() + DataTest.UserRegister.WEB_EMAIL_SERVER;
	String firstName = DataTest.UserRegister.FIRST_NAME;
	String lastName = DataTest.UserRegister.LAST_NAME;
	String password = DataTest.UserRegister.PASSWORD;

	@Parameters({ "browser", "environment" })
	@BeforeClass
	public void beforeClass(String browserName, String environment) {
		ConfigFactory.setProperty("env", environment.toLowerCase());
		env = ConfigFactory.create(Environment.class);
		driver = getBrowserDriver(browserName, env.getUserUrl());

		userHomePage = PageGeneratorManager.getUserHomePage(driver);
		userHomePage.openLinkInHeaderByTitle(driver, "Register");
		userRegisterPage = PageGeneratorManager.getUserRegisterPage(driver);
	}

	@Test
	public void TC_01_Register_With_Empty_Data() {
		log.info("Register_01 - Step 01: Register with empty First Name");
		userRegisterPage.enterToTextboxByID(driver, "", "FirstName");

		log.info("Register_01 - Step 02: Register with empty Last Name");
		userRegisterPage.enterToTextboxByID(driver, "", "LastName");

		log.info("Register_01 - Step 03: Register with empty Email");
		userRegisterPage.enterToTextboxByID(driver, "", "Email");

		log.info("Register_01 - Step 04: Register with empty Password");
		userRegisterPage.enterToTextboxByID(driver, "", "Password");

		log.info("Register_01 - Step 05: Register with empty Confirm Password");
		userRegisterPage.enterToTextboxByID(driver, "", "ConfirmPassword");

		log.info("Register_01 - Step 06: Click to Register button");
		userRegisterPage.clickToButtonByTitleText(driver, "Register");

		log.info("Register_01 - Step 07: Verify error message is displayed: 'First name is required.'");
		verifyEquals(userRegisterPage.getFieldErrorMessageByID(driver, "FirstName-error"), "First name is required.");

		log.info("Register_01 - Step 08: Verify error message is displayed: 'Last name is required.'");
		verifyEquals(userRegisterPage.getFieldErrorMessageByID(driver, "LastName-error"), "Last name is required.");

		log.info("Register_01 - Step 09: Verify error message is displayed: 'Email is required.'");
		verifyEquals(userRegisterPage.getFieldErrorMessageByID(driver, "Email-error"), "Email is required.");

		log.info("Register_01 - Step 10: Verify error message is displayed: 'Password is required.'");
		verifyEquals(userRegisterPage.getFieldErrorMessageByID(driver, "Password-error"), "Password is required.");

		log.info("Register_01 - Step 11: Verify error message is displayed: 'Password is required.'");
		verifyEquals(userRegisterPage.getFieldErrorMessageByID(driver, "ConfirmPassword-error"), "Password is required.");
	}

	@Test
	public void TC_02_Register_With_Invalid_Email() {
		log.info("Register_02 - Step 01: Click to Register link in header");
		userRegisterPage.clickToRegisterLinkInHeader(driver);

		log.info("Register_02 - Step 02: Register with First Name: " + firstName);
		userRegisterPage.enterToTextboxByID(driver, firstName, "FirstName");

		log.info("Register_02 - Step 03: Register with Last Name: " + lastName);
		userRegisterPage.enterToTextboxByID(driver, lastName, "LastName");

		log.info("Register_02 - Step 04: Register with invalid Email: " + invalidEmail);
		userRegisterPage.enterToTextboxByID(driver, invalidEmail, "Email");

		log.info("Register_02 - Step 05: Register with Password: " + password);
		userRegisterPage.enterToTextboxByID(driver, password, "Password");

		log.info("Register_02 - Step 06: Register with Confirm Password: " + password);
		userRegisterPage.enterToTextboxByID(driver, password, "ConfirmPassword");

		log.info("Register_02 - Step 07: Click to Register button");
		userRegisterPage.clickToButtonByTitleText(driver, "Register");

		log.info("Register_02 - Step 08: Verify error message is displayed: 'Wrong email'");
		verifyEquals(userRegisterPage.getFieldErrorMessageByID(driver, "Email-error"), "Wrong email");
	}

	@Test
	public void TC_03_Register_With_Valid_Information() {
		log.info("Register_03 - Step 01: Click to Register link in header");
		userRegisterPage.clickToRegisterLinkInHeader(driver);

		log.info("Register_03 - Step 02: Register with First Name: " + firstName);
		userRegisterPage.enterToTextboxByID(driver, firstName, "FirstName");

		log.info("Register_03 - Step 03: Register with Last Name: " + lastName);
		userRegisterPage.enterToTextboxByID(driver, lastName, "LastName");

		log.info("Register_03 - Step 04: Register with Email: " + email);
		userRegisterPage.enterToTextboxByID(driver, email, "Email");

		log.info("Register_03 - Step 05: Register with Password: " + password);
		userRegisterPage.enterToTextboxByID(driver, password, "Password");

		log.info("Register_03 - Step 06: Register with Confirm Password: " + password);
		userRegisterPage.enterToTextboxByID(driver, password, "ConfirmPassword");

		log.info("Register_03 - Step 07: Click to Register button");
		userRegisterPage.clickToButtonByTitleText(driver, "Register");

		log.info("Register_03 - Step 08: Verify success message is displayed: 'Your registration completed'");
		verifyEquals(userRegisterPage.getPageSuccessMessage(driver), "Your registration completed");
	}

	@Test
	public void TC_04_Register_With_Registered_Email() {
		log.info("Register_04 - Step 01: Click to Register link in header");
		if (userRegisterPage.getTextLogInLogOutInHeaderLink(driver).equals("Log out")) {
			userRegisterPage.clickToLinkByTitle(driver, "Log out");
			userRegisterPage.clickToRegisterLinkInHeader(driver);
		} else if (userRegisterPage.getTextLogInLogOutInHeaderLink(driver).equals("Log in")) {
			userRegisterPage.clickToRegisterLinkInHeader(driver);
		} else {
			throw new RuntimeException("Performance issues!");
		}

		log.info("Register_04 - Step 02: Register with First Name: " + firstName);
		userRegisterPage.enterToTextboxByID(driver, firstName, "FirstName");

		log.info("Register_04 - Step 03: Register with Last Name: " + lastName);
		userRegisterPage.enterToTextboxByID(driver, lastName, "LastName");

		log.info("Register_04 - Step 04: Register with Email: " + email);
		userRegisterPage.enterToTextboxByID(driver, email, "Email");

		log.info("Register_04 - Step 05: Register with Password: " + password);
		userRegisterPage.enterToTextboxByID(driver, password, "Password");

		log.info("Register_04 - Step 06: Register with Confirm Password: " + password);
		userRegisterPage.enterToTextboxByID(driver, password, "ConfirmPassword");

		log.info("Register_04 - Step 07: Click to Register button");
		userRegisterPage.clickToButtonByTitleText(driver, "Register");

		log.info("Register_04 - Step 08: Verify error message is displayed: 'The specified email already exists'");
		verifyEquals(userRegisterPage.getPageErrorMessage(driver), "The specified email already exists");
	}

	@Test
	public void TC_05_Register_With_Password_Less_Than_6_Characters() {
		log.info("Register_05 - Step 01: Click to Register link in header");
		userRegisterPage.clickToRegisterLinkInHeader(driver);

		log.info("Register_05 - Step 02: Register with First Name: " + firstName);
		userRegisterPage.enterToTextboxByID(driver, firstName, "FirstName");

		log.info("Register_05 - Step 03: Register with Last Name: " + lastName);
		userRegisterPage.enterToTextboxByID(driver, lastName, "LastName");

		log.info("Register_05 - Step 04: Register with Email: " + email);
		userRegisterPage.enterToTextboxByID(driver, email, "Email");

		log.info("Register_05 - Step 05: Register with Password: " + invalidPassword);
		userRegisterPage.enterToTextboxByID(driver, invalidPassword, "Password");

		log.info("Register_05 - Step 06: Register with Confirm Password: " + invalidPassword);
		userRegisterPage.enterToTextboxByID(driver, invalidPassword, "ConfirmPassword");

		log.info("Register_05 - Step 07: Click to Register button");
		userRegisterPage.clickToButtonByTitleText(driver, "Register");

		log.info("Register_05 - Step 08: Verify error message is displayed: 'Password must meet the following rules: must have at least 6 characters'");
		verifyEquals(userRegisterPage.getFieldErrorMessageByID(driver, "Password-error"), "Password must meet the following rules:" + "\n" + "must have at least 6 characters");
	}

	@Test
	public void TC_06_Register_With_Password_Not_Equal_Confirm_Password() {
		log.info("Register_06 - Step 01: Click to Register link in header");
		userRegisterPage.clickToRegisterLinkInHeader(driver);

		log.info("Register_06 - Step 02: Register with First Name: " + firstName);
		userRegisterPage.enterToTextboxByID(driver, firstName, "FirstName");

		log.info("Register_06 - Step 03: Register with Last Name: " + lastName);
		userRegisterPage.enterToTextboxByID(driver, lastName, "LastName");

		log.info("Register_06 - Step 04: Register with Email: " + email);
		userRegisterPage.enterToTextboxByID(driver, email, "Email");

		log.info("Register_06 - Step 05: Register with Password: " + password);
		userRegisterPage.enterToTextboxByID(driver, password, "Password");

		String incorrectConfirmPassword = getReverseString(driver, password);
		log.info("Register_06 - Step 06: Register with Confirm Password: " + incorrectConfirmPassword);
		userRegisterPage.enterToTextboxByID(driver, incorrectConfirmPassword, "ConfirmPassword");

		log.info("Register_06 - Step 07: Click to Register button");
		userRegisterPage.clickToButtonByTitleText(driver, "Register");

		log.info("Register_06 - Step 08: Verify error message is displayed: 'The password and confirmation password do not match.'");
		verifyEquals(userRegisterPage.getFieldErrorMessageByID(driver, "ConfirmPassword-error"), "The password and confirmation password do not match.");
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserDriver();
	}
}
