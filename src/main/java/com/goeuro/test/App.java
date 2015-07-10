package com.goeuro.test;

import go.euro.data.Location;
import go.euro.reader.WebReader;
import go.euro.writer.CSVFileWriter;
import go.euro.writer.GoEuroFileWriter;

import java.io.IOException;
import java.util.List;

import com.google.gson.JsonSyntaxException;

/**
 * https://github.com/goeuro/dev-test
 * http://api.goeuro.com/api/v2/position/suggest/en/Berlin
 */
public class App {
		
	/**
	 * The default filename of the configuration file.
	 */
	public static final String DEFAULT_CONFIG = "GoEuroTest.config";
	
	/**
	 * Application entry point. 
	 * <p>Usage: java -jar GoEuroTest.jar <CITY_NAME></p>
	 * @param args CITY_NAME (e.g. "Berlin")
	 */
	public static void main(String[] args) {
	
		System.out.println("Starting and reading the configuration file...");
		
		// 1. Read configuration parameters
		Config.initialize(DEFAULT_CONFIG);
		
		// 2. Validate inputed parameters and configuration
		if (!areValidParameters(args)) {
			System.out.println();
			return;
		}
		
		String city = args[0];
		
		// 3. Retrieve and parse Locations from the GoEuro WEB API
		WebReader reader = new WebReader();
		List<Location> locations = null;
		try {
			
			locations = reader.getLocations(city);
			
		} catch (IOException e) {
			System.out.println("WEB request did not succeed. No file is modified. Exiting...");
			System.out.println("Info: " + e.getMessage());
			return;
		} catch (JsonSyntaxException e) {
			System.out.println("Error parsing JSON. No file is modified. Exiting...");
			System.out.println("Info: " + e.getMessage());
			return;
		}
		
		System.out.printf("%d locations found for the city '%s'.", locations.size(), city);
		System.out.println();
				
		// 4. Save locations to the CSV file
		GoEuroFileWriter writer = new CSVFileWriter();		
		String filename = Config.getProperty(Config.KEY_OUTPUT_FILENAME);				
		boolean append = Config.getBoolean(Config.KEY_OUTPUT_APPEND, Config.DEFAULT_OUTPUT_APPEND);					
		try {
			
			writer.save(locations, filename, append);
			System.out.printf("Locations are successfully saved to the file '%s'.", filename);
			
		} catch (IOException e) {
			System.out.println("Locations are not saved due to IO error.");
			System.out.println("Info: " + e.getMessage());
		}
		
		System.out.println();
	}
	
	/**
	 * Verify command line arguments and configuration parameters.
	 * @param args Command line arguments
	 * @return false, if parameters are invalid.
	 */
	private static boolean areValidParameters(String[] args) {
		
		// Check if an application is disabled
		if (!Config.getBoolean(Config.KEY_APP_ENABLED, Config.DEFAULT_APP_ENABLED)) {
			System.out.println("Application is disabled by the configuration file.");
			return false;
		}
				
		// Validate CITY_NAME command line argument
		if (args == null || args.length == 0 || StringUtils.isEmpty(args[0])) {
			System.out.println("Please provide 'CITY_NAME' as a command line argument.");
			System.out.println("Usage: java -jar GoEuroTest.jar <CITY_NAME>");
			return false;
		}
		
		// Validate output file configuration parameter
		String filename = Config.getProperty(Config.KEY_OUTPUT_FILENAME);
		if (StringUtils.isEmpty(filename)) {
			System.out.println("Output filename is null or empty. Verify configuration file.");
			return false;
		}
				
		return true;
	}

}
