package domain.winning;

import static domain.winning.WinningStatistics.SIZE_WITH_BONUS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class WinningCounter {

    private final WinningStatistics winningStatistics;
    private final int count;

    public static List<WinningCounter> count(List<Matcher> matchers) {
        List<WinningStatistics> winningStatisticsList = matchers.stream()
                .map(WinningCounter::findMatchInfo)
                .toList();

        List<WinningCounter> winningCounters = new ArrayList<>();
        for (WinningStatistics winningStatistics : WinningStatistics.values()) {
            addWinningCountDtoIfExists(winningStatistics, winningStatisticsList, winningCounters);
        }
        return winningCounters;
    }

    private WinningCounter(WinningStatistics winningStatistics, int count) {
        this.winningStatistics = winningStatistics;
        this.count = count;
    }

    private static void addWinningCountDtoIfExists(WinningStatistics winningStatistics,
                                                   List<WinningStatistics> winningStatisticsList,
                                                   List<WinningCounter> winningCounters) {
        if (winningStatistics != WinningStatistics.NONE) {
            int count = Collections.frequency(winningStatisticsList, winningStatistics);
            winningCounters.add(new WinningCounter(winningStatistics, count));
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
                    .orElse(WinningStatistics.NONE);
        }

        return winningStatisticsFilteredByMatchCount.getFirst();
    }

    private static boolean hasBonusCondition(List<WinningStatistics> winningStatisticsFilteredByMatchCount) {
        return winningStatisticsFilteredByMatchCount.size() == SIZE_WITH_BONUS;
    }

    public int getCount() {
        return count;
    }

    public WinningStatistics getWinningStatistics() {
        return winningStatistics;
    }
}
