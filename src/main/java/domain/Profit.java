package domain;

public class Profit {
    private int profit;

    public void addWinningMoney(int winningMoney) {
        this.profit += winningMoney;
    }

    public int getProfit() {
        return this.profit;
    }
}
