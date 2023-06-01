package pageUIs.user.header;

public class UserWishlistPageUI {
	public static final String PRODUCT_INFO_ROW_IN_TABLE = "xpath=//span[@class='sku-number' and text()='%s']/parent::td/following-sibling::td/a[@class='product-name' and text()='%s']/parent::td/following-sibling::td/span[@class='product-unit-price' and text()='%s']/parent::td/following-sibling::td/input[@class='qty-input' and @value='%s']/parent::td/following-sibling::td/span[@class='product-subtotal' and text()='%s']";
	public static final String PRODUCT_INFO_ROW_IN_TABLE_FROM_SHARING_LINK = "xpath=//span[@class='sku-number' and text()='%s']/parent::td/following-sibling::td/a[@class='product-name' and text()='%s']/parent::td/following-sibling::td/span[@class='product-unit-price' and text()='%s']/parent::td/following-sibling::td/span[@class='product-quantity' and text()='%s']/parent::td/following-sibling::td/span[@class='product-subtotal' and text()='%s']";
	public static final String SHARING_LINK = "xpath=//span[text()='Your wishlist URL for sharing:']/following-sibling::a";
	public static final String WISHLIST_OF_PERSON_TITLE = "css=div.page.wishlist-page h1";
	public static final String PAGE_MESSAGE = "xpath=//div[@class='page wishlist-page']//div[contains(text(),'%s')]";
	public static final String REMOVE_PRODUCT_ICON = "xpath=//span[@class='sku-number' and text()='%s']/parent::td/following-sibling::td/a[@class='product-name' and text()='%s']/parent::td/following-sibling::td/span[@class='product-unit-price' and text()='%s']/parent::td/following-sibling::td/input[@class='qty-input' and @value='%s']/parent::td/following-sibling::td/span[@class='product-subtotal' and text()='%s']/parent::td/following-sibling::td[@class='remove-from-cart']/button";
}
