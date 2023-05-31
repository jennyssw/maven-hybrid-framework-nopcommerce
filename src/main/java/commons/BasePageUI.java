package commons;

public class BasePageUI {
	public static final String DYNAMIC_TEXTBOX_BY_ID = "xpath=//input[@id='%s']";
	public static final String DYNAMIC_TEXTAREA_BY_ID = "xpath=//textarea[@id='%s']";
	public static final String DYNAMIC_BUTTON_BY_TITLE_TEXT = "xpath=//button[text()='%s']";
	public static final String DYNAMIC_BUTTON_BY_TITLE_NORMALIZE_SPACE = "xpath=//*[normalize-space()='%s']";
	public static final String DYNAMIC_BUTTON_BY_ID = "xpath=//button[@id='%s']";
	public static final String DYNAMIC_LINK_BY_TITLE = "xpath=//a[text()='%s']";
	public static final String DYNAMIC_LINK_BY_CLASS_NAME = "class=%s";
	public static final String DYNAMIC_RADIO_BY_CLASS_NAME = "css=span.%s>input";
	public static final String DYNAMIC_CHECKBOX_BY_ID = "xpath=//*[@id='%s']";
	public static final String DYNAMIC_CHECKBOX_OR_RADIO_BY_TITLE = "xpath=//label[text()='%s']/parent::*/input";
	public static final String DYNAMIC_DROPDOWN_BY_NAME = "name=%s";
	public static final String DYNAMIC_DROPDOWN_BY_ID = "xpath=//select[@id='%s']";
	public static final String DYNAMIC_WEB_MENU_BY_TITLE = "xpath=//ul[@class='top-menu notmobile']//a[contains(text(),'%s')]";
	public static final String DYNAMIC_WEB_SUBMENU_BY_TITLE = "xpath=//ul[@class='top-menu notmobile']//a[contains(text(),'%s')]/following-sibling::ul[@class='sublist first-level']//a[contains(text(),'%s')]";
	public static final String DYNAMIC_FIELD_ERROR_MESSAGE_BY_ID = "id=%s";
	public static final String PAGE_ERROR_MESSAGE = "css=div[class='message-error validation-summary-errors'] li";
	public static final String PAGE_SUCCESS_MESSAGE = "css=div[class='page registration-result-page'] div.result";
	public static final String BAR_NOTI_SUCCESS_MESSAGE = "css=div.bar-notification.success>p";
	public static final String CLOSE_BAR_NOTI_SUCCESS_MESSAGE_ICON = "css=div.bar-notification.success>span.close";
	public static final String ALERT_SUCCESS_MESSAGE = "xpath=//div[@class='content-wrapper']/div[contains(string(),'%s')]";
	public static final String CLOSE_ALERT_SUCCESS_MESSAGE_ICON = "css=button[data-dismiss='alert']";
	public static final String LOG_LINK = "xpath=//a[contains(text(),'Log')]";
}
