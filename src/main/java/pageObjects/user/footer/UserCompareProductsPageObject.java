package pageObjects.user.footer;

import org.openqa.selenium.WebDriver;

import pageObjects.user.NavigationPageObject;
import pageUIs.user.footer.UserCompareProductsPageUI;

public class UserCompareProductsPageObject extends NavigationPageObject {
	WebDriver driver;

	public UserCompareProductsPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public boolean isProductInformationDisplayed(WebDriver driver, String productName1, String productName2, String unitPrice1, String unitPrice2) {
		waitForElementVisible(driver, UserCompareProductsPageUI.PRODUCT_INFO_ROW_IN_TABLE, productName1, productName2, unitPrice1, unitPrice2);
		return isElementDisplayedInDOM(driver, UserCompareProductsPageUI.PRODUCT_INFO_ROW_IN_TABLE, productName1, productName2, unitPrice1, unitPrice2);
	}

	public boolean isPageMessageDisplayed(WebDriver driver, String message) {
		waitForElementVisible(driver, UserCompareProductsPageUI.PAGE_MESSAGE_BY_TEXT, message);
		return isElementDisplayedInDOM(driver, UserCompareProductsPageUI.PAGE_MESSAGE_BY_TEXT, message);
	}

	public boolean isProductAddedToCompareProductsPageUndisplayed(WebDriver driver, String productName1, String productName2, String unitPrice1, String unitPrice2) {
		waitForElementInvisibleNotInDOM(driver, UserCompareProductsPageUI.PRODUCT_INFO_ROW_IN_TABLE, productName1, productName2, unitPrice1, unitPrice2);
		return isElementUndisplayed(driver, UserCompareProductsPageUI.PRODUCT_INFO_ROW_IN_TABLE, productName1, productName2, unitPrice1, unitPrice2);
	}
}
