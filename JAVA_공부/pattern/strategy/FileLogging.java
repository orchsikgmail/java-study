package interviews.java.pattern.strategy;

import java.io.File;
import java.io.FileWriter;

public class FileLogging implements MyLogging {
	private final File toWrite;
	
	public FileLogging(final File toWrite) {
		this.toWrite = toWrite;
	}
	
	@Override
	public void write(String message) {
		try {
			final FileWriter fos = new FileWriter(toWrite);
			fos.write(message);
			fos.close();
		} catch (Exception e) {
			// IOException Ã³¸®ÇÏ±â
		}
	}
}
