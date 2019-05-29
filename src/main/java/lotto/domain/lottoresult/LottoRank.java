package lotto.domain.lottoresult;

import java.util.Arrays;

public enum LottoRank {
    FIRST(6, 2_000_000_000),
    SECOND(5, 1_500_000),
    THIRD(4, 50_000),
    FOURTH(3, 5_000),
    FAIL(0, 0);

    private static final int WINNING_MIN_COUNT = 3;

    private final int countOfMatch;
    private final int reward;

    LottoRank(int countOfMatch, int reward) {
        this.countOfMatch = countOfMatch;
        this.reward = reward;
    }

    public static LottoRank rankOf(int countOfMatch) {
        if (countOfMatch < WINNING_MIN_COUNT) {
            return FAIL;
        }
        return Arrays.stream(values())
                .filter(rank -> rank.countOfMatch == countOfMatch)
                .findAny()
                .get();
    }

    public int getReward() {
        return reward;
    }
}
