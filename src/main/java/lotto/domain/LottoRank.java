package lotto.domain;

public enum LottoRank {
    FIRST(6, 2_000_000_000),
    SECOND(5, 1_500_000),
    THIRD(4, 50_000),
    FOURTH(3, 5_000);

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

    public static LottoRank valueOf(int countOfMatch) {
        for (LottoRank rank : LottoRank.values()) {
            if (rank.countOfMatch == countOfMatch) {
                return rank;
            }
        }
        throw new IllegalArgumentException("유효한 값이 아닙니다.");
    }
}
