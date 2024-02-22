package learning.stepDefination;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import learning.TestComponents.BaseTest;
import learning.pageobject.CartPage;
import learning.pageobject.CheckoutPage;
import learning.pageobject.ConfirmationPage;
import learning.pageobject.LandingPage;
import learning.pageobject.ProductCatelogPage;

public class StepDefinationImp extends BaseTest {
	public LandingPage landingpage;
	public ProductCatelogPage productCatelogPage;
	public ConfirmationPage confirmationPage;
	String productName;

	@Given("I landed on E-commerce page")
	public void I_landed_on_Ecommerce_page() throws IOException {
		landingpage = launchApplication();
	}

	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_username_and_password(String username, String password) {
		productCatelogPage = landingPage.loginApplication(username, password);
	}

	@When("^I add product(.+) from cart$")
	public void i_add_product_to_cart(String productName) {
		List<WebElement> products = productCatelogPage.getProductList();
		productCatelogPage.addProductToCart(productName);
	}

	@When("^Checkout (.+) and submit the order$")
	public void checkout_submit_order(String productName) {
		CartPage cartPage = productCatelogPage.goToCartPage();
		Boolean match = cartPage.verifyProductDisplaying(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutpage = cartPage.goToCheckOut();
		checkoutpage.selectCountry("india");
		confirmationPage = checkoutpage.submitOrder();
	}
	@Then ("{string} message is displayed in confirmationPage")
	public void message_is_displayed_in_configurationpage(String  string)
	{
		String msg = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(msg.equalsIgnoreCase(string));
	}
	@Then ("{string} error message is displayed in confirmationPage")
	public void error_message_is_displayed_in_configurationpage(String  string)
	{
		Assert.assertEquals(string, landingPage.getErrorMessage());
		driver.close();
	}
}
