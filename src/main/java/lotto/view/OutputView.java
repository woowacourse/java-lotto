package lotto.view;

import java.util.List;

import lotto.domain.Rank;
import lotto.domain.Ranks;

public class OutputView {
	private static final int MULTIPLIER_FOR_PERCENT = 100;

	public static void printResult(final Ranks ranks) {
		final Ranks reversed = Rank.getOrderReversed();
		final List<Rank> havePrizes = reversed.getHavePrizes();
		havePrizes.forEach(rank -> printEachResult(rank, ranks));
	}

	private static void printEachResult(final Rank rank, final Ranks ranks) {
		if (rank.equals(Rank.SECOND)) {
			printRank("%d개 일치, 보너스 볼 일치 (%d원)- %d개", rank, ranks);
			return;
		}
		printRank("%d개 일치 (%d원)- %d개", rank, ranks);
	}

	private static void printRank(final String message, final Rank rank, final Ranks ranks) {
		System.out.printf(message, rank.getMatchCount(), rank.getAmount(), ranks.getCountOf(rank));
		emptyLine();
	}

	public static void printLottoAmount(final int size) {
		System.out.printf("%d개를 구매했습니다.", size);
		emptyLine();
	}

	public static void printLottoState(final List<String> ticketLogs) {
		ticketLogs.forEach(System.out::println);
	}

	public static void printProfit(double calculate) {
		System.out.printf("총 수익률은 %.1f%%입니다.", calculate * MULTIPLIER_FOR_PERCENT);
	}

	private static void emptyLine() {
		System.out.println();
	}
}
