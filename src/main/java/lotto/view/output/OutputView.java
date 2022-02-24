package lotto.view.output;

import static lotto.view.output.OutputMessage.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lotto.domain.rank.Rank;
import lotto.domain.ticket.Ball;
import lotto.domain.ticket.Ticket;
import lotto.domain.ticket.Tickets;
import lotto.dto.AnalysisDto;

public class OutputView {

	private void printMessage(final String message) {
		System.out.println(message);
	}

	private void printMessage(final OutputMessage outputMessage) {
		printMessage(outputMessage.getMessage());
	}

	public void printMessageOfRequestCreditMoney() {
		printMessage(REQUEST_CREDIT_MONEY);
	}

	public void printMessageOfRequestWinningNumbers() {
		printMessage(REQUEST_WINNING_NUMBERS);
	}

	public void printMessageOfRequestBonusNumber() {
		printMessage(REQUEST_BONUS_NUMBER);
	}

	public void printTitleOfAnalysis() {
		printMessage(TITLE_OF_ANALYSIS);
	}

	public void printDividingLine() {
		printMessage(DIVIDING_LINE);
	}

	public void printEmptyLine() {
		printMessage(EMPTY_STRING);
	}

	public void printTicketCount(final Tickets tickets) {
		final int ticketCount = tickets.getTicketsCount();
		final String message = String.format(TICKET_COUNT_FORMAT.getMessage(), ticketCount);
		printMessage(message);
	}

	public void printTickets(final Tickets tickets) {
		tickets.getTickets()
			.stream()
			.map(this::makeTicketFormat)
			.forEach(this::printMessage);
	}

	private String makeTicketFormat(final Ticket ticket) {
		final List<String> ticketBalls = ticket.getBalls()
			.stream()
			.map(Ball::getBallNumber)
			.map(String::valueOf)
			.collect(Collectors.toUnmodifiableList());

		return "[" + String.join(",", ticketBalls) + "]";
	}

	public void printAnalysis(final AnalysisDto analysisDto) {
		final Map<Rank, Integer> rankCounts = analysisDto.getRankCounts();

		for (Rank rank : rankCounts.keySet()) {
			final int count = rankCounts.get(rank);
			String message = String.format("%d개 일치", rank.getMatchCount());
			if (rank.getBonusBallMatched()) {
				message += ", 보너스 볼 일치";
			}
			message += String.format("(%d원) - %d개", rank.getPrize(), count);
			printMessage(message);
		}

		final double rate = analysisDto.getRate();
		final String message = String.format(PROFIT_RAGE_FORMAT.getMessage(), rate);
		printMessage(message);
	}

}
