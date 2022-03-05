package model.result;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LottoResult {
	private long sumMoneyOfReturns;
	private Map<Rank, Integer> countOfResult;

	public LottoResult() {
		this.sumMoneyOfReturns = 0;
		countOfResult = new HashMap<>();
		for (final Rank rank : Rank.values()) {
			countOfResult.put(rank, 0);
		}
	}

	public void increaseCountOfRank(Rank rank) {
		countOfResult.putIfAbsent(rank, 0);
		countOfResult.compute(rank, (r, count) -> count + 1);
	}

	public int getCountOfResult(Rank rank) {
		return countOfResult.getOrDefault(rank, 0);
	}

	public long getSumOfRewards() {
		Arrays.stream(Rank.values())
			.filter(rank -> rank.checkNumberToReward())
			.forEach(
				statistics -> sumMoneyOfReturns += (countOfResult.getOrDefault(statistics, 0)
					* (long)statistics.getValue()));

		return sumMoneyOfReturns;
	}
}
