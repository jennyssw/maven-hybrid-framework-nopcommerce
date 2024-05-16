package com.nopcommerce.admin;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.nopcommerce.data.DataTest;

import commons.BaseTest;
import environmentConfig.PropertiesConfig;
import pageObjects.admin.AdminAddANewAddressPageObject;
import pageObjects.admin.AdminAddANewCustomerPageObject;
import pageObjects.admin.AdminEditAddressPageObject;
import pageObjects.admin.AdminEditCustomerDetailsPageObject;
import pageObjects.admin.AdminLoginPageObject;
import pageObjects.admin.PageGeneratorManager;
import pageObjects.admin.menu.AdminCustomersPageObject;
import pageObjects.admin.menu.AdminDashboardPageObject;
import pageObjects.admin.menu.AdminProductsPageObject;

public class CustomerTest extends BaseTest {
	WebDriver driver;
	PropertiesConfig propertiesConfig;
	AdminLoginPageObject adminLoginPage;
	AdminDashboardPageObject adminDashboardPage;
	AdminProductsPageObject adminProductsPage;
	AdminCustomersPageObject adminCustomersPage;
	AdminAddANewCustomerPageObject adminAddANewCustomerPage;
	AdminEditCustomerDetailsPageObject adminEditCustomerDetailsPage;
	AdminAddANewAddressPageObject adminAddANewAddressPage;
	AdminEditAddressPageObject adminEditAddressPage;
	String driverInstanceName;
	String fullAddress;
	String productName = DataTest.AdminSearchProduct.PRODUCT_NAME;
	String sku = DataTest.AdminSearchProduct.SKU;
	String price = DataTest.AdminSearchProduct.PRICE;
	String stockQuantity = DataTest.AdminSearchProduct.STOCK_QUANTITY;
	String password = DataTest.AdminCustomer.PASSWORD;
	String gender = DataTest.AdminCustomer.GENDER;
	String email = DataTest.AdminCustomer.PRE_EMAIL_ADDRESS + getRandomNumber() + DataTest.AdminCustomer.WEB_EMAIL_SERVER;
	String email2 = "edit_" + DataTest.AdminCustomer.PRE_EMAIL_ADDRESS + getRandomNumber() + DataTest.AdminCustomer.WEB_EMAIL_SERVER;
	String firstName = "Jenny";
	String firstName2 = "Edit " + firstName;
	String lastName = "Automation_" + getRandomNumber();
	String lastName2 = "Edit " + lastName;
	String fullName = firstName + " " + lastName;
	String fullName2 = firstName2 + " " + lastName2;
	String monthOfBirth = DataTest.AdminCustomer.MONTH_OF_BIRTH;
	String monthOfBirth2 = DataTest.AdminCustomer.MONTH_OF_BIRTH_2;
	String dayOfBirth = DataTest.AdminCustomer.DAY_OF_BIRTH;
	String dayOfBirth2 = DataTest.AdminCustomer.DAY_OF_BIRTH_2;
	String yearOfBirth = DataTest.AdminCustomer.YEAR_OF_BIRTH;
	String yearOfBirth2 = DataTest.AdminCustomer.YEAR_OF_BIRTH_2;
	String dateOfBirth = monthOfBirth + "/" + dayOfBirth + "/" + yearOfBirth;
	String dateOfBirth2 = monthOfBirth2 + "/" + dayOfBirth2 + "/" + yearOfBirth2;
	String company = DataTest.AdminCustomer.COMPANY;
	String company2 = DataTest.AdminCustomer.COMPANY_2;
	String country = DataTest.AdminCustomer.COUNTRY;
	String country2 = DataTest.AdminCustomer.COUNTRY_2;
	String state = DataTest.AdminCustomer.STATE;
	String state2 = DataTest.AdminCustomer.STATE_2;
	String county = DataTest.AdminCustomer.COUNTY;
	String county2 = DataTest.AdminCustomer.COUNTY_2;
	String city = DataTest.AdminCustomer.CITY;
	String city2 = DataTest.AdminCustomer.CITY_2;
	String address1 = DataTest.AdminCustomer.ADDRESS_1;
	String address1_2 = DataTest.AdminCustomer.ADDRESS_1_2;
	String address2 = DataTest.AdminCustomer.ADDRESS_2;
	String address2_2 = DataTest.AdminCustomer.ADDRESS_2_2;
	String zipCode = DataTest.AdminCustomer.ZIP_CODE;
	String zipCode2 = DataTest.AdminCustomer.ZIP_CODE_2;
	String phoneNum = DataTest.AdminCustomer.PHONE_NUM;
	String phoneNum2 = DataTest.AdminCustomer.PHONE_NUM_2;
	String faxNum = DataTest.AdminCustomer.FAX_NUM;
	String faxNum2 = DataTest.AdminCustomer.FAX_NUM_2;

