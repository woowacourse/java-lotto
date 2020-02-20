package lotto.view;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import lotto.domain.LottoTicket;
import lotto.domain.LottoTickets;
import lotto.domain.Rank;
import lotto.domain.Ranks;

public class OutputView {
	public static final int PERCENT = 100;

	public static void printResult(final Ranks ranks) {
		final Rank[] rankValues = Rank.values();

		List<Rank> rankResults = Arrays.asList(rankValues);
		Collections.reverse(rankResults);

		rankResults.stream()
			.filter(hasPrize())
			.forEach(rank -> printEachResult(rank, ranks));
	}

	private static Predicate<Rank> hasPrize() {
		return rank -> !rank.equals(Rank.NONE);
	}

	private static void printEachResult(final Rank rank, final Ranks ranks) {
		if (rank.equals(Rank.SECOND)) {
			System.out.printf("%d개 일치, 보너스 볼 일치 (%d원)- %d개",
				rank.getMatchCount(), rank.getAmount(), ranks.getCountOf(rank));
			emptyLine();
			return;
		}
		System.out.printf("%d개 일치 (%d원)- %d개", rank.getMatchCount(), rank.getAmount(), ranks.getCountOf(rank));
		emptyLine();
	}

	public static void printLottoState(final LottoTickets lottoTickets) {
		System.out.printf("%d개를 구매했습니다.", lottoTickets.stream().count());
		emptyLine();
		lottoTickets.stream()
			.map(LottoTicket::toString)
			.forEach(System.out::println);
	}

	public static void printProfit(double calculate) {
		System.out.printf("총 수익률은 %.1f%%입니다.", calculate * PERCENT);
	}

	private static void emptyLine() {
		System.out.println();
	}
}
