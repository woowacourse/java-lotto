package domain;

import java.util.Collections;
import java.util.Map;

public class LottoResult {
	private final Map<Rank, Integer> ranks;

	public LottoResult(Map<Rank, Integer> ranks) {
		this.ranks = ranks;
	}

	public int calculateTotalProfit() {
		return ranks.entrySet()
			.stream()
			.mapToInt(entry -> calculateRankProfit(entry.getKey(), entry.getValue()))
			.sum();
	}

	private int calculateRankProfit(Rank rank, int count) {
		return rank.getMoney() * count;
	}

	public Map<Rank, Integer> getRanks() {
		return Collections.unmodifiableMap(ranks);
	}
}