	@Parameters({ "browser", "environment" })
	@BeforeClass
	public void beforeClass(String browserName, String environment) {
		propertiesConfig = PropertiesConfig.getProperties(environment);
		driver = getBrowserDriver(browserName, propertiesConfig.getAdminUrl());
		driverInstanceName = driver.toString().toLowerCase();

		adminLoginPage = PageGeneratorManager.getAdminLoginPage(driver);
		adminLoginPage.enterToTextboxByID(driver, DataTest.AdminLogin.ADMIN_EMAIL, "Email");
		adminLoginPage.enterToTextboxByID(driver, DataTest.AdminLogin.ADMIN_PASSWORD, "Password");
		adminLoginPage.clickToButtonByTitleText(driver, "Log in");
		adminDashboardPage = PageGeneratorManager.getAdminDashboardPage(driver);
	}

	@Test
	public void TC_01_Create_New_Customer() {
		log.info("Customers_01 - Step 01: Click to Customers link in menu and Customers link in sub menu");
		adminCustomersPage = adminDashboardPage.openAdminCustomersPage(driver, "Customers", "Customers");

		log.info("Customers_01 - Step 02: Click Add New button");
		adminCustomersPage.clickToButtonByTitleNormalizeSpace(driver, "Add new");
		adminAddANewCustomerPage = PageGeneratorManager.getAdminAddANewCustomerPage(driver);

		log.info("Customers_01 - Step 03: Enter to Email textbox: " + email);
		adminAddANewCustomerPage.enterToTextboxByID(driver, email, "Email");

		log.info("Customers_01 - Step 04: Enter to Password textbox: " + password);
		adminAddANewCustomerPage.enterToTextboxByID(driver, password, "Password");

		log.info("Customers_01 - Step 05: Enter to First Name textbox: " + firstName);
		adminAddANewCustomerPage.enterToTextboxByID(driver, firstName, "FirstName");

		log.info("Customers_01 - Step 06: Enter to Last Name textbox: " + lastName);
		adminAddANewCustomerPage.enterToTextboxByID(driver, lastName, "LastName");

		log.info("Customers_01 - Step 07: Click to Gender radio: " + gender);
		adminAddANewCustomerPage.clickToGenderRadio(driver, gender);

		log.info("Customers_01 - Step 08: Enter to Date picker: " + dateOfBirth);
		adminAddANewCustomerPage.enterToDatePickerByID(driver, dateOfBirth);

		log.info("Customers_01 - Step 09: Enter to Company textbox: " + company);
		adminAddANewCustomerPage.enterToTextboxByID(driver, company, "Company");

		log.info("Customers_01 - Step 10: Select tag in Customer Roles taglist: Guests");
		adminAddANewCustomerPage.selectTagInCustomerRolesTaglistInAddNewCusPage(driver, "Guests");

		log.info("Customers_01 - Step 11: Click to Delete icon in Customer Roles taglist by name tag: Registered");
		adminAddANewCustomerPage.clickToDeleteIconInCustomerRolesTaglistByTitle(driver, "Registered");

		log.info("Customers_01 - Step 12: Check to Active checkbox");
		adminAddANewCustomerPage.clickToCheckboxByID(driver, "Active");

		log.info("Customers_01 - Step 13: Enter to Admin Comment textarea: Add new Customer (Guest)");
		adminAddANewCustomerPage.enterToTextareaByID(driver, "Add new Customer (Guest)", "AdminComment");

		log.info("Customers_01 - Step 14: Click to Save and Continue Edit button");
		adminAddANewCustomerPage.clickToButtonByTitleNormalizeSpace(driver, "Save and Continue Edit");
		adminEditCustomerDetailsPage = PageGeneratorManager.getAdminEditCustomerDetailsPage(driver);

		log.info("Customers_01 - Step 15: Verify alert success message is displayed: The new customer has been added successfully.");
		verifyTrue(adminEditCustomerDetailsPage.isAlertSuccessMessageDisplayed(driver, "The new customer has been added successfully."));

		log.info("Customers_01 - Step 16: Verify Email value is displayed: " + email);
		verifyEquals(adminEditCustomerDetailsPage.getTextValueInTextboxByID(driver, "Email"), email);

		log.info("Customers_01 - Step 17: Verify First Name value is displayed: " + firstName);
		verifyEquals(adminEditCustomerDetailsPage.getTextValueInTextboxByID(driver, "FirstName"), firstName);

		log.info("Customers_01 - Step 18: Verify Last Name value is displayed: " + lastName);
		verifyEquals(adminEditCustomerDetailsPage.getTextValueInTextboxByID(driver, "LastName"), lastName);

		log.info("Customers_01 - Step 19: Verify selected radio is displayed: " + gender);
		verifyTrue(adminEditCustomerDetailsPage.isSelectedRadioDisplayed(driver, gender));

		String dateOfBirthOutput = monthOfBirth + "/" + dayOfBirth + "/" + yearOfBirth;
		log.info("Customers_01 - Step 20: Verify Date Of Birth value is displayed: " + dateOfBirthOutput);
		verifyTrue(adminEditCustomerDetailsPage.isDateOfBirthEntered(driver, dateOfBirthOutput));

		log.info("Customers_01 - Step 21: Verify Company value is displayed: " + company);
		verifyEquals(adminEditCustomerDetailsPage.getTextValueInTextboxByID(driver, "Company"), company);

		log.info("Customers_01 - Step 22: Verify Customer Roles value is displayed: " + "Guests");
		verifyTrue(adminEditCustomerDetailsPage.isSelectedCustomerRoleTagDisplayed(driver, "Guests"));

		log.info("Customers_01 - Step 23: Verify Active checkbox is selected");
		verifyTrue(adminEditCustomerDetailsPage.isCheckboxSelectedByID(driver, "Active"));

		log.info("Customers_01 - Step 24: Verify Admin Comment value is displayed: " + "Add new Customer (Guest)");
		verifyEquals(adminEditCustomerDetailsPage.getTextInTextareaByID(driver, "AdminComment"), "Add new Customer (Guest)");

		log.info("Customers_01 - Step 25: Click to Back To Customer List link");
		adminEditCustomerDetailsPage.clickToLinkByTitle(driver, "back to customer list");
		adminCustomersPage = PageGeneratorManager.getAdminCustomersPage(driver);

		log.info("Customers_01 - Step 26: Select tag in Customer Roles taglist: Guests");
		adminCustomersPage.openSearchCustomerCard(driver);
		adminCustomersPage.selectTagInCustomerRolesTaglistInCustomersPage(driver, "Guests");

		log.info("Customers_01 - Step 27: Click to Delete icon in Customer Roles taglist by name tag: Registered");
		adminCustomersPage.clickToDeleteIconInCustomerRolesTaglistByTitle(driver, "Registered");

		log.info("Customers_01 - Step 28: Click to Search button");
		adminCustomersPage.clickToSearchCustomerButton(driver);

		log.info("Customers_01 - Step 29: Verify Customer Info is displayed");
		adminCustomersPage.scrollToCustomerInfoByJS(driver, "Guest", fullName, "Guests", company, "true");
		verifyTrue(adminCustomersPage.isCustomerInfoDisplayed(driver, "Guest", fullName, "Guests", company, "true"));
	}

