package learning.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import learning.AbstractComponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent{
	WebDriver driver;


	public ConfirmationPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(tagName="h1")
	WebElement msg;
	 
	public String getConfirmationMessage()
	{
		return msg.getText();
	}
}
