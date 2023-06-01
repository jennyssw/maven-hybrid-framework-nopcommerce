package pageObjects.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageObjects.user.sidebar.UserCustomerInfoPageObject;
import pageUIs.user.UserProductReviewPageUI;

public class UserProductReviewPageObject extends BasePage {
	WebDriver driver;

	public UserProductReviewPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void addReviewTitleTextbox(WebDriver driver, String reviewTitle) {
		waitForElementVisible(driver, UserProductReviewPageUI.REVIEW_TITLE_TEXTBOX);
		sendkeyToElement(driver, UserProductReviewPageUI.REVIEW_TITLE_TEXTBOX, reviewTitle);
	}

	public void addReviewTextTextarea(WebDriver driver, String reviewText) {
		waitForElementVisible(driver, UserProductReviewPageUI.REVIEW_TEXT_TEXTAREA);
		sendkeyToElement(driver, UserProductReviewPageUI.REVIEW_TEXT_TEXTAREA, reviewText);
	}

	public void clickToSubmitReviewButton(WebDriver driver) {
		waitForElementClickable(driver, UserProductReviewPageUI.SUBMIT_REVIEW_BUTTON);
		clickToElement(driver, UserProductReviewPageUI.SUBMIT_REVIEW_BUTTON);
	}

	public UserCustomerInfoPageObject clickToMyAccountLinkInHeader(WebDriver driver, String headerLinkByText) {
		waitForElementClickable(driver, UserProductReviewPageUI.HEADER_LINK_BY_TEXT, headerLinkByText);
		clickToElement(driver, UserProductReviewPageUI.HEADER_LINK_BY_TEXT, headerLinkByText);
		return PageGeneratorManager.getUserCustomerInfoPage(driver);
	}
}
