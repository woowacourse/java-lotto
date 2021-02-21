package lotto.domain;

import java.util.stream.Stream;

public enum WinningBoard {
    ZERO(0, 0, ""),
    FIFTH(3, 5_000, "3개 일치"),
    FOURTH(4, 50_000, "4개 일치"),
    THIRD(5, 1_500_000, "5개 일치"),
    SECOND(5, 30_000_000, "5개 일치, 보너스 볼 일치"),
    FIRST(6, 2_000_000_000, "6개 일치");

    private final int hitCount;
    private final int reward;
    private final String message;

    WinningBoard(int hitCount, int reward, String message) {
        this.hitCount = hitCount;
        this.reward = reward;
        this.message = message;
    }

    public static WinningBoard findWinnings(int hits, boolean hitBonus) {
        if (hits == SECOND.hitCount && hitBonus) {
            return SECOND;
        }
        return Stream.of(values())
            .filter(value -> hits == value.hitCount)
            .findFirst()
            .orElse(ZERO);
    }

    public int getReward() {
        return reward;
    }

    public String getMessage() {
        return message;
    }
}
