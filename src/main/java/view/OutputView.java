package view;

import static view.messages.OutputViewMessages.*;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import domain.lottery.Lottery;
import domain.Rank;
import domain.lottery.LotteryNumber;

public class OutputView {

	public void printStatistics(Map<Rank, Integer> ranking, double incomePercent) {
		System.out.println(RESULT_STATISTICS.getMessage());
		printRanking(ranking);
		printIncomePercent(incomePercent);
	}

	private void printRanking(Map<Rank, Integer> ranking) {
		List<Rank> sortedRank = ranking.keySet()
			.stream()
			.sorted(Comparator.comparing(Rank::getPrize))
			.collect(Collectors.toList());

		sortedRank.forEach((rank) -> {
			System.out.println(makeRakingMessage(rank, ranking.get(rank)));
		});
	}

	private String makeRakingMessage(final Rank rank, final int winningCount) {
		if (rank == Rank.SECOND) {
			return rank.getCorrectedBalls() + "개 일치, 보너스 볼 일치 (" + rank.getPrize()
				+ "원) - " + winningCount + "개";
		}
		return rank.getCorrectedBalls() + "개 일치 (" + rank.getPrize()
			+ "원) - " + winningCount + "개";
	}

	private void printIncomePercent(final double incomePercent) {
		System.out.printf(RETURN_RATE.getMessage(), incomePercent);
	}

	public void printLotteries(List<Lottery> lotteries) {
		lotteries.forEach((lottery ->
			System.out.println(converToLotteryNumbers(lottery.getNumbers()))
		));
	}

	private String converToLotteryNumbers(List<LotteryNumber> lotteryNumbers) {
		return lotteryNumbers.stream()
			.map(LotteryNumber::getNumber)
			.collect(Collectors.toList())
			.toString();
	}

	public void printException(String message) {
		System.out.println(EXCEPTION_PREFIX.getMessage() + message);
	}
}
