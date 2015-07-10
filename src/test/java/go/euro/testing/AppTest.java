package go.euro.testing;
import static org.junit.Assert.assertEquals;
import go.euro.data.Location;
import go.euro.reader.CSVFileReader;
import go.euro.reader.GoEuroFileReader;
import go.euro.reader.WebReader;
import go.euro.writer.CSVFileWriter;
import go.euro.writer.GoEuroFileWriter;

import java.io.File;
import java.io.IOException;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import com.goeuro.test.App;
import com.goeuro.test.Config;

public class AppTest {

	private boolean outputFileExists() {
		String filename = Config.getProperty(Config.KEY_OUTPUT_FILENAME);
		File f = new File(filename);
		return f.exists();
	}

	@Test
	public void testApp() {

		// Delete default output file
		String filename = Config.getProperty(Config.KEY_OUTPUT_FILENAME);
		File f = new File(filename);
		if (f.exists()) {
			Assert.assertTrue(f.delete());
		}

		// Launch application with different input parameters
		App.main(null);
		Assert.assertFalse(outputFileExists());

		App.main(new String[0]);
		Assert.assertFalse(outputFileExists());

		App.main(new String[] {});
		Assert.assertFalse(outputFileExists());

		App.main(new String[] { null });
		Assert.assertFalse(outputFileExists());

		App.main(new String[] { "" });
		Assert.assertFalse(outputFileExists());
		
		App.main(new String[] { " " });
		Assert.assertFalse(outputFileExists());

		App.main(new String[] { "       " });
		Assert.assertFalse(outputFileExists());
	}

	@Test
	public void testAppend() throws IOException {

		final String city = "Berlin";
		final String filename = "testOutput.csv";

		// Delete test file
		File f = new File(filename);
		if (f.exists()) {
			Assert.assertTrue(f.delete());
		}

		// Download locations
		WebReader webReader = new WebReader();
		List<Location> locations = webReader.getLocations(city);
		assertEquals(true, locations.size() != 0);

		// Store locations to the CSV file
		GoEuroFileWriter writer = new CSVFileWriter();
		writer.save(locations, filename, false);

		// Verify stored locations
		GoEuroFileReader reader = new CSVFileReader();
		List<Location> locations2 = reader.readLocations(filename);
		assertEquals(true, locations.size() == locations2.size());

		// APPEND locations to the same file
		writer.save(locations, filename, true);

		// Verify stored locations
		locations2 = reader.readLocations(filename);
		Assert.assertEquals("Append does not work", locations.size() * 2, locations2.size());

		Assert.assertTrue(f.delete());
	}

	@Test
	public void testNumberOfLocations() throws IOException {

		final String city = "Berlin";

		// Download locations
		WebReader webReader = new WebReader();
		List<Location> locations = webReader.getLocations(city);
		Assert.assertEquals(6, locations.size());
	}
}
