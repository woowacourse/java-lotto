package view;

import java.util.List;
import java.util.Map;

import domain.Rank;
import domain.Ticket;
import domain.dto.WinningAnalyzeDto;

public class OutputView {
	private static final String TICKET_START_SIGN = "[";
	private static final String TICKET_END_SIGN = "]";
	private static final String BALL_DELIMITER = ",";
	private static final String PAYMENT_COUNT_MESSAGE = "개를 구매했습니다.";
	private static final String MANUAL_LOTTO_NUMBER_MESSAGE = "수동으로 %d, ";
	private static final String AUTO_LOTTO_NUMBER_MESSAGE = "자동으로 %d";
	private static final String ANALYSIS_TITLE_MESSAGE = "당첨 통계";
	private static final String DIVIDING_LINE = "---------";
	private static final String MATCH_COUNT_PRIZE_MESSAGE = "%d개 일치";
	private static final String PRIZE_MESSAGE = " (%d원)";
	private static final String BONUS_BALL_MESSAGE = ", 보너스 볼 일치";
	private static final String RANK_COUNT_MESSAGE = " - %d개 %n";
	private static final String PROFIT_RATE_MESSAGE = "총 수익률은 %.2f입니다.";
	private static final String LINE_DELIMITER = "\n";

	public static void printTickets(List<Ticket> tickets, final int manualCount) {
		System.out.println();

		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(String.format(MANUAL_LOTTO_NUMBER_MESSAGE, manualCount))
			.append(String.format(AUTO_LOTTO_NUMBER_MESSAGE, tickets.size() - manualCount))
			.append(PAYMENT_COUNT_MESSAGE);

		System.out.println(stringBuilder.toString());
		tickets.forEach(OutputView::printTicket);
	}

	private static void printTicket(Ticket ticket) {
		StringBuilder stringBuilder = new StringBuilder(TICKET_START_SIGN);
		stringBuilder.append(String.join(BALL_DELIMITER, ticket.makeBallsToStrings()))
			.append(TICKET_END_SIGN);

		System.out.println(stringBuilder.toString());
	}

	public static void printStatistics(WinningAnalyzeDto winningAnalyzeDto) {
		System.out.println(LINE_DELIMITER + ANALYSIS_TITLE_MESSAGE);
		System.out.println(DIVIDING_LINE);

		Map<Rank, Integer> analyzeResult = winningAnalyzeDto.getAnalyzeResult();
		double profitRate = winningAnalyzeDto.getProfitRate();

		analyzeResult.keySet()
			.forEach(rank -> OutputView.printStatistic(rank, analyzeResult.get(rank)));

		System.out.printf(PROFIT_RATE_MESSAGE, profitRate);
	}

	private static void printStatistic(Rank rank, int count) {
		System.out.printf(MATCH_COUNT_PRIZE_MESSAGE, rank.getMatchCount());

		if (rank.isBonusBallMatched()) {
			System.out.print(BONUS_BALL_MESSAGE);
		}

		System.out.printf(PRIZE_MESSAGE, rank.getPrize());
		System.out.printf(RANK_COUNT_MESSAGE, count);
	}
}
