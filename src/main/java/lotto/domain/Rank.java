package lotto.domain;

public enum Rank {
    FIRST(6, 2_000_000_000),
    //SECOND(5, 30_000_000),
    THIRD(5, 1_500_000),
    FOURTH(4, 50_000),
    FIFTH(3, 5_000),
    MISS(0, 0);

    private int countOfMatch;
    private int winningMoney;

    private static final int WINNING_MIN_COUNT = 3;

    Rank(int countOfMatch, int winningMoney) {
        this.countOfMatch = countOfMatch;
        this.winningMoney = winningMoney;
    }

    public int getCountOfMatch() {
        return this.countOfMatch;
    }

    public int getWinningMoney() {
        return this.winningMoney;
    }

    public static Rank valueOf(int countOfMatch) {
        if(countOfMatch < WINNING_MIN_COUNT) {
            return MISS;
        }

        for (Rank rank : values()) {
            if (rank.matchCount(countOfMatch)) {
                return rank;
            }
        }

        throw new IllegalArgumentException(countOfMatch + " 값은 유효하지 않습니다.");
    }

    private boolean matchCount(int countOfMatch) {
        return (this.countOfMatch == countOfMatch);
    }

}
