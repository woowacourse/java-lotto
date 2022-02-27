package view;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import domain.Rank;
import domain.Ticket;
import domain.Tickets;

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


}
