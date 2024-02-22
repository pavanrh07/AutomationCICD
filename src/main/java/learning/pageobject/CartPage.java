package learning.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import learning.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {
	WebDriver driver;
	public CartPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	//List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
	@FindBy(css=".cartSection h3")
	List<WebElement> productTitles;
	
	//driver.findElement(By.xpath("//li[@class='totalRow']/button")).click();
	@FindBy(xpath="//li[@class='totalRow']/button")
	WebElement checkOutEle;
	public Boolean verifyProductDisplaying(String productName) {
		Boolean match = productTitles.stream()
				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		return match;
	}
	public  CheckoutPage goToCheckOut() {
		checkOutEle.click();
		CheckoutPage checkoutpage=new CheckoutPage(driver);
		return checkoutpage;
		
	}
}
