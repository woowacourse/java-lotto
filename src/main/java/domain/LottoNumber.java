package domain;

import java.util.Objects;

public class LottoNumber implements Comparable<LottoNumber> {
	private final int number;

	public LottoNumber(int number) {
		validate(number);
		this.number = number;
	}

	private void validate(int number) {
		if (number < 1 || number > 45) {
			throw new IllegalArgumentException("로또번호는 1~45의 수가 필요합니다.");
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		LottoNumber that = (LottoNumber)o;
		return number == that.number;
	}

	@Override
	public int hashCode() {
		return Objects.hash(number);
	}

	public int getNumber() {
		return number;
	}

	@Override
	public int compareTo(LottoNumber other) {
		return this.number - other.number;
	}
}
