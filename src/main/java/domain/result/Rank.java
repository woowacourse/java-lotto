package domain.result;

public enum Rank {

    FIRST(6, 2000000000),
    SECOND(5, 30000000),
    THIRD(5, 1500000),
    FOURTH(4, 50000),
    FIFTH(3, 5000),
    MISS(0, 0);

    public int getCountOfMatches() {
        return countOfMatches;
    }

    public int getWinningMoney() {
        return winningMoney;
    }

    private int countOfMatches;
    private int winningMoney;

    Rank(int countOfMatches, int winningMoney) {
        this.countOfMatches = countOfMatches;
        this.winningMoney = winningMoney;
    }

    public static Rank valueOf(int countOfMatches, boolean matchBonusNumber) {
        if (countOfMatches == FIRST.countOfMatches) {
            return FIRST;
        }
        if (countOfMatches == 5 && matchBonusNumber) {
            return SECOND;
        }
        if (countOfMatches == 5) {
            return THIRD;
        }
        if (countOfMatches == 4) {
            return FOURTH;
        }
        if (countOfMatches == 3) {
            return FIFTH;
        }
        return MISS;
    }

}
