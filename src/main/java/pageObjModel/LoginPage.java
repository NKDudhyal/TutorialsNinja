package pageObjModel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	WebDriver driver;
	public LoginPage(WebDriver driver) {
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//input[@id='input-email']")
	private WebElement enterEmailAddress;
	public void enterEmailAddress(String emailText)
	{
		enterEmailAddress.sendKeys(emailText);
	}
	
	@FindBy(xpath = "//input[@id='input-password']")
	private WebElement enterPassword;
	public void enterPassword(String passwordText)
	{
		enterPassword.sendKeys(passwordText);
	}
	
	@FindBy(xpath = "//input[@type='submit']")
	private WebElement clickOnLoginButton;
	public void clickOnLoginButton()
	{
		clickOnLoginButton.click();
	}
	
	@FindBy(xpath = "//div[contains(@class,'alert-dismissible')]")
	private WebElement WarningMessageForEmail;
	public String WarningMessageForEmail()
	{
		String wrorningmessage = WarningMessageForEmail.getText();
		return wrorningmessage;
	}
	
	@FindBy(xpath = "(//a[text()='Forgotten Password'])[1]")
	private WebElement verifyForgetPassworddisplay;
	public boolean verifyForgetPassworddisplay()
	{
		boolean ForgetPassworddisplay =  verifyForgetPassworddisplay.isDisplayed();
		 return ForgetPassworddisplay;
	}
	
	@FindBy(xpath = "(//a[text()='Forgotten Password'])[1]")
	private WebElement clickOnForgotPassword;
	public void clickOnForgotPassword()
	{
		clickOnForgotPassword.click();
	}
	
	@FindBy(xpath = "//input[@id='input-email']")
	private WebElement emailPlaceholder;
	public String emailPlaceholder()
	{
		String emailph = emailPlaceholder.getAccessibleName();
		return emailph;
	}
	
	@FindBy(xpath = "//input[@id='input-password']")
	private WebElement passwordPlaceholder;
	public String passwordPlaceholder()
	{
		String passwordph = passwordPlaceholder.getAccessibleName();
		return passwordph;
	}
}
