package lotto.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public enum WinningResult {
    ZERO(0, 0, ""),
    FIFTH_PRIZE(3, 5000, "3개 일치"),
    FOURTH_PRIZE(4, 50000, "4개 일치"),
    THIRD_PRIZE(5, 1500000, "5개 일치"),
    SECOND_PRIZE(6, 30_000_000, "5개 일치, 보너스 볼 일치"),
    FIRST_PRIZE(7, 2_000_000_000, "6개 일치");

    private final int hitCount;
    private final int winnings;
    private final String message;

    WinningResult(int hitCount, int winnings, String message) {
        this.hitCount = hitCount;
        this.winnings = winnings;
        this.message = message;
    }

    public static int calculateWinnings(List<Integer> hitCounts) {
        int totalReward = 0;
        for (int i = FIFTH_PRIZE.hitCount; i <= FIRST_PRIZE.hitCount; i++) {
            totalReward += hitCounts.get(i) * getWinningsByCount(i);
        }
        return totalReward;
    }

    public static List<List<String>> getWinningResult(List<Integer> hitCounts) {
        List<List<String>> winningResult = new ArrayList<>();
        for (int i = FIFTH_PRIZE.hitCount; i <= FIRST_PRIZE.hitCount; i++) {
            WinningResult result = getEachWinningResult(i);
            winningResult.add(Arrays.asList(
                    result.message,
                    Integer.toString(result.winnings),
                    Integer.toString(hitCounts.get(i))));
        }
        return winningResult;
    }

    private static WinningResult getEachWinningResult(int count) {
        return Arrays.stream(values())
                .filter(value -> count == value.hitCount)
                .findFirst()
                .get();
    }

    private static int getWinningsByCount(int count) {
        return Arrays.stream(values())
                .filter(value -> count == value.hitCount)
                .findFirst()
                .get()
                .winnings;
    }
}
