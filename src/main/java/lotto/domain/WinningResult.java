package lotto.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public enum WinningResult {
    ZERO(0, 0, ""),
    FIFTH_PRIZE(3, 5000, "3개 일치"),
    FOURTH_PRIZE(4, 50000, "4개 일치"),
    THIRD_PRIZE(5, 1500000, "5개 일치"),
    SECOND_PRIZE(6, 30000000, "5개 일치, 보너스 볼 일치"),
    FIRST_PRIZE(7, 2000000000, "6개 일치");

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
            totalReward += hitCounts.get(i) * getWinnings(i);
        }
        return totalReward;
    }

    private static int getWinnings(int count) {
        return Arrays.stream(values())
                .filter(value -> count == value.hitCount)
                .findFirst()
                .get()
                .winnings;
    }

    public static String toString(List<Integer> hitCounts) {
        StringBuilder stringBuilder = new StringBuilder();
        IntStream.rangeClosed(FIFTH_PRIZE.hitCount, FIRST_PRIZE.hitCount).forEach(index -> {
            WinningResult winningResult = getWinningResult(index);
            stringBuilder.append(winningResult.message + " (" + winningResult.winnings + ")원 - " +
                    hitCounts.get(index) + "개" + System.lineSeparator());

        });
        return stringBuilder.toString();
    }

    private static WinningResult getWinningResult(int count) {
        return Arrays.stream(values())
                .filter(value -> count == value.hitCount)
                .findFirst()
                .get();
    }
}
