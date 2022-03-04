package model.result;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RateOfReturn {
	private long sumMoneyOfReturns;
	private Map<Rank, Integer> countOfResult;

	public RateOfReturn() {
		this.sumMoneyOfReturns = 0;
		countOfResult = new HashMap<>();
		for (final Rank rank : Rank.values()) {
			countOfResult.put(rank, 0);
		}
	}

	public void increaseCountOfRank(Rank rank) {
		countOfResult.compute(rank, (r, count) -> count + 1);
	}

	public int getCountOfResult(Rank rank) {
		return countOfResult.getOrDefault(rank, 0);
	}

	public long getRateOfReturn() {
		Arrays.stream(Rank.values())
			.filter(rank -> rank.checkNumberToReward())
			.forEach(
				statistics -> sumMoneyOfReturns += (countOfResult.getOrDefault(statistics, 0) * statistics.getValue()));

		return sumMoneyOfReturns;
	}
}
