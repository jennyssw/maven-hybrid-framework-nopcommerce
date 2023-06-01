package pageObjects.user.header;

import org.openqa.selenium.WebDriver;

import pageObjects.user.NavigationPageObject;
import pageUIs.user.header.UserLoginPageUI;

public class UserLoginPageObject extends NavigationPageObject {
	WebDriver driver;

	public UserLoginPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public boolean isErrorMessageDisplayedInValidationSummaryErrors(WebDriver driver, String actualMessage) {
		waitForElementVisible(driver, UserLoginPageUI.SUMMARY_VALIDATION_ERROR_MESSAGE);
		actualMessage = getElementText(driver, UserLoginPageUI.SUMMARY_VALIDATION_ERROR_MESSAGE);
		return isElementDisplayedInDOM(driver, UserLoginPageUI.SUMMARY_VALIDATION_ERROR_MESSAGE);
	}
}
