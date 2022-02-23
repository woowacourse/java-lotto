package lotto.dto;

import java.util.Map;

import lotto.domain.rank.Rank;

public class AnalysisDto {

	private final Map<Rank, Integer> rankCounts;
	private final double rate;

	public AnalysisDto(final Map<Rank, Integer> rankCounts, final double rate) {
		this.rankCounts = rankCounts;
		this.rate = rate;
	}

	public Map<Rank, Integer> getRankCounts() {
		return rankCounts;
	}

	public double getRate() {
		return rate;
	}

}
