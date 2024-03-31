package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/test/java/cucumber",glue="masudeokarwork.stepDefinations",
monochrome=true,plugin= {"html:target/cucumber.html"})
public class TestNGTestRunner extends AbstractTestNGCucumberTests {

}

//@CucumberOptions(features="src/test/java/cucumber",glue="masudeokarwork.stepDefinations",
//monochrome=true,tags ="@ErrorValidation",plugin= {"html:target/cucumber.html"})
//public class TestNGTestRunner extends AbstractTestNGCucumberTests {
//
//} 
// use for to run specific test by using "tags" above method.