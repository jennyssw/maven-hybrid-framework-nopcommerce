package pageObjects.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageObjects.user.header.UserLoginPageObject;
import pageObjects.user.header.UserRegisterPageObject;
import pageObjects.user.header.UserShoppingCartPageObject;
import pageObjects.user.header.UserWishlistPageObject;
import pageObjects.user.sidebar.UserCustomerInfoPageObject;
import pageUIs.user.NavigationPageUI;

public class NavigationPageObject extends BasePage {
	WebDriver driver;

	public NavigationPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public UserRegisterPageObject clickToRegisterLinkInHeader(WebDriver driver) {
		waitForElementClickable(driver, NavigationPageUI.REGISTER_LINK_IN_HEADER);
		clickToElement(driver, NavigationPageUI.REGISTER_LINK_IN_HEADER);
		return PageGeneratorManager.getUserRegisterPage(driver);
	}

	public UserLoginPageObject clickToLoginLinkInHeader(WebDriver driver) {
		waitForElementClickable(driver, NavigationPageUI.LOGIN_LINK_IN_HEADER);
		clickToElement(driver, NavigationPageUI.LOGIN_LINK_IN_HEADER);
		return PageGeneratorManager.getUserLoginPage(driver);
	}

	public UserShoppingCartPageObject clickToShoppingCartLinkInHeader(WebDriver driver) {
		waitForElementClickable(driver, NavigationPageUI.SHOPPING_CART_LINK_IN_HEADER);
		clickToElement(driver, NavigationPageUI.SHOPPING_CART_LINK_IN_HEADER);
		return PageGeneratorManager.getUserShoppingCartPage(driver);
	}

	public UserWishlistPageObject clickToWishlistLinkInHeader(WebDriver driver) {
		waitForElementClickable(driver, NavigationPageUI.WISHLIST_LINK_IN_HEADER);
		clickToElement(driver, NavigationPageUI.WISHLIST_LINK_IN_HEADER);
		return PageGeneratorManager.getUserWishlistPage(driver);
	}

	public UserCustomerInfoPageObject clickToMyAccountLinkInHeader(WebDriver driver) {
		waitForElementClickable(driver, NavigationPageUI.MY_ACCOUNT_LINK_IN_HEADER);
		clickToElement(driver, NavigationPageUI.MY_ACCOUNT_LINK_IN_HEADER);
		return PageGeneratorManager.getUserCustomerInfoPage(driver);
	}
}
