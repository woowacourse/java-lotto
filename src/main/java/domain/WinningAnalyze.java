package domain;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import domain.dto.WinningAnalyzeDto;

public class WinningAnalyze {
	private static final String PRIZE_COUNT_MESSAGE = "%d개";
	private static final String PROFIT_RATE_MESSAGE = "총 수익률은 %.2f입니다.";
	private static final String LINE_DELIMITER = "\n";

	private final Tickets tickets;
	private final WinningNumber winningNumber;

	public WinningAnalyze(Tickets tickets, WinningNumber winningNumber) {
		this.tickets = tickets;
		this.winningNumber = winningNumber;
	}

	public WinningAnalyzeDto analyze() {
		Map<Rank, Integer> analyzeResult = initRankResult();
		List<Rank> ranks = tickets.getRanks(winningNumber);
		ranks.forEach(rank -> analyzeResult.put(rank, analyzeResult.get(rank) + 1));

		double profitRate = calculateProfitRate(analyzeResult);

		return new WinningAnalyzeDto(analyzeResult, profitRate);
	}

	private Map<Rank, Integer> initRankResult() {
		Map<Rank, Integer> analyzeResult = new LinkedHashMap<>();

		Arrays.stream(Rank.values())
			.forEach(rank -> analyzeResult.put(rank, 0));

		return analyzeResult;
	}

	private double calculateProfitRate(Map<Rank, Integer> analyzeResult) {
		int payment = tickets.size() * Ticket.PRICE;
		double profit = getProfit(analyzeResult);

		return (profit / payment);
	}

	private double getProfit(Map<Rank, Integer> analyzeResult) {
		return analyzeResult.keySet()
			.stream()
			.mapToDouble(rank -> rank.getPrize() * analyzeResult.get(rank))
			.sum();
	}

/*	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();

		analyzeResult.keySet()
			.forEach(rank -> stringBuilder
				.append(rank.toString())
				.append(String.format(PRIZE_COUNT_MESSAGE, analyzeResult.get(rank)))
				.append(LINE_DELIMITER));

		stringBuilder.append(String
			.format(PROFIT_RATE_MESSAGE, Math.floor(profitRate * 100) / 100.0));

		return stringBuilder.toString();
	}*/
}
