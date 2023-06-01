package pageObjects.admin.menu;

import org.openqa.selenium.WebDriver;

public class AdminDashboardPageObject extends MenuNavigationPageObject {
	WebDriver driver;

	public AdminDashboardPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
}
