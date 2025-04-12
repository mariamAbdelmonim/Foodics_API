package framework;

import config.ConfigurationManager;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeSuite;

import static constants.ApiConstants.*;

/**
 * Base test class for API test automation framework
 * Initializes common REST Assured configurations and request specifications
 */
public class BaseApiTest {

    /**
     * Pre-configured request specification shared across all test classes
     * Includes:
     * - Common headers (Content-Type: application/json)
     * - Request/response logging filters
     */
    protected static RequestSpecification requestSpec;

    /**
     * Configures base REST Assured settings before test execution
     * - Sets base URI from configuration
     * - Enables request/response logging for failed tests
     * - Initializes default request specification
     */
    @BeforeSuite(alwaysRun = true)
    public void initializeRestAssuredConfig() {
        // Load and apply base configuration
        applyBaseConfiguration();

        // Initialize request specification
        initializeRequestSpecification();
    }

    private void applyBaseConfiguration() {
        RestAssured.baseURI = ConfigurationManager.getBaseUri();
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    private void initializeRequestSpecification() {
        requestSpec = new RequestSpecBuilder()
                .addHeader(CONTENT_TYPE_HEADER, APPLICATION_JSON_VALUE)
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .build();
    }
}