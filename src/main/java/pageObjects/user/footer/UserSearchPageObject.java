package pageObjects.user.footer;

import org.openqa.selenium.WebDriver;

import pageObjects.user.NavigationPageObject;
import pageUIs.user.footer.UserSearchPageUI;

public class UserSearchPageObject extends NavigationPageObject {
	WebDriver driver;

	public UserSearchPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public void enterToSearchKeywordTextbox(WebDriver driver, String searchValue) {
		waitForElementVisible(driver, UserSearchPageUI.SEARCH_KEYWORD_TEXTBOX);
		sendkeyToElement(driver, UserSearchPageUI.SEARCH_KEYWORD_TEXTBOX, searchValue);
	}

	public void clickToSearchButton(WebDriver driver) {
		waitForElementClickable(driver, UserSearchPageUI.SEARCH_BUTTON);
		clickToElement(driver, UserSearchPageUI.SEARCH_BUTTON);
	}

	public boolean isErrorMessageDisplayedByTitle(WebDriver driver, String messageTitle) {
		waitForElementVisible(driver, UserSearchPageUI.DYNAMIC_ERROR_MESSAGE_BY_TITLE, messageTitle);
		return isElementDisplayedInDOM(driver, UserSearchPageUI.DYNAMIC_ERROR_MESSAGE_BY_TITLE, messageTitle);
	}

	public boolean isProductLinkDisplayedByTitle(WebDriver driver, String productLinkTitle) {
		waitForElementVisible(driver, UserSearchPageUI.DYNAMIC_PRODUCT_LINK_BY_TITLE, productLinkTitle);
		return isElementDisplayedInDOM(driver, UserSearchPageUI.DYNAMIC_PRODUCT_LINK_BY_TITLE, productLinkTitle);
	}

	public void enterToDropdownByTitle(WebDriver driver, String dropdownValue, String dropdownTitle) {
		waitForElementClickable(driver, UserSearchPageUI.DYNAMIC_DROPDOWN_BY_TITLE, dropdownTitle);
		selectItemInDefaultDropdown(driver, UserSearchPageUI.DYNAMIC_DROPDOWN_BY_TITLE, dropdownValue, dropdownTitle);
	}
}
