package domain;

import java.util.Arrays;

public enum Rank {
	NO_RANK(0, new Money(0), "미당첨"),
	FIFTH(3, new Money(5_000), "3개 일치"),
	FOURTH(4, new Money(50_000), "4개 일치"),
	THIRD(5, new Money(1_500_000), "5개 일치"),
	SECOND(5, new Money(30_000_000), "5개 일치, 보너스 볼 일치"),
	FIRST(6, new Money(2_000_000_000), "6개 일치");

	private static final int FIVE_MATCHED = 5;
	private int matchedCount;
	private Money winningMoney;
	private String message;

	Rank(int matchedCount, Money winningMoney, String message) {
		this.matchedCount = matchedCount;
		this.winningMoney = winningMoney;
		this.message = message;
	}

	public static Rank of(int count, boolean bonusNumberMatch) {
		if (count == FIVE_MATCHED && bonusNumberMatch) {
			return Rank.SECOND;
		}
		return Arrays.stream(values())
			.filter(rank -> rank.matchedCount == count)
			.findFirst()
			.orElse(NO_RANK);
	}

	public double totalMoneyByRank(int count) {
		return winningMoney.multiply(count);
	}

	public Money getWinningMoney() {
		return winningMoney;
	}

	public String getMessage() {
		return this.message;
	}
}
