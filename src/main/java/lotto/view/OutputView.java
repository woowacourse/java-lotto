package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.Lottos;
import lotto.domain.result.GameResult;
import lotto.domain.result.GameResultDto;
import lotto.domain.result.RankCount;
import lotto.domain.result.Statistic;

public class OutputView {
	public static void printPieces(int parseToPiece) {
		System.out.println(parseToPiece + "개를 구매했습니다.");
	}

	public static void printLottos(Lottos lottos) {
		for (Lotto lotto : lottos) {
			System.out.println(lotto.getNumbers());
		}
		System.out.println();
	}

	public static void printResult(GameResultDto gameResult) {
		System.out.println("당첨 통계 \n ==============");
		printRank(gameResult.getGameResult());
		printProfit(gameResult.getProfit());
	}

	private static void printRank(GameResult gameResult) {
		for (RankCount rankCount : gameResult.getResult()) {
			printEachRank(rankCount);
		}
	}

	private static void printEachRank(RankCount rankCount) {
		StringBuilder sb = new StringBuilder();
		Statistic statistic = rankCount.getStatistic();
		if (checkBonus(rankCount))
			return;
		sb.append(statistic.getMatchingNumbers())
			.append("개 일치 (")
			.append(statistic.getPrize())
			.append("원) --")
			.append(rankCount.getCount())
			.append("개");
		System.out.println(sb.toString());
	}

	private static void printSecond(RankCount rankCount) {
		StringBuilder sb = new StringBuilder();
		sb.append(rankCount.getStatistic().getMatchingNumbers())
			.append("개 일치, 보너스 볼 일치 (")
			.append(rankCount.getStatistic().getPrize())
			.append("원) --")
			.append(rankCount.getCount())
			.append("개");
		System.out.println(sb.toString());
	}

	private static boolean checkBonus(RankCount rankCount) {
		if (Statistic.BONUS == rankCount.getStatistic()) {
			printSecond(rankCount);
			return true;
		}
		return false;
	}

	private static void printProfit(double profit) {
		System.out.println("총 수익률은 " + profit + "% 입니다.");
	}
}
