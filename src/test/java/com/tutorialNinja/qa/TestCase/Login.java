package com.tutorialNinja.qa.TestCase;

import static org.testng.Assert.assertEquals;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import base.Base;
import pageObjModel.Homepage;
import pageObjModel.LoginPage;
import pageObjModel.MyAccountPage;
import utilities.Utils;

public class Login extends Base {

	public WebDriver driver;

	@DataProvider
	public Object[][] supplyDataFromExcel()
	{
		Object[][] data = Utils.getDataFromExcelFile("Login");
		return data;
	}
	
	@BeforeMethod
	public void launch() {
		loadPropertiesFile();
		driver = browserAndWebsiteOpen(config.getProperty("browserName"));
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	
	@Test(priority = 1,dataProvider = "supplyDataFromExcel")
	public void verifyLoginWithValidCredentials(String email, String password) {
		Homepage homepage = new Homepage(driver);
		homepage.clickOnMyAccount();
		homepage.selectLoginOption();
		LoginPage loginpage = new LoginPage(driver);
		loginpage.enterEmailAddress(email);
		loginpage.enterPassword(password);
		loginpage.clickOnLoginButton();
		MyAccountPage myaccountpage = new MyAccountPage(driver);
		Assert.assertTrue(myaccountpage.verifingTheEditYoutAccountInformation());
	}
	

	@Test(priority = 2)
	public void verifyLoginWithInvalidCredentials() {
		Homepage homepage = new Homepage(driver);
		homepage.clickOnMyAccount();
		homepage.selectLoginOption();
		LoginPage loginpage = new LoginPage(driver);
		loginpage.enterEmailAddress(Utils.generatedEmailWithTimeStamp()); 
		loginpage.enterPassword("ninjaa");
		loginpage.clickOnLoginButton();
		String actualErrorMessage = loginpage.WarningMessageForEmail();
		assertEquals(actualErrorMessage, "Warning: N match for E-Mail Address and/or Password.");
	}

	@Test(priority = 3)
	public void verifyLoginWithInvalidEmail() {
		Homepage homepage = new Homepage(driver);
		homepage.clickOnMyAccount();
		homepage.selectLoginOption();
		LoginPage loginpage = new LoginPage(driver);
		loginpage.enterEmailAddress(Utils.generatedEmailWithTimeStamp()); 
		loginpage.enterPassword("ninja");
		loginpage.clickOnLoginButton();
		String actualErrorMessage = loginpage.WarningMessageForEmail();
		assertEquals(actualErrorMessage, "Warning: No match for E-Mail Address and/or Password.");
	}

	@Test(priority = 4,dataProvider = "supplyDataFromExcel")
	public void verifyLoginWithInvalidPassword(String email, String password) {
		Homepage homepage = new Homepage(driver);
		homepage.clickOnMyAccount();
		homepage.selectLoginOption();
		LoginPage loginpage = new LoginPage(driver);
		loginpage.enterEmailAddress(email); 
		loginpage.enterPassword("ninjaa");
		loginpage.clickOnLoginButton();
		String actualErrorMessage = loginpage.WarningMessageForEmail();
		assertEquals(actualErrorMessage, "Warning: No match for E-Mail Address and/or Password.");
	}

	@Test(priority = 5)
	public void verifyLoginWithEmptyFields() {
		Homepage homepage = new Homepage(driver);
		homepage.clickOnMyAccount();
		homepage.selectLoginOption();
		LoginPage loginpage = new LoginPage(driver);
		loginpage.enterEmailAddress(""); 
		loginpage.enterPassword("");
		loginpage.clickOnLoginButton();
		String actualErrorMessage = loginpage.WarningMessageForEmail();
		assertEquals(actualErrorMessage, "Warning: No match for E-Mail Address and/or Password.");
	}

	@Test(priority = 6)
	public void verifyForgotPassowrdWorkProperly() {
		Homepage homepage = new Homepage(driver);
		homepage.clickOnMyAccount();
		homepage.selectLoginOption();
		LoginPage loginpage = new LoginPage(driver);
		loginpage.verifyForgetPassworddisplay();
		loginpage.verifyForgetPassworddisplay();
		String actualRedirectURL = driver.getCurrentUrl();
		Assert.assertEquals(actualRedirectURL, "https://tutorialsninja.com/demo/index.php?route=account/login");
	}

	@Test(priority = 7,dataProvider = "supplyDataFromExcel")
	public void verifyThatUserShouldLoginWithKeyboard(String email, String password) throws AWTException {
		Homepage homepage = new Homepage(driver);
		homepage.clickOnMyAccount();
		homepage.selectLoginOption();
		LoginPage loginpage = new LoginPage(driver);
		loginpage.enterEmailAddress(email); 
		loginpage.enterPassword(password);
		Robot rb = new Robot();
		rb.keyPress(KeyEvent.VK_TAB);
		rb.keyRelease(KeyEvent.VK_TAB);
		rb.keyPress(KeyEvent.VK_TAB);
		rb.keyRelease(KeyEvent.VK_TAB);
		rb.keyPress(KeyEvent.VK_ENTER);
		rb.keyRelease(KeyEvent.VK_ENTER);
		String actualURL = driver.getCurrentUrl();
		String currentURL = "https://tutorialsninja.com/demo/index.php?route=account/account";
		assertEquals(actualURL, currentURL);
	}

	@Test(priority = 8)
	public void verifyThePlaceHolderInEmailAndPasswordField() throws AWTException {
		Homepage homepage = new Homepage(driver);
		homepage.clickOnMyAccount();
		homepage.selectLoginOption();
		LoginPage loginpage = new LoginPage(driver);
		String actualUserNamePlaceHolder = loginpage.emailPlaceholder();
		Assert.assertEquals(actualUserNamePlaceHolder, "E-Mail Address");
		String actualPasswordPlaceHolder = loginpage.passwordPlaceholder();
		Assert.assertEquals(actualPasswordPlaceHolder, "Password");
	}

	@Test(priority = 9,dataProvider = "supplyDataFromExcel")
	public void verifyLoggingoutFromTheApplicationAndBrowsingBackUsingBrowserBackButton(String email, String password) throws AWTException {
		Homepage homepage = new Homepage(driver);
		homepage.clickOnMyAccount();
		homepage.selectLoginOption();
		LoginPage loginpage = new LoginPage(driver);
		loginpage.enterEmailAddress(email); 
		loginpage.enterPassword(password);
		loginpage.clickOnLoginButton();
		driver.navigate().back();
	}
}