	@Test
	public void TC_02_Search_Customer_With_Email() {
		log.info("Customers_02 - Step 01: Enter to Email textbox with: " + email);
		adminCustomersPage.openSearchCustomerCard(driver);
		adminCustomersPage.enterToTextboxByID(driver, email, "SearchEmail");

		log.info("Customers_02 - Step 02: Click to Search button");
		adminCustomersPage.clickToSearchCustomerButton(driver);

		log.info("Customers_02 - Step 03: Verify only one item is displayed");
		adminCustomersPage.scrollToCustomerInfoByJS(driver, "Guest", fullName, "Guests", company, "true");
		verifyTrue(adminCustomersPage.isCustomerInfoDisplayed(driver, "Guest", fullName, "Guests", company, "true"));
		verifyTrue(adminCustomersPage.isOneItemInTableDisplayed(driver));
	}

	@Test
	public void TC_03_Search_Customer_With_First_Name_Last_Name() {
		log.info("Customers_03 - Step 01: Enter to Email textbox with 'null'");
		adminCustomersPage.openSearchCustomerCard(driver);
		adminCustomersPage.enterToTextboxByID(driver, "", "SearchEmail");

		log.info("Customers_03 - Step 02: Enter to First Name textbox with: " + firstName);
		adminCustomersPage.enterToTextboxByID(driver, firstName, "SearchFirstName");

		log.info("Customers_03 - Step 03: Enter to Last Name textbox with: " + lastName);
		adminCustomersPage.enterToTextboxByID(driver, lastName, "SearchLastName");

		log.info("Customers_03 - Step 04: Click to Search button");
		adminCustomersPage.clickToSearchCustomerButton(driver);

		log.info("Customers_03 - Step 05: Verify only one item is displayed");
		adminCustomersPage.scrollToCustomerInfoByJS(driver, "Guest", fullName, "Guests", company, "true");
		verifyTrue(adminCustomersPage.isCustomerInfoDisplayed(driver, "Guest", fullName, "Guests", company, "true"));
		verifyTrue(adminCustomersPage.isOneItemInTableDisplayed(driver));
	}

