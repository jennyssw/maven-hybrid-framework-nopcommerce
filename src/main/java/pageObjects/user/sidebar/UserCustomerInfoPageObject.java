package pageObjects.user.sidebar;

import org.openqa.selenium.WebDriver;

public class UserCustomerInfoPageObject extends SidebarNavigationPageObject {
	WebDriver driver;

	public UserCustomerInfoPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
}
