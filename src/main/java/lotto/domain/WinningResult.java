package lotto.domain;

public enum WinningResult {
    FIRST_PLACE(2_000_000_000,0)
    ,SECOND_PLACE(30_000_000, 0)
    ,THIRD_PLACE(1_500_000, 0)
    ,FOURTH_PLACE(50_000, 0)
    ,FIRTH_PLACE(5_000, 0);

    int winningCount;
    final int winningMoney;

    private WinningResult(int winningMoney,int winningCount){
        this.winningCount = winningCount;
        this.winningMoney = winningMoney;
    }
}
