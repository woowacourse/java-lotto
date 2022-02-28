package lotto.model.number;

import java.util.Objects;

public class WinningNumber {

	private final Number number;

	private WinningNumber(Number number) {
		this.number = number;
	}

	public static WinningNumber from(String input) {
		Number number = Number.from(input.trim());
		return new WinningNumber(number);
	}

	public boolean match(int number) {
		return this.number.getNumber() == number;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		WinningNumber that = (WinningNumber)o;
		return number.equals(that.number);
	}

	@Override
	public int hashCode() {
		return Objects.hash(number);
	}
}
