package lotto.domain;

public enum LottoRank {
    FIRST(6, 2_000_000_000),
    SECOND(5, 30_000_000),
    THIRD(5, 1_500_000),
    FOURTH(4, 50_000),
    FIFTH(3, 5_000),
    MISS(0, 0);

    private static final int WINNING_MIN_COUNT = 3;

    private int countOfMatch;
    private int winningAmount;

    LottoRank(int countOfMatch, int winningAmount) {
        this.countOfMatch = countOfMatch;
        this.winningAmount = winningAmount;
    }

    public int getCountOfMatch() {
        return countOfMatch;
    }

    public int getWinningAmount() {
        return winningAmount;
    }

    public static LottoRank valueOf(int countOfMatch, boolean bonusMatch) {
        if (countOfMatch < WINNING_MIN_COUNT) {
            return MISS;
        }

        if ((SECOND.countOfMatch == countOfMatch) && bonusMatch) {
            return SECOND;
        }

        for (LottoRank rank : values()) {
            if ((rank.countOfMatch == countOfMatch) && rank != SECOND) {
                return rank;
            }
        }
        throw new IllegalArgumentException("유효한 값이 아닙니다.");
    }
}
