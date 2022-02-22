package domain;

import java.util.Objects;
import java.util.Random;

public class LottoNumber {

	private static final String ERROR_MESSAGE_NOT_IN_RANGE = "유효한 로또 번호가 아닙니다.";

	private static final int MINIMUM_VALUE = 1;
	private static final int MAXIMUM_VALUE = 45;
	private static final int BOUND_OF_RANDOM = 45;
	private static final int START_OF_RANDOM = 1;

	private final int value;

	private LottoNumber(int value) {
		validateInRange(value);
		this.value = value;
	}

	public static LottoNumber createByInput(int value) {
		return new LottoNumber(value);
	}

	public static LottoNumber createByRandom() {
		Random random = new Random();
		int randomValue = random.nextInt(BOUND_OF_RANDOM) + START_OF_RANDOM;
		return new LottoNumber(randomValue);
	}

	private void validateInRange(int value) {
		if (value < MINIMUM_VALUE || value > MAXIMUM_VALUE) {
			throw new IllegalArgumentException(ERROR_MESSAGE_NOT_IN_RANGE);
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		LottoNumber that = (LottoNumber)o;
		return value == that.value;
	}

	@Override
	public int hashCode() {
		return Objects.hash(value);
	}
}
