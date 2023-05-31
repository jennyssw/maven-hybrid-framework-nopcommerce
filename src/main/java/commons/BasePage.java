package commons;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

	public static BasePage getBasePageInstance() {
		return new BasePage();
	}

	public Set<Cookie> getCookies(WebDriver driver) {
		return driver.manage().getCookies();
	}

	public void setCookies(WebDriver driver, Set<Cookie> cookies) {
		for (Cookie cookie : cookies) {
			driver.manage().addCookie(cookie);
		}
		sleepInSecond(3);
		refreshCurrentPage(driver);
	}

	public void openPageUrl(WebDriver driver, String pageUrl) {
		driver.get(pageUrl);
	}

	public String getPageUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public String getPageSourceCode(WebDriver driver) {
		return driver.getPageSource();
	}

	public void backToPage(WebDriver driver) {
		driver.navigate().back();
	}

	public void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}

	public void refreshCurrentPage(WebDriver driver) {
		driver.navigate().refresh();
	}

	public Alert waitForAlertPresence(WebDriver driver) {
		return new WebDriverWait(driver, longTimeout).until(ExpectedConditions.alertIsPresent());
	}

	public void acceptAlert(WebDriver driver) {
		driver.switchTo().alert().accept();
	}

	public void cancelAlert(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}

	public void sendkeyToAlert(WebDriver driver, String valueToSendkey) {
		driver.switchTo().alert().sendKeys(valueToSendkey);
	}

	public void switchToWindowByID(WebDriver driver, String expectedID) {
		Set<String> allTabIDs = driver.getWindowHandles();

		for (String id : allTabIDs) {
			if (!id.equals(expectedID)) {
				driver.switchTo().window(id);
				break;
			}
		}
	}

	public void switchToWindowByTitle(WebDriver driver, String expectedTitle) {
		Set<String> allTabIDs = driver.getWindowHandles();

		for (String id : allTabIDs) {
			driver.switchTo().window(id);
			String actualTitle = driver.getTitle();
			if (actualTitle.equals(expectedTitle)) {
				break;
			}
		}
	}

	public boolean closeAllWindowsWithoutParent(WebDriver driver, String parentID) {
		Set<String> allWindowIDs = driver.getWindowHandles();

		for (String runWindow : allWindowIDs) {
			if (!runWindow.equals(parentID)) {
				driver.switchTo().window(runWindow);
				driver.close();
			}
		}

		driver.switchTo().window(parentID);
		if (driver.getWindowHandles().size() == 1)
			return true;
		else
			return false;
	}

	public String castRestParameter(String locator, String... values) {
		locator = String.format(locator, (Object[]) values);
		return locator;
	}

	public By getByLocator(String locator) {
		By by = null;
		if (locator.startsWith("id=") || (locator.startsWith("ID=") || (locator.startsWith("Id=")))) {
			by = By.id(locator.substring(3));
		} else if (locator.startsWith("class=") || (locator.startsWith("CLASS=") || (locator.startsWith("Class=")))) {
			by = By.className(locator.substring(6));
		} else if (locator.startsWith("name=") || (locator.startsWith("NAME=") || (locator.startsWith("Name=")))) {
			by = By.name(locator.substring(5));
		} else if (locator.startsWith("css=") || (locator.startsWith("CSS=") || (locator.startsWith("Css=")))) {
			by = By.cssSelector(locator.substring(4));
		} else if (locator.startsWith("xpath=") || (locator.startsWith("XPATH=") || (locator.startsWith("Xpath=")))) {
			by = By.xpath(locator.substring(6));
		} else {
			throw new RuntimeException("Locator is not valid!");
		}
		return by;
	}

	public WebElement getWebElement(WebDriver driver, String locator) {
		return driver.findElement(getByLocator(locator));
	}

	public List<WebElement> getListElement(WebDriver driver, String locator) {
		return driver.findElements(getByLocator(locator));
	}

	public void clickToElement(WebDriver driver, String locator) {
		getWebElement(driver, locator).click();
	}

	public void clickToElement(WebDriver driver, String locator, String... values) {
		getWebElement(driver, castRestParameter(locator, values)).click();
	}

	public void sendkeyToElement(WebDriver driver, String locator, String valueToInput) {
		WebElement element = getWebElement(driver, locator);
		element.clear();
		element.sendKeys(valueToInput);
	}

	public void sendkeyToElement(WebDriver driver, String locator, String valueToInput, String... values) {
		WebElement element = getWebElement(driver, castRestParameter(locator, values));
		element.clear();
		element.sendKeys(valueToInput);
	}

	public void selectItemInDefaultDropdown(WebDriver driver, String locator, String itemText) {
		Select select = new Select(getWebElement(driver, locator));
		select.selectByVisibleText(itemText);
	}

	public void selectItemInDefaultDropdown(WebDriver driver, String locator, String itemText, String... values) {
		Select select = new Select(getWebElement(driver, castRestParameter(locator, values)));
		select.selectByVisibleText(itemText);
	}

	public String getFirstSelectedTextItem(WebDriver driver, String locator) {
		Select select = new Select(getWebElement(driver, locator));
		return select.getFirstSelectedOption().getText();
	}

	public String getFirstSelectedTextItem(WebDriver driver, String locator, String... values) {
		Select select = new Select(getWebElement(driver, castRestParameter(locator, values)));
		return select.getFirstSelectedOption().getText();
	}

	public boolean isDropdownMultiple(WebDriver driver, String locator) {
		Select select = new Select(getWebElement(driver, locator));
		return select.isMultiple();
	}

	public void selectItemInDropdown(WebDriver driver, String parentXpath, String childXpath, String expectedTextItem) {
		waitForElementClickable(driver, parentXpath);
		getWebElement(driver, parentXpath).click();
		sleepInSecond(2);

		List<WebElement> childItems = new WebDriverWait(driver, longTimeout).until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(childXpath)));

		for (WebElement tempElement : childItems) {
			if (tempElement.getText().trim().equals(expectedTextItem)) {
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", tempElement);
				sleepInSecond(1);
				tempElement.click();
				sleepInSecond(1);
				break;
			}
		}
	}

	public String getElementText(WebDriver driver, String locator) {
		return getWebElement(driver, locator).getText();
	}

	public String getElementText(WebDriver driver, String locator, String... values) {
		return getWebElement(driver, castRestParameter(locator, values)).getText();
	}

	public String getElementAttributeValue(WebDriver driver, String locator, String attributeName) {
		return getWebElement(driver, locator).getAttribute(attributeName);
	}

	public String getElementAttributeValue(WebDriver driver, String locator, String attributeName, String... values) {
		return getWebElement(driver, castRestParameter(locator, values)).getAttribute(attributeName);
	}

	public String getElementCssValue(WebDriver driver, String locator, String propertyName) {
		return getWebElement(driver, locator).getCssValue(propertyName);
	}

	public int getListElementSize(WebDriver driver, String locator) {
		return getListElement(driver, locator).size();
	}

	public int getListElementSize(WebDriver driver, String locator, String... values) {
		return getListElement(driver, castRestParameter(locator, values)).size();
	}

	public void checkToCheckboxOrRadio(WebDriver driver, String locator) {
		WebElement element = getWebElement(driver, locator);
		if (!element.isSelected()) {
			element.click();
		}
	}

	public void checkToCheckboxOrRadio(WebDriver driver, String locator, String... values) {
		WebElement element = getWebElement(driver, castRestParameter(locator, values));
		if (!element.isSelected()) {
			element.click();
		}
	}

	public void uncheckToCheckbox(WebDriver driver, String locator) {
		WebElement element = getWebElement(driver, locator);
		if (element.isSelected()) {
			element.click();
		}
	}

	public void uncheckToCheckbox(WebDriver driver, String locator, String... values) {
		WebElement element = getWebElement(driver, castRestParameter(locator, values));
		if (element.isSelected()) {
			element.click();
		}
	}

	public boolean isElementDisplayedInDOM(WebDriver driver, String locator) {
		return getWebElement(driver, locator).isDisplayed();
	}

	public boolean isElementDisplayedInDOM(WebDriver driver, String locator, String... values) {
		return getWebElement(driver, castRestParameter(locator, values)).isDisplayed();
	}

	// Element Invisible In DOM
	// Element Invisible Not In DOM
	public boolean isElementUndisplayed(WebDriver driver, String locator) {
		setImplicitTime(driver, shortTimeout);
		List<WebElement> elements = getListElement(driver, locator);
		setImplicitTime(driver, longTimeout);
		if (elements.size() == 0) {
			System.out.println("Element not in DOM");
			return true;
		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			System.out.println("Element in DOM but not visible / displayed");
			return true;
		} else {
			System.out.println("Element in DOM and visible");
			return false;
		}
	}

	public boolean isElementUndisplayed(WebDriver driver, String locator, String... values) {
		setImplicitTime(driver, shortTimeout);
		List<WebElement> elements = getListElement(driver, castRestParameter(locator, values));
		setImplicitTime(driver, longTimeout);
		if (elements.size() == 0) {
			System.out.println("Element not in DOM");
			return true;
		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			System.out.println("Element in DOM but not visible / displayed");
			return true;
		} else {
			System.out.println("Element in DOM and visible");
			return false;
		}
	}

	public void setImplicitTime(WebDriver driver, long timeInSecond) {
		driver.manage().timeouts().implicitlyWait(timeInSecond, TimeUnit.SECONDS);
	}

	public boolean isElementEnabled(WebDriver driver, String locator) {
		return getWebElement(driver, locator).isEnabled();
	}

	public boolean isElementSelected(WebDriver driver, String locator) {
		return getWebElement(driver, locator).isSelected();
	}

	public boolean isElementSelected(WebDriver driver, String locator, String... values) {
		return getWebElement(driver, castRestParameter(locator, values)).isSelected();
	}

	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void switchToIframe(WebDriver driver, String locator) {
		driver.switchTo().frame(getWebElement(driver, locator));
	}

	public void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	public void hoverMouseToElement(WebDriver driver, String locator) {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(driver, locator)).perform();
	}

	public void hoverMouseToElement(WebDriver driver, String locator, String... values) {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(driver, castRestParameter(locator, values))).perform();
	}

	public void rightClickToElement(WebDriver driver, String locator) {
		Actions action = new Actions(driver);
		action.contextClick(getWebElement(driver, locator)).perform();
	}

	public void doubleClickToElement(WebDriver driver, String locator) {
		Actions action = new Actions(driver);
		action.doubleClick(getWebElement(driver, locator)).perform();
	}

	public void dragAndDropElement(WebDriver driver, String sourceLocator, String targetLocator) {
		Actions action = new Actions(driver);
		action.dragAndDrop(getWebElement(driver, sourceLocator), getWebElement(driver, targetLocator)).perform();
	}

	public void pressKeyToElement(WebDriver driver, String locator, Keys key) {
		Actions action = new Actions(driver);
		action.sendKeys(getWebElement(driver, locator), key).perform();
	}

	public void pressKeyToElement(WebDriver driver, String locator, Keys key, String... values) {
		Actions action = new Actions(driver);
		action.sendKeys(getWebElement(driver, castRestParameter(locator, values)), key).perform();
	}

	public void hightLightElement(WebDriver driver, String locator) {
		WebElement element = getWebElement(driver, locator);
		String originalStyle = element.getAttribute("style");
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
		sleepInSecond(2);
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
	}

	public void clickToElementByJS(WebDriver driver, String locator) {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", getWebElement(driver, locator));
	}

	public void scrollToElementOnTop(WebDriver driver, String locator) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, locator));
	}

	public void scrollToElementOnDown(WebDriver driver, String locator) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", getWebElement(driver, locator));
	}

	public void sendkeyToElementByJS(WebDriver driver, String locator, String value) {
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + value + "')", getWebElement(driver, locator));
	}

	public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
		((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, locator));
	}

	public String getElementValidationMessage(WebDriver driver, String locator) {
		return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;", getWebElement(driver, locator));
	}

	public boolean isImageLoaded(WebDriver driver, String locator) {
		boolean status = (Boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
				getWebElement(driver, locator));
		if (status) {
			return true;
		}
		return false;
	}

	public void waitForElementVisible(WebDriver driver, String locator) {
		new WebDriverWait(driver, longTimeout).until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locator)));
	}

	public void waitForElementVisible(WebDriver driver, String locator, String... values) {
		new WebDriverWait(driver, longTimeout).until(ExpectedConditions.visibilityOfElementLocated(getByLocator(castRestParameter(locator, values))));
	}

	public void waitForElementAllVisible(WebDriver driver, String locator) {
		new WebDriverWait(driver, longTimeout).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locator)));
	}

	public void waitForElementInvisibleInDOM(WebDriver driver, String locator) {
		new WebDriverWait(driver, longTimeout).until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locator)));
	}

	public void waitForElementInvisibleInDOM(WebDriver driver, String locator, String... values) {
		new WebDriverWait(driver, longTimeout).until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(castRestParameter(locator, values))));
	}

	public void waitForElementInvisibleNotInDOM(WebDriver driver, String locator) {
		setImplicitTime(driver, shortTimeout);
		new WebDriverWait(driver, shortTimeout).until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locator)));
		setImplicitTime(driver, longTimeout);
	}

	public void waitForElementInvisibleNotInDOM(WebDriver driver, String locator, String... values) {
		setImplicitTime(driver, shortTimeout);
		new WebDriverWait(driver, shortTimeout).until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(castRestParameter(locator, values))));
		setImplicitTime(driver, longTimeout);
	}

	public void waitForElementClickable(WebDriver driver, String locator) {
		new WebDriverWait(driver, longTimeout).until(ExpectedConditions.elementToBeClickable(getByLocator(locator)));
	}

	public void waitForElementClickable(WebDriver driver, WebElement element) {
		new WebDriverWait(driver, longTimeout).until(ExpectedConditions.elementToBeClickable(element));
	}

	public void waitForElementClickable(WebDriver driver, String locator, String... values) {
		new WebDriverWait(driver, longTimeout).until(ExpectedConditions.elementToBeClickable(getByLocator(castRestParameter(locator, values))));
	}

	public void uploadMultipleFiles(WebDriver driver, String locator, String... fileNames) {
		String uploadFilePath = GlobalConstants.UPLOAD_PATH;
		String fullFileName = "";
		for (String file : fileNames) {
			fullFileName = fullFileName + uploadFilePath + file + "\n";
		}
		fullFileName = fullFileName.trim();
		getWebElement(driver, locator).sendKeys(fullFileName);
	}

	/** Common function for Web Component **/
	public String getCurrentCalendar() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		sdf.format(c.getTime());
		int month = c.get(Calendar.MONTH) + 1;
		int day = c.get(Calendar.DAY_OF_MONTH);
		int year = c.get(Calendar.YEAR);
		Date date = c.getTime();
		String monthString = Integer.toString(month);
		String dayString = Integer.toString(day);
		String yearString = Integer.toString(year);
		String dateString = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date.getTime());

		switch (monthString) {
		case "1":
			monthString = "Jan";
			break;
		case "2":
			monthString = "Feb";
			break;
		case "3":
			monthString = "Mar";
			break;
		case "4":
			monthString = "Apr";
			break;
		case "5":
			monthString = "May";
			break;
		case "6":
			monthString = "June";
			break;
		case "7":
			monthString = "July";
			break;
		case "8":
			monthString = "Aug";
			break;
		case "9":
			monthString = "Sep";
			break;
		case "10":
			monthString = "Oct";
			break;
		case "11":
			monthString = "Nov";
			break;
		case "12":
			monthString = "Dec";
			break;
		default:
			monthString = null;
			break;
		}

		String currentCalendar = dateString + ", " + monthString + " " + dayString + ", " + yearString;
		return currentCalendar;
	}

	public void enterToTextboxByID(WebDriver driver, String valueToInput, String textboxID) {
		waitForElementVisible(driver, BasePageUI.DYNAMIC_TEXTBOX_BY_ID, textboxID);
		sendkeyToElement(driver, BasePageUI.DYNAMIC_TEXTBOX_BY_ID, valueToInput, textboxID);
	}

	public String getTextValueInTextboxByID(WebDriver driver, String textboxID) {
		waitForElementVisible(driver, BasePageUI.DYNAMIC_TEXTBOX_BY_ID, textboxID);
		return getElementAttributeValue(driver, BasePageUI.DYNAMIC_TEXTBOX_BY_ID, "value", textboxID);
	}

	public void enterToTextareaByID(WebDriver driver, String valueToInput, String textareaID) {
		waitForElementVisible(driver, BasePageUI.DYNAMIC_TEXTAREA_BY_ID, textareaID);
		sendkeyToElement(driver, BasePageUI.DYNAMIC_TEXTAREA_BY_ID, valueToInput, textareaID);
	}

	public String getTextInTextareaByID(WebDriver driver, String textareaID) {
		waitForElementVisible(driver, BasePageUI.DYNAMIC_TEXTAREA_BY_ID, textareaID);
		return getElementText(driver, BasePageUI.DYNAMIC_TEXTAREA_BY_ID, textareaID);
	}

	public void selectItemInDefaultDropdownByID(WebDriver driver, String itemText, String dropdownID) {
		waitForElementClickable(driver, BasePageUI.DYNAMIC_DROPDOWN_BY_ID, dropdownID);
		selectItemInDefaultDropdown(driver, BasePageUI.DYNAMIC_DROPDOWN_BY_ID, itemText, dropdownID);
	}

	public void selectItemInDefaultDropdownByName(WebDriver driver, String itemText, String dropdownName) {
		waitForElementClickable(driver, BasePageUI.DYNAMIC_DROPDOWN_BY_NAME, dropdownName);
		selectItemInDefaultDropdown(driver, BasePageUI.DYNAMIC_DROPDOWN_BY_NAME, itemText, dropdownName);
	}

	public String getFirstSelectedTextInDefaultDropdownByID(WebDriver driver, String dropdownID) {
		waitForElementVisible(driver, BasePageUI.DYNAMIC_DROPDOWN_BY_ID, dropdownID);
		return getFirstSelectedTextItem(driver, BasePageUI.DYNAMIC_DROPDOWN_BY_ID, dropdownID);
	}

	public void clickToButtonByTitleText(WebDriver driver, String buttonTitle) {
		waitForElementClickable(driver, BasePageUI.DYNAMIC_BUTTON_BY_TITLE_TEXT, buttonTitle);
		clickToElement(driver, BasePageUI.DYNAMIC_BUTTON_BY_TITLE_TEXT, buttonTitle);
	}

	public void clickToButtonByTitleNormalizeSpace(WebDriver driver, String buttonTitle) {
		waitForElementClickable(driver, BasePageUI.DYNAMIC_BUTTON_BY_TITLE_NORMALIZE_SPACE, buttonTitle);
		clickToElement(driver, BasePageUI.DYNAMIC_BUTTON_BY_TITLE_NORMALIZE_SPACE, buttonTitle);
		sleepInSecond(3);
	}

	public void clickToButtonByID(WebDriver driver, String buttonID) {
		waitForElementClickable(driver, BasePageUI.DYNAMIC_BUTTON_BY_ID, buttonID);
		clickToElement(driver, BasePageUI.DYNAMIC_BUTTON_BY_ID, buttonID);
	}

	public void clickToLinkByTitle(WebDriver driver, String linkTitle) {
		waitForElementClickable(driver, BasePageUI.DYNAMIC_LINK_BY_TITLE, linkTitle);
		clickToElement(driver, BasePageUI.DYNAMIC_LINK_BY_TITLE, linkTitle);
	}

	public void clickToLinkByClassName(WebDriver driver, String linkClassName) {
		waitForElementClickable(driver, BasePageUI.DYNAMIC_LINK_BY_CLASS_NAME, linkClassName);
		clickToElement(driver, BasePageUI.DYNAMIC_LINK_BY_CLASS_NAME, linkClassName);
	}

	public void hoverToMenuAndClickToSubmenuByTitle(WebDriver driver, String menuTitle, String submenuTitle) {
		waitForElementVisible(driver, BasePageUI.DYNAMIC_WEB_MENU_BY_TITLE, menuTitle);
		hoverMouseToElement(driver, BasePageUI.DYNAMIC_WEB_MENU_BY_TITLE, menuTitle);

		waitForElementClickable(driver, BasePageUI.DYNAMIC_WEB_SUBMENU_BY_TITLE, menuTitle, submenuTitle);
		clickToElement(driver, BasePageUI.DYNAMIC_WEB_SUBMENU_BY_TITLE, menuTitle, submenuTitle);
	}

	public void clickToRadioByClassName(WebDriver driver, String radioClassName) {
		waitForElementClickable(driver, BasePageUI.DYNAMIC_RADIO_BY_CLASS_NAME, radioClassName);
		checkToCheckboxOrRadio(driver, BasePageUI.DYNAMIC_RADIO_BY_CLASS_NAME, radioClassName);
	}

	public void isRadioByClassNameDisplayed(WebDriver driver, String radioClassName) {
		waitForElementVisible(driver, BasePageUI.DYNAMIC_RADIO_BY_CLASS_NAME, radioClassName);
		isElementSelected(driver, BasePageUI.DYNAMIC_RADIO_BY_CLASS_NAME, radioClassName);
	}

	public void clickToCheckboxByID(WebDriver driver, String checkboxID) {
		waitForElementClickable(driver, BasePageUI.DYNAMIC_CHECKBOX_BY_ID, checkboxID);
		checkToCheckboxOrRadio(driver, BasePageUI.DYNAMIC_CHECKBOX_BY_ID, checkboxID);
	}

	public boolean isCheckboxSelectedByID(WebDriver driver, String checkboxID) {
		waitForElementVisible(driver, BasePageUI.DYNAMIC_CHECKBOX_BY_ID, checkboxID);
		return isElementSelected(driver, BasePageUI.DYNAMIC_CHECKBOX_BY_ID, checkboxID);
	}

	public void checkToCheckboxOrRadioByTitle(WebDriver driver, String checkboxOrRadioTitle) {
		waitForElementClickable(driver, BasePageUI.DYNAMIC_CHECKBOX_OR_RADIO_BY_TITLE, checkboxOrRadioTitle);
		checkToCheckboxOrRadio(driver, BasePageUI.DYNAMIC_CHECKBOX_OR_RADIO_BY_TITLE, checkboxOrRadioTitle);
	}

	public void uncheckToCheckboxTitle(WebDriver driver, String checkboxTitle) {
		waitForElementClickable(driver, BasePageUI.DYNAMIC_CHECKBOX_OR_RADIO_BY_TITLE, checkboxTitle);
		uncheckToCheckbox(driver, BasePageUI.DYNAMIC_CHECKBOX_OR_RADIO_BY_TITLE, checkboxTitle);
	}

	public void clickToCloseBarNotiSuccessMessageIcon(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.CLOSE_BAR_NOTI_SUCCESS_MESSAGE_ICON);
		clickToElement(driver, BasePageUI.CLOSE_BAR_NOTI_SUCCESS_MESSAGE_ICON);
		sleepInSecond(3);
	}

	public void clickToCloseAlertSuccessMessageIcon(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.CLOSE_ALERT_SUCCESS_MESSAGE_ICON);
		clickToElement(driver, BasePageUI.CLOSE_ALERT_SUCCESS_MESSAGE_ICON);
		sleepInSecond(3);
	}

	public String getFieldErrorMessageByID(WebDriver driver, String fieldID) {
		waitForElementVisible(driver, BasePageUI.DYNAMIC_FIELD_ERROR_MESSAGE_BY_ID, fieldID);
		return getElementText(driver, BasePageUI.DYNAMIC_FIELD_ERROR_MESSAGE_BY_ID, fieldID);
	}

	public String getPageErrorMessage(WebDriver driver) {
		waitForElementVisible(driver, BasePageUI.PAGE_ERROR_MESSAGE);
		return getElementText(driver, BasePageUI.PAGE_ERROR_MESSAGE);
	}

	public String getPageSuccessMessage(WebDriver driver) {
		waitForElementVisible(driver, BasePageUI.PAGE_SUCCESS_MESSAGE);
		return getElementText(driver, BasePageUI.PAGE_SUCCESS_MESSAGE);
	}

	public String getBarNotiSuccessMessage(WebDriver driver) {
		waitForElementVisible(driver, BasePageUI.BAR_NOTI_SUCCESS_MESSAGE);
		return getElementText(driver, BasePageUI.BAR_NOTI_SUCCESS_MESSAGE);
	}

	public boolean isAlertSuccessMessageDisplayed(WebDriver driver, String message) {
		waitForElementVisible(driver, BasePageUI.ALERT_SUCCESS_MESSAGE, message);
		return isElementDisplayedInDOM(driver, BasePageUI.ALERT_SUCCESS_MESSAGE, message);
	}

	public String getTextLogInLogOutInHeaderLink(WebDriver driver) {
		waitForElementVisible(driver, BasePageUI.LOG_LINK);
		return getElementText(driver, BasePageUI.LOG_LINK);
	}

	private long longTimeout = GlobalConstants.LONG_TIMEOUT;
	private long shortTimeout = GlobalConstants.SHORT_TIMEOUT;
}
