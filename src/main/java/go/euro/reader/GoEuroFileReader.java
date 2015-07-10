package go.euro.reader;

import go.euro.data.Location;

import java.io.IOException;
import java.util.List;

/**
 * Interface for reading GoEuro information from the file. 
 */
public interface GoEuroFileReader {

	/**
	 * Read all locations from the the specified file.
	 * @param fileName Name/path of the file to read.
	 * @return List of locations.
	 * @throws IOException
	 */
	public List<Location> readLocations(String fileName) throws IOException;
}
