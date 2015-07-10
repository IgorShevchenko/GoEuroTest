package go.euro.data;

/**
 * Enumeration of the possible output file types. 
 */
public enum FileType {
	CSV  (".csv"),
	JSON (".json"),
	XML  (".xm;");

	/**
	 * File extension (e.g. ".csv") for the output file type. 
	 */
	private String extension;
	
	/**
	 * Initialize file type with a file extension.
	 * @param extension File extension (e.g. ".csv").
	 */
	private FileType(String extension) {
		this.extension = extension;
	}
	
	/**
	 * Get file extension (e.g. ".csv").
	 * @return
	 */
	public String getExtension() {
		return this.extension;
	}
}
