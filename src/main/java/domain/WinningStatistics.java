package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum WinningStatistics {

    NONE(0, 0, false),
    THREE(3, 5_000, false),
    FOUR(4, 50_000, false),
    FIVE(5, 1_500_000, false),
    FIVE_WITH_BONUS(5, 30_000_000, true),
    SIX(6, 2_000_000_000, false);

    private static final int SIZE_WITH_BONUS = 2;

    private final int matchCount;
    private final int prizeMoney;
    private final boolean isBonusMatched;

    WinningStatistics(int matchCount, int prizeMoney, boolean isBonusMatched) {
        this.matchCount = matchCount;
        this.prizeMoney = prizeMoney;
        this.isBonusMatched = isBonusMatched;
    }

    public static List<WinningCount> calculateWinningCount(List<Matcher> matchers) {
        List<WinningStatistics> winningStatisticsList = matchers.stream()
                .map(WinningStatistics::findMatchInfo)
                .toList();

        List<WinningCount> winningCounts = new ArrayList<>();

        for (WinningStatistics winningStatistics : WinningStatistics.values()) {
            addWinningCountDtoIfExists(winningStatistics, winningStatisticsList, winningCounts);
        }

        return winningCounts;
    }

    public static double calculateYield(int purchaseAmount, List<WinningCount> winningCounts) {
        int totalPrizeMoney = winningCounts.stream()
                .mapToInt(winningCount ->
                        winningCount.winningStatistics().getPrizeMoney() * winningCount.count())
                .sum();

        return (double) totalPrizeMoney / purchaseAmount;
    }

    private static void addWinningCountDtoIfExists(WinningStatistics winningStatistics,
                                                   List<WinningStatistics> winningStatisticsList,
                                                   List<WinningCount> winningCounts) {
        if (winningStatistics != WinningStatistics.NONE) {
            int count = Collections.frequency(winningStatisticsList, winningStatistics);
            winningCounts.add(new WinningCount(winningStatistics, count));
        }
    }

    private static WinningStatistics findMatchInfo(Matcher matcher) {
        List<WinningStatistics> winningStatisticsFilteredByMatchCount =
                Arrays.stream(WinningStatistics.values())
                        .filter(winningStatistics -> winningStatistics.isMatchCount(matcher.getWinningNumberCount()))
                        .toList();

        if (hasBonusCondition(winningStatisticsFilteredByMatchCount)) {
            return winningStatisticsFilteredByMatchCount.stream()
                    .filter(winningStatistics -> winningStatistics.isBonusMatched(matcher.isHasBonusNumber()))
                    .findFirst()
                    .orElse(NONE);
        }

        return winningStatisticsFilteredByMatchCount.getFirst();
    }

    private static boolean hasBonusCondition(List<WinningStatistics> winningStatisticsFilteredByMatchCount) {
        return winningStatisticsFilteredByMatchCount.size() == SIZE_WITH_BONUS;
    }

    private boolean isMatchCount(int value) {
        return matchCount == value;
    }

    private boolean isBonusMatched(boolean value) {
        return isBonusMatched == value;
    }

    public int getPrizeMoney() {
        return prizeMoney;
    }

    public int getMatchCount() {
        return matchCount;
    }
}
