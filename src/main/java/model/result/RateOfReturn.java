package model.result;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RateOfReturn {
	private final int money;
	private int sumMoneyOfReturns;
	private Map<Rank, Integer> countOfResult;

	public RateOfReturn(int money) {
		this.money = money;
		this.sumMoneyOfReturns = 0;
		countOfResult = new HashMap<>();
	}

	public void saveResult(Rank rank) {
		countOfResult.put(rank, countOfResult.getOrDefault(rank, 0) + 1);
	}

	public int countStatistics(Rank rank) {
		return countOfResult.getOrDefault(rank, 0);
	}

	public double getRateOfReturn() {
		Arrays.stream(Rank.values())
			.filter(rank -> rank.checkNumberToReward())
			.forEach(
				statistics -> sumMoneyOfReturns += (countOfResult.getOrDefault(statistics, 0) * statistics.getValue()));

		return (sumMoneyOfReturns / (double)money);
	}
}
