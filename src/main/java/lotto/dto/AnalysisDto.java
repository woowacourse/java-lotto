package lotto.dto;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import lotto.domain.rank.Rank;

public class AnalysisDto {

	private final Map<Rank, Integer> rankCounts;
	private final double profitRate;

	public AnalysisDto(final List<Rank> ranks, final int creditMoney) {
		this.rankCounts = calculateRankCounts(ranks);
		this.profitRate = calculateProfitRate(ranks, creditMoney);
	}

	private Map<Rank, Integer> calculateRankCounts(final List<Rank> ranks) {
		final Map<Rank, Integer> rankMap = new LinkedHashMap<>();

		for (Rank rank : Rank.values()) {
			final int count = Collections.frequency(ranks, rank);
			rankMap.put(rank, count);
		}
		return rankMap;
	}

	private double calculateProfitRate(final List<Rank> ranks, final int creditMoney) {
		long total = ranks.stream()
				.mapToLong(Rank::getPrize)
				.sum();
		return (double) total / creditMoney;
	}

	public Map<Rank, Integer> getRankCounts() {
		return rankCounts;
	}

	public double getProfitRate() {
		return profitRate;
	}

}
