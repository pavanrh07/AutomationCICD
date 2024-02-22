package learning.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import learning.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {
	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}

	@FindBy(css = "[placeholder='Select Country']")
	WebElement country;
	@FindBy(css = ".action__submit")
	WebElement submit;

	@FindBy(css = ".ta-item:nth-of-type(2)")
	WebElement selectCountry;

	By results = By.cssSelector(".ta-results");

	public void selectCountry(String countryName) {
		Actions a = new Actions(driver);
		a.sendKeys(country, countryName).build().perform();
		waitForElementToAppear(results);
		selectCountry.click();
	}

	public ConfirmationPage submitOrder() {
		Actions a = new Actions(driver);
		a.moveToElement(driver.findElement(By.cssSelector(".action__submit"))).click().build().perform();
		return new ConfirmationPage(driver);
		// return confirmationPage;
	}

}
