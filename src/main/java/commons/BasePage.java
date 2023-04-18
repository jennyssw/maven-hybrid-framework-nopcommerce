package commons;

import java.util.List;
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

	public boolean isDropdownMultiple(WebDriver driver, String locator) {
		Select select = new Select(getWebElement(driver, locator));
		return select.isMultiple();
	}

	public void selectItemInDropdown(WebDriver driver, String parentXpath, String childXpath, String expectedTextItem) {
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

	// Element Visible In DOM (1)
	// Element Invisible In DOM (2.1)
	public boolean isElementDisplayedInDOM(WebDriver driver, String locator) {
		return getWebElement(driver, locator).isDisplayed();
	}

	// Element Visible In DOM (1)
	// Element Invisible In DOM (2.1)
	public boolean isElementDisplayedInDOM(WebDriver driver, String locator, String... values) {
		return getWebElement(driver, castRestParameter(locator, values)).isDisplayed();
	}

	// Element Invisible In DOM (2.1)
	// Element Invisible Not In DOM (2.2)
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

	private long longTimeout = GlobalConstants.LONG_TIMEOUT;
	private long shortTimeout = GlobalConstants.SHORT_TIMEOUT;
}
