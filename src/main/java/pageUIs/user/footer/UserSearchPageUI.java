package pageUIs.user.footer;

public class UserSearchPageUI {
	public static final String SEARCH_BUTTON = "xpath=//div[@class='buttons']//button[text()='Search']";
	public static final String SEARCH_KEYWORD_TEXTBOX = "xpath=//div[@class='inputs']/label[text()='Search keyword:']/following-sibling::input";
	public static final String DYNAMIC_DROPDOWN_BY_TITLE = "xpath=//label[text()='%s']/following-sibling::select";
	public static final String DYNAMIC_PRODUCT_LINK_BY_TITLE = "xpath=//h2[@class='product-title']/a[text()='%s']";
	public static final String DYNAMIC_ERROR_MESSAGE_BY_TITLE = "xpath=//div[@class='search-results']//div[text()='%s']";
}
