package pageObjModel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Homepage {
	
	WebDriver driver;
	
	public Homepage(WebDriver driver)
	{
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//span[text()='My Account']")
	private WebElement clickOnMyAccount;
	public void clickOnMyAccount()
	{
		clickOnMyAccount.click();
	}
	
	@FindBy(xpath = "(//a[text()='Login'])[1]")
	private WebElement selectLoginOption;
	public void selectLoginOption()
	{
		selectLoginOption.click();
	}
	
	@FindBy(xpath = "//a[text()='Register']")
	private WebElement selectRegistorOption;
	public void selectRegistorOption()
	{
		selectRegistorOption.click();
	}
	
	
	
	
	
	
}
