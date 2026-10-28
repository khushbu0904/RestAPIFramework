package com.khushbu.automation.data;

import com.khushbu.automation.config.ConfigManager;

public final class TestDataSourceFactory {
    private TestDataSourceFactory() {}
    public static TestDataSource<LoginData> loginDataSource() {
        return switch (ConfigManager.get("data.source", "json").toLowerCase()) {
            case "json" -> new JsonLoginDataSource();
            case "csv" -> new CsvLoginDataSource();
            default -> throw new IllegalArgumentException("Unsupported data.source. Register a source in TestDataSourceFactory.");
        };
    }
}