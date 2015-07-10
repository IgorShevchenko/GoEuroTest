package go.euro.reader;

import go.euro.data.Location;

import java.io.IOException;
import java.util.List;

/**
 * Class for reading GoEuro information from the JSON file. 
 */
public class JsonFileReader implements GoEuroFileReader {

	public List<Location> readLocations(String fileName) throws IOException {
		
		throw new UnsupportedOperationException("JSON file reader is currently not supported.");
	}
}
