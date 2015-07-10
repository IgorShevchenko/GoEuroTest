package com.goeuro.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Application configuration
 */
public class Config {

	private static final Properties config = new Properties();
	
	/*
	 * Configuration keys 
	 */
	
	public static final String KEY_APP_ENABLED     = "app.enabled";
	public static final String KEY_API_ENDPOINT    = "api.endpoint";
	public static final String KEY_API_RETRY_COUNT = "api.retrycount";
	public static final String KEY_OUTPUT_APPEND   = "output.append";
	public static final String KEY_OUTPUT_FILENAME = "output.filename";
	public static final String KEY_OUTPUT_DELIM    = "output.delimiter";
	public static final String KEY_OUTPUT_NEWLINE  = "output.newline";
	public static final String KEY_HEADER_ID       = "header.id";
	public static final String KEY_HEADER_NAME     = "header.name";
	public static final String KEY_HEADER_TYPE     = "header.type";
	public static final String KEY_HEADER_LAT      = "header.latitude";
	public static final String KEY_HEADER_LON      = "header.longitude";
	
	/*
	 * Configuration default values
	 */
	
	public static final boolean DEFAULT_APP_ENABLED     = true;
	public static final String  DEFAULT_API_ENDPOINT    = "http://api.goeuro.com/api/v2/position/suggest/en/";
	public static final int     DEFAULT_API_RETRY_COUNT = 3;
	public static final boolean DEFAULT_OUTPUT_APPEND   = false;
	public static final String  DEFAULT_OUTPUT_FILENAME = "output.csv";
	public static final String  DEFAULT_OUTPUT_DELIM    = ",";
	public static final String  DEFAULT_OUTPUT_NEWLINE  = "\n";
	public static final String  DEFAULT_HEADER_ID       = "ID";
	public static final String  DEFAULT_HEADER_NAME     = "NAME";
	public static final String  DEFAULT_HEADER_TYPE     = "TYPE";
	public static final String  DEFAULT_HEADER_LAT      = "LATITUDE";
	public static final String  DEFAULT_HEADER_LON      = "LONGITUDE";

	/*
	 * Set DEFAULT configuration values
	 */
	
	static {			
		config.setProperty(KEY_API_ENDPOINT, DEFAULT_API_ENDPOINT);
		config.setProperty(KEY_HEADER_ID, DEFAULT_HEADER_ID);
		config.setProperty(KEY_HEADER_NAME, DEFAULT_HEADER_NAME);
		config.setProperty(KEY_HEADER_TYPE, DEFAULT_HEADER_TYPE);
		config.setProperty(KEY_HEADER_LAT, DEFAULT_HEADER_LAT);
		config.setProperty(KEY_HEADER_LON, DEFAULT_HEADER_LON);
		config.setProperty(KEY_OUTPUT_DELIM, DEFAULT_OUTPUT_DELIM);
		config.setProperty(KEY_OUTPUT_NEWLINE, DEFAULT_OUTPUT_NEWLINE);
		config.setProperty(KEY_OUTPUT_FILENAME, DEFAULT_OUTPUT_FILENAME);
		config.setProperty(KEY_APP_ENABLED, String.valueOf(DEFAULT_APP_ENABLED));		
		config.setProperty(KEY_API_RETRY_COUNT, String.valueOf(DEFAULT_API_RETRY_COUNT));
		config.setProperty(KEY_OUTPUT_APPEND, String.valueOf(DEFAULT_OUTPUT_APPEND));
	}
	
	/**
	 * No need to instantiate
	 */
	private Config() {
	
	}
	
	/**
	 * Get integer value stored by the specified configuration key.
	 * Use specified default value if the stored value cannot be parsed to the integer.
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static int getInteger(String key, int defaultValue) {
		try {
			return new Integer(Config.getProperty(key));
		} catch (NumberFormatException e) {
			System.out.printf("Key '%s' cannot be parsed to integer. Switching to the default value.", key);
			System.out.println();
			return defaultValue;
		}		
	}
	
	/**
	 * Get boolean value stored by the specified configuration key.
	 * Use specified default value if the stored value cannot be parsed to the boolean.
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static boolean getBoolean(String key, boolean defaultValue) {
		try {
			return new Boolean(Config.getProperty(key));
		} catch (NumberFormatException e) {
			System.out.printf("Key '%s' cannot be parsed to boolean. Switching to the default value.", key);
			System.out.println();
			return defaultValue;
		}		
	}
	
	/**
	 * Searches for the property with the specified key.
	 * @param key The key to look for.
	 * @return The value for the key. Returns null if the property is not found.
	 */
	public static String getProperty(String key) {
		return config.getProperty(key);
	}
	
	/**
	 * Searches for the property with the specified key.
	 * The method returns the default value argument if the property is not found.
	 * @param key The key to look for.
	 * @param defaultValue The default value.
	 * @return The value for the key or the default value.
	 */
	public static String getProperty(String key, String defaultValue) {
		return config.getProperty(key, defaultValue);
	}
	
	/**
	 * Loads parameters from the given configuration file
	 * @param configFileName The path to the configuration file
	 */
	public static void initialize(String configFileName) {
		
		if (StringUtils.isEmpty(configFileName)) {
			System.out.println("Cofiguration filename is null or empty.");
			return;
		}	
		
		// 1. Read parameters from the configuration file.
		// 2. Or create new default configuration file is such does not exist.
		
		final File configFile = new File(configFileName);		
		if (!configFile.exists()) {
			try {
				config.store(new FileOutputStream(configFile), "GoEuro configuration file");
			} catch (IOException e) {
				System.out.println("IOException while creating default configuration file: " + configFileName);
			}
		} else {
			try {
				config.load(new FileInputStream(configFile));
			} catch (IOException e) {
				System.out.println("IOException while reading the configuration file: " + configFileName);
			}
		}
	}
}
