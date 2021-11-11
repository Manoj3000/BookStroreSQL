package bookstore.util;

import java.util.Scanner;

public class UtilScanner {

	private static final Scanner SCANNER = new Scanner(System.in);
	
	public static int getInt(String message) {
		message(message);
		return SCANNER.nextInt();
	}

	public static int getIntNoMsg(String message) {
		System.out.println(message);
		return SCANNER.nextInt();
	}

	public static String getString(String message) {
		message(message);
		return SCANNER.next();
	}

	public static double getDouble(String message) {
		message(message);
		return SCANNER.nextDouble();
	}

	private static void message(String msg) {
		System.out.println("Enter the " + msg);
	}
}
