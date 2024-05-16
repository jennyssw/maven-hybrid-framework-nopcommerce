package pageObjects.user.footer;

import org.openqa.selenium.WebDriver;

import pageObjects.user.NavigationPageObject;
import pageUIs.user.footer.UserRecentlyViewedProductsPageUI;

public class UserRecentlyViewedProductsPageObject extends NavigationPageObject {
	WebDriver driver;

	public UserRecentlyViewedProductsPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public boolean isProductAddedToRecentlyViewedProductsPageDisplayed(WebDriver driver, String productName) {
		waitForElementVisible(driver, UserRecentlyViewedProductsPageUI.PRODUCT_LINK_BY_TEXT, productName);
		return isElementDisplayedInDOM(driver, UserRecentlyViewedProductsPageUI.PRODUCT_LINK_BY_TEXT, productName);
	}
}
