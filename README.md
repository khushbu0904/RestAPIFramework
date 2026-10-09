# REST API Automation Framework

This repository contains a simple API test suite written in Java with REST Assured and TestNG. It uses [JSONPlaceholder](https://jsonplaceholder.typicode.com/), a public demo API, so you can try the tests without setting up your own service or configuring credentials.

## Tools used

- Java 17
- REST Assured
- TestNG
- Maven
- GitHub Actions

## What the tests cover

The current suite checks four basic scenarios:

- **Get a post:** checks the status code and a few important response fields.
- **Filter posts by user:** checks that the API returns posts for the requested user ID.
- **Create a post:** checks the status code and fields returned by the API.
- **Missing post:** checks that requesting a post that doesn’t exist returns 404.

JSONPlaceholder is a demo service, so the create-post request returns a simulated response rather than permanently saving a post.

## Project structure

```text
RestAPIFramework/
├── .github/workflows/api-tests.yml
├── src/test/java/com/khushbu/api/
│   ├── client/ApiClient.java
│   └── tests/PostsApiTest.java
├── pom.xml
├── .gitignore
└── README.md
```

The shared request setup lives in `ApiClient`. The individual test cases are in `PostsApiTest`.

## Running the tests

You’ll need JDK 17 or later, Maven, and an internet connection.

Clone the repository:

```bash
git clone https://github.com/khushbu0904/RestAPIFramework.git
cd RestAPIFramework
```

Run the suite:

```bash
mvn clean test
```

The default base URL is `https://jsonplaceholder.typicode.com`. You can override it for another compatible test environment:

```bash
mvn clean test -DbaseUrl=https://jsonplaceholder.typicode.com
```

## GitHub Actions

The workflow in `.github/workflows/api-tests.yml` sets up Java 17 and runs the Maven tests on pushes to `main`, pull requests targeting `main`, and manual runs. It also uploads the Surefire reports when they are available.

Open the **Actions** tab to check the result of a run.

## Possible next steps

Some useful additions would be JSON schema validation, more detailed test reports, and examples for authenticated APIs.

**Test status:** The files are committed to the repository, but the test suite has not been independently run in this session. Check the latest GitHub Actions run for the actual result.
