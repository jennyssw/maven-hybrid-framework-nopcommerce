package com.nopcommerce.data;

import commons.BaseTest;

public class DataTest extends BaseTest {

	public static class UserRegister {
		public static final String FIRST_NAME = "Jenny";
		public static final String LAST_NAME = "Ngo";
		public static final String FULL_NAME = FIRST_NAME + " " + LAST_NAME;
		public static final String PASSWORD = "123456";
		public static final String PRE_EMAIL_ADDRESS = "jennyautomation";
		public static final String WEB_EMAIL_SERVER = "@gmail.com";
	}

	public static class UserLogin {
		public static final String PASSWORD = UserRegister.PASSWORD;
	}

	public static class UserAccount {
		public static final String GENDER = "Female";
		public static final String FIRST_NAME = "Jenny";
		public static final String LAST_NAME = "Automation";
		public static final String DATE_OF_BIRTH_DAY = "1";
		public static final String DATE_OF_BIRTH_MONTH = "January";
		public static final String DATE_OF_BIRTH_YEAR = "1999";
		public static final String COMPANY = "Automation VN";
		public static final String COUNTRY = "Viet Nam";
		public static final String STATE = "Other";
		public static final String CITY = "Da Nang";
		public static final String ADDRESS_1 = "123/04 Le Lai";
		public static final String ADDRESS_2 = "234/05 Hai Phong";
		public static final String ZIP_CODE = "550000";
		public static final String PHONE_NUMBER = "0123456789";
		public static final String FAX_NUMBER = "0987654321";
		public static final String PASSWORD = UserLogin.PASSWORD;
		public static final String PRE_EMAIL_ADDRESS = "jennyautomation.vn";
		public static final String WEB_EMAIL_SERVER = "@gmail.com";
	}

	public static class UserOrderProduct {
		public static final String PHONE_NUM = "0123456789";
		public static final String COUNTRY = "Viet Nam";
		public static final String CITY_1 = "Da Nang";
		public static final String CITY_2 = "Ho Chi Minh";
		public static final String ADDRESS_1 = "743 Le Loi";
		public static final String ADDRESS_2 = "30 Huynh Thuc Khang";
		public static final String ZIP_CODE_1 = "50000";
		public static final String ZIP_CODE_2 = "700000";
		public static final String SHIPPING_PRICE = "$0.00";
		public static final String TAX_PRICE = "$0.00";
		public static final String FIRST_NAME = UserRegister.FIRST_NAME;
		public static final String LAST_NAME = UserRegister.LAST_NAME;
		public static final String FULL_NAME = UserRegister.FULL_NAME;
		public static final String PASSWORD = UserLogin.PASSWORD;

		public static final String PROCESSOR_1 = "2.5 GHz Intel Pentium Dual-Core E2200 [+$15.00]";
		public static final String PROCESSOR_2 = "2.2 GHz Intel Pentium Dual-Core E2200";
		public static final String RAM_1 = "8GB [+$60.00]";
		public static final String RAM_2 = "4GB [+$20.00]";
		public static final String HDD_1 = "400 GB [+$100.00]";
		public static final String HDD_2 = "320 GB";
		public static final String OS_1 = "Vista Premium [+$60.00]";
		public static final String OS_2 = "Vista Home [+$50.00]";
		public static final String SOFTWARE_1 = "Microsoft Office [+$50.00]";
		public static final String SOFTWARE_2 = "Acrobat Reader [+$10.00]";
		public static final String SOFTWARE_3 = "Total Commander [+$5.00]";

		public static final String SKU_1 = "COMP_CUST";
		public static final String PRODUCT_NAME_1 = "Build your own computer";
		public static final String MIN_PRICE_WITH_ATTRIBUTES_1 = "$1,500.00";
		public static final String MIN_PRICE_WITH_ATTRIBUTES_2 = "$1,320.00";

		public static final String SKU_2 = "AP_MBP_13";
		public static final String PRODUCT_NAME_2 = "Apple MacBook Pro 13-inch";
		public static final String MIN_PRICE_2 = "$1,800.00";
	}

	public static class AdminLogin {
		public static final String ADMIN_EMAIL = "admin@yourstore.com";
		public static final String ADMIN_PASSWORD = "admin";
	}

	public static class AdminSearchProduct {
		public static final String PRODUCT_NAME = "Lenovo IdeaCentre 600 All-in-One PC";
		public static final String SKU = "LE_IC_600";
		public static final String PRICE = "500";
		public static final String STOCK_QUANTITY = "10000";
	}

	public static class AdminCustomer {
		public static final String PRE_EMAIL_ADDRESS = "automationfc";
		public static final String WEB_EMAIL_SERVER = "@gmail.com";
		public static final String PASSWORD = "123456";
		public static final String GENDER = "Female";

		public static final String DAY_OF_BIRTH = "13";
		public static final String MONTH_OF_BIRTH = "9";
		public static final String YEAR_OF_BIRTH = "1997";
		public static final String DATE_OF_BIRTH = MONTH_OF_BIRTH + "/" + DAY_OF_BIRTH + "/" + YEAR_OF_BIRTH;
		public static final String COMPANY = "Automation VN " + getRandomNumber();
		public static final String COUNTRY = "Viet Nam";
		public static final String STATE = "Other";
		public static final String COUNTY = "Hai Chau";
		public static final String CITY = "Da Nang";
		public static final String ADDRESS_1 = "743 Le Loi";
		public static final String ADDRESS_2 = "456 Le Lai";
		public static final String ZIP_CODE = "50000";
		public static final String PHONE_NUM = "0123456789";
		public static final String FAX_NUM = "+842839100751";

		public static final String DAY_OF_BIRTH_2 = "2";
		public static final String MONTH_OF_BIRTH_2 = "2";
		public static final String YEAR_OF_BIRTH_2 = "1998";
		public static final String DATE_OF_BIRTH_2 = MONTH_OF_BIRTH_2 + "/" + DAY_OF_BIRTH_2 + "/" + YEAR_OF_BIRTH;
		public static final String COMPANY_2 = "Automation US " + getRandomNumber();
		public static final String COUNTRY_2 = "United States";
		public static final String STATE_2 = "California";
		public static final String COUNTY_2 = "";
		public static final String CITY_2 = "Albany";
		public static final String ADDRESS_1_2 = "123 PO Box";
		public static final String ADDRESS_2_2 = "356 Los Bancos";
		public static final String ZIP_CODE_2 = "986589";
		public static final String PHONE_NUM_2 = "0987654666";
		public static final String FAX_NUM_2 = "+441619998888";
	}
}
