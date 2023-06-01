package pageObjects.user.sidebar;

import org.openqa.selenium.WebDriver;

import pageObjects.user.PageGeneratorManager;
import pageObjects.user.UserOrderDetailsPageObject;
import pageUIs.user.sidebar.UserCustomerOrdersPageUI;

public class UserCustomerOrdersPageObject extends SidebarNavigationPageObject {
	WebDriver driver;

	public UserCustomerOrdersPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public String getTextOrderNumberInOrdersPage(WebDriver driver) {
		waitForElementVisible(driver, UserCustomerOrdersPageUI.ORDER_NUMBER_TEXT);
		return getElementText(driver, UserCustomerOrdersPageUI.ORDER_NUMBER_TEXT);
	}

	public UserOrderDetailsPageObject clickToDetailsButton(WebDriver driver) {
		waitForElementClickable(driver, UserCustomerOrdersPageUI.DETAILS_BUTTON);
		clickToElement(driver, UserCustomerOrdersPageUI.DETAILS_BUTTON);
		return PageGeneratorManager.getUserOrderDetailsPage(driver);
	}
}
