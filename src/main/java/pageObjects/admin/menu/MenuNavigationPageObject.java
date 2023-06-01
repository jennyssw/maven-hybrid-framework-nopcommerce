package pageObjects.admin.menu;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageObjects.admin.PageGeneratorManager;
import pageUIs.admin.menu.MenuNavigationPageUI;

public class MenuNavigationPageObject extends BasePage {
	WebDriver driver;

	public MenuNavigationPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public AdminDashboardPageObject openAdminDashboardPage(WebDriver driver) {
		waitForElementClickable(driver, MenuNavigationPageUI.DASHBOARD_LINK_IN_MENU);
		clickToElement(driver, MenuNavigationPageUI.DASHBOARD_LINK_IN_MENU);
		return PageGeneratorManager.getAdminDashboardPage(driver);
	}

	public AdminProductsPageObject openAdminProductsPage(WebDriver driver, String menuTitle, String subMenuTitle) {
		waitForElementClickable(driver, MenuNavigationPageUI.DYNAMIC_MENU_LINK_WITH_TITLE, menuTitle);
		sleepInSecond(3);
		clickToElement(driver, MenuNavigationPageUI.DYNAMIC_MENU_LINK_WITH_TITLE, menuTitle);

		waitForElementClickable(driver, MenuNavigationPageUI.DYNAMIC_SUB_MENU_LINK_WITH_TITLE, subMenuTitle);
		clickToElement(driver, MenuNavigationPageUI.DYNAMIC_SUB_MENU_LINK_WITH_TITLE, subMenuTitle);
		sleepInSecond(3);

		return PageGeneratorManager.getAdminProductsPage(driver);
	}

	public AdminCustomersPageObject openAdminCustomersPage(WebDriver driver, String menuTitle, String subMenuTitle) {
		waitForElementClickable(driver, MenuNavigationPageUI.DYNAMIC_MENU_LINK_WITH_TITLE, menuTitle);
		sleepInSecond(3);
		clickToElement(driver, MenuNavigationPageUI.DYNAMIC_MENU_LINK_WITH_TITLE, menuTitle);

		waitForElementClickable(driver, MenuNavigationPageUI.DYNAMIC_SUB_MENU_LINK_WITH_TITLE, subMenuTitle);
		clickToElement(driver, MenuNavigationPageUI.DYNAMIC_SUB_MENU_LINK_WITH_TITLE, subMenuTitle);
		sleepInSecond(3);

		return PageGeneratorManager.getAdminCustomersPage(driver);
	}
}
