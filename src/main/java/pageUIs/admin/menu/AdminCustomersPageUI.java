package pageUIs.admin.menu;

public class AdminCustomersPageUI {
	public static final String DYNAMIC_DELETE_ICON_BY_NAME_TAG = "xpath=//span[text()='%s']/following-sibling::span[@title='delete']";
	public static final String NAME_TAG_ITEM = "css=ul[id='SelectedCustomerRoleIds_listbox']>li";
	public static final String CUSTOMER_ROLES_TAG_LIST_IN_CUSTOMERS = "css=div[class='k-widget k-multiselect k-multiselect-clearable'] div[role='listbox']";
	public static final String CUSTOMER_INFO_ROW_IN_TABLE_EDIT_ICON = "xpath=//tr/td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td/i[@nop-value='%s']/parent::td/following-sibling::td/a";
	public static final String ITEM_NUMBER_IN_TABLE = "xpath=//table[@id='customers-grid']/tbody/tr";
	public static final String ADD_NEW_ADDRESS_BUTTON = "xpath=//button[normalize-space()='Add new address']";
	public static final String TOGGLE_ICON = "xpath=//div[@id='customer-address']//i[contains(@class,'toggle-icon')]";
	public static final String DYNAMIC_TOGGLE_ICON_BY_CLASS_NAME = "xpath=//div[@id='customer-address']//i[contains(@class,'%s')]";
	public static final String SEARCH_CUSTOMER_BUTTON = "xpath=//button[@id='search-customers']";
	public static final String COLLAPSE_ICON = "xpath=//div[contains(@class,'row search-row')]//i[contains(@class,'angle')]";
}
