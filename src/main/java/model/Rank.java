package model;

public enum Rank {
    FIFTH(3, 5_000),
    FOURTH(4, 50_000),
    THIRD(5, 1_500_000),
    SECOND(5, 30_000_000),
    FIRST(6, 2_000_000_000),
    FAIL(0, 0);

    private final int matchNumber;
    private final int winningAmount;

    Rank(int matchNumber, int winningAmount) {
        this.matchNumber = matchNumber;
        this.winningAmount = winningAmount;
    }

    public int getMatchNumber() {
        return matchNumber;
    }

    public int getWinningAmount() {
        return winningAmount;
    }
}