	@Test
	public void TC_04_Search_Customer_With_Company() {
		log.info("Customers_04 - Step 01: Enter to First Name textbox with 'null'");
		adminCustomersPage.openSearchCustomerCard(driver);
		adminCustomersPage.enterToTextboxByID(driver, "", "SearchFirstName");

		log.info("Customers_04 - Step 02: Enter to Last Name textbox with 'null'");
		adminCustomersPage.enterToTextboxByID(driver, "", "SearchLastName");

		log.info("Customers_04 - Step 03: Enter to Company textbox with " + company);
		adminCustomersPage.enterToTextboxByID(driver, company, "SearchCompany");

		log.info("Customers_04 - Step 04: Click to Search button");
		adminCustomersPage.clickToSearchCustomerButton(driver);

		log.info("Customers_04 - Step 05: Verify only one item is displayed");
		adminCustomersPage.scrollToCustomerInfoByJS(driver, "Guest", fullName, "Guests", company, "true");
		verifyTrue(adminCustomersPage.isCustomerInfoDisplayed(driver, "Guest", fullName, "Guests", company, "true"));
		verifyTrue(adminCustomersPage.isOneItemInTableDisplayed(driver));
	}

	@Test
	public void TC_05_Search_Customer_With_Full_Data() {
		log.info("Customers_05 - Step 01: Enter to Email textbox with: " + email);
		adminCustomersPage.openSearchCustomerCard(driver);
		adminCustomersPage.enterToTextboxByID(driver, email, "SearchEmail");

		log.info("Customers_05 - Step 02: Enter to First Name textbox with: " + firstName);
		adminCustomersPage.enterToTextboxByID(driver, firstName, "SearchFirstName");

		log.info("Customers_05 - Step 03: Enter to Last Name textbox with: " + lastName);
		adminCustomersPage.enterToTextboxByID(driver, lastName, "SearchLastName");

		log.info("Customers_05 - Step 04: Enter to Month Of Birth textbox with: " + monthOfBirth);
		adminCustomersPage.selectItemInDefaultDropdownByID(driver, monthOfBirth, "SearchMonthOfBirth");

		log.info("Customers_05 - Step 05: Enter to Day Of Birth textbox with: " + dayOfBirth);
		adminCustomersPage.selectItemInDefaultDropdownByID(driver, dayOfBirth, "SearchDayOfBirth");

		log.info("Customers_05 - Step 06: Enter to Company textbox with " + company);
		adminCustomersPage.enterToTextboxByID(driver, company, "SearchCompany");

		log.info("Customers_05 - Step 07: Click to Search button");
		adminCustomersPage.clickToSearchCustomerButton(driver);

		log.info("Customers_05 - Step 08: Verify only one item is displayed");
		adminCustomersPage.scrollToCustomerInfoByJS(driver, "Guest", fullName, "Guests", company, "true");
		verifyTrue(adminCustomersPage.isCustomerInfoDisplayed(driver, "Guest", fullName, "Guests", company, "true"));
		verifyTrue(adminCustomersPage.isOneItemInTableDisplayed(driver));
	}

	@Test
	public void TC_06_Edit_Customer() {
		log.info("Customers_06 - Step 01: Click to Edit icon of Customer Info row");
		adminEditCustomerDetailsPage = adminCustomersPage.clickToEditIconOfCustomerInfoRow(driver, "Guest", fullName, "Guests", company, "true");

		log.info("Customers_06 - Step 02: Enter to Email textbox with: " + email2);
		adminEditCustomerDetailsPage.enterToTextboxByID(driver, email2, "Email");

		log.info("Customers_06 - Step 03: Enter to First Name textbox with: " + firstName2);
		adminEditCustomerDetailsPage.enterToTextboxByID(driver, firstName2, "FirstName");

		log.info("Customers_06 - Step 04: Enter to Last Name textbox with: " + lastName2);
		adminEditCustomerDetailsPage.enterToTextboxByID(driver, lastName2, "LastName");

		log.info("Customers_06 - Step 05: Enter to Date Of Birth textbox with: " + dateOfBirth2);
		adminEditCustomerDetailsPage.enterToDatePickerByID(driver, dateOfBirth2);

		log.info("Customers_06 - Step 06: Enter to Company textbox with: " + company2);
		adminEditCustomerDetailsPage.enterToTextboxByID(driver, company2, "Company");

		log.info("Customers_06 - Step 07: Enter to Admin Comment textarea with: Edit Customer (Guest)");
		adminEditCustomerDetailsPage.enterToTextareaByID(driver, "Edit Customer (Guest)", "AdminComment");

		log.info("Customers_06 - Step 08: Click to Save button");
		adminEditCustomerDetailsPage.clickToButtonByTitleNormalizeSpace(driver, "Save");
		adminCustomersPage = PageGeneratorManager.getAdminCustomersPage(driver);

		log.info("Customers_06 - Step 09: Verify alert success message is displayed: The new customer has been added successfully.");
		verifyTrue(adminCustomersPage.isAlertSuccessMessageDisplayed(driver, "The customer has been updated successfully."));

		log.info("Customers_06 - Step 10: Enter to Email textbox with: " + email2);
		adminCustomersPage.enterToTextboxByID(driver, email2, "SearchEmail");

		log.info("Customers_06 - Step 11: Enter to First Name textbox with: " + firstName2);
		adminCustomersPage.enterToTextboxByID(driver, firstName2, "SearchFirstName");

		log.info("Customers_06 - Step 12: Enter to Last Name textbox with: " + lastName2);
		adminCustomersPage.enterToTextboxByID(driver, lastName2, "SearchLastName");

		log.info("Customers_06 - Step 13: Enter to Month Of Birth textbox with: " + monthOfBirth2);
		adminCustomersPage.selectItemInDefaultDropdownByID(driver, monthOfBirth2, "SearchMonthOfBirth");

		log.info("Customers_06 - Step 14: Enter to Day Of Birth textbox with: " + dayOfBirth2);
		adminCustomersPage.selectItemInDefaultDropdownByID(driver, dayOfBirth2, "SearchDayOfBirth");

		log.info("Customers_06 - Step 15: Enter to Company textbox with: " + company2);
		adminCustomersPage.enterToTextboxByID(driver, company2, "SearchCompany");

		log.info("Customers_06 - Step 16: Select tag in Customer Roles taglist: Guests");
		adminCustomersPage.selectTagInCustomerRolesTaglistInCustomersPage(driver, "Guests");

		log.info("Customers_06 - Step 17: Click to Delete icon in Customer Roles taglist by name tag: Registered");
		adminCustomersPage.clickToDeleteIconInCustomerRolesTaglistByTitle(driver, "Registered");

		log.info("Customers_06 - Step 18: Click to Search button");
		adminCustomersPage.openSearchCustomerCard(driver);
		adminCustomersPage.clickToSearchCustomerButton(driver);

		log.info("Customers_06 - Step 19: Verify only one item is displayed");
		adminCustomersPage.scrollToCustomerInfoByJS(driver, "Guest", fullName2, "Guests", company2, "true");
		verifyTrue(adminCustomersPage.isCustomerInfoDisplayed(driver, "Guest", fullName2, "Guests", company2, "true"));
		verifyTrue(adminCustomersPage.isOneItemInTableDisplayed(driver));
	}

