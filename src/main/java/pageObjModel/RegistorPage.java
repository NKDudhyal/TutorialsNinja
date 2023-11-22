package pageObjModel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistorPage {

	WebDriver driver;
	public RegistorPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//input[@id='input-firstname']")
	private WebElement getFirstName;
	public void getFirstName(String firstName)
	{
		getFirstName.sendKeys(firstName);
	}
	
	@FindBy(xpath = "//input[@id='input-lastname']")
	private WebElement getLastName;
	public void getLastName(String lastName)
	{
		getLastName.sendKeys(lastName);
	}
	
	@FindBy(xpath = "//input[@id='input-email']")
	private WebElement getEmail;
	public void getEmail(String email)
	{
		getEmail.sendKeys(email);
	}
	
	@FindBy(xpath = "//input[@id='input-telephone']")
	private WebElement gettelephone;
	public void gettelephone(String telephone)
	{
		getEmail.sendKeys(telephone);
	}
	
	@FindBy(xpath = "//input[@id='input-password']")
	private WebElement getPassword;
	public void getPassword(String password)
	{
		getPassword.sendKeys(password);
	}
	
	@FindBy(xpath = "//input[@id='input-confirm']")
	private WebElement getConfirmPassword;
	public void getConfirmPassword(String confirmpassword)
	{
		getConfirmPassword.sendKeys(confirmpassword);
	}
	
	@FindBy(xpath = "//input[@type='checkbox']")
	private WebElement clickOnCheckBox;
	public void clickOnCheckBox()
	{
		clickOnCheckBox.click();
	}
	
	@FindBy(xpath = "//input[@type='submit']")
	private WebElement clcikOnSubmit;
	public void clcikOnSubmit()
	{
		clcikOnSubmit.click();
	}
	
	
	
}
