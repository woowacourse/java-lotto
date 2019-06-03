package lotto.domain;

import java.util.Arrays;

public enum Rank {
    FIRST(6, 2000000000),
    SECOND(5, 1500000),
    THIRD(4, 50000),
    FOURTH(3, 5000),
    LOSE(0, 0);

    private static final int LOSING_LOTTO_MINIMUM_MATCH_COUNT = 3;
    private static final String ERROR_INVALID_VALUE = " : 유효하지 않은 값";

    private int countOfMatch;
    private int winningMoney;

    Rank(int countOfMatch, int winningMoney) {
        this.countOfMatch = countOfMatch;
        this.winningMoney = winningMoney;
    }

    public static Rank valueOf(final int countOfMatch) {
        if (countOfMatch < LOSING_LOTTO_MINIMUM_MATCH_COUNT) {
            return LOSE;
        }

        return Arrays.stream(values())
                .filter(rank -> rank.isMatchCount(countOfMatch))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(countOfMatch + ERROR_INVALID_VALUE));
    }

    private boolean isMatchCount(int countOfMatch) {
        return this.countOfMatch == countOfMatch;
    }

    public int getTotalRankWinningMoney(int countOfRank) {
        return countOfRank * winningMoney;
    }

    public int getCountOfMatch() {
        return countOfMatch;
    }

    public int getWinningMoney() {
        return winningMoney;
    }
}
