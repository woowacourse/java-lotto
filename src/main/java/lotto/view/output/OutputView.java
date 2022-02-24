package lotto.view.output;

import static lotto.view.output.OutputMessage.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lotto.domain.rank.Rank;
import lotto.dto.AnalysisDto;
import lotto.dto.TicketDto;
import lotto.utils.Delimiter;

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

	public void printTicketCount(final List<TicketDto> ticketDtos) {
		final int ticketCount = ticketDtos.size();
		final String message = String.format(TICKET_COUNT_FORMAT.getMessage(), ticketCount);
		printMessage(message);
	}

	public void printTickets(final List<TicketDto> ticketDtos) {
		ticketDtos.stream()
			.map(this::makeTicketFormat)
			.forEach(this::printMessage);
	}

	private String makeTicketFormat(final TicketDto ticketDto) {
		final List<String> ticketBalls = ticketDto.getBallNumbers()
			.stream()
			.map(String::valueOf)
			.collect(Collectors.toUnmodifiableList());

		final String joinedBallNumbers = Delimiter.joinWithComma(ticketBalls);
		return String.format(TICKET_FORMAT.getMessage(), joinedBallNumbers);
	}

	public void printAnalysis(final AnalysisDto analysisDto) {
		final Map<Rank, Integer> rankCounts = analysisDto.getRankCounts();

		rankCounts.forEach(this::printAnalysisRank);

		final double rate = analysisDto.getProfitRate();
		final String message = String.format(PROFIT_RAGE_FORMAT.getMessage(), rate);
		printMessage(message);
	}

	private void printAnalysisRank(final Rank rank, final int rankCount) {
		final int matchCount = rank.getMatchCount();
		final long prizeMoney = rank.getPrizeMoney();

		final String messageFormat = selectMessageFormatOfAnalysisRank(rank.getBonusMatched());
		final String message = String.format(messageFormat, matchCount, prizeMoney, rankCount);
		printMessage(message);
	}

	private String selectMessageFormatOfAnalysisRank(final boolean isExtra) {
		if (isExtra) {
			return ANALYSIS_EXTRA_FORMAT.getMessage();
		}
		return ANALYSIS_FORMAT.getMessage();
	}

}
