package pageObjects.user;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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

	public boolean isProductNameSortAscending(WebDriver driver) {
		waitForElementAllVisible(driver, UserInventoryPageUI.PRODUCT_NAME);

		List<WebElement> productName = getListElement(driver, UserInventoryPageUI.PRODUCT_NAME);
		List<String> productNameTextUI = new ArrayList<String>();

		for (WebElement product : productName) {
			productNameTextUI.add(product.getText());
		}

		List<String> productNameTextSort = new ArrayList<String>(productNameTextUI);
		Collections.sort(productNameTextSort);

		return productNameTextSort.equals(productNameTextUI);
	}

	public boolean isProductNameSortDescending(WebDriver driver) {
		waitForElementAllVisible(driver, UserInventoryPageUI.PRODUCT_NAME);

		List<WebElement> productName = getListElement(driver, UserInventoryPageUI.PRODUCT_NAME);
		List<String> productNameTextUI = new ArrayList<String>();

		for (WebElement product : productName) {
			productNameTextUI.add(product.getText());
		}

		List<String> productNameTextSort = new ArrayList<String>(productNameTextUI);
		Collections.sort(productNameTextSort);
		Collections.reverse(productNameTextSort);

		return productNameTextSort.equals(productNameTextUI);
	}

	public boolean isProductPriceSortAscending(WebDriver driver) {
		waitForElementAllVisible(driver, UserInventoryPageUI.PRODUCT_PRICE);

		List<WebElement> productPrice = getListElement(driver, UserInventoryPageUI.PRODUCT_PRICE);
		List<Float> productPriceTextUI = new ArrayList<Float>();

		for (WebElement product : productPrice) {
			productPriceTextUI.add(Float.valueOf(product.getText().replace("$", "").replace(",", "")));
		}

		List<Float> productPriceTextSort = new ArrayList<Float>(productPriceTextUI);
		Collections.sort(productPriceTextSort);

		return productPriceTextSort.equals(productPriceTextUI);
	}

	public boolean isProductPriceSortDescending(WebDriver driver) {
		waitForElementAllVisible(driver, UserInventoryPageUI.PRODUCT_PRICE);

		List<WebElement> productPrice = getListElement(driver, UserInventoryPageUI.PRODUCT_PRICE);
		List<Float> productPriceTextUI = new ArrayList<Float>();

		for (WebElement product : productPrice) {
			productPriceTextUI.add(Float.valueOf(product.getText().replace("$", "").replace(",", "")));
		}

		List<Float> productPriceTextSort = new ArrayList<Float>(productPriceTextUI);
		Collections.sort(productPriceTextSort);
		Collections.reverse(productPriceTextSort);

		return productPriceTextSort.equals(productPriceTextUI);
	}

	public boolean isProductNumberDisplayed(WebDriver driver, String productNumber) {
		waitForElementAllVisible(driver, UserInventoryPageUI.PRODUCT_NAME);

		List<WebElement> productName = getListElement(driver, UserInventoryPageUI.PRODUCT_NAME);
		int actualProductNumber = productName.size();
		int expectedProductNumber = Integer.parseInt(productNumber);

		if (actualProductNumber == expectedProductNumber || actualProductNumber < expectedProductNumber) {
			return true;
		} else
			return false;
	}

	public boolean isPagerIconByTitleDisplayed(WebDriver driver, String titleIcon) {
		waitForElementVisible(driver, UserInventoryPageUI.DYNAMIC_PAGER_ICON_BY_TITLE, titleIcon);
		return isElementDisplayedInDOM(driver, UserInventoryPageUI.DYNAMIC_PAGER_ICON_BY_TITLE, titleIcon);
	}

	public boolean isPagingAreaNotDisplayed(WebDriver driver) {
		waitForElementInvisibleNotInDOM(driver, UserInventoryPageUI.PAGING_AREA);
		return isElementUndisplayed(driver, UserInventoryPageUI.PAGING_AREA);
	}

	public void clickToLinkByTitleInInventoryPage(WebDriver driver, String linkTitle) {
		waitForElementClickable(driver, UserInventoryPageUI.DYNAMIC_PRODUCT_LINK_BY_TITLE, linkTitle);
		clickToElement(driver, UserInventoryPageUI.DYNAMIC_PRODUCT_LINK_BY_TITLE, linkTitle);
	}
}
