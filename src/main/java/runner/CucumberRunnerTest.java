package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
    
@CucumberOptions	(tags = "", 
					features = {"src/main/resources/features/"}, 
					glue = {"stepDefinitions"},
					plugin = { "pretty", "json:target/cucumber-reports/Cucumber.json",
							"html:target/cucumber-reports/report.html"})
    
public class CucumberRunnerTest extends AbstractTestNGCucumberTests {
    
}