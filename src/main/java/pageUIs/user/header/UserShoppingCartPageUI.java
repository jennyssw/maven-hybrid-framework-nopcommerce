package pageUIs.user.header;

public class UserShoppingCartPageUI {
	public static final String REMOVE_ICON_IN_TABLE = "xpath=//span[@class='sku-number' and text()='%s']/parent::td/following-sibling::td[@class='product']/a[text()='%s']/parent::td/following-sibling::td/span[@class='product-unit-price' and text()='%s']/parent::td/following-sibling::td/input[@class='qty-input' and @value='%s']/parent::td/following-sibling::td/span[@class='product-subtotal' and text()='%s']/parent::td/following-sibling::td/button[@class='remove-btn']";
	public static final String PRODUCT_INFO_ROW_IN_TABLE = "xpath=//span[@class='sku-number' and text()='%s']/parent::td/following-sibling::td[@class='product']/a[text()='%s']/parent::td/following-sibling::td/span[@class='product-unit-price' and text()='%s']/parent::td/following-sibling::td/input[@class='qty-input' and @value='%s']/parent::td/following-sibling::td/span[@class='product-subtotal' and text()='%s']";
	public static final String PRODUCT_DETAIL_BY_PRODUCT_NAME = "xpath=//td[@class='product']/a[text()='%s']/following-sibling::div[@class='attributes']";
	public static final String SHOPPING_CART_MESSAGE = "xpath=//div[contains(text(),'%s')]";
	public static final String QUANTITY_TEXTBOX = "css=td[class='quantity']>input";
}
