package lotto.domain.ticket;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class LottoBall implements Comparable<LottoBall> {
	private static final int MINIMUM_NUMBER = 1;
	private static final int MAXIMUM_NUMBER = 45;
	private static final Map<Integer, LottoBall> BALL_CACHE = new HashMap<>();
	private static final String INVALID_LOTTO_NUMBER_EXCEPTION_MESSAGE = "유효한 로또 번호가 아닙니다.";

	static {
		initBallCaches();
	}

	private static void initBallCaches() {
		for (int number = MINIMUM_NUMBER; number <= MAXIMUM_NUMBER; number++) {
			BALL_CACHE.put(number, new LottoBall(number));
		}
	}

	private final int number;

	private LottoBall(int number) {
		validateNumberRange(number);
		this.number = number;
	}

	private void validateNumberRange(int number) {
		if (number < MINIMUM_NUMBER || number > MAXIMUM_NUMBER) {
			throw new IllegalArgumentException(INVALID_LOTTO_NUMBER_EXCEPTION_MESSAGE);
		}
	}

	public static LottoBall valueOf(int number) {
		if (BALL_CACHE.containsKey(number)) {
			return BALL_CACHE.get(number);
		}
		throw new IllegalArgumentException(INVALID_LOTTO_NUMBER_EXCEPTION_MESSAGE);
	}

	static Collection<LottoBall> values() {
		return Collections.unmodifiableCollection(BALL_CACHE.values());
	}

	@Override
	public int compareTo(LottoBall that) {
		return Integer.compare(this.number, that.number);
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (object == null || getClass() != object.getClass()) {
			return false;
		}
		LottoBall that = (LottoBall)object;
		return this.number == that.number;
	}

	@Override
	public int hashCode() {
		return Objects.hash(number);
	}

	@Override
	public String toString() {
		return Integer.toString(number);
	}
}
