# REST API Automation Framework

A portfolio-ready API test automation project built with **Java 17, REST Assured, TestNG, Maven, and GitHub Actions**. The sample suite targets [JSONPlaceholder](https://jsonplaceholder.typicode.com/), a public fake REST API intended for testing and prototyping.

## Tech stack

- Java 17
- REST Assured for HTTP requests and response assertions
- TestNG for test organization and execution
- Maven for dependency and build management
- GitHub Actions for CI on pushes and pull requests

## Project structure

```text
RestAPIFramework/
├── .github/workflows/api-tests.yml
├── src/
│   ├── test/java/com/khushbu/api/client/ApiClient.java
│   └── test/java/com/khushbu/api/tests/PostsApiTest.java
├── .gitignore
├── pom.xml
└── README.md
```

## Prerequisites

- JDK 17+
- Maven 3.9+ (or use an IDE with Maven support)
- Internet access to reach JSONPlaceholder

Check your setup:

```bash
java -version
mvn -version
```

## Run the tests

```bash
git clone https://github.com/khushbu0904/RestAPIFramework.git
cd RestAPIFramework
mvn clean test
```

To target another compatible API base URL, pass a system property:

```bash
mvn clean test -DbaseUrl=https://jsonplaceholder.typicode.com
```

The default base URL is `https://jsonplaceholder.typicode.com`. The suite does not require credentials or secrets.

## Current test coverage

| Scenario | What is asserted |
|---|---|
| Get a post by ID | HTTP 200, expected ID, title and body fields |
| Filter posts by user | HTTP 200, non-empty array, each returned post belongs to user 1 |
| Create a post | HTTP 201 and returned title, body and user ID |
| Request a missing post | HTTP 404 |

JSONPlaceholder simulates writes rather than persisting them. The create-post test validates the API's simulated response; it does **not** claim that a post was saved permanently.

## Continuous integration

GitHub Actions runs `mvn --batch-mode --no-transfer-progress clean test` on pushes and pull requests to `main`, and supports manual runs. View the **Actions** tab in this repository for the actual workflow result after GitHub runs it.

## Design notes

- `ApiClient` centralizes the base URL and common request specification.
- Test methods are independent and use response assertions rather than relying on execution order.
- The base URL can be overridden for a compatible test environment.
- No credentials, personal data, or external paid service is required.

## Limitations and next improvements

This is a focused starter framework, not a claim of production coverage. Potential next steps include JSON Schema validation, richer reporting, environment-specific configuration, contract testing, and authenticated API examples against a controlled test service.

**Verification note:** A successful commit means the source files were uploaded, not that the Java tests passed. Check the GitHub Actions run for execution evidence; run `mvn clean test` locally to verify in your environment.
