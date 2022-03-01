package lotto.view.output;

import static lotto.view.output.OutputMessage.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lotto.domain.analysis.Analysis;
import lotto.domain.ticket.Ticket;
import lotto.domain.ticket.Tickets;
import lotto.domain.winning.Rank;
import lotto.view.utils.Delimiter;

public class OutputView {

    public void printMessage(final OutputMessage outputMessage) {
        printMessage(outputMessage.getMessage());
    }

    private void printMessage(final String message) {
        System.out.println(message);
    }

    public void printTicketCount(final Tickets tickets) {
        final int ticketCount = tickets.getSize();
        final String message = String.format(TICKET_COUNT_FORMAT.getMessage(), ticketCount);
        printMessage(message);
    }

    public void printTickets(final Tickets tickets) {
        tickets.getTickets().stream()
                .map(this::makeTicketFormat)
                .forEach(this::printMessage);
    }

    private String makeTicketFormat(final Ticket ticketDto) {
        final List<String> ticketBalls = ticketDto.getBallNumbers()
                .stream()
                .map(String::valueOf)
                .collect(Collectors.toUnmodifiableList());

        final String joinedBallNumbers = Delimiter.COMMA.joinWith(ticketBalls);
        return String.format(TICKET_FORMAT.getMessage(), joinedBallNumbers);
    }

    public void printAnalysis(final Analysis analysis) {
        printAnalysisRankCounts(analysis);
        printAnalysisProfitRate(analysis);
    }

    private void printAnalysisRankCounts(final Analysis analysis) {
        final Map<Rank, Long> rankCounts = analysis.getRankCounts();
        for (Rank rank : Rank.values()) {
            long rankCount = 0;
            if (rankCounts.containsKey(rank)) {
                rankCount = rankCounts.get(rank);
            }
            printAnalysisRankCount(rank, rankCount);
        }
    }

    private void printAnalysisRankCount(final Rank rank, final long rankCount) {
        final int matchCount = rank.getMatchCount();
        final long prizeMoney = rank.getPrizeMoney();

        final String messageFormat = selectMessageFormatOfAnalysisRank(rank);
        printMessage(String.format(messageFormat, matchCount, prizeMoney, rankCount));
    }

    private String selectMessageFormatOfAnalysisRank(final Rank rank) {
        if (rank.getBonusMatched()) {
            return ANALYSIS_EXTRA_FORMAT.getMessage();
        }
        return ANALYSIS_FORMAT.getMessage();
    }

    private void printAnalysisProfitRate(final Analysis analysis) {
        final double profitRate = analysis.getProfitRate();
        printMessage(String.format(PROFIT_RAGE_FORMAT.getMessage(), profitRate));
    }

}
