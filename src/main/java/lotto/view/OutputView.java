package lotto.view;

import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;

import java.util.List;
import java.util.function.Function;

import lotto.domain.LottoTicket;
import lotto.domain.Rank;
import lotto.domain.Ranks;

public class OutputView {
	private static final int MULTIPLIER_FOR_PERCENT = 100;
	private static final String DELIMITER = ", ";
	private static final String START_BRACKET = "[";
	private static final String END_BRACKET = "]";

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

	public static void printLottoState(final List<LottoTicket> tickets) {
		tickets.stream()
			.map(LottoTicket::getNumbers)
			.map(getNumbersState())
			.forEach(System.out::println);
	}

	private static Function<List<String>, String> getNumbersState() {
		return numbers -> numbers.stream()
			.sorted(comparingInt(Integer::parseInt))
			.collect(joining(DELIMITER, START_BRACKET, END_BRACKET));
	}

	public static void printProfit(double calculate) {
		System.out.printf("총 수익률은 %.1f%%입니다.", calculate * MULTIPLIER_FOR_PERCENT);
	}

	private static void emptyLine() {
		System.out.println();
	}
}
