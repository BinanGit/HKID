import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.junit.Cucumber;
import io.cucumber.testng.CucumberOptions;                      //need to be testng
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.restassured.RestAssured;
import org.junit.runner.RunWith;
import org.testng.annotations.BeforeClass;
//cucumber api is old ver, not to use

//@RunWith(Cucumber.class)                                        //run cucumber.class, not used any more

@CucumberOptions(
        features = {"src/test/resources/features"},
        plugin = {
                "pretty",                                       //console pretty
                "html:target/reports/index.html",               //report
                "json:target/reports/cucumber.json"             //report in json
        },
//      monochrome = true,                                    //console no colour, vs pretty
        tags = ("@GenerateHKID_Verify"),                             //only run test with @tag
        glue = {"org.test.stepsdef"}

)

public class TestRunner extends AbstractTestNGCucumberTests {  //use this to replace RunWith
//      The only class will be run by Cucumber, put BeforeClass/ AfterClass here
//        @BeforeClass
//        public void setup() {
//                RestAssured.baseURI = "http://api.openweathermap.org/data/2.5/weather";
//                System.out.println("Before Class...baseURI = " + RestAssured.baseURI);
//
//        }

}
