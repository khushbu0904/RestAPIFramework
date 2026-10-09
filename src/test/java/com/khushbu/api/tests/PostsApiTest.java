package com.khushbu.api.tests;

import com.khushbu.api.client.ApiClient;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PostsApiTest {

    @Test(description = "Fetch a post by ID and validate its key fields")
    public void getPostByIdReturnsExpectedPost() {
        given()
                .spec(ApiClient.requestSpec())
        .when()
                .get("/posts/1")
        .then()
                .statusCode(200)
                .contentType(containsString("application/json"))
                .body("id", equalTo(1))
                .body("userId", equalTo(1))
                .body("title", not(emptyOrNullString()))
                .body("body", not(emptyOrNullString()));
    }

    @Test(description = "Filter posts by user ID")
    public void filterPostsByUserIdReturnsOnlyMatchingPosts() {
        given()
                .spec(ApiClient.requestSpec())
                .queryParam("userId", 1)
        .when()
                .get("/posts")
        .then()
                .statusCode(200)
                .body("size()", greaterThan(0))
                .body("userId", everyItem(equalTo(1)));
    }

    @Test(description = "Create a post and validate the simulated response")
    public void createPostReturnsCreatedPayload() {
        String payload = """
                {
                  "title": "REST Assured portfolio test",
                  "body": "Validate create endpoint response",
                  "userId": 1
                }
                """;

        given()
                .spec(ApiClient.requestSpec())
                .body(payload)
        .when()
                .post("/posts")
        .then()
                .statusCode(201)
                .body("title", equalTo("REST Assured portfolio test"))
                .body("body", equalTo("Validate create endpoint response"))
                .body("userId", equalTo(1))
                .body("id", notNullValue());
    }

    @Test(description = "A non-existent post returns HTTP 404")
    public void missingPostReturnsNotFound() {
        Response response = given()
                .spec(ApiClient.requestSpec())
        .when()
                .get("/posts/999999");

        response.then().statusCode(404);
    }
}
