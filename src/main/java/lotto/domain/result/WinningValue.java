package lotto.domain.result;

import java.util.Arrays;

public enum WinningValue {

    ZERO(0, 0, ""),
    FIFTH(3, 5_000, "3개 일치"),
    FORTH(4, 50_000, "4개 일치"),
    THIRD(5, 1_500_000, "5개 일치"),
    SECOND(5, 30_000_000, "5개 일치, 보너스번호 일치"),
    FIRST(6, 2_000_000_000, "6개 일치");

    private final int hitCount;
    private final int reward;
    private final String message;

    WinningValue(int hitCount, int reward, String message) {
        this.message = message;
        this.hitCount = hitCount;
        this.reward = reward;
    }

    public static WinningValue valueOf(int hitCount, boolean hitBonus) {
        if (isSecond(hitCount, hitBonus)) return SECOND;
        return Arrays.stream(WinningValue.values())
                .filter(winningValue -> winningValue.hitCount == hitCount)
                .findFirst()
                .orElse(ZERO);
    }

    private static boolean isSecond(int hitCount, boolean hitBonus) {
        return hitCount == SECOND.hitCount && hitBonus;
    }

    public int getReward() {
        return reward;
    }

    public String getMessage() {
        return message;
    }
}
