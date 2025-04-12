package tests;

/**
 * Centralized test data management for User API tests
 * Contains all constants and variables required for test execution
 */
public final class TestData {

    // Prevent instantiation
    private TestData() {}

    // User IDs
    private static String createdUserId;
    public static final String PREEXISTING_USER_ID = "2"; // Known existing user in the system

    // User creation data
    public static final String DEFAULT_USER_NAME = "Mariam Abd Elmoneim Elsaid";
    public static final String DEFAULT_USER_JOB_TITLE = "Software Tester";

    // User update data
    public static final String UPDATED_USER_NAME = "Updated Name";
    public static final String UPDATED_USER_JOB = "Updated Job";

    /**
     * Retrieves the ID of the most recently created user
     * @return String representing the user ID
     */
    public static String getCreatedUserId() {
        return createdUserId;
    }

    /**
     * Stores the ID of a newly created user for subsequent test operations
     * @param userId The user ID to store
     */
    public static void setCreatedUserId(String userId) {
        createdUserId = userId;
    }
}