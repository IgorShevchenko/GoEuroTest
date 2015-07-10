package go.euro.reader;

import go.euro.data.GeoPosition;
import go.euro.data.Location;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import com.goeuro.test.Config;
import com.goeuro.test.StringUtils;

/**
 * Class for reading GoEuro information from the CSV file. 
 */
public class CSVFileReader implements GoEuroFileReader {

	/**
	 * Read all locations from the the specified CSV file.
	 * @param fileName Name/path of the file to read.
	 * @return List of locations.
	 * @throws IOException
	 */
	public List<Location> readLocations(String fileName) throws IOException {
		
		if (StringUtils.isEmpty(fileName)) {
			throw new IllegalArgumentException("Input filename is null or empty.");
		}
		
		String delimiter = Config.getProperty(Config.KEY_OUTPUT_DELIM);	
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		List<Location> locations = new LinkedList<Location>();
		
		// Skip first line: headers
		String line = br.readLine();
	    while ((line = br.readLine()) != null) {
	        
	    	// Parse each line into location object
	    	String[] columns = line.split(delimiter);
	    	Location loc = parseLocation(columns);
	    	if (loc != null) {
	    		locations.add(loc);
	    	}
	    }
	    br.close();
		
		return locations;
	}
	
	/**
	 * Generate location object from the given array of columns.
	 * @param columns Array of ordered columns representing a location.
	 * @return NULL, if array-of-columns has less than 1 column. 
	 */
	private Location parseLocation(String[] columns) {
		
		if (columns == null || columns.length == 0) {
			return null;
		}
		
		// ID
		String id = columns[0];
		Location loc = new Location(id);
		
		// NAME
		if (columns.length > 1) {
			loc.setName(columns[1]);
		}
		
		// TYPE
		if (columns.length > 2) {
			loc.setType(columns[2]);
		}
		
		// GeoPosition: LATITUDE + LONGITUDE
		if (columns.length > 4) {
			loc.setGeo_position(new GeoPosition(columns[3], columns[4]));
		}
		
		return loc;
	}

}
