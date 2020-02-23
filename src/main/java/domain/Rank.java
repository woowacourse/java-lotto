package domain;

import java.util.Arrays;
import java.util.List;

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

	public static Money sumWinningMoney(List<Rank> ranks) {
		Money money = new Money(ranks.stream()
			.mapToDouble(rank -> rank.winningMoney.getMoney())
			.sum());
		return money;
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
