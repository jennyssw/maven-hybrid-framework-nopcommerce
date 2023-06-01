package pageUIs.admin;

public class AdminEditCustomerDetailsPageUI {
	public static final String GENDER_RADIO = "xpath=//label[normalize-space()='%s']/preceding-sibling::input";
	public static final String DYNAMIC_CUSTOMER_ROLE_TAG_BY_TITLE = "xpath=//ul[@id='SelectedCustomerRoleIds_taglist']//span[text()='%s']";
	public static final String TOGGLE_ICON = "xpath=//div[@id='customer-address']//i[contains(@class,'toggle-icon')]";
	public static final String ADDRESS_INFO_ROW_IN_TABLE = "xpath=//tr/td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td/div[contains(text(),'%s')]";
	public static final String EDIT_ICON_OF_ADDRESS_INFO_ROW = "xpath=//tr/td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td[contains(string(),'%s')]/following-sibling::td/a[contains(text(),'Edit')]";
	public static final String DELETE_ICON_OF_ADDRESS_INFO_ROW = "xpath=//tr/td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td[contains(string(),'%s')]/following-sibling::td/a[contains(text(),'Delete')]";
	public static final String DELETED_SUCCESS_MESSAGE = "xpath=//table[@id='activitylog-grid']//td[text()='No data available in table']";
}
