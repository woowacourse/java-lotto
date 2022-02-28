package view;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import controller.dto.LotteriesDto;
import controller.dto.RankDto;
import controller.dto.WinningResultDto;
import utils.LotteryMessage;

public class OutputView {

	public static void printStatistics(final WinningResultDto winningResult) {
		System.out.println(LotteryMessage.WINNING_STATISTICS);
		printRanking(winningResult.getRanking());
		printIncomePercent(winningResult.getWinningPercent());
	}

	private static void printRanking(final Map<RankDto, Integer> ranking) {
		List<RankDto> sortedRank = ranking.keySet()
			.stream()
			.sorted(Comparator.comparing(RankDto::getPrize))
			.collect(Collectors.toList());

		sortedRank.forEach((rank) -> {
			System.out.println(LotteryMessage.makeRankingMessage(rank, ranking.get(rank)));
		});
	}

	private static void printIncomePercent(final double incomePercent) {
		System.out.printf(LotteryMessage.TOTAL_EARNING_RATE, incomePercent);
	}

	public static void printLotteries(final LotteriesDto lotteries) {
		lotteries.getLotteries()
			.forEach(lottery -> printLotteryNumbers(lottery.getNumbers()));
	}

	private static void printLotteryNumbers(final List<Integer> lotteryNumbers) {
		System.out.println(lotteryNumbers.toString());
	}
}
