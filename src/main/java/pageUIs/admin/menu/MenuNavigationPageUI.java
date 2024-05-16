package pageUIs.admin.menu;

public class MenuNavigationPageUI {
	public static final String DASHBOARD_LINK_IN_MENU = "xpath=//p[contains(text(),'Dashboard')]//parent::a";
	public static final String MENU_LINK_BY_TEXT = "xpath=//p[normalize-space()='%s']/parent::a/parent::li[@class='nav-item has-treeview']/a";
	public static final String SUB_MENU_LINK_BY_TEXT = "xpath=//li[@class='nav-item']//p[normalize-space()='%s']";
}
