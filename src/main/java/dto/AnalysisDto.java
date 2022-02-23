package dto;

import java.util.Map;

import domain.Rank;

public class AnalysisDto {
	private final Map<Rank, Integer> rankCounts;
	private final double rate;

	public AnalysisDto(Map<Rank, Integer> rankCounts, double rate) {
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
