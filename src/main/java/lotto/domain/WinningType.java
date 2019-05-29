package lotto.domain;

public enum WinningType {

    FOURTH(3, 5_000),
    THIRD(4, 50_000),
    SECOND(5, 1_500_000),
    FIRST(6, 2_000_000_000);

    private final int matchNum;
    private final int reward;

    private WinningType(final int matchNum, final int reward) {
        this.matchNum = matchNum;
        this.reward = reward;
    }

    public static WinningType valueOf(final int matchNum) {
        for (WinningType value : WinningType.values()) {
            if (value.getMatchNum() == matchNum) {
                return value;
            }
        }
        return null;
    }

    public int getMatchNum() {
        return matchNum;
    }

    public int getReward() {
        return reward;
    }
}
