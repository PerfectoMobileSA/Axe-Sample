package ps;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import com.perfecto.reportium.test.TestContext;
import com.perfecto.reportium.test.result.TestResultFactory;

public class WebADA_Axe extends BaseDriver {
	AccessibilityBase a11y;

	@Test
	public void GoogleTest() throws Exception {

		reportiumClient.testStart("Demo - ADA validation with Axe", new TestContext("ADA", "axe"));
		reportiumClient.stepStart("step 1: Load Page");
		driver.get("https://www.google.com");


		reportiumClient.stepStart("step 2: Capture Screenshot ");
		((RemoteWebDriver) driver).getScreenshotAs(OutputType.BASE64);

		reportiumClient.stepStart("step 3: Running AXE Framework");
		a11y.checkAccessibilityViolations();
		reportiumClient.testStop(TestResultFactory.createSuccess());
	}
}
