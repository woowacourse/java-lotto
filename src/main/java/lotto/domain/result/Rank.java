package lotto.domain.result;

import java.util.Arrays;

public enum Rank {
    FIRST(6, 2000000000),
    SECOND(5, 30000000),
    THIRD(5, 1500000),
    FOURTH(4, 50000),
    FIFTH(3, 5000),
    LOSE(0, 0);

    private static final int LOSING_LOTTO_MINIMUM_MATCH_COUNT = 3;
    private static final String ERROR_INVALID_VALUE = " : 유효하지 않은 값";

    private Integer countOfMatch;
    private Integer winningMoney;

    Rank(Integer countOfMatch, Integer winningMoney) {
        this.countOfMatch = countOfMatch;
        this.winningMoney = winningMoney;
    }

    public static Rank valueOf(final Integer countOfMatch, Boolean matchBonus) {
        if (countOfMatch < LOSING_LOTTO_MINIMUM_MATCH_COUNT) {
            return LOSE;
        }

        if (SECOND.isMatchCount(countOfMatch) && matchBonus) {
            return SECOND;
        }

        return Arrays.stream(values())
                .filter(rank -> rank != SECOND)
                .filter(rank -> rank.isMatchCount(countOfMatch))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(countOfMatch + ERROR_INVALID_VALUE));
    }

    private boolean isMatchCount(Integer countOfMatch) {
        return this.countOfMatch.equals(countOfMatch);
    }

    public int getTotalRankWinningMoney(Integer countOfRank) {
        return countOfRank * winningMoney;
    }

    public int getCountOfMatch() {
        return countOfMatch;
    }

    public int getWinningMoney() {
        return winningMoney;
    }
}
