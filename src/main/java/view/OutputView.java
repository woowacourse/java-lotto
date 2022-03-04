package view;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import controller.dto.LotteriesDto;
import controller.dto.RankDto;
import controller.dto.WinningResultDto;
import domain.PurchaseAmount;
import utils.DomainTerminology;

public class OutputView {

	private static final String WINNING_STATISTICS = "당첨 통계\n--------\n";
	private static final String TOTAL_EARNING_RATE = "총 수익률은 %.2f 입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)";
	private static final String COUNT_UNIT = "개";
	private static final String AGREEMENT = "일치";

	public static void printStatistics(final WinningResultDto winningResult) {
		System.out.println(WINNING_STATISTICS);
		printRanking(winningResult.getRanking());
		printIncomePercent(winningResult.getWinningPercent());
	}

	private static void printRanking(final Map<RankDto, Integer> ranking) {
		List<RankDto> sortedRank = ranking.keySet()
			.stream()
			.sorted(Comparator.comparing(RankDto::getPrize))
			.collect(Collectors.toList());

		sortedRank.forEach((rank) -> {
			System.out.println(makeRankingMessage(rank, ranking.get(rank)));
		});
	}

	public static String makeRankingMessage (final RankDto rank, final int winningCount) {
		String rakingMessage = rank.getCorrectedBalls() + COUNT_UNIT + " " + AGREEMENT;
		if(rank.getRankName().equals("SECOND")) {
			rakingMessage += ", " + DomainTerminology.BONUS_BALL + " " + AGREEMENT;
		}
		return rakingMessage + " (" + rank.getPrize() + PurchaseAmount.MONEY_UNIT + ") - "
			+ winningCount + COUNT_UNIT;
	}

	private static void printIncomePercent(final double incomePercent) {
		System.out.printf(TOTAL_EARNING_RATE, incomePercent);
	}

	public static void printLotteries(final LotteriesDto lotteries) {
		lotteries.getLotteries()
			.forEach(lottery -> printLotteryNumbers(lottery.getNumbers()));
	}

	private static void printLotteryNumbers(final List<Integer> lotteryNumbers) {
		System.out.println(lotteryNumbers.toString());
	}
}
