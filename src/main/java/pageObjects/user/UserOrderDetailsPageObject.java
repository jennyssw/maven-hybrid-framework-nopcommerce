package pageObjects.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageObjects.user.header.UserShoppingCartPageObject;
import pageUIs.user.UserOrderDetailsPageUI;

public class UserOrderDetailsPageObject extends BasePage {
	WebDriver driver;

	public UserOrderDetailsPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public String getTextOrderNumberInOrderDetailsPage(WebDriver driver) {
		waitForElementVisible(driver, UserOrderDetailsPageUI.ORDER_NUMBER_TEXT);
		return getElementText(driver, UserOrderDetailsPageUI.ORDER_NUMBER_TEXT);
	}

	public String getTextOrderDateInOrderDetailsPage(WebDriver driver) {
		waitForElementVisible(driver, UserOrderDetailsPageUI.ORDER_DATE_TEXT);
		return getElementText(driver, UserOrderDetailsPageUI.ORDER_DATE_TEXT);
	}

	public String getTextOrderStatusInOrderDetailsPage(WebDriver driver) {
		waitForElementVisible(driver, UserOrderDetailsPageUI.ORDER_STATUS_TEXT);
		return getElementText(driver, UserOrderDetailsPageUI.ORDER_STATUS_TEXT);
	}

	public String getTextOrderTotalInOrderDetailsPage(WebDriver driver) {
		waitForElementVisible(driver, UserOrderDetailsPageUI.ORDER_TOTAL_TEXT);
		return getElementText(driver, UserOrderDetailsPageUI.ORDER_TOTAL_TEXT);
	}

	public String getTextBillingAddress(WebDriver driver, String billingAddressInfoByClassName) {
		waitForElementVisible(driver, UserOrderDetailsPageUI.DYNAMIC_BILLING_INFO_TEXT_BY_CLASS_NAME, billingAddressInfoByClassName);
		return getElementText(driver, UserOrderDetailsPageUI.DYNAMIC_BILLING_INFO_TEXT_BY_CLASS_NAME, billingAddressInfoByClassName).trim();
	}

	public String getTextPaymentMethod(WebDriver driver, String paymentLabelByClassName, String paymentValueByClassName) {
		waitForElementVisible(driver, UserOrderDetailsPageUI.DYNAMIC_PAYMENT_METHOD_TEXT_BY_CLASS_NAME, paymentLabelByClassName);
		waitForElementVisible(driver, UserOrderDetailsPageUI.DYNAMIC_PAYMENT_METHOD_TEXT_BY_CLASS_NAME, paymentValueByClassName);
		String label = getElementText(driver, UserOrderDetailsPageUI.DYNAMIC_PAYMENT_METHOD_TEXT_BY_CLASS_NAME, paymentLabelByClassName).trim();
		String value = getElementText(driver, UserOrderDetailsPageUI.DYNAMIC_PAYMENT_METHOD_TEXT_BY_CLASS_NAME, paymentValueByClassName).trim();
		return label.concat(" ").concat(value);
	}

	public String getTextShippingAddress(WebDriver driver, String shippingAddressInfoByClassName) {
		waitForElementVisible(driver, UserOrderDetailsPageUI.DYNAMIC_SHIPPING_INFO_TEXT_BY_CLASS_NAME, shippingAddressInfoByClassName);
		return getElementText(driver, UserOrderDetailsPageUI.DYNAMIC_SHIPPING_INFO_TEXT_BY_CLASS_NAME, shippingAddressInfoByClassName).trim();
	}

	public String getTextShippingMethod(WebDriver driver, String shippingLabelByClassName, String shippingValueByClassName) {
		waitForElementVisible(driver, UserOrderDetailsPageUI.DYNAMIC_SHIPPING_METHOD_TEXT_BY_CLASS_NAME, shippingLabelByClassName);
		waitForElementVisible(driver, UserOrderDetailsPageUI.DYNAMIC_SHIPPING_METHOD_TEXT_BY_CLASS_NAME, shippingValueByClassName);
		String label = getElementText(driver, UserOrderDetailsPageUI.DYNAMIC_SHIPPING_METHOD_TEXT_BY_CLASS_NAME, shippingLabelByClassName).trim();
		String value = getElementText(driver, UserOrderDetailsPageUI.DYNAMIC_SHIPPING_METHOD_TEXT_BY_CLASS_NAME, shippingValueByClassName).trim();
		return label.concat(" ").concat(value);
	}

	public boolean isProductInfoRowDisplayed(WebDriver driver, String sku, String productName, String price, String quantity, String total) {
		waitForElementVisible(driver, UserOrderDetailsPageUI.PRODUCT_INFO_ROW_IN_TABLE, sku, productName, price, quantity, total);
		return isElementDisplayedInDOM(driver, UserOrderDetailsPageUI.PRODUCT_INFO_ROW_IN_TABLE, sku, productName, price, quantity, total);
	}

	public String getTextGiftWrapping(WebDriver driver) {
		waitForElementVisible(driver, UserOrderDetailsPageUI.GIFT_WRAPPING_OPTION_TEXT);
		return getElementText(driver, UserOrderDetailsPageUI.GIFT_WRAPPING_OPTION_TEXT).trim();
	}

	public String getTextSubTotalRowInTotalInfo(WebDriver driver) {
		waitForElementVisible(driver, UserOrderDetailsPageUI.SUB_TOTAL_VALUE_TEXT);
		return getElementText(driver, UserOrderDetailsPageUI.SUB_TOTAL_VALUE_TEXT).trim();
	}

	public String getTextShippingRowAtTotalInfo(WebDriver driver) {
		waitForElementVisible(driver, UserOrderDetailsPageUI.SHIPPING_VALUE_TEXT);
		return getElementText(driver, UserOrderDetailsPageUI.SHIPPING_VALUE_TEXT).trim();
	}

	public String getTextTaxRowAtTotalInfo(WebDriver driver) {
		waitForElementVisible(driver, UserOrderDetailsPageUI.TAX_VALUE_TEXT);
		return getElementText(driver, UserOrderDetailsPageUI.TAX_VALUE_TEXT).trim();
	}

	public String getTextTotalRowAtTotalInfo(WebDriver driver) {
		waitForElementVisible(driver, UserOrderDetailsPageUI.TOTAL_VALUE_TEXT);
		return getElementText(driver, UserOrderDetailsPageUI.TOTAL_VALUE_TEXT).trim();
	}

	public UserShoppingCartPageObject clickToReorderButton(WebDriver driver) {
		waitForElementClickable(driver, UserOrderDetailsPageUI.REORDER_BUTTON);
		clickToElement(driver, UserOrderDetailsPageUI.REORDER_BUTTON);
		return PageGeneratorManager.getUserShoppingCartPage(driver);
	}
}
