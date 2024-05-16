package pageUIs.admin;

public class AdminAddANewCustomerPageUI {
	public static final String GENDER_RADIO_BY_TEXT = "xpath=//label[normalize-space()='%s']/preceding-sibling::input";
	public static final String DELETE_ICON_BY_TITLE = "xpath=//li[@title='%s']/span[@class='select2-selection__choice__remove']";
	public static final String CUSTOMER_ROLES_TAG_LIST_IN_ADD_NEW_CUSTOMER = "xpath=//select[@id='SelectedCustomerRoleIds']/following-sibling::span";
	public static final String OPTION_ITEM = "xpath=//ul[@class='select2-results__options']/li";
	public static final String DATE_PICKER = "xpath=//input[@id='DateOfBirth']";
}
