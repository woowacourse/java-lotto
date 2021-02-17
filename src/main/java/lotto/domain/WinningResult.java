package lotto.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public enum WinningResult {
    ZERO(0, 0, ""),
    ONE(1, 0, ""),
    TWO(2, 0, ""),
    THREE(3, 5000, "3개 일치"),
    FOUR(4, 50000, "4개 일치"),
    FIVE(5, 1500000, "5개 일치"),
    BONUS(6, 30000000, "5개 일치, 보너스 볼 일치"),
    SIX(7, 2000000000, "6개 일치");

    private int hitCount;
    private int reward;
    private String message;

    WinningResult(int hitCount, int reward, String message) {
        this.hitCount = hitCount;
        this.reward = reward;
        this.message = message;
    }

    public static int calculateTotalReward(List<Integer> hitCounts) {
        int totalReward = 0;
        for (int i = THREE.hitCount; i <= SIX.hitCount; i++) {
            totalReward += hitCounts.get(i) * findReward(i);
        }
        return totalReward;
    }

    private static int findReward(int count) {
        return Arrays.stream(values())
            .filter(value -> count == value.hitCount)
            .findFirst()
            .get()
            .reward;
    }

    public static String toString(List<Integer> hitCounts) {
        StringBuilder stringBuilder = new StringBuilder();
        IntStream.rangeClosed(THREE.hitCount, SIX.hitCount).forEach(index -> {
            WinningResult winningResult = find(index);
            stringBuilder.append(winningResult.message + " (" + winningResult.reward + ")원 - " +
                hitCounts.get(index) + "개" + System.lineSeparator());

        });
        return stringBuilder.toString();
    }

    private static WinningResult find(int count) {
        return Arrays.stream(values())
            .filter(value -> count == value.hitCount)
            .findFirst()
            .get();
    }
}
