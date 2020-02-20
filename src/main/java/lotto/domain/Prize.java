package lotto.domain;

import java.util.Objects;

public class Prize {
	private static final long MINIMUM_PRIZE = 0L;
	private static final String INVALID_PRIZE_EXCEPTION_MESSAGE = "유효하지 않는 금액입니다.";
	private static final int PROFITS_PERCENTAGE = 100;

	private final long prize;

	private Prize(long prize) {
		validateNonNegative(prize);
		this.prize = prize;
	}

	private void validateNonNegative(long prize) {
		if (prize < MINIMUM_PRIZE) {
			throw new IllegalArgumentException(INVALID_PRIZE_EXCEPTION_MESSAGE);
		}
	}

	public static Prize of(long prize) {
		return new Prize(prize);
	}

	public Prize multiply(long count) {
		return Prize.of(prize * count);
	}

	public Prize plus(Prize otherPrize) {
		return Prize.of(prize + otherPrize.prize);
	}

	public long findProfits(Money money) {
		return (prize * PROFITS_PERCENTAGE) / money.getMoney();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Prize prize1 = (Prize)o;
		return prize == prize1.prize;
	}

	@Override
	public int hashCode() {
		return Objects.hash(prize);
	}

	@Override
	public String toString() {
		return String.valueOf(prize);
	}
}
