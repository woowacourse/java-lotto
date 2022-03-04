package lotto.model.lotto;

import java.util.Objects;

public class LottoBall implements Comparable<LottoBall> {
	private static final String ERROR_TYPE = "[ERROR] 로또 번호는 숫자로만 입력해주세요";
	private static final String ERROR_BOUND = "[ERROR] 로또 번호는 1 이상 45 이하로 입력해주세요";
	int MIN_WINNING_NUMBER = 1;
	int MAX_WINNING_NUMBER = 45;

	private final int number;

	private LottoBall(int number) {
		checkBound(number);
		this.number = number;
	}

	private void checkBound(int number) {
		if (number < MIN_WINNING_NUMBER || number > MAX_WINNING_NUMBER) {
			throw new IllegalArgumentException(ERROR_BOUND);
		}
	}

	public static LottoBall from(String input) {
		try {
			return new LottoBall(Integer.parseInt(input.trim()));
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException(ERROR_TYPE);
		}
	}

	public int getNumber() {
		return number;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		LottoBall lottoBall1 = (LottoBall)o;
		return number == lottoBall1.number;
	}

	@Override
	public int hashCode() {
		return Objects.hash(number);
	}

	@Override
	public int compareTo(LottoBall o) {

		if (this.number > o.number) {
			return 1;
		}
		if (this.number < o.number) {
			return -1;
		}
		return 0;
	}
}
