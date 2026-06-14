package generic_utility;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class JavaUtility {

	public static int getRandomNumber() {
		double db = Math.random() * 1000;
		int random = (int) db;
		return random;
	}

	public static String getCureentTime() {
		LocalDateTime ldc = LocalDateTime.now();
		String time = DateTimeFormatter.ofPattern("hhmmss_ddmmyyyy").format(ldc);
		return time;
	}
}
