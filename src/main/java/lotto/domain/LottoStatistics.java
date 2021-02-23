package lotto.domain;

import java.util.*;

import static lotto.domain.Rank.RANK_END_INDEX;
import static lotto.domain.Rank.RANK_START_INDEX;

public class LottoStatistics {
	private static final int STATISTICS_INITIAL_VALUE = 0;

	private final Map<Rank, Integer> statistics = new LinkedHashMap<>();
	private final Money moneyInvested;

	public LottoStatistics(List<Rank> ranks, Money money) {
		initializeEmptyStatistics();
		aggregateStatisticsAccordingTo(ranks);
		this.moneyInvested = money;
	}

	private void initializeEmptyStatistics() {
		for (Rank rank : Rank.values()) {
			statistics.put(rank, STATISTICS_INITIAL_VALUE);
		}
	}

	private void aggregateStatisticsAccordingTo(List<Rank> ranks) {
		for (Rank rank : ranks) {
			statistics.put(rank, statistics.get(rank) + 1);
		}
	}

	public List<Integer> getWinCountByRank() {
		List<Integer> winCountByRank = new ArrayList<>(statistics.values()).subList(RANK_START_INDEX, RANK_END_INDEX);
		Collections.reverse(winCountByRank);
		return winCountByRank;
	}

	public float getProfitRate() {
		return moneyInvested.divide(getTotalProfit());
	}

	private Money getTotalProfit() {
		long totalProfit = 0;
		for (Rank rank : statistics.keySet()) {
			totalProfit += (long) statistics.get(rank) * rank.getPrize();
		}
		return new Money(totalProfit);
	}
}
