package view;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import domain.Rank;
import domain.Ticket;
import domain.Tickets;
import dto.AnalysisDto;

public class OutputView {
	private static final String COUNT_MESSAGE = "개를 구매했습니다.";
	private static final String ANALYSIS_TITLE = "당첨 통계";
	private static final String DIVIDING_LINE = "---------";
	private static final String PROFIT_RATE_MESSAGE_FORMAT = "총 수익률은 %.2f입니다.";
	private static final String ANALYSIS_MATCH_COUNT_MESSAGE = "%d개 일치";
	private static final String ANALYSIS_BONUS_BALL_COUNT_MESSAGE = ", 보너스 볼 일치";
	private static final String ANALYSIS_PRIZE_COUNT_MESSAGE = "(%d원) - %d개";

	private static final String START_SIGN = "[";
	private static final String END_SIGN = "]";

	private static final String ORIGIN_TICKET_DELIMITER = ",";

	public static void printTickets(Tickets tickets) {
		printTicketCount(tickets);

		tickets.getTickets()
			.stream()
			.map(OutputView::makeTicketFormat)
			.forEach(System.out::println);

		System.out.println();
	}

	private static void printTicketCount(Tickets tickets) {
		System.out.println(tickets.getSize() + COUNT_MESSAGE);
	}

	private static String makeTicketFormat(Ticket ticket) {
		List<String> ticketBalls = ticket.getBallNumbers()
			.stream()
			.map(String::valueOf)
			.collect(Collectors.toUnmodifiableList());

		return START_SIGN + String.join(ORIGIN_TICKET_DELIMITER, ticketBalls) + END_SIGN;
	}

	public static void printAnalysis(AnalysisDto analysisDto) {
		System.out.println(ANALYSIS_TITLE);
		System.out.println(DIVIDING_LINE);

		Map<Rank, Integer> rankCounts = analysisDto.getRankCounts();

		for (Rank rank : rankCounts.keySet()) {
			int count = rankCounts.get(rank);
			String message = String.format(ANALYSIS_MATCH_COUNT_MESSAGE, rank.getMatchCount());
			if (rank.getBonusBallMatched()) {
				message += ANALYSIS_BONUS_BALL_COUNT_MESSAGE;
			}
			message += String.format(ANALYSIS_PRIZE_COUNT_MESSAGE, rank.getPrize(), count);
			System.out.println(message);
		}

		double rate = analysisDto.getRate();
		System.out.printf(PROFIT_RATE_MESSAGE_FORMAT, rate);
	}
}
