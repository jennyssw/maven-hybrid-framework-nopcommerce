package pageObjects.admin.menu;

import org.openqa.selenium.WebDriver;

import pageObjects.admin.AdminAddANewAddressPageObject;
import pageObjects.admin.AdminEditCustomerDetailsPageObject;
import pageObjects.admin.PageGeneratorManager;
import pageUIs.admin.menu.AdminCustomersPageUI;

public class AdminCustomersPageObject extends MenuNavigationPageObject {
	WebDriver driver;

	public AdminCustomersPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public void selectTagInCustomerRolesTaglistInCustomersPage(WebDriver driver, String expectedItemText) {
		selectItemInDropdown(driver, AdminCustomersPageUI.CUSTOMER_ROLES_TAG_LIST_IN_CUSTOMERS, AdminCustomersPageUI.OPTION_ITEM, expectedItemText);
	}

	public boolean isCustomerInfoDisplayed(WebDriver driver, String emailRole, String fullName, String customerRole, String company, String statusIcon) {
		waitForElementVisible(driver, AdminCustomersPageUI.CUSTOMER_INFO_ROW_IN_TABLE_EDIT_ICON, emailRole, fullName, customerRole, company, statusIcon);
		return isElementDisplayedInDOM(driver, AdminCustomersPageUI.CUSTOMER_INFO_ROW_IN_TABLE_EDIT_ICON, emailRole, fullName, customerRole, company, statusIcon);
	}

	public boolean isOneItemInTableDisplayed(WebDriver driver) {
		sleepInSecond(3);
		waitForElementAllVisible(driver, AdminCustomersPageUI.ITEM_NUMBER_IN_TABLE);
		int itemNum = getListElementSize(driver, AdminCustomersPageUI.ITEM_NUMBER_IN_TABLE);
		return itemNum == 1;
	}

	public AdminEditCustomerDetailsPageObject clickToEditIconOfCustomerInfoRow(WebDriver driver, String emailRole, String fullName, String customerRole, String company, String statusIcon) {
		waitForElementClickable(driver, AdminCustomersPageUI.CUSTOMER_INFO_ROW_IN_TABLE_EDIT_ICON, emailRole, fullName, customerRole, company, statusIcon);
		clickToElement(driver, AdminCustomersPageUI.CUSTOMER_INFO_ROW_IN_TABLE_EDIT_ICON, emailRole, fullName, customerRole, company, statusIcon);
		sleepInSecond(3);
		return PageGeneratorManager.getAdminEditCustomerDetailsPage(driver);
	}

	public AdminAddANewAddressPageObject clickToAddNewAddressButton(WebDriver driver) {
		waitForElementClickable(driver, AdminCustomersPageUI.TOGGLE_ICON);

		if (getElementAttributeValue(driver, AdminCustomersPageUI.TOGGLE_ICON, "class").contains("minus")) {
			clickToElementByJS(driver, AdminCustomersPageUI.ADD_NEW_ADDRESS_BUTTON);
		} else if (getElementAttributeValue(driver, AdminCustomersPageUI.TOGGLE_ICON, "class").contains("plus")) {
			clickToElement(driver, AdminCustomersPageUI.TOGGLE_ICON);
			clickToElementByJS(driver, AdminCustomersPageUI.ADD_NEW_ADDRESS_BUTTON);
		}
		sleepInSecond(3);
		return PageGeneratorManager.getAdminAddANewAddressPage(driver);
	}

	public void clickToDeleteIconInCustomerRolesTaglistByTitle(WebDriver driver, String nameTag) {
		waitForElementClickable(driver, AdminCustomersPageUI.DELETE_ICON_BY_TITLE, nameTag);
		clickToElement(driver, AdminCustomersPageUI.DELETE_ICON_BY_TITLE, nameTag);
	}

	public void clickToSearchCustomerButton(WebDriver driver) {
		waitForElementClickable(driver, AdminCustomersPageUI.SEARCH_CUSTOMER_BUTTON);
		clickToElement(driver, AdminCustomersPageUI.SEARCH_CUSTOMER_BUTTON);
		sleepInSecond(3);
	}

	public void openSearchCustomerCard(WebDriver driver) {
		waitForElementClickable(driver, AdminCustomersPageUI.COLLAPSE_ICON);

		if (getElementAttributeValue(driver, AdminCustomersPageUI.COLLAPSE_ICON, "class").contains("up")) {
		} else if (getElementAttributeValue(driver, AdminCustomersPageUI.COLLAPSE_ICON, "class").contains("down")) {
			clickToElement(driver, AdminCustomersPageUI.COLLAPSE_ICON);
		}
	}

	public void scrollToCustomerInfoByJS(WebDriver driver, String emailRole, String fullName, String customerRole, String company, String statusIcon) {
		scrollToElementOnDownByJS(driver, AdminCustomersPageUI.CUSTOMER_INFO_ROW_IN_TABLE_EDIT_ICON, emailRole, fullName, customerRole, company, statusIcon);
	}
}
