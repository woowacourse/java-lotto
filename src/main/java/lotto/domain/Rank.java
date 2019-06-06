package lotto.domain;

public enum Rank {
    FIRST(6, false, 2_000_000_000), // 1등
    SECOND(5, true, 30_000_000), // 2등
    THIRD(5, false, 1_500_000), // 3등
    FOURTH(4, false, 50_000), // 4등
    FIFTH(3, false, 5_000), // 5등
    MISS(0, false, 0);

    private int countOfMatch;
    private boolean matchBonusNo;
    private int winningMoney;

    Rank(int countOfMatch, boolean matchBonusNo, int winningMoney) {
        this.countOfMatch = countOfMatch;
        this.matchBonusNo = matchBonusNo;
        this.winningMoney = winningMoney;
    }

    public int getCountOfMatch() {
        return countOfMatch;
    }

    public int getWinningMoney() {
        return winningMoney;
    }

    public boolean getMatchBonusNo() {
        return matchBonusNo;
    }

    public static Rank valueOf(int countOfMatch, boolean matchBonusNo) {
        for (Rank rank : values()) {
            if (rank.matchCount(countOfMatch) && rank.matchBonusNo(matchBonusNo)) {
                return rank;
            }
        }
        return MISS;
    }

    private boolean matchCount(int countOfMatch) {
        return this.countOfMatch == countOfMatch;
    }

    private boolean matchBonusNo(boolean matchBonusNo) {
        return this.matchBonusNo == matchBonusNo;
    }
}
