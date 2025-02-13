package domain;

public record WinningCountDto(
        WinningStatistics winningStatistics,
        int count
) {

    public static final String WINNING_COUNT_FORMAT = "%d개 일치 (%d원)- %d개";

    @Override
    public String toString() {
        return String.format(WINNING_COUNT_FORMAT, winningStatistics.getMatchCount(),
                winningStatistics.getPrizeMoney(), count);
    }
}
