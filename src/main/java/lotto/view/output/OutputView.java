package lotto.view.output;

import static lotto.view.output.OutputMessage.ANALYSIS_EXTRA_FORMAT;
import static lotto.view.output.OutputMessage.ANALYSIS_FORMAT;
import static lotto.view.output.OutputMessage.PROFIT_RAGE_FORMAT;
import static lotto.view.output.OutputMessage.TICKET_COUNT_FORMAT;
import static lotto.view.output.OutputMessage.TICKET_FORMAT;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lotto.domain.rank.Rank;
import lotto.dto.AnalysisDto;
import lotto.dto.TicketBundlesDto;
import lotto.dto.TicketDto;
import lotto.view.utils.Delimiter;

public class OutputView {

    public void printMessage(final OutputMessage outputMessage) {
        printMessage(outputMessage.getMessage());
    }

    private void printMessage(final String message) {
        System.out.println(message);
    }

    public void printTicketCount(final TicketBundlesDto ticketBundlesDto) {
        final int manualTicketCount = ticketBundlesDto.getManualTickets().size();
        final int randomTicketCount = ticketBundlesDto.getAutomaticTickets().size();
        final String message = String.format(TICKET_COUNT_FORMAT.getMessage(), manualTicketCount, randomTicketCount);
        printMessage(message);
    }

    public void printAllTickets(final TicketBundlesDto ticketBundlesDto) {
        printTickets(ticketBundlesDto.getManualTickets());
        printTickets(ticketBundlesDto.getAutomaticTickets());
    }

    private void printTickets(final List<TicketDto> ticketDtos) {
        ticketDtos.stream()
                .map(this::makeTicketFormat)
                .forEach(this::printMessage);
    }

    private String makeTicketFormat(final TicketDto ticketDto) {
        final List<String> ticketBalls = ticketDto.getBallNumbers()
                .stream()
                .map(String::valueOf)
                .collect(Collectors.toUnmodifiableList());

        final String joinedBallNumbers = Delimiter.COMMA.joinWith(ticketBalls);
        return String.format(TICKET_FORMAT.getMessage(), joinedBallNumbers);
    }

    public void printAnalysis(final AnalysisDto analysisDto) {
        printAnalysisRankCounts(analysisDto);
        printAnalysisProfitRate(analysisDto);
    }

    private void printAnalysisRankCounts(final AnalysisDto analysisDto) {
        final Map<Rank, Long> rankCounts = analysisDto.getRankCounts();
        for (Rank rank : Rank.values()) {
            final long rankCount = selectRankCountIfContainsRank(rankCounts, rank);
            printAnalysisRankCount(rank, rankCount);
        }
    }

    private long selectRankCountIfContainsRank(final Map<Rank, Long> rankCounts, final Rank rank) {
        if (rankCounts.containsKey(rank)) {
            return rankCounts.get(rank);
        }
        final long defaultRankCount = 0;
        return defaultRankCount;
    }

    private void printAnalysisRankCount(final Rank rank, final long rankCount) {
        final int matchCount = rank.getMatchCount();
        final long prizeMoney = rank.getPrizeMoney();

        final String messageFormat = selectMessageFormatOfAnalysisRank(rank);
        printMessage(String.format(messageFormat, matchCount, prizeMoney, rankCount));
    }

    private String selectMessageFormatOfAnalysisRank(final Rank rank) {
        if (rank.isTrueThatBonusMatchMustBeTrue()) {
            return ANALYSIS_EXTRA_FORMAT.getMessage();
        }
        return ANALYSIS_FORMAT.getMessage();
    }

    private void printAnalysisProfitRate(final AnalysisDto analysisDto) {
        final double profitRate = analysisDto.getProfitRate();
        printMessage(String.format(PROFIT_RAGE_FORMAT.getMessage(), profitRate));
    }

}
