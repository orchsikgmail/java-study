package interviews.java.pattern.strategy;

public class ConsoleLogging implements MyLogging {
	@Override
	public void write(String message) {
		System.out.println(message);
	}
}
