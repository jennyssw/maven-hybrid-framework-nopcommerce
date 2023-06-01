package pageObjects.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageObjects.user.sidebar.UserCustomerInfoPageObject;
import pageUIs.user.UserCheckoutPageUI;

public class UserCheckoutPageObject extends BasePage {
	WebDriver driver;

	public UserCheckoutPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isPaymentInformationDisplayed(WebDriver driver, String infoText) {
		waitForElementVisible(driver, UserCheckoutPageUI.PAYMENT_INFORMATION_TEXT, infoText);
		return isElementDisplayedInDOM(driver, UserCheckoutPageUI.PAYMENT_INFORMATION_TEXT, infoText);
	}

	public String getTextBillingAddress(WebDriver driver, String billingAddressInfoByClassName) {
		waitForElementVisible(driver, UserCheckoutPageUI.DYNAMIC_BILLING_INFO_TEXT_BY_CLASS_NAME, billingAddressInfoByClassName);
		return getElementText(driver, UserCheckoutPageUI.DYNAMIC_BILLING_INFO_TEXT_BY_CLASS_NAME, billingAddressInfoByClassName).trim();
	}

	public String getTextPaymentMethod(WebDriver driver, String paymentLabelByClassName, String paymentValueByClassName) {
		waitForElementVisible(driver, UserCheckoutPageUI.DYNAMIC_PAYMENT_METHOD_TEXT_BY_CLASS_NAME, paymentLabelByClassName);
		waitForElementVisible(driver, UserCheckoutPageUI.DYNAMIC_PAYMENT_METHOD_TEXT_BY_CLASS_NAME, paymentValueByClassName);
		String label = getElementText(driver, UserCheckoutPageUI.DYNAMIC_PAYMENT_METHOD_TEXT_BY_CLASS_NAME, paymentLabelByClassName).trim();
		String value = getElementText(driver, UserCheckoutPageUI.DYNAMIC_PAYMENT_METHOD_TEXT_BY_CLASS_NAME, paymentValueByClassName).trim();
		return label.concat(" ").concat(value);
	}

	public String getTextShippingAddress(WebDriver driver, String shippingAddressInfoByClassName) {
		waitForElementVisible(driver, UserCheckoutPageUI.DYNAMIC_SHIPPING_INFO_TEXT_BY_CLASS_NAME, shippingAddressInfoByClassName);
		return getElementText(driver, UserCheckoutPageUI.DYNAMIC_SHIPPING_INFO_TEXT_BY_CLASS_NAME, shippingAddressInfoByClassName).trim();
	}

	public String getTextShippingMethod(WebDriver driver, String shippingLabelByClassName, String shippingValueByClassName) {
		waitForElementVisible(driver, UserCheckoutPageUI.DYNAMIC_SHIPPING_METHOD_TEXT_BY_CLASS_NAME, shippingLabelByClassName);
		waitForElementVisible(driver, UserCheckoutPageUI.DYNAMIC_SHIPPING_METHOD_TEXT_BY_CLASS_NAME, shippingValueByClassName);
		String label = getElementText(driver, UserCheckoutPageUI.DYNAMIC_SHIPPING_METHOD_TEXT_BY_CLASS_NAME, shippingLabelByClassName).trim();
		String value = getElementText(driver, UserCheckoutPageUI.DYNAMIC_SHIPPING_METHOD_TEXT_BY_CLASS_NAME, shippingValueByClassName).trim();
		return label.concat(" ").concat(value);
	}

	public String getTextGiftWrapping(WebDriver driver) {
		waitForElementVisible(driver, UserCheckoutPageUI.GIFT_WRAPPING_OPTION_TEXT);
		return getElementText(driver, UserCheckoutPageUI.GIFT_WRAPPING_OPTION_TEXT).trim();
	}

	public boolean isShippingRowInTotalInfoDisplayed(WebDriver driver, String shippingMethod, String shippingValue) {
		waitForElementVisible(driver, UserCheckoutPageUI.DYNAMIC_SELECTED_SHIPPING_METHOD_VALUE_TEXT_BY_TITLE, shippingMethod, shippingValue);
		return isElementDisplayedInDOM(driver, UserCheckoutPageUI.DYNAMIC_SELECTED_SHIPPING_METHOD_VALUE_TEXT_BY_TITLE, shippingMethod, shippingValue);
	}

	public String getTextSubTotalRowInTotalInfo(WebDriver driver) {
		waitForElementVisible(driver, UserCheckoutPageUI.SUB_TOTAL_VALUE_TEXT);
		return getElementText(driver, UserCheckoutPageUI.SUB_TOTAL_VALUE_TEXT).trim();
	}

