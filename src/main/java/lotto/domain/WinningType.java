package lotto.domain;

public enum WinningType {

    MISS(0, 0, false),
    FIFTH(3, 5_000, false),
    FOURTH(4, 50_000, false),
    THIRD(5, 1_500_000, false),
    SECOND(5, 30_000_000, true),
    FIRST(6, 2_000_000_000, false);

    private final int matchNum;
    private final int reward;
    private final boolean bonus;

    private WinningType(final int matchNum, final int reward, boolean bonus) {
        this.matchNum = matchNum;
        this.reward = reward;
        this.bonus = bonus;
    }

    public static WinningType valueOf(final int matchNum, boolean hasBonus) {
        if ( (hasBonus == SECOND.bonus) && SECOND.matchNum == matchNum) {
            return SECOND;
        }

        for (WinningType value : WinningType.values()) {
            if (value.getMatchNum() == matchNum) {
                return value;
            }
        }
        return MISS;
    }

    public int getMatchNum() {
        return matchNum;
    }

    public int getReward() {
        return reward;
    }
}
