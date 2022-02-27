package domain;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class WinningAnalyze {
	private static final int LOTTO_PRICE = 1000;

	private static final String PRIZE_COUNT_MESSAGE = "%d개";
	private static final String PROFIT_RATE_MESSAGE = "총 수익률은 %.2f입니다.";
	private static final String LINE_DELIMITER = "\n";

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
		double payment = tickets.size() * LOTTO_PRICE;
		double profit = getProfit();

		profitRate = (profit / payment);
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

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();

		analyzeResult.keySet()
			.forEach(rank -> stringBuilder
				.append(rank.toString())
				.append(String.format(PRIZE_COUNT_MESSAGE, analyzeResult.get(rank)))
				.append(LINE_DELIMITER));

		stringBuilder.append(String.format(PROFIT_RATE_MESSAGE, profitRate));

		return stringBuilder.toString();
	}
}
