package pageObjects.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.user.UserHomePageUI;

public class UserHomePageObject extends BasePage {
	WebDriver driver;

	public UserHomePageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void openLinkInHeaderByTitle(WebDriver driver, String headerLinkByTitle) {
		waitForElementClickable(driver, UserHomePageUI.HEADER_LINK_BY_TEXT, headerLinkByTitle);
		clickToElement(driver, UserHomePageUI.HEADER_LINK_BY_TEXT, headerLinkByTitle);
	}

	public void openLinkInFooterByTitle(WebDriver driver, String footerLinkByTitle) {
		waitForElementClickable(driver, UserHomePageUI.FOOTER_LINK_BY_TEXT, footerLinkByTitle);
		clickToElement(driver, UserHomePageUI.FOOTER_LINK_BY_TEXT, footerLinkByTitle);
	}

	public boolean isTopicTitleUserHomePageDisplayed(WebDriver driver) {
		waitForElementVisible(driver, UserHomePageUI.TOPIC_BLOCK_TITLE);
		return isElementDisplayedInDOM(driver, UserHomePageUI.TOPIC_BLOCK_TITLE);
	}
}
