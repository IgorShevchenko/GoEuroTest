package go.euro.writer;

import go.euro.data.Location;

import java.io.IOException;
import java.util.List;

/**
 * JSON file writer for saving GoEuro information. 
 */
public class JsonFileWriter implements GoEuroFileWriter {

	public void save(List<Location> locations, String fileName, boolean append)
			throws IOException {
		
		throw new UnsupportedOperationException("JSON file writer is currently not supported.");	
	}

}
