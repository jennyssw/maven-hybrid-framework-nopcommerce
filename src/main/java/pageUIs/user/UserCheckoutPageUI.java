package pageUIs.user;

public class UserCheckoutPageUI {
	public static final String PAYMENT_INFORMATION_TEXT = "xpath=//*[contains(text(),\"%s\")]";
	public static final String DYNAMIC_BILLING_INFO_TEXT_BY_CLASS_NAME = "xpath=//strong[text()='Billing Address']/ancestor::div[@class='billing-info']//li[@class='%s']";
	public static final String DYNAMIC_PAYMENT_METHOD_TEXT_BY_CLASS_NAME = "xpath=//strong[text()='Payment']/ancestor::div[@class='payment-method-info']//span[@class='%s']";
	public static final String DYNAMIC_SHIPPING_INFO_TEXT_BY_CLASS_NAME = "xpath=//strong[text()='Shipping Address']/ancestor::div[@class='shipping-info']//li[@class='%s']";
	public static final String DYNAMIC_SHIPPING_METHOD_TEXT_BY_CLASS_NAME = "xpath=//strong[text()='Shipping']/ancestor::div[@class='shipping-method-info']//span[@class='%s']";
	public static final String DYNAMIC_SELECTED_SHIPPING_METHOD_VALUE_TEXT_BY_TITLE = "xpath=//label[text()='Shipping:']/following-sibling::span[text()='%s']/parent::td/following-sibling::td/span[text()='%s']";
	public static final String GIFT_WRAPPING_OPTION_TEXT = "css=div.cart-options>div.selected-checkout-attributes";
	public static final String PRODUCT_INFO_ROW_IN_TABLE = "xpath=//label[text()='SKU:']/following-sibling::span[text()='%s']/parent::td/following-sibling::td/a[text()='%s']/parent::td/following-sibling::td/label[text()='Price:']/following-sibling::span[text()='%s']/parent::td/following-sibling::td/label[text()='Qty.:']/following-sibling::span[text()='%s']/parent::td/following-sibling::td/label[text()='Total:']/following-sibling::span[text()='%s']";
	public static final String SUB_TOTAL_VALUE_TEXT = "xpath=//label[text()='Sub-Total:']/parent::td/following-sibling::td";
	public static final String TAX_VALUE_TEXT = "xpath=//label[text()='Tax:']/parent::td/following-sibling::td/span";
	public static final String TOTAL_VALUE_TEXT = "xpath=//label[text()='Total:']/parent::td/following-sibling::td/span/strong";
	public static final String YOU_WILL_EARN_VALUE_TEXT = "xpath=//label[text()='You will earn:']/parent::td/following-sibling::td/span";
	public static final String CHECKOUT_TITLE = "css=div.page.checkout-page.order-completed-page h1";
	public static final String SECTION_ORDER_TITLE = "css=div.section.order-completed>div.title>strong";
	public static final String ORDER_NUMBER_TEXT = "xpath=//div[@class='section order-completed']/div[@class='details']//strong[contains(text(),'Order number')]";
	public static final String MY_ACCOUNT_LINK_IN_HEADER = "xpath=//div[@class='header-links']//a[text()='My account']";
	public static final String DYNAMIC_BILLING_BUTTON_BY_TITLE = "xpath=//div[@id='billing-buttons-container']/button[text()='%s']";
	public static final String DYNAMIC_SHIPPING_METHOD_BUTTON_BY_TITLE = "xpath=//div[@id='shipping-method-buttons-container']/button[text()='%s']";
	public static final String DYNAMIC_PAYMENT_METHOD_BUTTON_BY_TITLE = "xpath=//div[@id='payment-method-buttons-container']/button[text()='%s']";
	public static final String DYNAMIC_PAYMENT_INFO_BUTTON_BY_TITLE = "xpath=//div[@id='payment-info-buttons-container']/button[text()='%s']";
}
