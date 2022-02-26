package lotto.view.output;

import static lotto.view.output.OutputMessage.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lotto.domain.rank.Rank;
import lotto.dto.AnalysisDto;
import lotto.dto.TicketDto;
import lotto.dto.TicketsDto;
import lotto.view.utils.Delimiter;

public class OutputView {

    public void printMessage(final OutputMessage outputMessage) {
        printMessage(outputMessage.getMessage());
    }

    private void printMessage(final String message) {
        System.out.println(message);
    }

    public void printTicketCount(final TicketsDto ticketDtos) {
        final int ticketCount = ticketDtos.getSize();
        final String message = String.format(TICKET_COUNT_FORMAT.getMessage(), ticketCount);
        printMessage(message);
    }

    public void printTickets(final TicketsDto ticketDtos) {
        ticketDtos.getTicketDtos().stream()
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
