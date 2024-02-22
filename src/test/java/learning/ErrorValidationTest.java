package learning;
import java.util.List;
import java.io.IOException;
import java.time.Duration;

//import org.bouncycastle.oer.its.ieee1609dot2.basetypes.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import io.github.bonigarcia.wdm.WebDriverManager;
import learning.TestComponents.BaseTest;
import learning.pageobject.CartPage;
import learning.pageobject.CheckoutPage;
import learning.pageobject.ConfirmationPage;
import learning.pageobject.LandingPage;
import learning.pageobject.ProductCatelogPage;

public class ErrorValidationTest extends BaseTest {
	@Test(groups= {"Error Handling"})
	//@Test(groups= {"Error Handling"},retryAnalyzer=Retry.class)
	public void loginErrorValidation() throws IOException {

		landingPage.loginApplication("pavanhkumar.h@gmail.com", "Passworf d@123");
		;
		Assert.assertEquals("Incorrect email r password.", landingPage.getErrorMessage());
	}

	@Test
	public void productErrorValidation() throws IOException {
		String productName = "ADIDAS ORIGINAL";
		ProductCatelogPage productCatelogPage = landingPage.loginApplication("pavan.h@gmail.com", "Password@123");
		List<WebElement> products = productCatelogPage.getProductList();
		productCatelogPage.addProductToCart(productName);
		CartPage cartPage = productCatelogPage.goToCartPage();
		Boolean match = cartPage.verifyProductDisplaying("DAS ORIGINAL");
		Assert.assertFalse(match);
	}
	
}
