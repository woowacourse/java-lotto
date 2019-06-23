package lotto.domain;


public enum Rank {
    FIRST(6, 2_000_000_000),
    SECOND(5, 30_000_000),
    THIRD(5, 1_500_000),
    FOURTH(4, 50_000),
    FIFTH(3, 5_000),
    MISS(0, 0);

    private static final int WINNING_MIN_COUNT = 3;

    private int countOfMatch;
    private int winningMoney;

    Rank(int countOfMatch, int winningMoney) {
        this.countOfMatch = countOfMatch;
        this.winningMoney = winningMoney;
    }

    public int getCountOfMatch() {
        return countOfMatch;
    }

    public int getWinningMoney() {
        return winningMoney;
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

    public long calculatePrizeOfThisRank(int countOfLotto) {
        return winningMoney * countOfLotto;
    }

    private boolean matchCount(int countOfMatch) {
        return this.countOfMatch == countOfMatch;
    }

    @Override
    public String toString() {
        if (this == SECOND) {
            return this.getCountOfMatch() + "개 일치, 보너스볼 일치 (" + this.getWinningMoney() + "원)  ";
        }
        return this.getCountOfMatch() + "개 일치 (" + this.getWinningMoney() + "원)  ";
    }
}
