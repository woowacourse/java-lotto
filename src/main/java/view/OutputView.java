package view;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import domain.Lottery;
import domain.Rank;

public class OutputView {

	public static void printStatistics(Map<Rank, Integer> ranking, double incomePercent) {
		System.out.println("당첨 통계\n--------\n");
		printRanking(ranking);
		printIncomePercent(incomePercent);
	}

	private static void printRanking(Map<Rank, Integer> ranking) {
		List<Rank> sortedRank = ranking.keySet()
			.stream()
			.sorted(Comparator.comparing(Rank::getPrize))
			.collect(Collectors.toList());

		sortedRank.forEach((rank) -> {
			System.out.println(makeRakingMessage(rank, ranking.get(rank)));
		});
	}

	private static String makeRakingMessage(final Rank rank, final int winningCount) {
		if(rank == Rank.SECOND) {
			return rank.getCorrectedBalls() + "개 일치, 보너스 볼 일치 (" + rank.getPrize()
				+ "원) - " + winningCount + "개";
		}
		return rank.getCorrectedBalls() + "개 일치 (" + rank.getPrize()
			+ "원) - " + winningCount + "개";
	}

	private static void printIncomePercent(final double incomePercent) {
		System.out.printf("총 수익률은 %.2f 입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)", incomePercent);
	}

	public static void printLotteries(List<Lottery> lotteries) {
		lotteries.forEach((lottery -> {
			System.out.println(lottery.getNumbers().toString());
		}));
	}
}
