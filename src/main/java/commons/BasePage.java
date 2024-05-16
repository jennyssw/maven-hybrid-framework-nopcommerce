package commons;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

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

	protected Set<Cookie> getCookies(WebDriver driver) {
		return driver.manage().getCookies();
	}

	protected void setCookies(WebDriver driver, Set<Cookie> cookies) {
		for (Cookie cookie : cookies) {
			driver.manage().addCookie(cookie);
		}
		sleepInSecond(3);
		refreshCurrentPage(driver);
	}

	protected void openPageUrl(WebDriver driver, String pageUrl) {
		driver.get(pageUrl);
	}

	protected String getPageUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	protected String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	protected String getPageSourceCode(WebDriver driver) {
		return driver.getPageSource();
	}

	public void backToPage(WebDriver driver) {
		driver.navigate().back();
	}

	protected void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}

	protected void refreshCurrentPage(WebDriver driver) {
		driver.navigate().refresh();
	}

	protected Alert waitForAlertPresence(WebDriver driver) {
		return new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.alertIsPresent());
	}

	protected void acceptAlert(WebDriver driver) {
		driver.switchTo().alert().accept();
	}

	protected void cancelAlert(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}

	protected void sendkeyToAlert(WebDriver driver, String valueToSendkey) {
		driver.switchTo().alert().sendKeys(valueToSendkey);
	}

	protected void switchToWindowByID(WebDriver driver, String expectedID) {
		Set<String> allTabIDs = driver.getWindowHandles();

		for (String id : allTabIDs) {
			if (!id.equals(expectedID)) {
				driver.switchTo().window(id);
				break;
			}
		}
	}

	protected void switchToWindowByTitle(WebDriver driver, String expectedTitle) {
		Set<String> allTabIDs = driver.getWindowHandles();

		for (String id : allTabIDs) {
			driver.switchTo().window(id);
			String actualTitle = driver.getTitle();
			if (actualTitle.equals(expectedTitle)) {
				break;
			}
		}
	}

	protected boolean closeAllWindowsWithoutParent(WebDriver driver, String parentID) {
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

	private String castRestParameter(String locator, String... values) {
		locator = String.format(locator, (Object[]) values);
		return locator;
	}

	private By getByLocator(String locator) {
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

	private WebElement getWebElement(WebDriver driver, String locator) {
		return driver.findElement(getByLocator(locator));
	}

	protected List<WebElement> getListElement(WebDriver driver, String locator) {
		return driver.findElements(getByLocator(locator));
	}

	protected void clickToElement(WebDriver driver, String locator) {
		getWebElement(driver, locator).click();
	}

	protected void clickToElement(WebDriver driver, String locator, String... values) {
		getWebElement(driver, castRestParameter(locator, values)).click();
	}

	protected void sendkeyToElement(WebDriver driver, String locator, String valueToInput) {
		WebElement element = getWebElement(driver, locator);
		element.clear();
		element.sendKeys(valueToInput);
	}

	protected void sendkeyToElement(WebDriver driver, String locator, String valueToInput, String... values) {
		WebElement element = getWebElement(driver, castRestParameter(locator, values));
		element.clear();
		element.sendKeys(valueToInput);
	}

	protected void selectItemInDefaultDropdown(WebDriver driver, String locator, String itemText) {
		Select select = new Select(getWebElement(driver, locator));
		select.selectByVisibleText(itemText);
	}

	protected void selectItemInDefaultDropdown(WebDriver driver, String locator, String itemText, String... values) {
		Select select = new Select(getWebElement(driver, castRestParameter(locator, values)));
		select.selectByVisibleText(itemText);
	}

	protected String getFirstSelectedTextItem(WebDriver driver, String locator) {
		Select select = new Select(getWebElement(driver, locator));
		return select.getFirstSelectedOption().getText();
	}

	protected String getFirstSelectedTextItem(WebDriver driver, String locator, String... values) {
		Select select = new Select(getWebElement(driver, castRestParameter(locator, values)));
		return select.getFirstSelectedOption().getText();
	}

	protected boolean isDropdownMultiple(WebDriver driver, String locator) {
		Select select = new Select(getWebElement(driver, locator));
		return select.isMultiple();
	}

	protected void selectItemInDropdown(WebDriver driver, String parentXpath, String childXpath, String expectedTextItem) {
		waitForElementClickable(driver, parentXpath);
		sleepInSecond(2);
		getWebElement(driver, parentXpath).click();
		sleepInSecond(2);

		List<WebElement> childItems = new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(childXpath)));

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

	protected String getElementText(WebDriver driver, String locator) {
		return getWebElement(driver, locator).getText();
	}

	protected String getElementText(WebDriver driver, String locator, String... values) {
		return getWebElement(driver, castRestParameter(locator, values)).getText();
	}

	protected String getElementAttributeValue(WebDriver driver, String locator, String attributeName) {
		return getWebElement(driver, locator).getAttribute(attributeName);
	}

	protected String getElementAttributeValue(WebDriver driver, String locator, String attributeName, String... values) {
		return getWebElement(driver, castRestParameter(locator, values)).getAttribute(attributeName);
	}

	protected String getElementCssValue(WebDriver driver, String locator, String propertyName) {
		return getWebElement(driver, locator).getCssValue(propertyName);
	}

	protected int getListElementSize(WebDriver driver, String locator) {
		return getListElement(driver, locator).size();
	}

	protected int getListElementSize(WebDriver driver, String locator, String... values) {
		return getListElement(driver, castRestParameter(locator, values)).size();
	}

	protected void checkToCheckboxOrRadio(WebDriver driver, String locator) {
		WebElement element = getWebElement(driver, locator);
		if (!element.isSelected()) {
			element.click();
		}
	}

	protected void checkToCheckboxOrRadio(WebDriver driver, String locator, String... values) {
		WebElement element = getWebElement(driver, castRestParameter(locator, values));
		if (!element.isSelected()) {
			element.click();
		}
	}

	protected void uncheckToCheckbox(WebDriver driver, String locator) {
		WebElement element = getWebElement(driver, locator);
		if (element.isSelected()) {
			element.click();
		}
	}

	protected void uncheckToCheckbox(WebDriver driver, String locator, String... values) {
		WebElement element = getWebElement(driver, castRestParameter(locator, values));
		if (element.isSelected()) {
			element.click();
		}
	}

	protected boolean isElementDisplayedInDOM(WebDriver driver, String locator) {
		return getWebElement(driver, locator).isDisplayed();
	}

	protected boolean isElementDisplayedInDOM(WebDriver driver, String locator, String... values) {
		return getWebElement(driver, castRestParameter(locator, values)).isDisplayed();
	}

	protected boolean isElementUndisplayed(WebDriver driver, String locator) {
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

	protected boolean isElementUndisplayed(WebDriver driver, String locator, String... values) {
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

	public boolean isElementSortedAscendingByString(WebDriver driver, String locator) {
		List<WebElement> elements = getListElement(driver, locator);
		List<String> elementUI = new ArrayList<String>();

		for (WebElement element : elements) {
			elementUI.add(element.getText());
		}

		List<String> elementSort = new ArrayList<String>(elementUI);
		Collections.sort(elementSort);

		return elementSort.equals(elementUI);
	}

	public boolean isElementSortedDescendingByString(WebDriver driver, String locator) {
		List<WebElement> elements = getListElement(driver, locator);
		List<String> elementUI = new ArrayList<String>();

		for (WebElement element : elements) {
			elementUI.add(element.getText());
		}

		List<String> elementSort = new ArrayList<String>(elementUI);
		Collections.sort(elementSort);
		Collections.reverse(elementSort);

		return elementSort.equals(elementUI);
	}

	public boolean isElementSortedAscendingByFloat(WebDriver driver, String locator) {
		List<WebElement> elements = getListElement(driver, locator);
		List<Float> elementUI = new ArrayList<Float>();

		for (WebElement element : elements) {
			elementUI.add(Float.valueOf(element.getText().replace("$", "").replace(",", "")));
		}

		List<Float> elementSort = new ArrayList<Float>(elementUI);
		Collections.sort(elementSort);

		return elementSort.equals(elementUI);
	}

	public boolean isElementSortedDescendingByFloat(WebDriver driver, String locator) {
		List<WebElement> elements = getListElement(driver, locator);
		List<Float> elementUI = new ArrayList<Float>();

		for (WebElement element : elements) {
			elementUI.add(Float.valueOf(element.getText().replace("$", "").replace(",", "")));
		}

		List<Float> elementSort = new ArrayList<Float>(elementUI);
		Collections.sort(elementSort);
		Collections.reverse(elementSort);

		return elementSort.equals(elementUI);
	}

	public boolean isElementNumberDisplayed(WebDriver driver, String locator, String elementNumber) {
		List<WebElement> productName = getListElement(driver, locator);
		int actualElementNumber = productName.size();
		int expectedElementNumber = Integer.parseInt(elementNumber);

		if (actualElementNumber == expectedElementNumber || actualElementNumber < expectedElementNumber) {
			return true;
		} else
			return false;
	}

	protected boolean isElementEnabled(WebDriver driver, String locator) {
		return getWebElement(driver, locator).isEnabled();
	}

	protected boolean isElementSelected(WebDriver driver, String locator) {
		return getWebElement(driver, locator).isSelected();
	}

	protected boolean isElementSelected(WebDriver driver, String locator, String... values) {
		return getWebElement(driver, castRestParameter(locator, values)).isSelected();
	}

	protected void setImplicitTime(WebDriver driver, long timeInSecond) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeInSecond));
	}

	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	protected void switchToIframe(WebDriver driver, String locator) {
		driver.switchTo().frame(getWebElement(driver, locator));
	}

	protected void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	protected void hoverMouseToElement(WebDriver driver, String locator) {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(driver, locator)).perform();
	}

	protected void hoverMouseToElement(WebDriver driver, String locator, String... values) {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(driver, castRestParameter(locator, values))).perform();
	}

	protected void rightClickToElement(WebDriver driver, String locator) {
		Actions action = new Actions(driver);
		action.contextClick(getWebElement(driver, locator)).perform();
	}

	protected void doubleClickToElement(WebDriver driver, String locator) {
		Actions action = new Actions(driver);
		action.doubleClick(getWebElement(driver, locator)).perform();
	}

	protected void dragAndDropElement(WebDriver driver, String sourceLocator, String targetLocator) {
		Actions action = new Actions(driver);
		action.dragAndDrop(getWebElement(driver, sourceLocator), getWebElement(driver, targetLocator)).perform();
	}

	protected void pressKeyToElement(WebDriver driver, String locator, Keys key) {
		Actions action = new Actions(driver);
		action.sendKeys(getWebElement(driver, locator), key).perform();
	}

	protected void pressKeyToElement(WebDriver driver, String locator, Keys key, String... values) {
		Actions action = new Actions(driver);
		action.sendKeys(getWebElement(driver, castRestParameter(locator, values)), key).perform();
	}

	public void pressEnterOrReturnKey(WebDriver driver, String locator) {
		Actions action = new Actions(driver);
		if (GlobalConstants.OS_NAME.toLowerCase().contains("mac os")) {
			action.keyDown(getWebElement(driver, locator), Keys.RETURN).perform();
		} else
			action.keyDown(getWebElement(driver, locator), Keys.ENTER).perform();
	}

	public void hightlightElement(WebDriver driver, String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getWebElement(driver, locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
		sleepInSecond(2);
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
	}

	public void clickToElementByJS(WebDriver driver, String locator) {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", getWebElement(driver, locator));
		sleepInSecond(3);
	}

	public void scrollToElementOnTopByJS(WebDriver driver, String locator) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, locator));
	}

	public void scrollToElementOnDownByJS(WebDriver driver, String locator) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", getWebElement(driver, locator));
	}

	public void scrollToElementOnDownByJS(WebDriver driver, String locator, String... values) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", getWebElement(driver, castRestParameter(locator, values)));
	}

	public void scrollToBottomPageByJS(WebDriver driver) {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void setAttributeInDOM(WebDriver driver, String locator, String attributeName, String attributeValue) {
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('" + attributeName + "', '" + attributeValue + "');", getWebElement(driver, locator));
	}

	public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
		((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, locator));
	}

	public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove, String... values) {
		((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, castRestParameter(locator, values)));
	}

	public void sendkeyToElementByJS(WebDriver driver, String locator, String value) {
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + value + "')", getWebElement(driver, locator));
	}

	public void sendkeyToElementByJS(WebDriver driver, String locator, String valueToInput, String... values) {
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + valueToInput + "')", getWebElement(driver, castRestParameter(locator, values)));
	}

	public String getAttributeInDOMByJS(WebDriver driver, String locator, String attributeName) {
		return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].getAttribute('" + attributeName + "');", getWebElement(driver, locator));
	}

	public String getAttributeInDOMByJS(WebDriver driver, String locator, String attributeName, String... values) {
		return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].getAttribute('" + attributeName + "');", getWebElement(driver, castRestParameter(locator, values)));
	}

	public String getElementValidationMessage(WebDriver driver, String locator) {
		return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;", getWebElement(driver, locator));
	}

	public boolean isImageLoaded(WebDriver driver, String locator) {
		return (boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].complete " + "&& typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getWebElement(driver, locator));
	}

	protected void waitForElementVisible(WebDriver driver, String locator) {
		new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locator)));
	}

	protected void waitForElementVisible(WebDriver driver, String locator, String... values) {
		new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.visibilityOfElementLocated(getByLocator(castRestParameter(locator, values))));
	}

	protected void waitForElementAllVisible(WebDriver driver, String locator) {
		new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locator)));
	}

	protected void waitForElementInvisibleInDOM(WebDriver driver, String locator) {
		new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locator)));
	}

	protected void waitForElementInvisibleInDOM(WebDriver driver, String locator, String... values) {
		new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(castRestParameter(locator, values))));
	}

	protected void waitForElementInvisibleNotInDOM(WebDriver driver, String locator) {
		setImplicitTime(driver, shortTimeout);
		new WebDriverWait(driver, Duration.ofSeconds(shortTimeout)).until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locator)));
		setImplicitTime(driver, longTimeout);
	}

	protected void waitForElementInvisibleNotInDOM(WebDriver driver, String locator, String... values) {
		setImplicitTime(driver, shortTimeout);
		new WebDriverWait(driver, Duration.ofSeconds(shortTimeout)).until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(castRestParameter(locator, values))));
		setImplicitTime(driver, longTimeout);
	}

	protected void waitForElementClickable(WebDriver driver, String locator) {
		new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.elementToBeClickable(getByLocator(locator)));
	}

	protected void waitForElementClickable(WebDriver driver, WebElement element) {
		new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.elementToBeClickable(element));
	}

	protected void waitForElementClickable(WebDriver driver, String locator, String... values) {
		new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.elementToBeClickable(getByLocator(castRestParameter(locator, values))));
	}

	protected void uploadMultipleFiles(WebDriver driver, String locator, String... fileNames) {
		String uploadFilePath = GlobalConstants.UPLOAD_PATH;
		String fullFileName = "";
		for (String file : fileNames) {
			fullFileName = fullFileName + uploadFilePath + file + "\n";
		}
		fullFileName = fullFileName.trim();
		getWebElement(driver, locator).sendKeys(fullFileName);
	}

	public String getCurrentDate(WebDriver driver) {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, MMMM dd, yyyy");
		dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
		String currentDate = dateFormat.format(date);

		Map<String, String> monthMap = new HashMap<>();
		monthMap.put("January", "Jan");
		monthMap.put("February", "Feb");
		monthMap.put("March", "Mar");
		monthMap.put("April", "Apr");
		monthMap.put("May", "May");
		monthMap.put("June", "Jun");
		monthMap.put("July", "Jul");
		monthMap.put("August", "Aug");
		monthMap.put("September", "Sep");
		monthMap.put("October", "Oct");
		monthMap.put("November", "Nov");
		monthMap.put("December", "Dec");

		for (Map.Entry<String, String> entry : monthMap.entrySet()) {
			if (currentDate.contains(entry.getKey())) {
				currentDate = currentDate.replace(entry.getKey(), entry.getValue());
				break;
			}
		}
		System.out.println("Current Date and Time in Vietnam timezone: " + currentDate);
		return currentDate;
	}

	public String convertCurrentDateToVietnamTimeZone(WebDriver driver, String currentBrowserDate) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, MMMM dd, yyyy");
		dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
		Date date = dateFormat.parse(currentBrowserDate);
		String currentDate = dateFormat.format(date);

		Map<String, String> monthMap = new HashMap<>();
		monthMap.put("January", "Jan");
		monthMap.put("February", "Feb");
		monthMap.put("March", "Mar");
		monthMap.put("April", "Apr");
		monthMap.put("May", "May");
		monthMap.put("June", "Jun");
		monthMap.put("July", "Jul");
		monthMap.put("August", "Aug");
		monthMap.put("September", "Sep");
		monthMap.put("October", "Oct");
		monthMap.put("November", "Nov");
		monthMap.put("December", "Dec");

		for (Map.Entry<String, String> entry : monthMap.entrySet()) {
			if (currentDate.contains(entry.getKey())) {
				currentDate = currentDate.replace(entry.getKey(), entry.getValue());
				break;
			}
		}
		System.out.println("Current Date and Time in Vietnam timezone: " + currentDate);
		return currentDate;
	}

	public String selectExpectedText(WebDriver driver, String expectedTextOtherBrowsers, String expectedTextSafariBrowser) {
		String driverInstanceName = driver.toString().toLowerCase();
		if (driverInstanceName.contains("safari")) {
			return expectedTextSafariBrowser;
		} else
			return expectedTextOtherBrowsers;
	}

	/** Common functions for Web Component **/
	public void enterToTextboxByID(WebDriver driver, String valueToInput, String textboxID) {
		waitForElementVisible(driver, BasePageUI.DYNAMIC_TEXTBOX_BY_ID, textboxID);
		sendkeyToElement(driver, BasePageUI.DYNAMIC_TEXTBOX_BY_ID, valueToInput, textboxID);
	}

	public void enterToTextboxByClassName(WebDriver driver, String valueToInput, String textboxClass) {
		waitForElementVisible(driver, BasePageUI.DYNAMIC_TEXTBOX_BY_CLASS, textboxClass);
		sendkeyToElement(driver, BasePageUI.DYNAMIC_TEXTBOX_BY_CLASS, valueToInput, textboxClass);
	}

	public void enterToTextareaByID(WebDriver driver, String valueToInput, String textareaID) {
		waitForElementVisible(driver, BasePageUI.DYNAMIC_TEXTAREA_BY_ID, textareaID);
		sendkeyToElement(driver, BasePageUI.DYNAMIC_TEXTAREA_BY_ID, valueToInput, textareaID);
	}

	public String getTextValueInTextboxByID(WebDriver driver, String textboxID) {
		waitForElementVisible(driver, BasePageUI.DYNAMIC_TEXTBOX_BY_ID, textboxID);
		return getElementAttributeValue(driver, BasePageUI.DYNAMIC_TEXTBOX_BY_ID, "value", textboxID);
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
		sleepInSecond(2);
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
	}

	public String getFieldErrorMessageByID(WebDriver driver, String fieldID) {
		waitForElementVisible(driver, BasePageUI.DYNAMIC_FIELD_ERROR_MESSAGE_BY_ID, fieldID);
		return getElementText(driver, BasePageUI.DYNAMIC_FIELD_ERROR_MESSAGE_BY_ID, fieldID);
	}

	public String getFieldErrorMessageByClass(WebDriver driver, String fieldClass) {
		waitForElementVisible(driver, BasePageUI.DYNAMIC_FIELD_ERROR_MESSAGE_BY_CLASS, fieldClass);
		return getElementText(driver, BasePageUI.DYNAMIC_FIELD_ERROR_MESSAGE_BY_CLASS, fieldClass);
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
		waitForElementVisible(driver, BasePageUI.DYNAMIC_ALERT_SUCCESS_MESSAGE_BY_TEXT, message);
		return isElementDisplayedInDOM(driver, BasePageUI.DYNAMIC_ALERT_SUCCESS_MESSAGE_BY_TEXT, message);
	}

	public String getTextLogInLogOutInHeaderLink(WebDriver driver) {
		waitForElementVisible(driver, BasePageUI.LOG_LINK);
		return getElementText(driver, BasePageUI.LOG_LINK);
	}

	private long longTimeout = GlobalConstants.LONG_TIMEOUT;
	private long shortTimeout = GlobalConstants.SHORT_TIMEOUT;
}