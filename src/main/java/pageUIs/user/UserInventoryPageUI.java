package pageUIs.user;

public class UserInventoryPageUI {
	public static final String SORT_DROPDOWN = "xpath=//select[@id='products-orderby']";
	public static final String DISPLAY_DROPDOWN = "xpath=//select[@id='products-pagesize']";
	public static final String PRODUCT_NAME = "css=h2.product-title>a";
	public static final String PRODUCT_PRICE = "css=span.price.actual-price";
	public static final String PAGING_AREA = "css=div.pager";
	public static final String DYNAMIC_PAGER_ICON_BY_TITLE = "xpath=//a[text()='%s']/parent::li";
	public static final String DYNAMIC_PRODUCT_LINK_BY_TITLE = "xpath=//h2[@class='product-title']//a[text()='%s']";
}
