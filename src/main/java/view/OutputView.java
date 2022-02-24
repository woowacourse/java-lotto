package view;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import domain.Lottery;
import domain.Rank;
import utils.LotteryMessage;

public class OutputView {

	public static void printStatistics(Map<Rank, Integer> ranking, double incomePercent) {
		System.out.println(LotteryMessage.WINNING_STATISTICS);
		printRanking(ranking);
		printIncomePercent(incomePercent);
	}

	private static void printRanking(Map<Rank, Integer> ranking) {
		List<Rank> sortedRank = ranking.keySet()
			.stream()
			.sorted(Comparator.comparing(Rank::getPrize))
			.collect(Collectors.toList());

		sortedRank.forEach((rank) -> {
			System.out.println(LotteryMessage.makeRankingMessage(rank, ranking.get(rank)));
		});
	}

	private static void printIncomePercent(final double incomePercent) {
		System.out.printf(LotteryMessage.TOTAL_EARNING_RATE, incomePercent);
	}

	public static void printLotteries(List<Lottery> lotteries) {
		lotteries.forEach((lottery -> {
			System.out.println(lottery.getNumbers().toString());
		}));
	}
}
