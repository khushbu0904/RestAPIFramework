package com.khushbu.api.client;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import static io.restassured.http.ContentType.JSON;

/**
 * Shared request configuration for API tests.
 */
public final class ApiClient {
    private static final String DEFAULT_BASE_URL = "https://jsonplaceholder.typicode.com";

    private ApiClient() {
        // Utility class.
    }

    public static RequestSpecification requestSpec() {
        String baseUrl = System.getProperty("baseUrl", DEFAULT_BASE_URL);
        return new RequestSpecBuilder()
                .setBaseUri(baseUrl)
                .setContentType(JSON)
                .setAccept(JSON)
                .build();
    }
}
