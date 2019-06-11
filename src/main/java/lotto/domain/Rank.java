package lotto.domain;

/**
 * @author heebg
 * @version 1.0 2019-06-02
 */
public enum Rank {
    FIRST(6, 2_000_000_000, 1), // 1등
    SECOND(5, 30_000_000, 2), // 2등
    THIRD(5, 1_500_000, 3), // 3등
    FOURTH(4, 50_000, 4), // 4등
    FIFTH(3, 5_000, 5), // 5등
    MISS(0, 0, 6);

    private static final int WINNING_MIN_COUNT = 3;

    private int countOfMatch;
    private int winningMoney;
    private int rank;

    Rank(int countOfMatch, int winningMoney, int rank) {
        this.countOfMatch = countOfMatch;
        this.winningMoney = winningMoney;
        this.rank = rank;
    }

    public int getCountOfMatch() {
        return countOfMatch;
    }

    public int getWinningMoney() {
        return winningMoney;
    }

    public int getRank() {
        return rank;
    }

    public static Rank valueOf(int countOfMatch, boolean matchBonus) {
        if (countOfMatch < WINNING_MIN_COUNT) {
            return MISS;
        }

        if (SECOND.matchCount(countOfMatch) && matchBonus) {
            return SECOND;
        }

        for (Rank rank : values()) {
            if (rank.matchCount(countOfMatch) && rank != SECOND) {
                return rank;
            }
        }

        throw new IllegalArgumentException(countOfMatch + "는 유효하지 않은 값입니다.");
    }

    private boolean matchCount(int countOfMatch) {
        return this.countOfMatch == countOfMatch;
    }
}
