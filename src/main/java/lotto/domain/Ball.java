package lotto.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Ball implements Comparable<Ball> {
	private static final int MINIMUM_NUMBER = 1;
	private static final int MAXIMUM_NUMBER = 45;
	private static final Map<Integer, Ball> ballCaches = new HashMap<>();
	private static final String INVALID_LOTTO_NUMBER_EXCEPTION_MESSAGE = "유효한 로또 번호가 아닙니다.";

	static {
		initBallCaches();
	}

	private static void initBallCaches() {
		for (int number = MINIMUM_NUMBER; number <= MAXIMUM_NUMBER; number++) {
			ballCaches.put(number, new Ball(number));
		}
	}

	private final int number;

	private Ball(int number) {
		validateNumberRange(number);
		this.number = number;
	}

	private void validateNumberRange(int number) {
		if (number < MINIMUM_NUMBER || number > MAXIMUM_NUMBER) {
			throw new IllegalArgumentException(INVALID_LOTTO_NUMBER_EXCEPTION_MESSAGE);
		}
	}

	public static Ball of(int number) {
		if (ballCaches.containsKey(number)) {
			return ballCaches.get(number);
		}
		return new Ball(number);
	}

	public static List<Ball> getBalls() {
		return new ArrayList<>(ballCaches.values());
	}

	@Override
	public int compareTo(Ball o) {
		return Integer.compare(number, o.number);
	}

	@Override
	public String toString() {
		return Integer.toString(number);
	}
}
