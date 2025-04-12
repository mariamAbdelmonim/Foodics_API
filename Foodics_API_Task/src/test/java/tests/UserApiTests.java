package tests;

import constants.ApiConstants;
import framework.BaseApiTest;
import models.UserPayload;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

/**
 * Test suite for User API endpoints
 * Covers CRUD operations and error scenarios for user management
 */
public class UserApiTests extends BaseApiTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * TC001: Verify successful user creation with valid payload
     *
     * Test Steps:
     * 1. Prepare valid user creation payload
     * 2. Send POST request to /api/users endpoint
     * 3. Verify:
     *    - HTTP 201 status code
     *    - Response contains generated user ID
     *    - Returned user details match request payload
     *
     * Expected Result: API should create user and return details with 201 status
     */
    @Test(priority = 1)
    public void createUser_WithValidPayload_ShouldReturnCreated() throws JsonProcessingException {
        // Arrange
        UserPayload requestPayload = new UserPayload(TestData.DEFAULT_USER_NAME, TestData.DEFAULT_USER_JOB_TITLE);
        String requestBody = objectMapper.writeValueAsString(requestPayload);

        // Act
        Response response = given()
                .spec(requestSpec)
                .body(requestBody)
                .post(ApiConstants.USER_API_ENDPOINT);

        // Extract and store created user ID for subsequent tests
        String createdUserId = response.then().extract().path("id");
        TestData.setCreatedUserId(createdUserId);

        logRequestResponseDetails(requestBody, response);

        // Assert
        Assert.assertEquals(response.getStatusCode(), 201, "Unexpected status code");
        Assert.assertNotNull(createdUserId, "User ID should not be null");
        Assert.assertEquals(response.jsonPath().getString("name"),
                requestPayload.getName(), "Name mismatch");
        Assert.assertEquals(response.jsonPath().getString("job"),
                requestPayload.getJob(), "Job title mismatch");
    }

    /**
     * TC002: Verify error response for invalid user creation
     *
     * Test Steps:
     * 1. Prepare invalid payload with empty required fields
     * 2. Send POST request to /api/users endpoint
     * 3. Verify:
     *    - HTTP 400 status code
     *
     * Expected Result: API should reject invalid payload with proper error status
     */
    @Test(priority = 2)
    public void createUser_WithEmptyPayload_ShouldReturnBadRequest() throws JsonProcessingException {
        // Arrange
        UserPayload invalidPayload = new UserPayload("", "");
        String requestBody = objectMapper.writeValueAsString(invalidPayload);

        // Act
        Response response = given()
                .spec(requestSpec)
                .body(requestBody)
                .post(ApiConstants.USER_API_ENDPOINT);

        logRequestResponseDetails(requestBody, response);

        // Assert
        Assert.assertEquals(response.getStatusCode(), 400,
                "API should reject empty required fields");
    }

    /**
     * TC003: Verify successful retrieval of user details
     *
     * Test Steps:
     * 1. Send GET request to /api/users/{id} with valid user ID
     * 2. Verify:
     *    - HTTP 200 status code
     *    - Response contains expected user details
     *
     * Expected Result: API should return user details with 200 status
     */
    @Test(priority = 3)
    public void getUser_WithValidId_ShouldReturnUserDetails() {
        // Act
        Response response = RestAssured
                .get(ApiConstants.USER_API_ENDPOINT + "/" + TestData.PREEXISTING_USER_ID);

        logResponseDetails(response);

        // Assert
        Assert.assertEquals(response.getStatusCode(), 200, "User not found");
        Assert.assertEquals(response.jsonPath().get("data.id").toString(),
                "2", "User ID mismatch");
        Assert.assertEquals(response.jsonPath().getString("data.first_name"),
                "Janet", "First name mismatch");
    }

    /**
     * TC004: Verify error response for non-existent user
     *
     * Test Steps:
     * 1. Send GET request to /api/users/{id} with invalid user ID
     * 2. Verify:
     *    - HTTP 404 status code
     *
     * Expected Result: API should return not found status for invalid user ID
     */
    @Test(priority = 4)
    public void getUser_WithNonExistentId_ShouldReturnNotFound() {
        // Act
        Response response = RestAssured.get("/api/users/200");

        logResponseDetails(response);

        // Assert
        Assert.assertEquals(response.getStatusCode(), 404,
                "API should return not found for invalid user ID");
    }

    /**
     * TC005: Verify successful user details update
     *
     * Test Steps:
     * 1. Prepare updated user details payload
     * 2. Send PUT request to /api/users/{id} endpoint
     * 3. Verify:
     *    - HTTP 200 status code
     *    - Response contains updated user details
     *
     * Expected Result: API should update and return user details with 200 status
     */
    @Test(priority = 5)
    public void updateUser_WithValidPayload_ShouldReturnUpdatedDetails()
            throws JsonProcessingException {
        // Arrange
        UserPayload updatePayload = new UserPayload(
                TestData.UPDATED_USER_NAME,
                TestData.UPDATED_USER_JOB
        );
        String requestBody = objectMapper.writeValueAsString(updatePayload);

        // Act
        Response response = given()
                .spec(requestSpec)
                .body(requestBody)
                .put(ApiConstants.USER_API_ENDPOINT + "/" + TestData.getCreatedUserId());

        logRequestResponseDetails(requestBody, response);

        // Assert
        Assert.assertEquals(response.getStatusCode(), 200, "Update failed");
        Assert.assertEquals(response.jsonPath().getString("name"),
                updatePayload.getName(), "Name not updated");
        Assert.assertEquals(response.jsonPath().getString("job"),
                updatePayload.getJob(), "Job title not updated");
    }

    /**
     * Helper method to log request and response details
     * @param requestBody The request payload sent to API
     * @param response The API response object
     */
    private void logRequestResponseDetails(String requestBody, Response response) {
        System.out.println("=== Request ===");
        System.out.println(requestBody);
        System.out.println("=== Response ===");
        System.out.println(response.prettyPrint());
    }

    /**
     * Helper method to log response details
     * @param response The API response object
     */
    private void logResponseDetails(Response response) {
        System.out.println("=== Response ===");
        System.out.println(response.prettyPrint());
    }
}