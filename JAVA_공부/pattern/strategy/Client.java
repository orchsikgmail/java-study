package interviews.java.pattern.strategy;

public class Client {

	private final MyLogging logging;

	public Client(MyLogging logging){
		this.logging = logging;
	}

	public void doWork(final int count) {
		if(count%2 == 0) {
			logging.write("Even number: "+count);
		}
	}
}