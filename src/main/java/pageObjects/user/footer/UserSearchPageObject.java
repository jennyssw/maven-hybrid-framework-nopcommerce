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
		waitForElementVisible(driver, UserSearchPageUI.ERROR_MESSAGE_BY_TEXT, messageTitle);
		return isElementDisplayedInDOM(driver, UserSearchPageUI.ERROR_MESSAGE_BY_TEXT, messageTitle);
	}

	public boolean isProductLinkDisplayedByTitle(WebDriver driver, String productLinkTitle) {
		waitForElementVisible(driver, UserSearchPageUI.PRODUCT_LINK_BY_TEXT, productLinkTitle);
		return isElementDisplayedInDOM(driver, UserSearchPageUI.PRODUCT_LINK_BY_TEXT, productLinkTitle);
	}

	public void enterToDropdownByTitle(WebDriver driver, String dropdownValue, String dropdownTitle) {
		waitForElementClickable(driver, UserSearchPageUI.DROPDOWN_BY_TEXT, dropdownTitle);
		selectItemInDefaultDropdown(driver, UserSearchPageUI.DROPDOWN_BY_TEXT, dropdownValue, dropdownTitle);
	}
}
