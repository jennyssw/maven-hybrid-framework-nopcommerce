package pageUIs.admin;

public class AdminAddANewCustomerPageUI {
	public static final String GENDER_RADIO = "xpath=//label[normalize-space()='%s']/preceding-sibling::input";
	public static final String DYNAMIC_DELETE_ICON_BY_NAME_TAG = "xpath=//span[text()='%s']/following-sibling::span[@title='delete']";
	public static final String CUSTOMER_ROLES_TAG_LIST_IN_ADD_NEW_CUSTOMER = "css=div[class='input-group-append input-group-required'] div[role='listbox']";
	public static final String NAME_TAG_ITEM = "css=ul[id='SelectedCustomerRoleIds_listbox']>li";
}
