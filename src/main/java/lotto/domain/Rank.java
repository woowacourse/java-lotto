package lotto.domain;

import lotto.exception.InvalidRankMatchException;

public enum Rank {
    FIRST(6, 2_000_000_000),
    /*SECOND(5, 30_000_000),*/
    THIRD(5, 1_500_000),
    FOURTH(4, 50_000),
    FIFTH(3, 5_000),
    MISS(0, 0);

    private static final int WINNING_MIN_COUNT = 3;

    private int countOfMatch;
    private int winningMoney;

    private Rank(int countOfMatch, int winningMoney) {
        this.countOfMatch = countOfMatch;
        this.winningMoney = winningMoney;
    }

    public static Rank valueOf(int countOfMatch) {
        if (countOfMatch < WINNING_MIN_COUNT) {
            return MISS;
        }

        for (Rank rank : Rank.values()) {
            if (rank.countOfMatch == countOfMatch) {
                return rank;
            }
        }

        throw new InvalidRankMatchException("당첨 RANK 매칭 결과가 없습니다.");
    }

    public int getCountOfMatch() {
        return countOfMatch;
    }

    public int getWinningMoney() {
        return winningMoney;
    }
}
