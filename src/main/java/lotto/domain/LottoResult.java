package lotto.domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LottoResult {
	private static final int INIT_VALUE = 0;
	private static final int COUNT_ADDITIONAL_VALUE = 1;

	private final List<Rank> ranks;
	private final Money money;

	public LottoResult(List<Rank> ranks, Money money) {
		this.ranks = ranks;
		this.money = money;
	}

	public Map<Rank, Integer> getResult() {
		Map<Rank, Integer> counts = new HashMap<>();
		Arrays.stream(Rank.values())
			.forEach(rank -> counts.put(rank, INIT_VALUE));

		ranks.stream()
			.forEach(rank -> counts.replace(rank, counts.get(rank) + COUNT_ADDITIONAL_VALUE));
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
