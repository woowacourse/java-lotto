package domain;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import domain.dto.WinningAnalyzeDto;

public class WinningAnalyze {
	private final Tickets tickets;
	private final WinningNumber winningNumber;

	public WinningAnalyze(final Tickets tickets, final WinningNumber winningNumber) {
		this.tickets = tickets;
		this.winningNumber = winningNumber;
	}

	public WinningAnalyzeDto analyze() {
		Map<Rank, Integer> analyzeResult = Arrays.stream(Rank.values())
			.collect(Collectors.toMap(rank -> rank, rank -> 0));

		Rank.getRanks(tickets, winningNumber)
			.forEach(rank -> analyzeResult.put(rank, analyzeResult.get(rank) + 1));

		double profitRate = calculateProfitRate(analyzeResult);

		return new WinningAnalyzeDto(analyzeResult, profitRate);
	}

	private double calculateProfitRate(final Map<Rank, Integer> countByRank) {
		int payment = tickets.size() * Ticket.PRICE;
		double profit = getProfit(countByRank);

		return Math.floor((profit / payment) * 100) / 100.0;
	}

	private double getProfit(final Map<Rank, Integer> analyzeResult) {
		return analyzeResult.keySet()
			.stream()
			.mapToDouble(rank -> rank.getPrize() * analyzeResult.get(rank))
			.sum();
	}
}
