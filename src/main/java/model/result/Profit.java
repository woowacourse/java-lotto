package model.result;

public class Profit {
    private static final int INIT = 0;
    private int number;

    public int sumProfitMoney(WinningResult winningResult) {
        init();
        for (Rank rank : winningResult.getWinningCount().keySet()) {
            number += winningResult.getWinningCount().get(rank) * rank.getValue();
        }
        return number;
    }

    private void init() {
        this.number = INIT;
    }
}
