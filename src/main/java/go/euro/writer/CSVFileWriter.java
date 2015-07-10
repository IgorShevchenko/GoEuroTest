package go.euro.writer;

import go.euro.data.Location;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.goeuro.test.Config;
import com.goeuro.test.StringUtils;

/**
 * CSV file writer for saving GoEuro information.
 */
public class CSVFileWriter implements GoEuroFileWriter {
	
	/**
	 * Save the given list of locations to the specified file using CSV format.
	 * File will be created if necessary.
	 * @param locations List of locations to save.
	 * @param fileName Name/Path of the output file.
	 * @param append If set to true, entries will be appended to the existing file.
	 * @throws IOException
	 */
	public void save(List<Location> locations, String fileName, boolean append) 
			throws IOException {
		
		if (locations == null) {
			throw new NullPointerException("Cannot save NULL list.");
		}
		
		if (StringUtils.isEmpty(fileName)) {
			throw new IllegalArgumentException("Output filename is null or empty.");
		}
				
		boolean insertHeaders = false;
		String newline = Config.getProperty(Config.KEY_OUTPUT_NEWLINE);
		String delimiter = Config.getProperty(Config.KEY_OUTPUT_DELIM);
		File file = new File(fileName);
						 				
		// File did not exist before. Need to insert headers
		if (!file.exists()) {
			insertHeaders = true;
		}

		FileWriter fw = new FileWriter(file, append);
		BufferedWriter bw = new BufferedWriter(fw);
			
		// Insert headers: 1) it is a new file or 2) overwritten file
		if (insertHeaders || !append) {
			bw.write(generateHeaderLine(delimiter));	
		}
			
		for (Location loc : locations) {
			bw.append(newline);
			bw.append(generateEntryLine(delimiter, loc));
		}			
			
		bw.close();
	}
	
	/**
	 * Generate headers line using specified delimiter to distinguish columns.
	 * @param delimiter Delimiter between columns (e.g. ',').
	 * @return
	 */
	private String generateHeaderLine(String delimiter) {
		
		StringBuilder header = new StringBuilder();
		
		header.append(Config.getProperty(Config.KEY_HEADER_ID));
		header.append(delimiter);
		
		header.append(Config.getProperty(Config.KEY_HEADER_NAME));
		header.append(delimiter);
		
		header.append(Config.getProperty(Config.KEY_HEADER_TYPE));
		header.append(delimiter);
		
		header.append(Config.getProperty(Config.KEY_HEADER_LAT));
		header.append(delimiter);
		
		header.append(Config.getProperty(Config.KEY_HEADER_LON));
		
		return header.toString();
	}
	
	/**
	 * Generate data entry line for the given location object.
	 * @param delimiter Delimiter between columns (e.g. ',').
	 * @param location Location to use.
	 * @return
	 */
	private String generateEntryLine(String delimiter, Location location) {
		
		if (location == null) {
			throw new NullPointerException("Cannot generate CSV output for a NULL object.");
		}
		
		StringBuilder entry = new StringBuilder();
		
		entry.append(location.get_id());
		entry.append(delimiter);
		
		entry.append(location.getName());
		entry.append(delimiter);
		
		entry.append(location.getType());
		entry.append(delimiter);
		
		if (location.getGeo_position() == null) {
			entry.append(delimiter);
		} else {
			entry.append(location.getGeo_position().getLatitude());
			entry.append(delimiter);
		
			entry.append(location.getGeo_position().getLongitude());
		}
		
		return entry.toString();
	}	
	
}
