package pageObjModel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyAccountPage {

	WebDriver driver;
	public MyAccountPage(WebDriver driver)
	{
		this.driver= driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(linkText = "Edit your account information")
	WebElement verifingTheEditYoutAccountInformation;
	public boolean verifingTheEditYoutAccountInformation()
	{
		 boolean succesfullLOginMessage =  verifingTheEditYoutAccountInformation.isDisplayed();
		 return succesfullLOginMessage;
	}
	
	@FindBy(xpath = "//div[@id='content']/h1")
	private WebElement verifyAccountHasCreated;
	public String verifyAccountHasCreated()
	{
		String actualSuccusfullMessage = verifyAccountHasCreated.getText();
		return actualSuccusfullMessage;
	}
}
