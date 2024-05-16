package pageUIs.user;

public class UserProductPageUI {
	public static final String ADD_TO_WISHLIST_BUTTON = "css=div.add-to-wishlist>button";
	public static final String PRODUCT_NAME_TEXT = "css=div.product-name>h1";
	public static final String SKU_TEXT = "css=div.sku>span.value";
	public static final String PRODUCT_PRICE_TEXT = "css=div.product-price>span";
	public static final String PRODUCT_QUANTITY_TEXT = "css=div.add-to-cart-panel>input";
	public static final String SHOPPING_CART_LINK = "xpath=//div[@class='header-links-wrapper']//span[text()='Shopping cart']";
	public static final String SHOPPING_CART_LINK_BY_PRODUCT_NUMBER = "xpath=//div[@class='header-links-wrapper']//span[text()='Shopping cart']/following-sibling::span[text()='(%s)']";
	public static final String LINK_IN_HEADER_BY_CLASS_NAME = "class=%s";
	public static final String ADD_TO_COMPARE_LIST_BUTTON_BY_H1_TITLE = "xpath=//h1[text()='%s']/parent::div/following-sibling::div[@class='overview-buttons']//button[text()='Add to compare list']";
	public static final String FOOTER_LINK_BY_TEXT = "xpath=//a[text()='%s']";
	public static final String ATTRIBUTE_PRODUCT_BY_CLASS_NAME = "css=div.mini-shopping-cart div.%s";
	public static final String PRODUCT_PRICE_BY_TEXT = "xpath=//div[@class='product-price']//span[text()='%s']";
}
