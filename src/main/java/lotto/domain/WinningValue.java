package lotto.domain;

import java.util.Arrays;

public enum WinningValue {

    ZERO(0, 0, ""),
    FIFTH(3, 5000, "3개 일치"),
    FORTH(4, 50000, "4개 일치"),
    THIRD(5, 1500000, "5개 일치"),
    SECOND(5, 30000000, "5개 일치(보너스번호 일치)"),
    FIRST(6, 2000000000, "6개 일치");

    private final int correctCount;
    private final int reward;
    private final String message;

    WinningValue(int correctCount, int reward, String message) {
        this.message = message;
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

    public String getMessage() {
        return message;
    }
}
