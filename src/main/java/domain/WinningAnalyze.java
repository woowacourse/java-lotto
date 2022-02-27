package domain;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class WinningAnalyze {
	private final Map<Rank, Integer> analyzeResult;
	private double profitRate;

	public WinningAnalyze() {
		this.analyzeResult = new LinkedHashMap<>();
		initRankResult();
	}

	private void initRankResult() {
		Arrays.stream(Rank.values())
			.forEach(rank -> analyzeResult.put(rank, 0));
	}

	public void analyze(Tickets tickets, WinningNumber winningNumber) {
		List<Rank> ranks = tickets.getRanks(winningNumber);
		ranks.forEach(rank -> analyzeResult.put(rank, analyzeResult.get(rank) + 1));

		calculateProfitRate(tickets);
	}

	private void calculateProfitRate(Tickets tickets) {
		double payment = tickets.size() * 1000;
		double profit = getProfit();

		profitRate =  (profit / payment);
	}

	private double getProfit() {
		return analyzeResult.keySet()
			.stream()
			.mapToDouble(rank -> rank.getPrize() * analyzeResult.get(rank))
			.sum();
	}

	public Map<Rank, Integer> getAnalyzeResult() {
		return analyzeResult;
	}

	public double getProfitRate() {
		return profitRate;
	}
}
