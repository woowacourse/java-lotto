package domain;

import java.util.Objects;

import static constant.LottoConstant.MAX_NUMBER;
import static constant.LottoConstant.MIN_NUMBER;

public class LottoNumber {

	private static final String NUMBER_IN_RANGE = MIN_NUMBER + "이상 " + MAX_NUMBER + "이하의 숫자만 허용됩니다.";

	private final int number;

	public LottoNumber(int number) {
		validateNumberInRange(number);
		this.number = number;
	}

	public int getNumber() {
		return this.number;
	}

	private void validateNumberInRange(int number) {
		if (number > MAX_NUMBER || number < MIN_NUMBER) {
			throw new IllegalArgumentException(NUMBER_IN_RANGE);
		}
	}

	@Override
	public boolean equals(Object o) {
		LottoNumber target = (LottoNumber) o;
		return this.number == target.getNumber();
	}

	@Override
	public int hashCode() {
		return Objects.hash(number);
	}
}
