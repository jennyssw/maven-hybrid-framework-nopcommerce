package pageObjects.user;

import org.openqa.selenium.WebDriver;

import pageObjects.user.footer.UserCompareProductsPageObject;
import pageObjects.user.footer.UserRecentlyViewedProductsPageObject;
import pageObjects.user.footer.UserSearchPageObject;
import pageObjects.user.header.UserLoginPageObject;
import pageObjects.user.header.UserRegisterPageObject;
import pageObjects.user.header.UserShoppingCartPageObject;
import pageObjects.user.header.UserWishlistPageObject;
import pageObjects.user.sidebar.UserCustomerAddressesPageObject;
import pageObjects.user.sidebar.UserCustomerChangePasswordPageObject;
import pageObjects.user.sidebar.UserCustomerInfoPageObject;
import pageObjects.user.sidebar.UserCustomerOrdersPageObject;
import pageObjects.user.sidebar.UserCustomerProductReviewPageObject;

public class PageGeneratorManager {
	public static UserHomePageObject getUserHomePage(WebDriver driver) {
		return new UserHomePageObject(driver);
	}

	public static UserRegisterPageObject getUserRegisterPage(WebDriver driver) {
		return new UserRegisterPageObject(driver);
	}

	public static UserLoginPageObject getUserLoginPage(WebDriver driver) {
		return new UserLoginPageObject(driver);
	}

	public static UserShoppingCartPageObject getUserShoppingCartPage(WebDriver driver) {
		return new UserShoppingCartPageObject(driver);
	}

	public static UserWishlistPageObject getUserWishlistPage(WebDriver driver) {
		return new UserWishlistPageObject(driver);
	}

	public static UserCustomerInfoPageObject getUserCustomerInfoPage(WebDriver driver) {
		return new UserCustomerInfoPageObject(driver);
	}

	public static UserCustomerAddressesPageObject getUserCustomerAddressesPage(WebDriver driver) {
		return new UserCustomerAddressesPageObject(driver);
	}

	public static UserCustumerAddressAddPageObject getUserCustomerAddressAddPage(WebDriver driver) {
		return new UserCustumerAddressAddPageObject(driver);
	}

	public static UserCustomerChangePasswordPageObject getUserCustomerChangePasswordPage(WebDriver driver) {
		return new UserCustomerChangePasswordPageObject(driver);
	}

	public static UserCustomerProductReviewPageObject getUserCustomerProductReviewPage(WebDriver driver) {
		return new UserCustomerProductReviewPageObject(driver);
	}

	public static UserCustomerOrdersPageObject getUserCustomerOrdersPage(WebDriver driver) {
		return new UserCustomerOrdersPageObject(driver);
	}

	public static UserProductPageObject getUserProductPage(WebDriver driver) {
		return new UserProductPageObject(driver);
	}

	public static UserProductReviewPageObject getUserProductReviewPage(WebDriver driver) {
		return new UserProductReviewPageObject(driver);
	}

	public static UserInventoryPageObject getUserInventoryPage(WebDriver driver) {
		return new UserInventoryPageObject(driver);
	}

	public static UserSearchPageObject getUserSearchPage(WebDriver driver) {
		return new UserSearchPageObject(driver);
	}

	public static UserCompareProductsPageObject getUserCompareProductsPage(WebDriver driver) {
		return new UserCompareProductsPageObject(driver);
	}

	public static UserRecentlyViewedProductsPageObject getUserRecentlyViewedProductsPage(WebDriver driver) {
		return new UserRecentlyViewedProductsPageObject(driver);
	}

	public static UserCheckoutPageObject getUserCheckoutPage(WebDriver driver) {
		return new UserCheckoutPageObject(driver);
	}

	public static UserOrderDetailsPageObject getUserOrderDetailsPage(WebDriver driver) {
		return new UserOrderDetailsPageObject(driver);
	}
}
