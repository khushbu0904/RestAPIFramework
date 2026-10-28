package com.khushbu.automation.data;

public record LoginData(String username, String password, String expectedOutcome) {
    public LoginData {
        if (username == null || password == null || expectedOutcome == null)
            throw new IllegalArgumentException("Login data fields must not be null");
    }
}