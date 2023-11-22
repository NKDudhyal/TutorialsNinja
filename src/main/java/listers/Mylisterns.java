package listers;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import utilities.ExtenReport;

public class Mylisterns implements ITestListener {

	public ExtentReports extentreport;
	public ExtentTest extentTest;
	
	@Override
	public void onStart(ITestContext context) {
		extentreport = ExtenReport.generateExtentReport();
	}
	
	@Override
	public void onTestStart(ITestResult result) {
		String testName = result.getName();
		extentTest =  extentreport.createTest(testName);
		extentTest.log(Status.INFO, testName + "-----------STARTED EXECUTING-----------");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String testName = result.getName();
		extentTest.log(Status.PASS, testName + "-----------GOT SUCCESFULL EXECUTED-----------");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String testName = result.getName();
		WebDriver driver = null;
		try 
		{
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} 
		catch (IllegalArgumentException 
				| IllegalAccessException 
				| NoSuchFieldException 
				| SecurityException e) {
			e.printStackTrace();
		}
		File srcScreenhot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String destiSSPath = System.getProperty(("user.dir")+"\\ScreenShots\\"+testName+".png");
		try {
			FileHandler.copy(srcScreenhot, new File(destiSSPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		extentTest.addScreenCaptureFromPath(destiSSPath);
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.FAIL, testName + "---------GOT FAILED---------");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String testName = result.getName();
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.SKIP, testName +"-----------GOT SKIPPED-----------");
	}

	@Override
	public void onFinish(ITestContext context) {
		extentreport.flush();
	}

}
