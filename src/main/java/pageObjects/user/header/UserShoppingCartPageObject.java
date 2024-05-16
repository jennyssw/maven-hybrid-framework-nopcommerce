package pageObjects.user.header;

import org.openqa.selenium.WebDriver;

import pageObjects.user.NavigationPageObject;
import pageUIs.user.header.UserShoppingCartPageUI;

public class UserShoppingCartPageObject extends NavigationPageObject {
	WebDriver driver;

	public UserShoppingCartPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public boolean isProductAddedToShoppingCartPageDisplayed(WebDriver driver, String skuNumber, String productName, String unitPrice, String quantity, String totalPrice) {
		waitForElementVisible(driver, UserShoppingCartPageUI.PRODUCT_INFO_ROW_IN_TABLE, skuNumber, productName, unitPrice, quantity, totalPrice);
		return isElementDisplayedInDOM(driver, UserShoppingCartPageUI.PRODUCT_INFO_ROW_IN_TABLE, skuNumber, productName, unitPrice, quantity, totalPrice);
	}

	public String getTextProductDetailToShoppingCartPageByProductName(WebDriver driver, String productName) {
		waitForElementVisible(driver, UserShoppingCartPageUI.PRODUCT_DETAIL_BY_PRODUCT_NAME, productName);
		return getElementText(driver, UserShoppingCartPageUI.PRODUCT_DETAIL_BY_PRODUCT_NAME, productName).trim();
	}

	public void clickToRemoveProductIconInShoppingCartPage(WebDriver driver, String skuNumber, String productName, String unitPrice, String quantity, String totalPrice) {
		waitForElementClickable(driver, UserShoppingCartPageUI.REMOVE_ICON_IN_TABLE, skuNumber, productName, unitPrice, quantity, totalPrice);
		clickToElement(driver, UserShoppingCartPageUI.REMOVE_ICON_IN_TABLE, skuNumber, productName, unitPrice, quantity, totalPrice);
	}

	public boolean isShoppingCartMessageDisplayed(WebDriver driver, String message) {
		waitForElementVisible(driver, UserShoppingCartPageUI.SHOPPING_CART_MESSAGE_BY_TEXT, message);
		return isElementDisplayedInDOM(driver, UserShoppingCartPageUI.SHOPPING_CART_MESSAGE_BY_TEXT, message);
	}

	public boolean isProductAddedToShoppingCartPageUndisplayed(WebDriver driver, String skuNumber, String productName, String unitPrice, String quantity, String totalPrice) {
		waitForElementInvisibleNotInDOM(driver, UserShoppingCartPageUI.PRODUCT_INFO_ROW_IN_TABLE, skuNumber, productName, unitPrice, quantity, totalPrice);
		return isElementUndisplayed(driver, UserShoppingCartPageUI.PRODUCT_INFO_ROW_IN_TABLE, skuNumber, productName, unitPrice, quantity, totalPrice);
	}

	public void enterToQuantityTextbox(WebDriver driver, String attributeRemove, String productNumber) {
		waitForElementVisible(driver, UserShoppingCartPageUI.QUANTITY_TEXTBOX);
		removeAttributeInDOM(driver, UserShoppingCartPageUI.QUANTITY_TEXTBOX, attributeRemove);
		sendkeyToElement(driver, UserShoppingCartPageUI.QUANTITY_TEXTBOX, productNumber);
	}

	public void pressEnterToQuantityTextbox(WebDriver driver) {
		pressEnterOrReturnKey(driver, UserShoppingCartPageUI.QUANTITY_TEXTBOX);
	}
}
