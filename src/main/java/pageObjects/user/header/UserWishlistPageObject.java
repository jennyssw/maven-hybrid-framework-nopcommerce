package pageObjects.user.header;

import org.openqa.selenium.WebDriver;

import pageObjects.user.NavigationPageObject;
import pageUIs.user.header.UserWishlistPageUI;

public class UserWishlistPageObject extends NavigationPageObject {
	WebDriver driver;

	public UserWishlistPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public boolean isProductAddedToWishlistPageDisplayed(WebDriver driver, String skuNumber, String productName, String unitPrice, String quantity, String totalPrice) {
		waitForElementVisible(driver, UserWishlistPageUI.PRODUCT_INFO_ROW_IN_TABLE, skuNumber, productName, unitPrice, quantity, totalPrice);
		return isElementDisplayedInDOM(driver, UserWishlistPageUI.PRODUCT_INFO_ROW_IN_TABLE, skuNumber, productName, unitPrice, quantity, totalPrice);
	}

	public boolean isProductAddedToWishlistSharingLinkDisplayed(WebDriver driver, String skuNumber, String productName, String unitPrice, String quantity, String totalPrice) {
		waitForElementVisible(driver, UserWishlistPageUI.PRODUCT_INFO_ROW_IN_TABLE_FROM_SHARING_LINK, skuNumber, productName, unitPrice, quantity, totalPrice);
		return isElementDisplayedInDOM(driver, UserWishlistPageUI.PRODUCT_INFO_ROW_IN_TABLE_FROM_SHARING_LINK, skuNumber, productName, unitPrice, quantity, totalPrice);
	}

	public void clickToSharingLink(WebDriver driver) {
		waitForElementClickable(driver, UserWishlistPageUI.SHARING_LINK);
		clickToElement(driver, UserWishlistPageUI.SHARING_LINK);
	}

	public String getWishlistOfPersonTitle(WebDriver driver) {
		waitForElementVisible(driver, UserWishlistPageUI.WISHLIST_OF_PERSON_TITLE);
		return getElementText(driver, UserWishlistPageUI.WISHLIST_OF_PERSON_TITLE);
	}

	public boolean isPageMessageDisplayed(WebDriver driver, String message) {
		waitForElementVisible(driver, UserWishlistPageUI.PAGE_MESSAGE, message);
		return isElementDisplayedInDOM(driver, UserWishlistPageUI.PAGE_MESSAGE, message);
	}

	public void clickToRemoveProductIcon(WebDriver driver, String skuNumber, String productName, String unitPrice, String quantity, String totalPrice) {
		waitForElementClickable(driver, UserWishlistPageUI.REMOVE_PRODUCT_ICON, skuNumber, productName, unitPrice, quantity, totalPrice);
		clickToElement(driver, UserWishlistPageUI.REMOVE_PRODUCT_ICON, skuNumber, productName, unitPrice, quantity, totalPrice);
	}

	public boolean isProductAddedToWishlistPageUndisplayed(WebDriver driver, String skuNumber, String productName, String unitPrice, String quantity, String totalPrice) {
		waitForElementInvisibleNotInDOM(driver, UserWishlistPageUI.PRODUCT_INFO_ROW_IN_TABLE, skuNumber, productName, unitPrice, quantity, totalPrice);
		return isElementUndisplayed(driver, UserWishlistPageUI.PRODUCT_INFO_ROW_IN_TABLE, skuNumber, productName, unitPrice, quantity, totalPrice);
	}
}
