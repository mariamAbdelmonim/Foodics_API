# User API Automation Framework

## 📌 Overview
This framework provides a comprehensive solution for automated testing of User API endpoints using REST Assured and TestNG. It covers CRUD operations and various edge cases for robust API testing.

## 🛠️ Technical Stack
- **Testing Framework**: TestNG
- **API Testing**: REST Assured
- **Build Tool**: Maven
- **JSON Processing**: Jackson
- **Logging**: REST Assured filters

## 📂 Project Structure
```
src/
├── main/
│   ├── java/
│   │   ├── config/            # Configuration management
│   │   ├── constants/         # API constants
│   │   ├── framework/         # Base test classes
│   │   ├── models/            # Data models
│   │   └── utils/             # Utility classes
│   └── resources/             # Configuration files
└── test/
    └── java/
        └── tests/             # Test implementations
```

## 🔧 Setup Instructions

### Prerequisites
- Java JDK 8+
- Maven 3.6+
- IDE (IntelliJ IDEA or Eclipse recommended)

### Installation
1. Clone the repository
   ```bash
   git clone [repository-url]
   ```
2. Navigate to project directory
   ```bash
   cd api-automation-framework
   ```
3. Build the project
   ```bash
   mvn clean install
   ```

## ⚙️ Configuration
Edit `src/main/resources/config.properties` to configure:
```properties
baseURI=https://reqres.in
timeout=20000
```

## 🧪 Test Cases Implemented
1. **User Creation**
    - Valid payload (TC001)
    - Empty payload (TC002)

2. **User Retrieval**
    - Existing user (TC003)
    - Non-existent user (TC004)

3. **User Update** (TC005)

## 🚀 Running Tests
Execute all tests:
```bash
mvn test
```

Run specific test group:
```bash
mvn test -Dgroups=smoke
```

## 📊 Reporting
TestNG generates reports in:
```
target/surefire-reports/
```

## 🧩 Key Components

### Core Classes
- **`BaseApiTest`**: Base class with common REST Assured configuration
- **`UserApiTests`**: Implementation of all test scenarios
- **`ConfigurationManager`**: Handles configuration properties
- **`UserPayload`**: Model class for user data

### Test Data Management
- **`TestData`**: Centralized test data storage
  ```java
  public static final String DEFAULT_USER_NAME = "Mariam Abd Elmoneim Elsaid";
  public static final String DEFAULT_USER_JOB_TITLE = "Software Tester";
  ```

## 💡 Best Practices
- **Page Object Pattern**: Used for API endpoint organization
- **Data-Driven**: Externalized test data management
- **Modular Design**: Reusable components and utilities
- **Comprehensive Logging**: Request/response logging for debugging

## 🐛 Troubleshooting
If tests fail:
1. Verify API is accessible at configured `baseURI`
2. Check network connectivity
3. Review logs in console and test reports

