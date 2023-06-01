package pageObjects.user.header;

import org.openqa.selenium.WebDriver;

import pageObjects.user.NavigationPageObject;

public class UserRegisterPageObject extends NavigationPageObject {
	WebDriver driver;

	public UserRegisterPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
}
