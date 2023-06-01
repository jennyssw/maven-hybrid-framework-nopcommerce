package pageObjects.user.sidebar;

import org.openqa.selenium.WebDriver;

public class UserCustomerAddressesPageObject extends SidebarNavigationPageObject {
	WebDriver driver;

	public UserCustomerAddressesPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
}