	@Test
	public void TC_07_Add_New_Address_In_Customer_Detail() {
		log.info("Customers_07 - Step 01: Click to Edit icon of Customer Info row");
		adminEditCustomerDetailsPage = adminCustomersPage.clickToEditIconOfCustomerInfoRow(driver, "Guest", fullName2, "Guests", company2, "true");

		log.info("Customers_07 - Step 02: Click to Add New Address button at Addresses Card");
		adminAddANewAddressPage = adminCustomersPage.clickToAddNewAddressButton(driver);

		log.info("Customers_07 - Step 03: Enter to First Name textbox with: " + firstName);
		adminAddANewAddressPage.enterToTextboxByID(driver, firstName, "Address_FirstName");

		log.info("Customers_07 - Step 04: Enter to Last Name textbox with: " + lastName);
		adminAddANewAddressPage.enterToTextboxByID(driver, lastName, "Address_LastName");

		log.info("Customers_07 - Step 05: Enter to Email textbox with: " + email);
		adminAddANewAddressPage.enterToTextboxByID(driver, email, "Address_Email");

		log.info("Customers_07 - Step 06: Enter to Company textbox with: " + company);
		adminAddANewAddressPage.enterToTextboxByID(driver, company, "Address_Company");

		log.info("Customers_07 - Step 07: Enter to Country dropdown with: " + country);
		adminAddANewAddressPage.selectItemInDefaultDropdownByID(driver, country, "Address_CountryId");

		log.info("Customers_07 - Step 08: Enter to State dropdown with: " + state);
		adminAddANewAddressPage.selectItemInDefaultDropdownByID(driver, state, "Address_StateProvinceId");

		log.info("Customers_07 - Step 09: Enter to County textbox with: " + county);
		adminAddANewAddressPage.enterToTextboxByID(driver, county, "Address_County");

		log.info("Customers_07 - Step 10: Enter to City textbox with: " + city);
		adminAddANewAddressPage.enterToTextboxByID(driver, city, "Address_City");

		log.info("Customers_07 - Step 11: Enter to Address 1 textbox with: " + address1);
		adminAddANewAddressPage.enterToTextboxByID(driver, address1, "Address_Address1");

		log.info("Customers_07 - Step 12: Enter to Address 2 textbox with: " + address2);
		adminAddANewAddressPage.enterToTextboxByID(driver, address2, "Address_Address2");

		log.info("Customers_07 - Step 13: Enter to Zip Code textbox with: " + zipCode);
		adminAddANewAddressPage.enterToTextboxByID(driver, zipCode, "Address_ZipPostalCode");

		log.info("Customers_07 - Step 14: Enter to Phone Number textbox with: " + phoneNum);
		adminAddANewAddressPage.enterToTextboxByID(driver, phoneNum, "Address_PhoneNumber");

		log.info("Customers_07 - Step 15: Enter to Fax Number textbox with: " + faxNum);
		adminAddANewAddressPage.enterToTextboxByID(driver, faxNum, "Address_FaxNumber");

		log.info("Customers_07 - Step 16: Click to Save button");
		adminAddANewAddressPage.clickToButtonByTitleNormalizeSpace(driver, "Save");
		adminEditAddressPage = PageGeneratorManager.getAdminEditAddressPage(driver);

		log.info("Customers_07 - Step 17: Verify alert success message is displayed: The new address has been added successfully.");
		verifyTrue(adminEditAddressPage.isAlertSuccessMessageDisplayed(driver, "The new address has been added successfully."));

		log.info("Customers_07 - Step 18: Click to Back To Customer Details link");
		adminEditAddressPage.clickToLinkByTitle(driver, "back to customer details");
		adminEditCustomerDetailsPage = PageGeneratorManager.getAdminEditCustomerDetailsPage(driver);

		log.info("Customers_07 - Step 19: Click to Addresses card header");
		adminEditCustomerDetailsPage.openAddressesCardHeader(driver);

		log.info("Customers_07 - Step 20: Verify Address Info is displayed");
		verifyTrue(adminEditCustomerDetailsPage.isAddressInfoDisplayed(driver, firstName, lastName, email, phoneNum, faxNum, address1, address2));

		log.info("Customers_07 - Step 21: Click to Edit icon of Address Info row");
		adminEditAddressPage = adminEditCustomerDetailsPage.clickToEditIconOfAddressInfoRow(driver, firstName, lastName, email, phoneNum, faxNum, address1, address2);

		log.info("Customers_07 - Step 22: Verify First Name value is displayed: " + firstName);
		verifyEquals(adminEditAddressPage.getTextValueInTextboxByID(driver, "Address_FirstName"), firstName);

		log.info("Customers_07 - Step 23: Verify Last Name value is displayed: " + lastName);
		verifyEquals(adminEditAddressPage.getTextValueInTextboxByID(driver, "Address_LastName"), lastName);

		log.info("Customers_07 - Step 24: Verify Email value is displayed: " + email);
		verifyEquals(adminEditAddressPage.getTextValueInTextboxByID(driver, "Address_Email"), email);

		log.info("Customers_07 - Step 25: Verify Company value is displayed: " + company);
		verifyEquals(adminEditAddressPage.getTextValueInTextboxByID(driver, "Address_Company"), company);

		log.info("Customers_07 - Step 26: Verify County value is displayed: " + county);
		verifyEquals(adminEditAddressPage.getTextValueInTextboxByID(driver, "Address_County"), county);

		log.info("Customers_07 - Step 27: Verify City value is displayed: " + city);
		verifyEquals(adminEditAddressPage.getTextValueInTextboxByID(driver, "Address_City"), city);

		log.info("Customers_07 - Step 28: Verify Address 1 value is displayed: " + address1);
		verifyEquals(adminEditAddressPage.getTextValueInTextboxByID(driver, "Address_Address1"), address1);

		log.info("Customers_07 - Step 29: Verify Address 2 value is displayed: " + address2);
		verifyEquals(adminEditAddressPage.getTextValueInTextboxByID(driver, "Address_Address2"), address2);

		log.info("Customers_07 - Step 30: Verify Zip Code value is displayed: " + zipCode);
		verifyEquals(adminEditAddressPage.getTextValueInTextboxByID(driver, "Address_ZipPostalCode"), zipCode);

		log.info("Customers_07 - Step 31: Verify Phone Number value is displayed: " + phoneNum);
		verifyEquals(adminEditAddressPage.getTextValueInTextboxByID(driver, "Address_PhoneNumber"), phoneNum);

		log.info("Customers_07 - Step 32: Verify Fax Number value is displayed: " + faxNum);
		verifyEquals(adminEditAddressPage.getTextValueInTextboxByID(driver, "Address_FaxNumber"), faxNum);

		log.info("Customers_07 - Step 33: Verify Country value is displayed: " + country);
		verifyEquals(adminEditAddressPage.getFirstSelectedTextInDefaultDropdownByID(driver, "Address_CountryId"), country);

		log.info("Customers_07 - Step 34: Verify State value is displayed: " + state);
		verifyEquals(adminEditAddressPage.getFirstSelectedTextInDefaultDropdownByID(driver, "Address_StateProvinceId"), state);
	}

