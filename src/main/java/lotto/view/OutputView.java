package lotto.view;

import java.util.List;

import lotto.domain.Lotto;
import lotto.domain.Lottos;
import lotto.domain.result.GameResultDto;
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
		printRank(gameResult.getStatistics());
		printProfit(gameResult.getProfit());
	}

	private static void printRank(List<Statistic> statistics) {
		for (Statistic statistic : statistics) {
			printEachRank(statistic);
		}
	}

	private static void printEachRank(Statistic statistic) {
		StringBuilder sb = new StringBuilder();
		if (checkBonus(statistic))
			return;
		sb.append(statistic.getMatchingNumbers())
			.append("개 일치 (")
			.append(statistic.getPrize())
			.append("원) --")
			.append(statistic.getCount())
			.append("개");
		System.out.println(sb.toString());
	}

	private static void printSecond(Statistic statistic) {
		StringBuilder sb = new StringBuilder();
		sb.append(statistic.getMatchingNumbers())
			.append("개 일치, 보너스 볼 일치 (")
			.append(statistic.getPrize())
			.append("원) --")
			.append(statistic.getCount())
			.append("개");
		System.out.println(sb.toString());
	}

	private static boolean checkBonus(Statistic statistic) {
		if (Statistic.BONUS == statistic) {
			printSecond(statistic);
			return true;
		}
		return false;
	}

	private static void printProfit(double profit) {
		System.out.println("총 수익률은 " + profit + "% 입니다.");
	}
}
