package pageObjects.admin.menu;

import org.openqa.selenium.WebDriver;

import pageUIs.admin.menu.AdminProductsPageUI;

public class AdminProductsPageObject extends MenuNavigationPageObject {
	WebDriver driver;

	public AdminProductsPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public boolean isProductInfoDisplayed(WebDriver driver, String rowPosition, String productName, String sku, String price, String stockQuantity) {
		waitForElementVisible(driver, AdminProductsPageUI.PRODUCT_INFO_ROW_IN_TABLE, rowPosition, productName, sku, price, stockQuantity);
		return isElementDisplayedInDOM(driver, AdminProductsPageUI.PRODUCT_INFO_ROW_IN_TABLE, rowPosition, productName, sku, price, stockQuantity);
	}

	public void uncheckSearchSubCategoriesCheckbox(WebDriver driver) {
		waitForElementClickable(driver, AdminProductsPageUI.SEARCH_SUB_CATEGORIES_CHECKBOX);
		uncheckToCheckbox(driver, AdminProductsPageUI.SEARCH_SUB_CATEGORIES_CHECKBOX);
	}

	public boolean isNoItemInTableDisplayed(WebDriver driver) {
		waitForElementVisible(driver, AdminProductsPageUI.NO_ITEM_MESSAGE_IN_TABLE);
		return isElementDisplayedInDOM(driver, AdminProductsPageUI.NO_ITEM_MESSAGE_IN_TABLE);
	}

	public void checkSearchSubCategoriesCheckbox(WebDriver driver) {
		waitForElementClickable(driver, AdminProductsPageUI.SEARCH_SUB_CATEGORIES_CHECKBOX);
		checkToCheckboxOrRadio(driver, AdminProductsPageUI.SEARCH_SUB_CATEGORIES_CHECKBOX);
	}

	public boolean isOneItemInTableDisplayed(WebDriver driver) {
		sleepInSecond(3);
		waitForElementAllVisible(driver, AdminProductsPageUI.ITEM_NUMBER_IN_TABLE);
		int itemNum = getListElementSize(driver, AdminProductsPageUI.ITEM_NUMBER_IN_TABLE);
		return itemNum == 1;
	}

	public void openSearchProductCard(WebDriver driver) {
		waitForElementClickable(driver, AdminProductsPageUI.COLLAPSE_ICON);

		if (getElementAttributeValue(driver, AdminProductsPageUI.COLLAPSE_ICON, "class").contains("up")) {
		} else if (getElementAttributeValue(driver, AdminProductsPageUI.COLLAPSE_ICON, "class").contains("down")) {
			clickToElement(driver, AdminProductsPageUI.COLLAPSE_ICON);
		}
	}

	public void clickToSearchProductButton(WebDriver driver) {
		waitForElementClickable(driver, AdminProductsPageUI.SEARCH_PRODUCT_BUTTON);
		clickToElement(driver, AdminProductsPageUI.SEARCH_PRODUCT_BUTTON);
		sleepInSecond(3);
	}
}
