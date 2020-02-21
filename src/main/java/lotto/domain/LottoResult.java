package lotto.domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LottoResult {
	private final List<Rank> ranks;
	private final Money money;

	public LottoResult(List<Rank> ranks, Money money) {
		this.ranks = ranks;
		this.money = money;
	}

	public Map<Rank, Integer> getResult() {
		Map<Rank, Integer> counts = new HashMap<>();
		Arrays.stream(Rank.values())
			.forEach(rank -> counts.put(rank, 0));

		ranks.stream()
			.forEach(rank -> counts.replace(rank, counts.get(rank) + 1));
		return counts;
	}

	public int getIncomeRate() {
		return money.calculateIncomeRate(getTotalReward());
	}

	private long getTotalReward() {
		return ranks.stream()
			.map(Rank::getReward)
			.reduce((x, y) -> x + y)
			.get();
	}

}
