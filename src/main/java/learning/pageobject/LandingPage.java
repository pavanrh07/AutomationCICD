package learning.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import learning.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {
	WebDriver driver;
	public LandingPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	//WebElement userName=driver.findElement(By.id("userEmail"));
	//Page Factory
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement passwordEle;
	
	@FindBy(id="login")
	WebElement Login;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	public ProductCatelogPage loginApplication(String email, String password)
	{
		//userEmail.sendKeys(email);
		userEmail.sendKeys(email);
		passwordEle.sendKeys(password);
		Login.click();
		ProductCatelogPage productCatelogPage = new ProductCatelogPage(driver);
		return productCatelogPage;
	}
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}
	public String getErrorMessage()
	{
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
}

