package lotto.model.lottorank;

public enum WinningMoneyType {
    MISS_WINNING_MONEY(0),
    FIFTH_WINNING_MONEY(5_000),
    FOURTH_WINNING_MONEY(50_000),
    THIRD_WINNING_MONEY(1_500_000),
    SECOND_WINNING_MONEY(30_000_000),
    FIRST_WINNING_MONEY(2_000_000_000);

    private int winningMoney;

    WinningMoneyType(int winningMoney) {
        this.winningMoney = winningMoney;
    }

    public int getWinningMoney() {
        return winningMoney;
    }
}
