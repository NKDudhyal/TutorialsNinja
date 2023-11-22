package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Base {
	WebDriver driver;
	public Properties config;
	public Properties testDataprop;

	public void loadPropertiesFile() {
		config = new Properties();
		testDataprop = new Properties();
		File f = new File(System.getProperty("user.dir") + "\\src\\main\\java\\config\\confif.properties");
		File f2 = new File(System.getProperty("user.dir")+ "\\src\\main\\java\\testData\\testdata.properties");
		
		
		try {
			FileInputStream testdatap = new FileInputStream(f2);
			testDataprop.load(testdatap);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			FileInputStream configp = new FileInputStream(f);
			config.load(configp);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public WebDriver browserAndWebsiteOpen(String BN) {

		if (BN.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (BN.equals("firefox")) {
			driver = new FirefoxDriver();
		} else if (BN.equals("edge")) {
			driver = new EdgeDriver();
		}
		driver.get(config.getProperty("url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		return driver;
	}
}
