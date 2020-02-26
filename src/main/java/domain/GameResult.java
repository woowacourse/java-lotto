package domain;

import java.util.HashMap;
import java.util.Map;

public class GameResult {
	private static final int DEFAULT_VALUE = 0;
	private static final int HUNDRED = 100;
	private final Map<Rank, Integer> ranks;

	private GameResult(Map<Rank, Integer> ranks) {
		this.ranks = ranks;
	}

	public static GameResult create(LottoGame lottoGame) {
		Map<Rank, Integer> ranks = new HashMap<>();
		lottoGame.addRanks(ranks);
		return new GameResult(ranks);
	}

	public double calculateProfit(Money purchaseMoney) {
		Money resultMoney = calculateResultMoney();
		return resultMoney.division(purchaseMoney) * HUNDRED;
	}

	public Money calculateResultMoney() {
		return new Money(prizeMoneyCalculation());
	}

	private double prizeMoneyCalculation() {
		return ranks.keySet()
			.stream()
			.mapToDouble(rank -> rank.totalMoneyByRank(ranks.get(rank)))
			.sum();
	}

	public int numberOfRank(Rank rank) {
		return ranks.getOrDefault(rank, DEFAULT_VALUE);
	}
}
