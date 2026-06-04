package com.orangehrm.config;

import java.io.InputStream;
import java.util.Properties;

/**
 * Loads {@code config/config.properties} from the test classpath once and
 * exposes typed getters. Any property can be overridden at runtime by passing
 * a matching {@code -Dkey=value} system property (system property wins).
 */
public final class ConfigReader {

    private static final Properties PROPERTIES = new Properties();

    static {
        try (InputStream in = ConfigReader.class.getClassLoader()
                .getResourceAsStream("config/config.properties")) {
            if (in == null) {
                throw new IllegalStateException("config/config.properties not found on the classpath");
            }
            PROPERTIES.load(in);
        } catch (Exception e) {
            throw new ExceptionInInitializerError("Failed to load config.properties: " + e.getMessage());
        }
    }

    private ConfigReader() {
        // utility class
    }

    public static String get(String key) {
        // System property overrides file value, enabling CI overrides.
        String override = System.getProperty(key);
        if (override != null && !override.isBlank()) {
            return override.trim();
        }
        String value = PROPERTIES.getProperty(key);
        if (value == null) {
            throw new IllegalArgumentException("Missing config key: " + key);
        }
        return value.trim();
    }

    public static String get(String key, String defaultValue) {
        try {
            return get(key);
        } catch (IllegalArgumentException e) {
            return defaultValue;
        }
    }

    public static int getInt(String key) {
        return Integer.parseInt(get(key));
    }

    public static boolean getBoolean(String key) {
        return Boolean.parseBoolean(get(key));
    }

    // Convenience accessors used across the framework
    public static String baseUrl()       { return get("base.url"); }
    public static String adminUsername() { return get("admin.username"); }
    public static String adminPassword() { return get("admin.password"); }
    public static String browser()       { return get("browser").toLowerCase(); }
    public static boolean headless()     { return getBoolean("headless"); }
    public static int explicitWait()     { return getInt("explicit.wait"); }
    public static int pageLoadTimeout()  { return getInt("page.load.timeout"); }
    public static int implicitWait()     { return getInt("implicit.wait"); }
}
