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
		waitForElementClickable(driver, AdminAddANewCustomerPageUI.GENDER_RADIO, gender);
		clickToElement(driver, AdminAddANewCustomerPageUI.GENDER_RADIO, gender);
	}

	public void clickToDeleteIconInCustomerRolesTaglistByNameTag(WebDriver driver, String nameTag) {
		waitForElementClickable(driver, AdminAddANewCustomerPageUI.DYNAMIC_DELETE_ICON_BY_NAME_TAG, nameTag);
		clickToElement(driver, AdminAddANewCustomerPageUI.DYNAMIC_DELETE_ICON_BY_NAME_TAG, nameTag);
	}

	public void selectTagInCustomerRolesTaglistInAddNewCusPage(WebDriver driver, String expectedItemText) {
		selectItemInDropdown(driver, AdminAddANewCustomerPageUI.CUSTOMER_ROLES_TAG_LIST_IN_ADD_NEW_CUSTOMER, AdminAddANewCustomerPageUI.NAME_TAG_ITEM, expectedItemText);
	}
}
