package lottery;

public class CountsOfRankDTO {
    private int numberOfMatching;
    private int winningMoney;
    private int countsOfRank;

    public int getNumberOfMatching() {
        return numberOfMatching;
    }

    void setNumberOfMatching(int numberOfMatching) {
        this.numberOfMatching = numberOfMatching;
    }

    public int getWinningMoney() {
        return winningMoney;
    }

    void setWinningMoney(int winningMoney) {
        this.winningMoney = winningMoney;
    }

    public int getCountsOfRank() {
        return countsOfRank;
    }

    void setCountsOfRank(int countsOfRank) {
        this.countsOfRank = countsOfRank;
    }
}
