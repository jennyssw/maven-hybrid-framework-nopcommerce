package pageUIs.admin.menu;

public class AdminCustomersPageUI {
	public static final String DELETE_ICON_BY_TITLE = "xpath=//li[@title='%s']/span[@class='select2-selection__choice__remove']";
	public static final String OPTION_ITEM = "xpath=//ul[@class='select2-results__options']/li";
	public static final String CUSTOMER_ROLES_TAG_LIST_IN_CUSTOMERS = "xpath=//select[@id='SelectedCustomerRoleIds']/following-sibling::span";
	public static final String CUSTOMER_INFO_ROW_IN_TABLE_EDIT_ICON = "xpath=//tr/td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td/i[@nop-value='%s']/parent::td/parent::tr//a[text()='Edit']";
	public static final String ITEM_NUMBER_IN_TABLE = "xpath=//table[@id='customers-grid']/tbody/tr";
	public static final String ADD_NEW_ADDRESS_BUTTON = "xpath=//button[normalize-space()='Add new address']";
	public static final String TOGGLE_ICON = "xpath=//div[@id='customer-address']//i[contains(@class,'toggle-icon')]";
	public static final String TOGGLE_ICON_BY_CLASS_NAME = "xpath=//div[@id='customer-address']//i[contains(@class,'%s')]";
	public static final String SEARCH_CUSTOMER_BUTTON = "xpath=//button[@id='search-customers']";
	public static final String COLLAPSE_ICON = "xpath=//div[contains(@class,'row search-row')]//i[contains(@class,'angle')]";
}
