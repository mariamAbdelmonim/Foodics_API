package tests;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeSuite;
import utils.ConfigReader;

public class BaseTest {

    /**
     * Setup method to initialize the base URI before the suite starts.
     * This method will be called before any test cases are run.
     */
    @BeforeSuite
    public void setup() {
        // Set the base URI for all the RestAssured API requests
        RestAssured.baseURI = ConfigReader.getProperty("baseURI");
    }
}
