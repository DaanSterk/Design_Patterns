package view;
import java.util.Scanner;

public class ConsoleInteractor {
	Scanner scanner;
	
	boolean closed;
	
	public ConsoleInteractor() {
		scanner = new Scanner(System.in);
	}
	
	public String[] command() {
		String[] input = scanner.nextLine().split(" ");
		if (input[0].equals("start")) {
			closed = true;
			input = null;
		}
		else if (input.length > 2) {
			System.out.println("Valid commands: 1: (gateName) (startingValue), 2: start");
			input = null;
		}
		return input;
	}
	
	public boolean isClosed() {
		return closed;
	}
	
}
