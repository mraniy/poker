package com.ymcraftservices.features;



import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty","html:FeaturesReport.html"},
        features = "src/test/resources",
        glue = "com.ymcraftservices.features"
)
public class PokerIntegrationTest {
}