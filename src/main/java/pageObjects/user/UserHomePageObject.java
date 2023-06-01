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
		waitForElementClickable(driver, UserHomePageUI.DYNAMIC_HEADER_LINK_BY_TITILE, headerLinkByTitle);
		clickToElement(driver, UserHomePageUI.DYNAMIC_HEADER_LINK_BY_TITILE, headerLinkByTitle);
	}

	public void openLinkInFooterByTitle(WebDriver driver, String footerLinkByTitle) {
		waitForElementClickable(driver, UserHomePageUI.DYNAMIC_FOOTER_LINK_BY_TITILE, footerLinkByTitle);
		clickToElement(driver, UserHomePageUI.DYNAMIC_FOOTER_LINK_BY_TITILE, footerLinkByTitle);
	}

	public boolean isTopicTitleUserHomePageDisplayed(WebDriver driver) {
		waitForElementVisible(driver, UserHomePageUI.TOPIC_BLOCK_TITLE);
		return isElementDisplayedInDOM(driver, UserHomePageUI.TOPIC_BLOCK_TITLE);
	}
}
