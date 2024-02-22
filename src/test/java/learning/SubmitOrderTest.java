package learning;

import java.util.HashMap;
import java.util.List;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
//import org.bouncycastle.oer.its.ieee1609dot2.basetypes.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import learning.TestComponents.BaseTest;
import learning.pageobject.CartPage;
import learning.pageobject.CheckoutPage;
import learning.pageobject.ConfirmationPage;
import learning.pageobject.LandingPage;
import learning.pageobject.OrderPage;
import learning.pageobject.ProductCatelogPage;

public class SubmitOrderTest extends BaseTest {
	String productName = "ADIDAS ORIGINAL";

	@Test(dataProvider = "getDat", groups = { "Purchase" })
	public void submitOrder(HashMap<String, String> input) throws IOException {

		ProductCatelogPage productCatelogPage = landingPage.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> products = productCatelogPage.getProductList();
		productCatelogPage.addProductToCart(input.get("productName"));
		CartPage cartPage = productCatelogPage.goToCartPage();
		Boolean match = cartPage.verifyProductDisplaying(input.get("productName"));
		Assert.assertTrue(match);
		CheckoutPage checkoutpage = cartPage.goToCheckOut();
		checkoutpage.selectCountry("india");
		ConfirmationPage confirmationPage = checkoutpage.submitOrder();

		// a.moveToElement(driver.findElement(By.cssSelector(".action__submit"))).click().build().perform();

		String msg = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(msg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}

//To Verify ADIDAS ORIGINAL  id displaying in order page
	@Test(dependsOnMethods = { "submitOrder" })
	public void orderHistoryTest() {
		ProductCatelogPage productCatelogPage = landingPage.loginApplication("pavankumar.h@gmail.com", "Password@123");
		OrderPage oderPage = productCatelogPage.goToOrdertPage();
		Assert.assertTrue(oderPage.verifyOrderDisplaying(productName));
	}

	

	@DataProvider
	public Object[][] getDat()
	{
		HashMap<String,String> map= new HashMap<String,String>();
		map.put("email", "pavankumar.h@gmail.com");
		map.put("password", "Password@123");
		map.put("productName", "ADIDAS ORIGINAL");
		
		HashMap<String,String> map1= new HashMap<String,String>();
		map1.put("email", "pavan.h@gmail.com");
		map1.put("password", "Password@123");
		map1.put("productName", "IPHONE 13 PRO");
		
		return new Object[][] {{map} ,{map1}};
//	}
//	@DataProvider
//	public Object[][] getDat()
//	{
//		return Object[][] {{"pavankumar.h@gmail.com","Password@123","ADIDAS ORIGINAL"},{"pavan.h@gmail.com","Password@123","IPHONE 13 PRO"}};
//	}

	}
}
