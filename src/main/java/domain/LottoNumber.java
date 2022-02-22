package domain;

public class LottoNumber {

	private static final int MINIMUM_VALUE = 1;
	private static final int MAXIMUM_VALUE = 45;
	private static final String ERROR_MESSAGE_NOT_IN_RANGE = "유효한 로또 번호가 아닙니다.";

	private final int value;

	public LottoNumber(int value) {
		validateInRange(value);
		this.value = value;
	}

	private void validateInRange(int value) {
		if (value < MINIMUM_VALUE || value > MAXIMUM_VALUE) {
			throw new IllegalArgumentException(ERROR_MESSAGE_NOT_IN_RANGE);
		}
	}
}
