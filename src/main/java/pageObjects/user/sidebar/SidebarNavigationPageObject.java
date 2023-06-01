package pageObjects.user.sidebar;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageObjects.user.PageGeneratorManager;
import pageUIs.user.sidebar.SidebarNavigationPageUI;

public class SidebarNavigationPageObject extends BasePage {
	WebDriver driver;

	public SidebarNavigationPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public UserCustomerInfoPageObject clickToCustomerInfoLinkInSidebar(WebDriver driver) {
		waitForElementClickable(driver, SidebarNavigationPageUI.CUSTOMER_INFO_LINK);
		clickToElement(driver, SidebarNavigationPageUI.CUSTOMER_INFO_LINK);
		return PageGeneratorManager.getUserCustomerInfoPage(driver);
	}

	public UserCustomerAddressesPageObject clickToAddressesLinkInSidebar(WebDriver driver) {
		waitForElementClickable(driver, SidebarNavigationPageUI.ADDRESSES_LINK);
		clickToElement(driver, SidebarNavigationPageUI.ADDRESSES_LINK);
		return PageGeneratorManager.getUserCustomerAddressesPage(driver);
	}

	public UserCustomerChangePasswordPageObject clickToChangePasswordLinkInSidebar(WebDriver driver) {
		waitForElementClickable(driver, SidebarNavigationPageUI.CHANGE_PASSWORD_LINK);
		clickToElement(driver, SidebarNavigationPageUI.CHANGE_PASSWORD_LINK);
		return PageGeneratorManager.getUserCustomerChangePasswordPage(driver);
	}

	public UserCustomerProductReviewPageObject clickToProductReviewLinkInSidebar(WebDriver driver) {
		waitForElementClickable(driver, SidebarNavigationPageUI.PRODUCT_REVIEW_LINK);
		clickToElement(driver, SidebarNavigationPageUI.PRODUCT_REVIEW_LINK);
		return PageGeneratorManager.getUserCustomerProductReviewPage(driver);
	}

	public UserCustomerOrdersPageObject clickToOrdersLinkInSidebar(WebDriver driver) {
		waitForElementClickable(driver, SidebarNavigationPageUI.ORDERS_LINK);
		clickToElement(driver, SidebarNavigationPageUI.ORDERS_LINK);
		return PageGeneratorManager.getUserCustomerOrdersPage(driver);
	}
}
