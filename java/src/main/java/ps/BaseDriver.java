package ps;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import com.perfecto.reportium.client.ReportiumClient;
import com.perfecto.reportium.exception.ReportiumException;
import com.perfecto.reportium.test.result.TestResultFactory;

public class BaseDriver {
	protected static RemoteWebDriver driver;
	protected ReportiumClient reportiumClient;

	@BeforeClass
	public void beforeClass(ITestContext context) throws Exception {

		// Replace <<cloud name>> with your perfecto cloud name (e.g. demo) or pass it
		// as gradle properties: -PcloudName=<<cloud name>>
		String cloudName = "<<cloud name>>";
		// Replace <<security token>> with your perfecto security token or pass it as
		// gradle properties: -PsecurityToken=<<SECURITY TOKEN>> More info:
		// https://developers.perfectomobile.com/display/PD/Generate+security+tokens
		String securityToken = "<<security token>>";

		System.out.println("Run started");
		String platform = context.getCurrentXmlTest().getParameter("platform");
		DesiredCapabilities capabilities = new DesiredCapabilities();
		if (platform.equalsIgnoreCase("Windows")) {
			capabilities.setCapability("platformName", "Windows");
			capabilities.setCapability("platformVersion", "11");
			capabilities.setCapability("browserName", "Chrome");
			capabilities.setCapability("browserVersion", "latest");
		}  else if (platform.equalsIgnoreCase("Mac")) {
			capabilities.setCapability("platformName", "Mac");
			capabilities.setCapability("platformVersion", "macOS Big Sur");
			capabilities.setCapability("browserName", "Chrome");
			capabilities.setCapability("browserVersion", "beta");
			capabilities.setCapability("location", "NA-US-BOS");
			capabilities.setCapability("resolution", "1920x1080");
		} else if (platform.equalsIgnoreCase("iOS")) {
//			TODO: Axe doesn't support mobile web
//			capabilities.setCapability("model", "iPhone.*");
		} else if (platform.equalsIgnoreCase("Android")) {
//			TODO: Axe doesn't support mobile web
//			capabilities.setCapability("model", "Galaxy.*");
		}
		capabilities.setCapability("securityToken", Utils.fetchSecurityToken(securityToken));

		try {
			System.out.println(Utils.fetchCloudName(cloudName));
			driver = new RemoteWebDriver(new URL("https://" + Utils.fetchCloudName(cloudName)
					+ ".perfectomobile.com/nexperience/perfectomobile/wd/hub"), capabilities);
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

			reportiumClient = Utils.setReportiumClient((RemoteWebDriver) driver, reportiumClient); // Creates
																									// reportiumClient
		} catch (SessionNotCreatedException e) {
			throw new RuntimeException("Driver not created with capabilities: " + capabilities.toString());
		}
	}

	@AfterMethod(alwaysRun = true)
	public void afterTest(ITestResult testResult) {
		int status = testResult.getStatus();
		switch (status) {
		case ITestResult.FAILURE:
			reportiumClient.testStop(TestResultFactory.createFailure("An error occurred", testResult.getThrowable()));
			break;
		case ITestResult.SUCCESS_PERCENTAGE_FAILURE:
		case ITestResult.SUCCESS:
			reportiumClient.testStop(TestResultFactory.createSuccess());
			break;
		case ITestResult.SKIP:
			// Ignore
			break;
		default:
			throw new ReportiumException("Unexpected status: " + status);
		}
	}

	@AfterClass
	public void afterClass() {
		try {
			System.out.println(reportiumClient.getReportUrl());
			driver.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (driver != null) {
			driver.quit();
		}
		System.out.println("Run ended");
	}

	public static WebDriver webDriver() {
		return driver;
	}
}
