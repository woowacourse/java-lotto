package domain;

public record WinningCountDto(
        WinningStatistics winningStatistics,
        int count
) {
}
