package domain;

public class LottoNumber {
	private final int number;

	private LottoNumber(int number) {
		this.number = number;
	}

	public static LottoNumber from(int number) {
		if (number < 1 || number > 45) {
			throw new IllegalArgumentException("로또 숫자는 1이상 45이하만 허용됩니다.");
		}

		return new LottoNumber(number);
	}
}
