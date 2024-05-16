package pageUIs.admin;

public class AdminEditCustomerDetailsPageUI {
	public static final String GENDER_RADIO_BY_TEXT = "xpath=//label[normalize-space()='%s']/preceding-sibling::input";
	public static final String CUSTOMER_ROLE_TAG_BY_TITLE = "xpath=//ul[@class='select2-selection__rendered']//li[@title='%s']";
	public static final String TOGGLE_ICON = "xpath=//div[@id='customer-address']//i[contains(@class,'toggle-icon')]";
	public static final String ADDRESS_INFO_ROW_IN_TABLE = "xpath=//tr/td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td/div[contains(string(),'%s') and contains(string(),'%s')]";
	public static final String EDIT_ICON_OF_ADDRESS_INFO_ROW = "xpath=//tr/td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td/div[contains(string(),'%s') and contains(string(),'%s')]/parent::td/following-sibling::td//a[text()='Edit']";
	public static final String DELETE_ICON_OF_ADDRESS_INFO_ROW = "xpath=//tr/td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td/div[contains(string(),'%s') and contains(string(),'%s')]/parent::td/following-sibling::td//a[text()='Delete']";
	public static final String DELETED_SUCCESS_MESSAGE = "xpath=//table[@id='activitylog-grid']//td[text()='No data available in table']";
	public static final String DATE_OF_BIRTH_CALENDAR = "xpath=//input[@id='DateOfBirth']";
	public static final String DATE_OF_BIRTH_CALENDAR_BY_VALUE = "xpath=//input[@id='DateOfBirth' and @value='%s']";
	public static final String DATE_PICKER = "xpath=//input[@id='DateOfBirth']";
}
