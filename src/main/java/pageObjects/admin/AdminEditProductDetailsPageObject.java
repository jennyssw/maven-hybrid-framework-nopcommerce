package pageObjects.admin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.admin.AdminEditProductDetailsPageUI;

public class AdminEditProductDetailsPageObject extends BasePage {
	WebDriver driver;

	public AdminEditProductDetailsPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isPageTitleDisplayed(WebDriver driver, String pageTitle) {
		waitForElementVisible(driver, AdminEditProductDetailsPageUI.PAGE_TITLE, pageTitle);
		return isElementDisplayedInDOM(driver, AdminEditProductDetailsPageUI.PAGE_TITLE, pageTitle);
	}

	public boolean isProductInfoDisplayed(WebDriver driver, String labelTextbox, String valueTextbox) {
		waitForElementVisible(driver, AdminEditProductDetailsPageUI.PRODUCT_INFO, labelTextbox, valueTextbox);
		return isElementDisplayedInDOM(driver, AdminEditProductDetailsPageUI.PRODUCT_INFO, labelTextbox, valueTextbox);
	}
}
