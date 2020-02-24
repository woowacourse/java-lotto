package lotto.domain.result;

import java.util.Arrays;

public enum WinningValue {

    ZERO(0, 0, " ", false),
    FIFTH(3, 5_000, "3개 일치",false),
    FORTH(4, 50_000, "4개 일치",false),
    THIRD(5, 1_500_000, "5개 일치",false),
    SECOND(5, 30_000_000, "5개 일치, 보너스번호 일치",true),
    FIRST(6, 2_000_000_000, "6개 일치",false);

    private final int hitCount;
    private final int reward;
    private final String message;
    private final boolean isBonus;

    WinningValue(int hitCount, int reward, String message, boolean isBonus) {
        this.message = message;
        this.isBonus = isBonus;
        this.hitCount = hitCount;
        this.reward = reward;
    }

    public static WinningValue valueOf(int hitCount, boolean hitBonus) {
        return Arrays.stream(WinningValue.values())
                .filter(winningValue -> winningValue.hitCount == hitCount && winningValue.isBonus == hitBonus)
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