	@Test
	public void TC_08_Edit_Address_In_Customer_Detail() {
		log.info("Customers_08 - Step 01: Click to Back To Customer Details link");
		adminEditAddressPage.clickToLinkByTitle(driver, "back to customer details");
		adminEditCustomerDetailsPage = PageGeneratorManager.getAdminEditCustomerDetailsPage(driver);

		log.info("Customers_08 - Step 02: Click to Back To Customer List link");
		adminEditCustomerDetailsPage.clickToLinkByTitle(driver, "back to customer list");
		adminCustomersPage = PageGeneratorManager.getAdminCustomersPage(driver);

		log.info("Customers_08 - Step 03: Enter to Email textbox with: " + email2);
		adminCustomersPage.enterToTextboxByID(driver, email2, "SearchEmail");

		log.info("Customers_08 - Step 04: Enter to First Name textbox with: " + firstName2);
		adminCustomersPage.enterToTextboxByID(driver, firstName2, "SearchFirstName");

		log.info("Customers_08 - Step 05: Enter to Last Name textbox with: " + lastName2);
		adminCustomersPage.enterToTextboxByID(driver, lastName2, "SearchLastName");

		log.info("Customers_08 - Step 06: Enter to Month Of Birth textbox with: " + monthOfBirth2);
		adminCustomersPage.selectItemInDefaultDropdownByID(driver, monthOfBirth2, "SearchMonthOfBirth");

		log.info("Customers_08 - Step 07: Enter to Day Of Birth textbox with: " + dayOfBirth2);
		adminCustomersPage.selectItemInDefaultDropdownByID(driver, dayOfBirth2, "SearchDayOfBirth");

		log.info("Customers_08 - Step 08: Enter to Company textbox with " + company2);
		adminCustomersPage.enterToTextboxByID(driver, company2, "SearchCompany");

		log.info("Customers_08 - Step 09: Select tag in Customer Roles taglist: Guests");
		adminCustomersPage.selectTagInCustomerRolesTaglistInCustomersPage(driver, "Guests");

		log.info("Customers_08 - Step 10: Click to Delete icon in Customer Roles taglist by name tag: Registered");
		adminCustomersPage.clickToDeleteIconInCustomerRolesTaglistByTitle(driver, "Registered");

		log.info("Customers_08 - Step 11: Click to Search button");
		adminCustomersPage.openSearchCustomerCard(driver);
		adminCustomersPage.clickToSearchCustomerButton(driver);

		log.info("Customers_08 - Step 12: Verify only one item is displayed");
		adminCustomersPage.scrollToCustomerInfoByJS(driver, "Guest", fullName2, "Guests", company2, "true");
		verifyTrue(adminCustomersPage.isCustomerInfoDisplayed(driver, "Guest", fullName2, "Guests", company2, "true"));
		verifyTrue(adminCustomersPage.isOneItemInTableDisplayed(driver));

		log.info("Customers_08 - Step 13: Click to Edit icon of Customer Info row");
		adminEditCustomerDetailsPage = adminCustomersPage.clickToEditIconOfCustomerInfoRow(driver, "Guest", fullName2, "Guests", company2, "true");

		log.info("Customers_08 - Step 14: Click to Addresses card header");
		adminEditCustomerDetailsPage.openAddressesCardHeader(driver);

		log.info("Customers_08 - Step 15: Click to Edit icon of Address Info row");
		adminEditAddressPage = adminEditCustomerDetailsPage.clickToEditIconOfAddressInfoRow(driver, firstName, lastName, email, phoneNum, faxNum, address1, address2);

		log.info("Customers_08 - Step 16: Enter to First Name textbox with: " + firstName2);
		adminEditAddressPage.enterToTextboxByID(driver, firstName2, "Address_FirstName");

		log.info("Customers_08 - Step 17: Enter to Last Name textbox with: " + lastName2);
		adminEditAddressPage.enterToTextboxByID(driver, lastName2, "Address_LastName");

		log.info("Customers_08 - Step 18: Enter to Email textbox with: " + email2);
		adminEditAddressPage.enterToTextboxByID(driver, email2, "Address_Email");

		log.info("Customers_08 - Step 19: Enter to Company textbox with: " + company2);
		adminEditAddressPage.enterToTextboxByID(driver, company2, "Address_Company");

		log.info("Customers_08 - Step 20: Enter to Country dropdown with: " + country2);
		adminEditAddressPage.selectItemInDefaultDropdownByID(driver, country2, "Address_CountryId");

		log.info("Customers_08 - Step 21: Enter to State dropdown with: " + state2);
		adminEditAddressPage.selectItemInDefaultDropdownByID(driver, state2, "Address_StateProvinceId");

		log.info("Customers_08 - Step 22: Enter to County textbox with empty data");
		adminEditAddressPage.enterToTextboxByID(driver, county2, "Address_County");

		log.info("Customers_08 - Step 23: Enter to City textbox with: " + city2);
		adminEditAddressPage.enterToTextboxByID(driver, city2, "Address_City");

		log.info("Customers_08 - Step 24: Enter to Address 1 textbox with: " + address1_2);
		adminEditAddressPage.enterToTextboxByID(driver, address1_2, "Address_Address1");

		log.info("Customers_08 - Step 25: Enter to Address 2 textbox with: " + address2_2);
		adminEditAddressPage.enterToTextboxByID(driver, address2_2, "Address_Address2");

		log.info("Customers_08 - Step 26: Enter to Zip Code textbox with: " + zipCode2);
		adminEditAddressPage.enterToTextboxByID(driver, zipCode2, "Address_ZipPostalCode");

		log.info("Customers_08 - Step 27: Enter to Phone Number textbox with: " + phoneNum2);
		adminEditAddressPage.enterToTextboxByID(driver, phoneNum2, "Address_PhoneNumber");

		log.info("Customers_08 - Step 28: Enter to Fax Number textbox with: " + faxNum2);
		adminEditAddressPage.enterToTextboxByID(driver, faxNum2, "Address_FaxNumber");

		log.info("Customers_08 - Step 29: Click to Save button");
		adminEditAddressPage.clickToButtonByTitleNormalizeSpace(driver, "Save");

		log.info("Customers_08 - Step 30: Verify alert success message is displayed: The address has been updated successfully.");
		verifyTrue(adminEditAddressPage.isAlertSuccessMessageDisplayed(driver, "The address has been updated successfully."));

		log.info("Customers_08 - Step 31: Verify First Name value is displayed: " + firstName2);
		verifyEquals(adminEditAddressPage.getTextValueInTextboxByID(driver, "Address_FirstName"), firstName2);

		log.info("Customers_08 - Step 32: Verify Last Name value is displayed: " + lastName2);
		verifyEquals(adminEditAddressPage.getTextValueInTextboxByID(driver, "Address_LastName"), lastName2);

		log.info("Customers_08 - Step 33: Verify Email value is displayed: " + email2);
		verifyEquals(adminEditAddressPage.getTextValueInTextboxByID(driver, "Address_Email"), email2);

		log.info("Customers_08 - Step 34: Verify Company value is displayed: " + company2);
		verifyEquals(adminEditAddressPage.getTextValueInTextboxByID(driver, "Address_Company"), company2);

		log.info("Customers_08 - Step 35: Verify County value is displayed: " + county2);
		verifyEquals(adminEditAddressPage.getTextValueInTextboxByID(driver, "Address_County"), county2);

		log.info("Customers_08 - Step 36: Verify City value is displayed: " + city2);
		verifyEquals(adminEditAddressPage.getTextValueInTextboxByID(driver, "Address_City"), city2);

		log.info("Customers_08 - Step 37: Verify Address 1 value is displayed: " + address1_2);
		verifyEquals(adminEditAddressPage.getTextValueInTextboxByID(driver, "Address_Address1"), address1_2);

		log.info("Customers_08 - Step 38: Verify Address 2 value is displayed: " + address2_2);
		verifyEquals(adminEditAddressPage.getTextValueInTextboxByID(driver, "Address_Address2"), address2_2);

		log.info("Customers_08 - Step 39: Verify Zip Code value is displayed: " + zipCode2);
		verifyEquals(adminEditAddressPage.getTextValueInTextboxByID(driver, "Address_ZipPostalCode"), zipCode2);

		log.info("Customers_08 - Step 40: Verify Phone Number value is displayed: " + phoneNum2);
		verifyEquals(adminEditAddressPage.getTextValueInTextboxByID(driver, "Address_PhoneNumber"), phoneNum2);

		log.info("Customers_08 - Step 41: Verify Fax Number value is displayed: " + faxNum2);
		verifyEquals(adminEditAddressPage.getTextValueInTextboxByID(driver, "Address_FaxNumber"), faxNum2);

		log.info("Customers_08 - Step 42: Verify Country value is displayed: " + country2);
		verifyEquals(adminEditAddressPage.getFirstSelectedTextInDefaultDropdownByID(driver, "Address_CountryId"), country2);

		log.info("Customers_08 - Step 43: Verify State value is displayed: " + state2);
		verifyEquals(adminEditAddressPage.getFirstSelectedTextInDefaultDropdownByID(driver, "Address_StateProvinceId"), state2);

		log.info("Customers_08 - Step 44: Click to Back To Customer Details link");
		adminEditAddressPage.clickToLinkByTitle(driver, "back to customer details");
		adminEditCustomerDetailsPage = PageGeneratorManager.getAdminEditCustomerDetailsPage(driver);

		log.info("Customers_08 - Step 45: Click to Addresses card header");
		adminEditCustomerDetailsPage.openAddressesCardHeader(driver);

		log.info("Customers_08 - Step 46: Verify Address Info is displayed");
		verifyTrue(adminEditCustomerDetailsPage.isAddressInfoDisplayed(driver, firstName2, lastName2, email2, phoneNum2, faxNum2, address1_2, address2_2));
	}

	@Test
	public void TC_09_Delete_Address_In_Customer_Detail() {
		log.info("Customers_09 - Step 01: Click to Delete icon of Address Info row");
		adminEditCustomerDetailsPage.clickToDeleteIconOfCustomerInfoRow(driver, firstName2, lastName2, email2, phoneNum2, faxNum2, address1_2, address2_2);

		log.info("Customers_09 - Step 02: Click accept to Confirm Alert");
		adminEditCustomerDetailsPage.clickAcceptToConfirmAlert(driver);

		log.info("Customers_09 - Step 03: Verify deleted success message is displayed");
		verifyTrue(adminEditCustomerDetailsPage.isDeletedMessageSuccessfullyDisplayed(driver));
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserDriver();
	}
}
