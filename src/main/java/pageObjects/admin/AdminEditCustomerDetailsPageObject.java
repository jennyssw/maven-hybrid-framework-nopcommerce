package pageObjects.admin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.admin.AdminEditCustomerDetailsPageUI;

public class AdminEditCustomerDetailsPageObject extends BasePage {
	WebDriver driver;

	public AdminEditCustomerDetailsPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickAcceptToConfirmAlert(WebDriver driver) {
		waitForAlertPresence(driver);
		acceptAlert(driver);
	}

	public boolean isSelectedRadioDisplayed(WebDriver driver, String gender) {
		waitForElementVisible(driver, AdminEditCustomerDetailsPageUI.GENDER_RADIO_BY_TEXT, gender);
		return isElementDisplayedInDOM(driver, AdminEditCustomerDetailsPageUI.GENDER_RADIO_BY_TEXT, gender);
	}

	public boolean isSelectedCustomerRoleTagDisplayed(WebDriver driver, String selectedTag) {
		waitForElementVisible(driver, AdminEditCustomerDetailsPageUI.CUSTOMER_ROLE_TAG_BY_TITLE, selectedTag);
		return isElementDisplayedInDOM(driver, AdminEditCustomerDetailsPageUI.CUSTOMER_ROLE_TAG_BY_TITLE, selectedTag);
	}

	public void openAddressesCardHeader(WebDriver driver) {
		waitForElementClickable(driver, AdminEditCustomerDetailsPageUI.TOGGLE_ICON);

		if (getElementAttributeValue(driver, AdminEditCustomerDetailsPageUI.TOGGLE_ICON, "class").contains("minus")) {
			System.out.println("Minus icon is displayed. Addresses Card is opened");
		} else if (getElementAttributeValue(driver, AdminEditCustomerDetailsPageUI.TOGGLE_ICON, "class").contains("plus")) {
			System.out.println("Plus icon is displayed. Addresses Card is closed");
			clickToElement(driver, AdminEditCustomerDetailsPageUI.TOGGLE_ICON);
		}
	}

	public boolean isAddressInfoDisplayed(WebDriver driver, String firstName, String lastName, String email, String phoneNum, String faxNum, String address1, String address2) {
		waitForElementVisible(driver, AdminEditCustomerDetailsPageUI.ADDRESS_INFO_ROW_IN_TABLE, firstName, lastName, email, phoneNum, faxNum, address1, address2);
		return isElementDisplayedInDOM(driver, AdminEditCustomerDetailsPageUI.ADDRESS_INFO_ROW_IN_TABLE, firstName, lastName, email, phoneNum, faxNum, address1, address2);
	}

	public AdminEditAddressPageObject clickToEditIconOfAddressInfoRow(WebDriver driver, String firstName, String lastName, String email, String phoneNum, String faxNum, String address1, String address2) {
		waitForElementClickable(driver, AdminEditCustomerDetailsPageUI.EDIT_ICON_OF_ADDRESS_INFO_ROW, firstName, lastName, email, phoneNum, faxNum, address1, address2);
		clickToElement(driver, AdminEditCustomerDetailsPageUI.EDIT_ICON_OF_ADDRESS_INFO_ROW, firstName, lastName, email, phoneNum, faxNum, address1, address2);
		sleepInSecond(3);
		return PageGeneratorManager.getAdminEditAddressPage(driver);
	}

	public void clickToDeleteIconOfCustomerInfoRow(WebDriver driver, String firstName, String lastName, String email, String phoneNum, String faxNum, String address1, String address2) {
		waitForElementClickable(driver, AdminEditCustomerDetailsPageUI.DELETE_ICON_OF_ADDRESS_INFO_ROW, firstName, lastName, email, phoneNum, faxNum, address1, address2);
		clickToElement(driver, AdminEditCustomerDetailsPageUI.DELETE_ICON_OF_ADDRESS_INFO_ROW, firstName, lastName, email, phoneNum, faxNum, address1, address2);
		sleepInSecond(2);
	}

	public boolean isDeletedMessageSuccessfullyDisplayed(WebDriver driver) {
		waitForElementVisible(driver, AdminEditCustomerDetailsPageUI.DELETED_SUCCESS_MESSAGE);
		return isElementDisplayedInDOM(driver, AdminEditCustomerDetailsPageUI.DELETED_SUCCESS_MESSAGE);
	}

	public boolean isDateOfBirthEntered(WebDriver driver2, String dateOfBirth) {
		waitForElementVisible(driver, AdminEditCustomerDetailsPageUI.DATE_OF_BIRTH_CALENDAR);
		return isElementDisplayedInDOM(driver, AdminEditCustomerDetailsPageUI.DATE_OF_BIRTH_CALENDAR_BY_VALUE, dateOfBirth);
	}

	public void enterToDatePickerByID(WebDriver driver, String dateOfBirthText) {
		waitForElementVisible(driver, AdminEditCustomerDetailsPageUI.DATE_PICKER);
		removeAttributeInDOM(driver, AdminEditCustomerDetailsPageUI.DATE_PICKER, "type");
		sendkeyToElement(driver, AdminEditCustomerDetailsPageUI.DATE_PICKER, dateOfBirthText);
	}
}