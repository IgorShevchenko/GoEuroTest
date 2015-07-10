package go.euro.writer;

import go.euro.data.FileType;

/**
 * Factory for returning specified file writer.
 */
public class GoEuroFileWriterFactory {

	/**
	 * Not need to instantiate.
	 */
	private GoEuroFileWriterFactory() {

	}
	
	/**
	 * Get file writer corresponding to the specified file type.  
	 * @param fileType Required output file type.
	 * @return File writer of the specified type.
	 */
	public static GoEuroFileWriter getFileWriter(FileType type) {
		
		switch (type) {		
		case CSV:
			return new CSVFileWriter();
		default:
			throw new UnsupportedOperationException(type + " file writer is currently not supported.");
		}		
	}
}
