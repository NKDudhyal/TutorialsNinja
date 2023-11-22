package com.tutorialNinja.qa.TestCase;

import static org.testng.Assert.assertEquals;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.Base;
import pageObjModel.Homepage;
import pageObjModel.MyAccountPage;
import pageObjModel.RegistorPage;
import utilities.Utils;

public class Registor extends Base {

	public WebDriver driver;

	@BeforeMethod
	public void launch() {
		loadPropertiesFile();
		driver = browserAndWebsiteOpen(config.getProperty("browserName"));
	}

	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}

	@Test(priority = 1)
	public void verifyRegisteringAnAccountByProvidingOnlyTheMandatoryFields() {
		Homepage homepage = new Homepage(driver);
		homepage.clickOnMyAccount();
		homepage.selectRegistorOption();
		RegistorPage registorpage = new RegistorPage(driver);
		registorpage.getFirstName(testDataprop.getProperty("RegistorFirstName"));
		registorpage.getLastName(testDataprop.getProperty("RegistorLastName"));
		registorpage.getEmail(Utils.generatedEmailWithTimeStamp());
		registorpage.gettelephone(testDataprop.getProperty("Telephone"));
		registorpage.getPassword(testDataprop.getProperty("Password"));
		registorpage.getConfirmPassword(testDataprop.getProperty("ConfirmPassword"));
		registorpage.clickOnCheckBox();
		registorpage.clcikOnSubmit();
//		MyAccountPage myaccountpage = new MyAccountPage(driver);
//		String actualSuccusfullMessage = myaccountpage.verifyAccountHasCreated();
//		assertEquals(actualSuccusfullMessage, "Your Account Has Been Created!");
	}
	
	@Test(priority = 2)
	public void verifyRegisteringWithoutEnteringCredentials() {
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.xpath("//a[text()='Register']")).click();
		driver.findElement(By.xpath("//input[@id='input-firstname']")).sendKeys("");
		driver.findElement(By.xpath("//input[@id='input-lastname']")).sendKeys("");
		driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys("");
		driver.findElement(By.xpath("//input[@id='input-telephone']")).sendKeys("");
		driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys("");
		driver.findElement(By.xpath("//input[@id='input-confirm']")).sendKeys("");
		driver.findElement(By.xpath("//input[@type='checkbox']")).click();
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		assertEquals(driver
				.findElement(By.xpath("//div[contains(text(),'First Name must be between 1 and 32 characters!')]"))
				.getText(), "First Name must be between 1 and 32 characters!");
		assertEquals(
				driver.findElement(By.xpath("//div[contains(text(),'Last Name must be between 1 and 32 characters!')]"))
						.getText(),
				"Last Name must be between 1 and 32 characters!");
		assertEquals(
				driver.findElement(By.xpath("//div[contains(text(),'E-Mail Address does not appear to be valid!')]"))
						.getText(),
				"E-Mail Address does not appear to be valid!");
		assertEquals(
				driver.findElement(By.xpath("//div[contains(text(),'Telephone must be between 3 and 32 characters!')]"))
						.getText(),
				"Telephone must be between 3 and 32 characters!");
		assertEquals(
				driver.findElement(By.xpath("//div[contains(text(),'Password must be between 4 and 20 characters!')]"))
						.getText(),
				"Password must be between 4 and 20 characters!");
	}

	@Test(priority = 3)
	public void verifyRegisteringAnAccountBySelectingYesSubscribtion() {
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.xpath("//a[text()='Register']")).click();
		driver.findElement(By.xpath("//input[@id='input-firstname']")).sendKeys(testDataprop.getProperty("RegistorFirstName"));
		driver.findElement(By.xpath("//input[@id='input-lastname']")).sendKeys(testDataprop.getProperty("RegistorLastName"));
		driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys(Utils.generatedEmailWithTimeStamp());
		driver.findElement(By.xpath("//input[@id='input-telephone']")).sendKeys(testDataprop.getProperty("Telephone"));
		driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys(testDataprop.getProperty("Password"));
		driver.findElement(By.xpath("//input[@id='input-confirm']")).sendKeys(testDataprop.getProperty("ConfirmPassword"));
		driver.findElement(By.xpath("(//input[@type='radio'])[2]")).click();
		driver.findElement(By.xpath("//input[@type='checkbox']")).click();
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		String actualSuccusfullMessage = driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
		assertEquals(actualSuccusfullMessage, "Your Account Has Been Created!");
	}

	@Test(priority = 4)
	public void verifyRegisteringAnAccountByEnteringDifferentPasswordsIntoPasswordAndPasswordConfirmFields() {
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.xpath("//a[text()='Register']")).click();
		driver.findElement(By.xpath("//input[@id='input-firstname']")).sendKeys(testDataprop.getProperty("RegistorFirstName"));
		driver.findElement(By.xpath("//input[@id='input-lastname']")).sendKeys(testDataprop.getProperty("RegistorLastName"));
		driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys(Utils.generatedEmailWithTimeStamp());
		driver.findElement(By.xpath("//input[@id='input-telephone']")).sendKeys(testDataprop.getProperty("Telephone"));
		driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys(testDataprop.getProperty("Password"));
		driver.findElement(By.xpath("//input[@id='input-confirm']")).sendKeys(testDataprop.getProperty("InvalidPassword"));
		driver.findElement(By.xpath("//input[@type='checkbox']")).click();
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		assertEquals(
				driver.findElement(By.xpath("//div[contains(text(),'Password confirmation does not match password!')]"))
						.getText(),
				"Password confirmation does not match password!", "Error message doent match");
	}
	
	@Test(priority = 5)
	public void verifyRegisteringAnAccountByProvidingTheExistingAccountDetails()
	{
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.xpath("//a[text()='Register']")).click();
		driver.findElement(By.xpath("//input[@id='input-firstname']")).sendKeys(testDataprop.getProperty("RegistorFirstName"));
		driver.findElement(By.xpath("//input[@id='input-lastname']")).sendKeys(testDataprop.getProperty("RegistorLastName"));
		driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys(testDataprop.getProperty("ExictingEmail"));
		driver.findElement(By.xpath("//input[@id='input-telephone']")).sendKeys(testDataprop.getProperty("Telephone"));
		driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys(testDataprop.getProperty("Password"));
		driver.findElement(By.xpath("//input[@id='input-confirm']")).sendKeys(testDataprop.getProperty("InvalidPassword"));
		driver.findElement(By.xpath("//input[@type='checkbox']")).click();
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		String actualErrorMessage = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]"))
				.getText();
		assertEquals(actualErrorMessage, "Warning: E-Mail Address is already registered!");
		driver.quit();
	}
	
	@Test(priority = 6)
	public void vVerifyRegisteringAnAccountByProvidingAnInvalidEmailAddressIntoTheEmailField()
	{
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.xpath("//a[text()='Register']")).click();
		driver.findElement(By.xpath("//input[@id='input-firstname']")).sendKeys(testDataprop.getProperty("RegistorFirstName"));
		driver.findElement(By.xpath("//input[@id='input-lastname']")).sendKeys(testDataprop.getProperty("RegistorLastName"));
		driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys(testDataprop.getProperty("RegistorInvalid_3"));
		driver.findElement(By.xpath("//input[@id='input-telephone']")).sendKeys(testDataprop.getProperty("Telephone"));
		driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys(testDataprop.getProperty("Password"));
		driver.findElement(By.xpath("//input[@id='input-confirm']")).sendKeys(testDataprop.getProperty("Password"));
		driver.findElement(By.xpath("//input[@type='checkbox']")).click();
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		String actualErrorMessage = driver.findElement(By.xpath("//div[contains(text(),'E-Mail Address does not appear to be valid!')]"))
				.getText();
		assertEquals(actualErrorMessage, "E-Mail Address does not appear to be valid!");
		driver.quit();
	}

}
