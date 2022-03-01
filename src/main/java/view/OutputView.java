package view;

import static view.messages.OutputViewMessages.*;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import domain.Result;
import domain.lottery.Lottery;
import domain.Rank;
import domain.lottery.LotteryNumber;

public class OutputView {

	public void printStatistics(final Result result) {
		System.out.println(RESULT_STATISTICS.getMessage());
		printRanking(result.getRankResult());
		printIncomePercent(result.getReturnRate());
	}

	private void printRanking(final Map<Rank, Integer> ranking) {
		final List<Rank> sortedRank = ranking.keySet()
			.stream()
			.sorted(Comparator.comparing(Rank::getPrize))
			.collect(Collectors.toList());

		sortedRank.forEach((rank) -> {
			System.out.println(makeRakingMessage(rank, ranking.get(rank)));
		});
	}

	private String makeRakingMessage(final Rank rank, final int winningCount) {
		if (rank == Rank.SECOND) {
			return String.format(RANKING_SECOND_MESSAGE.getMessage(),
				rank.getCorrectedBalls(), rank.getPrize(), winningCount);
		}
		return String.format(RANKING_MESSAGE.getMessage(),
			rank.getCorrectedBalls(), rank.getPrize(), winningCount);
	}

	private void printIncomePercent(final double incomePercent) {
		System.out.printf(RETURN_RATE.getMessage(), incomePercent);
	}

	public void printLotteries(final List<Lottery> lotteries) {
		lotteries.forEach((lottery ->
			System.out.println(convertToLotteryNumbers(lottery.getNumbers()))
		));
	}

	private String convertToLotteryNumbers(final Set<LotteryNumber> lotteryNumbers) {
		return lotteryNumbers.stream()
			.map(LotteryNumber::getNumber)
			.collect(Collectors.toList())
			.toString();
	}

	public void printException(final String message) {
		System.out.println(EXCEPTION_PREFIX.getMessage() + message);
	}
}
