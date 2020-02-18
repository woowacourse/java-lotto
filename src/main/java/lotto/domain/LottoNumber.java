package lotto.domain;

public class LottoNumber {
	public static final int MAX_NUMBER = 45;
	public static final int MIN_NUMBER = 1;

	private final int number;

	public LottoNumber(int number) {
		checkValidationOf(number);
		this.number = number;
	}

	private void checkValidationOf(int number) {
		if (number > MAX_NUMBER) {
			throw new IllegalArgumentException("로또 숫자는 45를 넘기면 안됩니다.");
		}
		if (number < MIN_NUMBER) {
			throw new IllegalArgumentException("로또 숫자는 0이하 일 수 없습니다.");
		}
	}
}
