package domain;

import java.util.Map;

public class GameResult {
	private static final int DEFAULT_VALUE = 0;
	private static final int HUNDRED = 100;
	private final Map<Rank, Integer> ranks;

	public GameResult(Map<Rank, Integer> ranks) {
		this.ranks = ranks;
	}

	public double calculateProfit(Money purchaseMoney) {
		return getResultMoney().getMoney() / purchaseMoney.getMoney() * HUNDRED;
	}

	public Money getResultMoney() {
		return new Money(prizeMoneyCalculation());
	}

	private double prizeMoneyCalculation() {
		return ranks.keySet()
			.stream()
			.mapToDouble(rank -> rank.getWinningMoney().getMoney() * ranks.get(rank))
			.sum();
	}

	public int numberOfRank(Rank rank) {
		return ranks.getOrDefault(rank, DEFAULT_VALUE);
	}
}
