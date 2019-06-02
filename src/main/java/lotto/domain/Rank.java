package lotto.domain;

public enum Rank {
    FIRST(6, 2_000_000_000, false),
    SECOND(5, 30_000_000, true),
    THIRD(5, 1_500_000, false),
    FOURTH(4, 50_000, false),
    FIFTH(3, 5_000, false),
    MISS(0, 0, false);

    private int countOfMatch;
    private int winningMoney;
    private boolean matchedBonus;

    private static final int WINNING_MIN_COUNT = 3;

    Rank(int countOfMatch, int winningMoney, boolean matchedBonus) {
        this.countOfMatch = countOfMatch;
        this.winningMoney = winningMoney;
        this.matchedBonus = matchedBonus;
    }

    public int getWinningMoney() {
        return this.winningMoney;
    }

    public static Rank valueOf(int countOfMatch, boolean matchBonus) {
        if (countOfMatch < WINNING_MIN_COUNT) {
            return MISS;
        }

        if (SECOND.matchCount(countOfMatch) && SECOND.isMatchedBonus(matchBonus)) {
            return SECOND;
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

    private boolean isMatchedBonus(boolean matchedBonus) {
        return (this.matchedBonus == matchedBonus);
    }

}
