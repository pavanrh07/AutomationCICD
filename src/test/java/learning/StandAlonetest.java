package learning;
import java.util.List;
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

import io.github.bonigarcia.wdm.WebDriverManager;
import learning.pageobject.LandingPage;

public class StandAlonetest {

	public static void main(String[] args){
		String productName="ADIDAS ORIGINAL";
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver =new ChromeDriver();
		driver.manage().window().maximize();
		
		
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		//LandingPage landingPage =new LandingPage(driver);
		driver.findElement(By.id("userEmail")).sendKeys("pavankumar.h@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Password@123");
		driver.findElement(By.id("login")).click();
		List<WebElement> products=driver.findElements(By.cssSelector(".mb-3"));
		WebElement prod=products.stream().filter(product->product.findElement(By.cssSelector("b")).getText().
				equalsIgnoreCase(productName)).findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(" #toast-container")));
	//	wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[routerlink*=cart]")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.cssSelector(" [routerlink*=cart]")).click();
		
		List<WebElement> cartProducts=driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match=cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		//Assert.assertFalse(match);
		driver.findElement(By.xpath("//li[@class='totalRow']/button")).click();
		
		Actions a =new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform(); 
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".ta-results"))));
		driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".action__submit")));
		
		a.moveToElement(driver.findElement(By.cssSelector(".action__submit"))).click().build().perform(); 
		//driver.findElement(By.cssSelector(".action__submit")).click();
		//driver.findElement(By.className("btnn action__submit ng-star-inserted")).click();
		
		 String msg=driver.findElement(By.tagName("h1")).getText();
		Assert.assertTrue(msg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		driver.close();
		
		
		
		

	}

}
