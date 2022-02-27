package view;

import domain.Tickets;
import domain.dto.WinningAnalyzeDto;

public class OutputView {
	private static final String PAYMENT_COUNT_MESSAGE = "개를 구매했습니다.";
	private static final String ANALYSIS_TITLE_MESSAGE = "당첨 통계";
	private static final String DIVIDING_LINE = "---------";

	public static void printTickets(Tickets tickets) {
		System.out.println(tickets.size() + PAYMENT_COUNT_MESSAGE);
		System.out.println(tickets.toString());
	}

	public static void printStatistics(WinningAnalyzeDto winningAnalyzeDto) {
		System.out.println();
		System.out.println(ANALYSIS_TITLE_MESSAGE);
		System.out.println(DIVIDING_LINE);

		/*System.out.println(winningAnalyze.toString());*/
	}
}
