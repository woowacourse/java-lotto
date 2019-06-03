package lotto.domain;

import java.util.Arrays;

public enum Rank {
    FIRST(6, 2000000000),
    SECOND(5, 1500000),
    THIRD(4, 50000),
    FOURTH(3, 5000),
    LOSE(0, 0);

    private static final int LOSING_LOTTO_MINIMUM_MATCH_COUNT = 3;
    
    private int countOfMatch;
    private int winningMoney;

    Rank(int countOfMatch, int winningMoney) {
        this.countOfMatch = countOfMatch;
        this.winningMoney = winningMoney;
    }

    public Rank valueOf(final int countOfMatch) {
        if (countOfMatch <= LOSING_LOTTO_MINIMUM_MATCH_COUNT) {
            return LOSE;
        }

        return Arrays.stream(values())
                .filter(rank -> rank.isMatchCount(countOfMatch))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(countOfMatch + " : 유효하지 않은 값"));
    }

    private boolean isMatchCount(int countOfMatch) {
        return this.countOfMatch == countOfMatch;
    }
}
