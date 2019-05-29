package lotto.domain;

public enum  WinnerType {

    FIRST(6, 2_000_000_000),
    SECOND(5, 1_500_000),
    THIRD(4, 50_000),
    FOURTH(3, 5_000),
    MISS(0, 0);

    private final int matchNum;
    private final int reward;

    private WinnerType(final int matchNum, final int reward) {
        this.matchNum = matchNum;
        this.reward = reward;
    }

    public static WinnerType valueOf(final int matchNum) {
        for (WinnerType value : WinnerType.values()) {
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
