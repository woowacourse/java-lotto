package lotto.domain;

import java.util.HashMap;
import java.util.Map;

public class Ball {
	private static final Map<Integer, Ball> ballCaches = new HashMap<>();
	public static final int MINIMUM_NUMBER = 1;
	public static final int MAXIMUM_NUMBER = 45;

	static {
		for(int i = MINIMUM_NUMBER; i <= MAXIMUM_NUMBER; i++) {
			ballCaches.put(i, new Ball(i));
		}
	}

	private final int number;

	private Ball (int number) {
		if (number < MINIMUM_NUMBER || number > MAXIMUM_NUMBER) {
			throw new IllegalArgumentException();
		}
		this.number = number;
	}

	public static Ball of(int number) {
		if (ballCaches.containsKey(number)) {
			return ballCaches.get(number);
		}
		return new Ball(number);
	}

	@Override
	public String toString() {
		return Integer.toString(number);
	}
}
