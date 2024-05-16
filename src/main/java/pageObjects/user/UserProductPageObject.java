package pageObjects.user;

import org.openqa.selenium.WebDriver;

import pageObjects.user.header.UserWishlistPageObject;
import pageUIs.user.UserProductPageUI;

public class UserProductPageObject extends UserHomePageObject {
	WebDriver driver;

	public UserProductPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public void clickToAddToWishlistButton(WebDriver driver) {
		waitForElementVisible(driver, UserProductPageUI.ADD_TO_WISHLIST_BUTTON);
		waitForElementClickable(driver, UserProductPageUI.ADD_TO_WISHLIST_BUTTON);
		clickToElement(driver, UserProductPageUI.ADD_TO_WISHLIST_BUTTON);
	}

	public String getProductNameText(WebDriver driver) {
		waitForElementVisible(driver, UserProductPageUI.PRODUCT_NAME_TEXT);
		return getElementText(driver, UserProductPageUI.PRODUCT_NAME_TEXT);
	}

	public String getSKUText(WebDriver driver) {
		waitForElementVisible(driver, UserProductPageUI.SKU_TEXT);
		return getElementText(driver, UserProductPageUI.SKU_TEXT);
	}

	public String getProductPriceText(WebDriver driver) {
		waitForElementVisible(driver, UserProductPageUI.PRODUCT_PRICE_TEXT);
		return getElementText(driver, UserProductPageUI.PRODUCT_PRICE_TEXT);
	}

	public String getProductQuantityTextByAttribute(WebDriver driver, String attributeName) {
		waitForElementVisible(driver, UserProductPageUI.PRODUCT_QUANTITY_TEXT);
		return getElementAttributeValue(driver, UserProductPageUI.PRODUCT_QUANTITY_TEXT, attributeName);
	}

	public UserWishlistPageObject clickToLinkInHeaderByClassName(WebDriver driver, String headerLinkClassName) {
		waitForElementClickable(driver, UserProductPageUI.LINK_IN_HEADER_BY_CLASS_NAME, headerLinkClassName);
		clickToElement(driver, UserProductPageUI.LINK_IN_HEADER_BY_CLASS_NAME, headerLinkClassName);
		return PageGeneratorManager.getUserWishlistPage(driver);
	}

	public void clickToAddProductToCompareListByTitle(WebDriver driver, String productTitle) {
		waitForElementClickable(driver, UserProductPageUI.ADD_TO_COMPARE_LIST_BUTTON_BY_H1_TITLE, productTitle);
		clickToElement(driver, UserProductPageUI.ADD_TO_COMPARE_LIST_BUTTON_BY_H1_TITLE, productTitle);
	}

	public void clickToLinkInFooterByTitle(WebDriver driver, String footerLinkTitle) {
		waitForElementClickable(driver, UserProductPageUI.FOOTER_LINK_BY_TEXT, footerLinkTitle);
		clickToElement(driver, UserProductPageUI.FOOTER_LINK_BY_TEXT, footerLinkTitle);
	}

	public void hoverMouseToShoppingCartInHeaderLink(WebDriver driver) {
		waitForElementVisible(driver, UserProductPageUI.SHOPPING_CART_LINK);
		hoverMouseToElement(driver, UserProductPageUI.SHOPPING_CART_LINK);
	}

	public String getTextAddedProductInMiniShoppingCart(WebDriver driver, String attributeTitle) {
		waitForElementVisible(driver, UserProductPageUI.ATTRIBUTE_PRODUCT_BY_CLASS_NAME, attributeTitle);
		return getElementText(driver, UserProductPageUI.ATTRIBUTE_PRODUCT_BY_CLASS_NAME, attributeTitle).trim();
	}

	public boolean isProductNumberAddedInShoppingCartPageDisplayed(WebDriver driver, String productNumber) {
		waitForElementVisible(driver, UserProductPageUI.SHOPPING_CART_LINK_BY_PRODUCT_NUMBER, productNumber);
		return isElementDisplayedInDOM(driver, UserProductPageUI.SHOPPING_CART_LINK_BY_PRODUCT_NUMBER, productNumber);
	}

	public boolean isProductPriceDisplayedByPrice(WebDriver driver, String price) {
		waitForElementVisible(driver, UserProductPageUI.PRODUCT_PRICE_BY_TEXT, price);
		return isElementDisplayedInDOM(driver, UserProductPageUI.PRODUCT_PRICE_BY_TEXT, price);
	}

	public void scrollToShoppingCardInHeaderLink(WebDriver driver) {
		waitForElementVisible(driver, UserProductPageUI.SHOPPING_CART_LINK);
		scrollToElementOnTopByJS(driver, UserProductPageUI.SHOPPING_CART_LINK);
	}
}
