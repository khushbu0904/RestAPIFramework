package com.khushbu.automation.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ConfigManager {
    private static final Properties PROPERTIES = new Properties();
    static {
        try (InputStream input = ConfigManager.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) throw new IllegalStateException("config.properties not found on test classpath");
            PROPERTIES.load(input);
        } catch (IOException e) { throw new ExceptionInInitializerError(e); }
    }
    private ConfigManager() {}
    public static String get(String key) {
        String value = System.getProperty(key);
        if (value != null && !value.isBlank()) return value;
        value = System.getenv(key.toUpperCase().replace('.', '_'));
        if (value != null && !value.isBlank()) return value;
        return PROPERTIES.getProperty(key);
    }
    public static String get(String key, String fallback) {
        String value = get(key); return value == null || value.isBlank() ? fallback : value;
    }
    public static int getInt(String key, int fallback) {
        try { return Integer.parseInt(get(key)); } catch (RuntimeException e) { return fallback; }
    }
    public static boolean getBoolean(String key, boolean fallback) {
        String value = get(key); return value == null ? fallback : Boolean.parseBoolean(value);
    }
}