package domain;

import java.util.Arrays;

public enum Rank {
	FIFTH(3, new Money(5_000)),
	FOURTH(4, new Money(50_000)),
	THIRD(5, new Money(1_500_000)),
	SECOND(5, new Money(30_000_000)),
	FIRST(6, new Money(2_000_000_000));

	private int matchedCount;
	private Money winningMoney;

	Rank(int matchedCount, Money winningMoney) {
		this.matchedCount = matchedCount;
		this.winningMoney = winningMoney;
	}

	public static Rank of(int count) {
		return Arrays.stream(values())
			.filter(rank -> rank.matchedCount == count)
			.findFirst()
			.orElse(null);
	}

	public int getMatchedCount() {
		return matchedCount;
	}

	public Money getWinningMoney() {
		return winningMoney;
	}
}
