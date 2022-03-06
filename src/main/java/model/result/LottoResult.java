package model.result;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LottoResult {
	private Map<Rank, Integer> countOfResult;

	public LottoResult() {
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
		return Arrays.stream(Rank.values())
			.filter(Rank::hasReward)
			.mapToLong(rank -> (countOfResult.getOrDefault(rank, 0) * (long)rank.getValue()))
			.sum();
	}
}
