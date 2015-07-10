package go.euro.writer;

import go.euro.data.Location;

import java.io.IOException;
import java.util.List;

/**
 * Interface for saving GoEuro information to the file. 
 */
public interface GoEuroFileWriter {

	/**
	 * Save the given list of locations to the specified file.
	 * File will be created if necessary.
	 * @param locations List of locations to save.
	 * @param fileName Name/Path of the output file.
	 * @param append If set to true, entries will be appended to the existing file.
	 * @throws IOException
	 */
	public void save(List<Location> locations, String fileName, boolean append) throws IOException;
}
