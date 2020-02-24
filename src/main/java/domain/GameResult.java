package domain;

import java.util.Map;

public class GameResult {
	private static final int DEFAULT_VALUE = 0;
	private final Map<Rank, Integer> ranks;

	public GameResult(Map<Rank, Integer> ranks) {
		this.ranks = ranks;
	}

	public Money getResultMoney() {
		Money money = new Money(ranks.keySet()
			.stream()
			.mapToDouble(rank -> rank.getWinningMoney().getMoney() * ranks.get(rank))
			.sum());
		return money;
	}

	public int getMatched(Rank rank) {
		return ranks.getOrDefault(rank, DEFAULT_VALUE);
	}
}
