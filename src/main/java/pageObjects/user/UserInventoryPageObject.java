package pageObjects.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.GlobalConstants;
import pageUIs.user.UserInventoryPageUI;

public class UserInventoryPageObject extends BasePage {
	WebDriver driver;

	public UserInventoryPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void selectSortDropdown(WebDriver driver, String valueItem) {
		waitForElementClickable(driver, UserInventoryPageUI.SORT_DROPDOWN);
		selectItemInDefaultDropdown(driver, UserInventoryPageUI.SORT_DROPDOWN, valueItem);
		sleepInSecond(GlobalConstants.SHORT_TIMEOUT);
	}

	public void selectDisplayDropdown(WebDriver driver, String valueItem) {
		waitForElementClickable(driver, UserInventoryPageUI.DISPLAY_DROPDOWN);
		selectItemInDefaultDropdown(driver, UserInventoryPageUI.DISPLAY_DROPDOWN, valueItem);
		sleepInSecond(GlobalConstants.SHORT_TIMEOUT);
	}

	public boolean isProductNameSortedAscending(WebDriver driver) {
		waitForElementAllVisible(driver, UserInventoryPageUI.PRODUCT_NAME);
		return isElementSortedAscendingByString(driver, UserInventoryPageUI.PRODUCT_NAME);
	}

	public boolean isProductNameSortedDescending(WebDriver driver) {
		waitForElementAllVisible(driver, UserInventoryPageUI.PRODUCT_NAME);
		return isElementSortedDescendingByString(driver, UserInventoryPageUI.PRODUCT_NAME);
	}

	public boolean isProductPriceSortedAscending(WebDriver driver) {
		waitForElementAllVisible(driver, UserInventoryPageUI.PRODUCT_PRICE);
		return isElementSortedAscendingByFloat(driver, UserInventoryPageUI.PRODUCT_PRICE);
	}

	public boolean isProductPriceSortedDescending(WebDriver driver) {
		waitForElementAllVisible(driver, UserInventoryPageUI.PRODUCT_PRICE);
		return isElementSortedDescendingByFloat(driver, UserInventoryPageUI.PRODUCT_PRICE);
	}

	public boolean isProductNumberDisplayed(WebDriver driver, String productNumber) {
		waitForElementAllVisible(driver, UserInventoryPageUI.PRODUCT_NAME);
		return isElementNumberDisplayed(driver, UserInventoryPageUI.PRODUCT_NAME, productNumber);
	}

	public boolean isPagerIconByTitleDisplayed(WebDriver driver, String titleIcon) {
		waitForElementVisible(driver, UserInventoryPageUI.PAGER_ICON_BY_TEXT, titleIcon);
		return isElementDisplayedInDOM(driver, UserInventoryPageUI.PAGER_ICON_BY_TEXT, titleIcon);
	}

	public boolean isPagingAreaNotDisplayed(WebDriver driver) {
		waitForElementInvisibleNotInDOM(driver, UserInventoryPageUI.PAGING_AREA);
		return isElementUndisplayed(driver, UserInventoryPageUI.PAGING_AREA);
	}

	public void clickToLinkByTitleInInventoryPage(WebDriver driver, String linkTitle) {
		waitForElementClickable(driver, UserInventoryPageUI.PRODUCT_LINK_BY_TEXT, linkTitle);
		clickToElement(driver, UserInventoryPageUI.PRODUCT_LINK_BY_TEXT, linkTitle);
	}
}
