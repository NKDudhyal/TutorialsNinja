package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtenReport{
	
	public static ExtentReports generateExtentReport()
	{
		Properties config = new Properties();
		File f = new File(System.getProperty("user.dir")+"\\src\\main\\java\\config\\confif.properties");
		try {
			FileInputStream configp = new FileInputStream(f);
			config.load(configp);
		} catch (IOException e) {
			e.printStackTrace();
		}
		ExtentReports extentrep = new ExtentReports();
		
		File ExtentReportFile = new File(System.getProperty("user.dir")+"\\test-output\\extentReport.html");
		ExtentSparkReporter sparkReport = new ExtentSparkReporter(ExtentReportFile);
		
		sparkReport.config().setTheme(Theme.DARK);
		sparkReport.config().setReportName("TutorialsNinja Website Report" );
		sparkReport.config().setDocumentTitle("TurorialsNinja Report");
		sparkReport.config().setTimeStampFormat("dd/mm/yyyy hh:mm:ss");
		
		extentrep.attachReporter(sparkReport);
		extentrep.setSystemInfo("Application URL", config.getProperty("url"));
		extentrep.setSystemInfo("Browser Name", config.getProperty("browserName"));
		extentrep.setSystemInfo("Java Version", System.getProperty("java.version"));
		extentrep.setSystemInfo("Operating System", System.getProperty("os.name"));
		
		return extentrep;
	}

	
}
