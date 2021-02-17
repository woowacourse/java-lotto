package lotto.domain;

import java.util.Arrays;
import java.util.List;

public enum WinningResult {
    ZERO(0,0,""),
    ONE(1,0,""),
    TWO(2,0,""),
    THREE(3, 5000, "3개 일치"),
    FOUR(4, 50000, "4개 일치"),
    FIVE(5, 1500000, "5개 일치"),
    SIX(6, 2000000000, "6개 일치"),
    BONUS(7, 30000000, "5개 일치, 보너스 볼 일치");

    private int hitCount;
    private int reward;
    private String message;

    WinningResult(int hitCount, int reward, String message) {
        this.hitCount = hitCount;
        this.reward = reward;
        this.message = message;
    }

    public static int calculateTotalReward(List<Integer> hitCounts){
        return hitCounts.stream()
            .mapToInt(WinningResult::findReward)
            .sum();
    }

    private static int findReward(int count) {
        return Arrays.stream(values())
            .filter(value -> count == value.hitCount)
            .findFirst()
            .get()
            .reward;
    }

    public static String toString(int count) {
        WinningResult winningResult = find(count);
        return winningResult.message + " (" + winningResult.reward + ")원 -";
    }

    private static WinningResult find(int count) {
        return Arrays.stream(values())
            .filter(value -> count == value.hitCount)
            .findFirst()
            .get();
    }
}