	public String getTextTaxRowInTotalInfo(WebDriver driver) {
		waitForElementVisible(driver, UserCheckoutPageUI.TAX_VALUE_TEXT);
		return getElementText(driver, UserCheckoutPageUI.TAX_VALUE_TEXT).trim();
	}

	public String getTextTotalRowInTotalInfo(WebDriver driver) {
		waitForElementVisible(driver, UserCheckoutPageUI.TOTAL_VALUE_TEXT);
		return getElementText(driver, UserCheckoutPageUI.TOTAL_VALUE_TEXT).trim();
	}

	public String getTextYouWillEarnRowInTotalInfo(WebDriver driver) {
		waitForElementVisible(driver, UserCheckoutPageUI.YOU_WILL_EARN_VALUE_TEXT);
		return getElementText(driver, UserCheckoutPageUI.YOU_WILL_EARN_VALUE_TEXT).trim();
	}

	public boolean isProductInfoRowDisplayed(WebDriver driver, String sku, String productName, String price, String quantity, String total) {
		waitForElementVisible(driver, UserCheckoutPageUI.PRODUCT_INFO_ROW_IN_TABLE, sku, productName, price, quantity, total);
		return isElementDisplayedInDOM(driver, UserCheckoutPageUI.PRODUCT_INFO_ROW_IN_TABLE, sku, productName, price, quantity, total);
	}

	public String getTextCheckoutTitle(WebDriver driver) {
		waitForElementVisible(driver, UserCheckoutPageUI.CHECKOUT_TITLE);
		return getElementText(driver, UserCheckoutPageUI.CHECKOUT_TITLE);
	}

	public String getTextSectionOrderTitle(WebDriver driver) {
		waitForElementVisible(driver, UserCheckoutPageUI.SECTION_ORDER_TITLE);
		return getElementText(driver, UserCheckoutPageUI.SECTION_ORDER_TITLE);
	}

	public UserCustomerInfoPageObject openMyAccountLinkInHeader(WebDriver driver) {
		waitForElementClickable(driver, UserCheckoutPageUI.MY_ACCOUNT_LINK_IN_HEADER);
		clickToElement(driver, UserCheckoutPageUI.MY_ACCOUNT_LINK_IN_HEADER);
		return PageGeneratorManager.getUserCustomerInfoPage(driver);
	}

	public String getTextOrderNumber(WebDriver driver) {
		waitForElementVisible(driver, UserCheckoutPageUI.ORDER_NUMBER_TEXT);
		return getElementText(driver, UserCheckoutPageUI.ORDER_NUMBER_TEXT);
	}

	public void clickToBillingButtonByTitle(WebDriver driver, String titleButton) {
		waitForElementClickable(driver, UserCheckoutPageUI.DYNAMIC_BILLING_BUTTON_BY_TITLE, titleButton);
		clickToElement(driver, UserCheckoutPageUI.DYNAMIC_BILLING_BUTTON_BY_TITLE, titleButton);
	}

	public void clickToShippingMethodButtonByTitle(WebDriver driver, String titleButton) {
		waitForElementClickable(driver, UserCheckoutPageUI.DYNAMIC_SHIPPING_METHOD_BUTTON_BY_TITLE, titleButton);
		clickToElement(driver, UserCheckoutPageUI.DYNAMIC_SHIPPING_METHOD_BUTTON_BY_TITLE, titleButton);
	}

	public void clickToPaymentMethodButtonByTitle(WebDriver driver, String titleButton) {
		waitForElementClickable(driver, UserCheckoutPageUI.DYNAMIC_PAYMENT_METHOD_BUTTON_BY_TITLE, titleButton);
		clickToElement(driver, UserCheckoutPageUI.DYNAMIC_PAYMENT_METHOD_BUTTON_BY_TITLE, titleButton);
	}

	public void clickToPaymentInfoButtonByTitle(WebDriver driver, String titleButton) {
		waitForElementClickable(driver, UserCheckoutPageUI.DYNAMIC_PAYMENT_INFO_BUTTON_BY_TITLE, titleButton);
		clickToElement(driver, UserCheckoutPageUI.DYNAMIC_PAYMENT_INFO_BUTTON_BY_TITLE, titleButton);
	}

	public void acceptAlertInCheckoutPage(WebDriver driver) {
		waitForAlertPresence(driver);
		acceptAlert(driver);
	}
}
