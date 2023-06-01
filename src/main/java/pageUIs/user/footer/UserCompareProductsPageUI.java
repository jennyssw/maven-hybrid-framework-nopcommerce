package pageUIs.user.footer;

public class UserCompareProductsPageUI {
	public static final String PAGE_MESSAGE = "xpath=//div[@class='page compare-products-page']//div[text()='%s']";
	public static final String PRODUCT_INFO_ROW_IN_TABLE = "xpath=//tr[@class='remove-product']/following-sibling::tr[@class='product-name']/td/a[text()='%s']/parent::td/following-sibling::td/a[text()='%s']/parent::td/parent::tr/following-sibling::tr[@class='product-price']/td[text()='%s']/following-sibling::td[text()='%s']";
}
