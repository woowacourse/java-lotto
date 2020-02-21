package lotto.view;

import java.util.function.Predicate;

import lotto.domain.LottoTicket;
import lotto.domain.LottoTickets;
import lotto.domain.Rank;
import lotto.domain.Ranks;

public class OutputView {
	private static final int MULTIPLIER_FOR_PERCENT = 100;

	public static void printResult(final Ranks ranks) {
		final Ranks reversed = Rank.getOrderReversed();
		reversed.stream()
			.filter(hasPrize())
			.forEach(rank -> printEachResult(rank, ranks));
	}

	private static Predicate<Rank> hasPrize() {
		return rank -> !rank.equals(Rank.NONE);
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

	public static void printLottoAmount(final LottoTickets lottoTickets) {
		System.out.printf("%d개를 구매했습니다.", lottoTickets.stream().count());
		emptyLine();
	}

	public static void printLottoState(final LottoTickets lottoTickets) {
		lottoTickets.stream()
			.map(LottoTicket::toString)
			.forEach(System.out::println);
	}

	public static void printProfit(double calculate) {
		System.out.printf("총 수익률은 %.1f%%입니다.", calculate * MULTIPLIER_FOR_PERCENT);
	}

	private static void emptyLine() {
		System.out.println();
	}
}
