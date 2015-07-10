package go.euro.reader;

import go.euro.data.Location;
import go.euro.parser.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Collections;
import java.util.List;

import com.goeuro.test.Config;

/**
 * Class for reading GoEuro information from the WEB API. 
 */
public class WebReader {

	/**
	 * Get locations for the given city from the WEB API.
	 * @param city Name of the target city (e.g "Berlin").
	 * @return List of retrieved locations. 
	 * @throws IOException 
	 */
	public List<Location> getLocations(String city) throws IOException {
			
		String endpoint = Config.getProperty(Config.KEY_API_ENDPOINT) + city;
		String json = downloadJson(endpoint);
				
		// No results found for the city
		if (json.length() == 0) {
			return Collections.emptyList();	
        }
		
		List<Location> locations = JsonParser.parseLocations(json);		
		return locations;
	}
	
	/**
	 * Download JSON from the given endpoint URL.
	 * Retry count is defined by the configuration.
	 * @param endpoint URL from which to download JSON.
	 * @return NOT null, or IOException.
	 * @throws IOException 
	 */
	private String downloadJson(String endpoint) throws IOException {
		
		int retryCount = 1;
		int maxRetryCount = Config.getInteger(Config.KEY_API_RETRY_COUNT, Config.DEFAULT_API_RETRY_COUNT);
		
		do {
			try {
			
				System.out.printf("Establishing API connection (attempt %d)...", retryCount++);
				System.out.println();
				
				URL web = new URL(endpoint);				
				BufferedReader br = new BufferedReader(new InputStreamReader(web.openStream()));																
		
	            // Read the whole stream
	            String inputLine;
	            StringBuilder sb = new StringBuilder();
	            while ((inputLine = br.readLine()) != null) {
	                sb.append(inputLine);
	            }
	            br.close();
				
	    		System.out.println("JSON string successfully retrieved.");
	            return sb.toString();
	            			
			} catch (IOException e) {
				if (retryCount > maxRetryCount) {
					throw e;
				}
			}
		} while (true);
	}
}
