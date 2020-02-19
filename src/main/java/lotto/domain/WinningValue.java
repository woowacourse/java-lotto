package lotto.domain;

import java.util.Arrays;

public enum WinningValue {

    ZERO(0, 0),
    FIFTH(3, 5000),
    FORTH(4, 50000),
    THIRD(5, 1500000),
    SECOND(5, 30000000),
    FIRST(6, 2000000000);

    private final int correctCount;
    private final int reward;

    WinningValue(int correctCount, int reward) {
        this.correctCount = correctCount;
        this.reward = reward;
    }

    public static WinningValue findWinningValue(int correctCount, boolean correctBonus) {
        if (correctCount < FIFTH.correctCount) return ZERO;
        if (correctCount == SECOND.correctCount && correctBonus) return SECOND;
        return Arrays.stream(WinningValue.values())
                .filter(winningValue -> winningValue.correctCount == correctCount)
                .findFirst()
                .orElseThrow(IllegalAccessError::new);
    }

    public int getReward() {
        return reward;
    }
}
