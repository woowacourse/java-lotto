package domain;

import java.util.Arrays;
import java.util.List;

public enum Rank {
	FIFTH(3, new Money(5000)),
	FOURTH(4, new Money(50000)),
	THIRD(5, new Money(1500000)),
	SECOND(5, new Money(30000000)),
	FIRST(6, new Money(2000000000));

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

	public static Money sumWinningMoney(List<Rank> ranks) {
		Money sumMoney = new Money(ranks.stream()
			.mapToInt(rank -> rank.winningMoney.getMoney())
			.sum());
		return sumMoney;
	}

	public int getMatchedCount() {
		return matchedCount;
	}

	public Money getWinningMoney() {
		return winningMoney;
	}

	public int getContainingCount(List<Rank> ranks) {
		return (int)ranks.stream()
			.filter(rank -> rank.equals(this))
			.count();
	}
}
