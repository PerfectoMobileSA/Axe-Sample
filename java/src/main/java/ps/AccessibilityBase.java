package ps;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.deque.html.axecore.results.*;
import com.deque.html.axecore.results.Results;
import com.deque.html.axecore.selenium.AxeBuilder;
import com.deque.html.axecore.selenium.AxeReporter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class AccessibilityBase extends BaseDriver {
	private static List<String> tags = Arrays.asList("wcag2a", "wcag2aa", "wcag21aa");

	public static void checkAccessibilityViolations() throws IOException {
		WebDriver webDriver = webDriver();
		AxeBuilder builder = new AxeBuilder();
		builder.withTags(tags);
		Results results = builder.analyze(webDriver);
		saveReport(results, getReportPath());
	}

	public static String getReportPath() {
		Path path = Paths.get(System.getProperty("user.dir"), "target", "accessibilityReport");
		return path.toString();
	}

	public static void checkAccessibilityViolationsOfSelector(String selector) throws FileNotFoundException {
		WebDriver webDriver = webDriver();
		AxeBuilder builder = new AxeBuilder();
		builder.withTags(tags);
		Results results = builder.include(Collections.singletonList(selector)).analyze(webDriver);
		saveReport(results, getReportPath());
		System.out.println("A11y test report saved to: " + getReportPath());
	}

	public static void saveReport(Results results, String reportFile) throws FileNotFoundException {
		List<Rule> violations = results.getViolations();
		if (violations.size() == 0) {
			Assert.assertTrue(true, "No violations found");
		} else {
			JsonParser jsonParser = new JsonParser();
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			AxeReporter.writeResultsToJsonFile(reportFile, results);
			JsonElement jsonElement = jsonParser.parse(new FileReader(reportFile + ".json"));
			String prettyJson = gson.toJson(jsonElement);
			AxeReporter.writeResultsToTextFile(reportFile, prettyJson);
			Assert.assertEquals(violations.size(), 0, violations.size() + " violations found");
		}
	}
}
