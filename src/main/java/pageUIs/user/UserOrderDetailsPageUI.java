package pageUIs.user;

public class UserOrderDetailsPageUI {
	public static final String ORDER_NUMBER_TEXT = "css=div.order-number";
	public static final String ORDER_DATE_TEXT = "css=li.order-date";
	public static final String ORDER_STATUS_TEXT = "css=li.order-status";
	public static final String ORDER_TOTAL_TEXT = "css=li.order-total";
	public static final String DYNAMIC_BILLING_INFO_TEXT_BY_CLASS_NAME = "xpath=//strong[text()='Billing Address']/ancestor::div[@class='billing-info']//li[@class='%s']";
	public static final String DYNAMIC_PAYMENT_METHOD_TEXT_BY_CLASS_NAME = "xpath=//strong[text()='Payment']/ancestor::div[@class='payment-method-info']//span[@class='%s']";
	public static final String DYNAMIC_SHIPPING_INFO_TEXT_BY_CLASS_NAME = "xpath=//strong[text()='Shipping Address']/ancestor::div[@class='shipping-info']//li[@class='%s']";
	public static final String DYNAMIC_SHIPPING_METHOD_TEXT_BY_CLASS_NAME = "xpath=//strong[text()='Shipping']/ancestor::div[@class='shipping-method-info']//span[@class='%s']";
	public static final String PRODUCT_INFO_ROW_IN_TABLE = "xpath=//label[text()='SKU:']/following-sibling::span[text()='%s']/parent::td/following-sibling::td[@class='product']//a[text()='%s']/ancestor::td/following-sibling::td/label[text()='Price:']/following-sibling::span[text()='%s']/parent::td/following-sibling::td/label[text()='Quantity:']/following-sibling::span[text()='%s']/parent::td/following-sibling::td/label[text()='Total:']/following-sibling::span[text()='%s']";
	public static final String GIFT_WRAPPING_OPTION_TEXT = "css=div.section.options>div.selected-checkout-attributes";
	public static final String SUB_TOTAL_VALUE_TEXT = "xpath=//label[text()='Sub-Total:']/parent::td/following-sibling::td";
	public static final String SHIPPING_VALUE_TEXT = "xpath=//label[text()='Shipping:']/parent::td/following-sibling::td/span";
	public static final String TAX_VALUE_TEXT = "xpath=//label[text()='Tax:']/parent::td/following-sibling::td/span";
	public static final String TOTAL_VALUE_TEXT = "xpath=//label[text()='Order Total:']/parent::td/following-sibling::td/span/strong";
	public static final String REORDER_BUTTON = "xpath=//button[text()='Re-order']";
}
