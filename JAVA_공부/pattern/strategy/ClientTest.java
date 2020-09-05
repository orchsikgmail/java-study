package interviews.java.pattern.strategy;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.junit.jupiter.api.Test;

class ClientTest {

	@Test
	public void useConsoleLogging() {
		final Client c1 = new Client(new ConsoleLogging());
		c1.doWork(32);
	}

	@Test
	public void useFileLogging() throws IOException {
		final File tempFile = File.createTempFile("test", "log");
		final Client c2 = new Client(new FileLogging(tempFile));
		c2.doWork(41);
		c2.doWork(42);
		c2.doWork(43);

		final BufferedReader reader = new BufferedReader(new FileReader(tempFile));
		assertEquals("Even number: 42", reader.readLine());
		assertEquals(-1, reader.read());
		reader.close();
	}

}
