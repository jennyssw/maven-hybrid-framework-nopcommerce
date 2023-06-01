package pageUIs.admin.menu;

public class AdminProductsPageUI {
	public static final String PRODUCT_INFO_ROW_IN_TABLE = "xpath=//table[@id='products-grid']//tr[@class='%s']/td/following-sibling::td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td[text()='%s']/preceding-sibling::td/following-sibling::td/i";
	public static final String ITEM_NUMBER_IN_TABLE = "xpath=//table[@id='products-grid']/tbody/tr";
	public static final String SEARCH_SUB_CATEGORIES_CHECKBOX = "xpath=//label[text()='Search subcategories']/parent::div/parent::div/following-sibling::div/input";
	public static final String NO_ITEM_MESSAGE_IN_TABLE = "xpath=//table[@id='products-grid']//td[text()='No data available in table']";
	public static final String COLLAPSE_ICON = "xpath=//div[contains(@class,'row search-row')]//i[contains(@class,'angle')]";
	public static final String SEARCH_PRODUCT_BUTTON = "xpath=//button[@id='search-products']";
}
