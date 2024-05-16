package pageObjects.admin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.admin.AdminAddANewCustomerPageUI;

public class AdminAddANewCustomerPageObject extends BasePage {
	WebDriver driver;

	public AdminAddANewCustomerPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToGenderRadio(WebDriver driver, String gender) {
		waitForElementClickable(driver, AdminAddANewCustomerPageUI.GENDER_RADIO_BY_TEXT, gender);
		clickToElement(driver, AdminAddANewCustomerPageUI.GENDER_RADIO_BY_TEXT, gender);
	}

	public void clickToDeleteIconInCustomerRolesTaglistByTitle(WebDriver driver, String nameTag) {
		waitForElementClickable(driver, AdminAddANewCustomerPageUI.DELETE_ICON_BY_TITLE, nameTag);
		clickToElement(driver, AdminAddANewCustomerPageUI.DELETE_ICON_BY_TITLE, nameTag);
	}

	public void selectTagInCustomerRolesTaglistInAddNewCusPage(WebDriver driver, String expectedItemText) {
		selectItemInDropdown(driver, AdminAddANewCustomerPageUI.CUSTOMER_ROLES_TAG_LIST_IN_ADD_NEW_CUSTOMER, AdminAddANewCustomerPageUI.OPTION_ITEM, expectedItemText);
	}

	public void enterToDatePickerByID(WebDriver driver, String dateOfBirthText) {
		waitForElementVisible(driver, AdminAddANewCustomerPageUI.DATE_PICKER);
		removeAttributeInDOM(driver, AdminAddANewCustomerPageUI.DATE_PICKER, "type");
		sendkeyToElement(driver, AdminAddANewCustomerPageUI.DATE_PICKER, dateOfBirthText);
	}
}
