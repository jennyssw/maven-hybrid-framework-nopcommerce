package pageObjects.user.sidebar;

import org.openqa.selenium.WebDriver;

import pageUIs.user.sidebar.UserCustomerProductReviewPageUI;

public class UserCustomerProductReviewPageObject extends SidebarNavigationPageObject {
	WebDriver driver;

	public UserCustomerProductReviewPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public boolean isProductReviewTitleDisplayed(WebDriver driver, String reviewTitle) {
		waitForElementVisible(driver, UserCustomerProductReviewPageUI.REVIEW_TITLE_TEXTBOX, reviewTitle);
		return isElementDisplayedInDOM(driver, UserCustomerProductReviewPageUI.REVIEW_TITLE_TEXTBOX, reviewTitle);
	}

	public boolean isProductReviewTextDisplayed(WebDriver driver, String reviewText) {
		waitForElementVisible(driver, UserCustomerProductReviewPageUI.REVIEW_TEXT_TEXTAREA, reviewText);
		return isElementDisplayedInDOM(driver, UserCustomerProductReviewPageUI.REVIEW_TEXT_TEXTAREA, reviewText);
	}
}
