package lotto.view.output;

import static lotto.view.output.OutputMessage.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lotto.domain.winning.Rank;
import lotto.dto.AnalysisDto;
import lotto.dto.TicketDto;
import lotto.dto.TicketManagerDto;
import lotto.view.utils.Delimiter;

public class OutputView {

    public void printMessage(final OutputMessage outputMessage) {
        printMessage(outputMessage.getMessage());
    }

    private void printMessage(final String message) {
        System.out.println(message);
    }

    public void printTicketCount(final TicketManagerDto ticketManagerDto) {
        final int manualTicketCount = ticketManagerDto.getPreparedTicketDtos().size();
        final int randomTicketCount = ticketManagerDto.getGeneratedTicketDtos().size();
        final String message = String.format(TICKET_COUNT_FORMAT.getMessage(), manualTicketCount, randomTicketCount);
        printMessage(message);
    }

    public void printAllTickets(final TicketManagerDto ticketManagerDto) {
        printTickets(ticketManagerDto.getPreparedTicketDtos());
        printTickets(ticketManagerDto.getGeneratedTicketDtos());
    }

    private void printTickets(final List<TicketDto> ticketDtos) {
        ticketDtos.stream()
                .map(this::makeTicketFormat)
                .forEach(this::printMessage);
    }

    private String makeTicketFormat(final TicketDto ticketDtos) {
        final List<String> ticketBalls = ticketDtos.getBallNumbers()
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

    private void printAnalysisProfitRate(final AnalysisDto analysisDto) {
        final double profitRate = analysisDto.getProfitRate();
        printMessage(String.format(PROFIT_RAGE_FORMAT.getMessage(), profitRate));
    }

}
