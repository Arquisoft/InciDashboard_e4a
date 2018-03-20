package utils;

public class Assert {
	
	public static void isEmpty(String str) {
		if (str.isEmpty()) {
			throw new IllegalArgumentException("Value cannot be empty");
		}
	}

	public static void isNull(Object o) {
		if (o == null) {
			throw new IllegalArgumentException("Value cannot be empty");
		}
	}

	public static void isLessThanZero(int i) {
		if (i < 0) {
			throw new IllegalArgumentException("Value cannot be lower than 0");
		}
	}
}
