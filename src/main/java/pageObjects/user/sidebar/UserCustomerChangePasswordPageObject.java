package pageObjects.user.sidebar;

import org.openqa.selenium.WebDriver;

import pageObjects.user.PageGeneratorManager;
import pageObjects.user.UserHomePageObject;
import pageUIs.user.sidebar.UserCustomerChangePasswordPageUI;

public class UserCustomerChangePasswordPageObject extends SidebarNavigationPageObject {
	WebDriver driver;

	public UserCustomerChangePasswordPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public UserHomePageObject clickToLogOutLinkInHeader(WebDriver driver) {
		waitForElementClickable(driver, UserCustomerChangePasswordPageUI.LOGOUT_LINK);
		clickToElement(driver, UserCustomerChangePasswordPageUI.LOGOUT_LINK);
		return PageGeneratorManager.getUserHomePage(driver);
	}
}
