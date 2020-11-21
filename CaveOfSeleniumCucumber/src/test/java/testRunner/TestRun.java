package testRunner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions
		(features={".//Features/Customer.feature"}, glue="stepDefinitions",
		dryRun=false,
		monochrome=true,
		//plugin = {"json:target/cucumber.json"})
		//plugin = {"pretty", "html:target/HtmlReports"})
		plugin = {"pretty", "html:test-output"},
		tags = {"@sanity, @regression"}
		
				)

public class TestRun {

}
