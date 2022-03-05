package domain.dto;

import java.util.Map;

import domain.Rank;

public class WinningAnalyzeDto {
	private final Map<Rank, Integer> analyzeResult;
	private final double profitRate;

	public WinningAnalyzeDto(Map<Rank, Integer> analyzeResult, double profitRate) {
		this.analyzeResult = Map.copyOf(analyzeResult);
		this.profitRate = profitRate;
	}

	public Map<Rank, Integer> getAnalyzeResult() {
		return analyzeResult;
	}

	public double getProfitRate() {
		return profitRate;
	}
}
