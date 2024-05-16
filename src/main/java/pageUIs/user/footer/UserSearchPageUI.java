package pageUIs.user.footer;

public class UserSearchPageUI {
	public static final String SEARCH_BUTTON = "xpath=//div[@class='buttons']//button[text()='Search']";
	public static final String SEARCH_KEYWORD_TEXTBOX = "xpath=//div[@class='inputs']/label[text()='Search keyword:']/following-sibling::input";
	public static final String DROPDOWN_BY_TEXT = "xpath=//label[text()='%s']/following-sibling::select";
	public static final String PRODUCT_LINK_BY_TEXT = "xpath=//h2[@class='product-title']/a[text()='%s']";
	public static final String ERROR_MESSAGE_BY_TEXT = "xpath=//div[@class='search-results']//div[text()='%s']";
}
