package pageObjects.admin;

import org.openqa.selenium.WebDriver;

import pageObjects.admin.menu.AdminCustomersPageObject;
import pageObjects.admin.menu.AdminDashboardPageObject;
import pageObjects.admin.menu.AdminProductsPageObject;

public class PageGeneratorManager {
	public static AdminLoginPageObject getAdminLoginPage(WebDriver driver) {
		return new AdminLoginPageObject(driver);
	}

	public static AdminDashboardPageObject getAdminDashboardPage(WebDriver driver) {
		return new AdminDashboardPageObject(driver);
	}

	public static AdminProductsPageObject getAdminProductsPage(WebDriver driver) {
		return new AdminProductsPageObject(driver);
	}

	public static AdminEditProductDetailsPageObject getAdminEditProductDetailsPage(WebDriver driver) {
		return new AdminEditProductDetailsPageObject(driver);
	}

	public static AdminCustomersPageObject getAdminCustomersPage(WebDriver driver) {
		return new AdminCustomersPageObject(driver);
	}

	public static AdminAddANewCustomerPageObject getAdminAddANewCustomerPage(WebDriver driver) {
		return new AdminAddANewCustomerPageObject(driver);
	}

	public static AdminEditCustomerDetailsPageObject getAdminEditCustomerDetailsPage(WebDriver driver) {
		return new AdminEditCustomerDetailsPageObject(driver);
	}

	public static AdminAddANewAddressPageObject getAdminAddANewAddressPage(WebDriver driver) {
		return new AdminAddANewAddressPageObject(driver);
	}

	public static AdminEditAddressPageObject getAdminEditAddressPage(WebDriver driver) {
		return new AdminEditAddressPageObject(driver);
	}
}
