package domain;

import java.util.Arrays;

public enum LottoRank {
    FIRST(6, false, 2_000_000_000, "6개 일치(2000000000원) - "),
    SECOND(5, true, 30_000_000, "5개 일치, 보너스볼 일치(30000000원) - "),
    THIRD(5, false, 1_500_000, "5개 일치(1500000원) - "),
    FOURTH(4, false, 50_000, "4개 일치(50000원) - "),
    FIFTH(3, false, 5000, "3개 일치(5000원) - ");

    private int winningMatchCount;
    private boolean isBonusMatch;
    private int winningMoney;
    private String resultMessage;

    LottoRank(int winningMatchCount, boolean isBonusMatch, int winningMoney, String resultMessage){
        this.winningMatchCount = winningMatchCount;
        this.isBonusMatch = isBonusMatch;
        this.winningMoney = winningMoney;
        this.resultMessage = resultMessage;
    }

    public static LottoRank findRank(int winningMatchCount, boolean bonusMatchCount) {
        if (winningMatchCount < 5) {
            return Arrays.stream(LottoRank.values())
                    .filter(result -> result.winningMatchCount == winningMatchCount)
                    .findFirst()
                    .orElse(null);
        }
        return Arrays.stream(LottoRank.values())
                .filter(result -> result.winningMatchCount == winningMatchCount)
                .filter(result -> result.isBonusMatch == bonusMatchCount)
                .findFirst()
                .orElse(null);
    }

    public String getResultMessage(){
        return resultMessage;
    }

    public int getWinningMoney(){
        return winningMoney;
    }
}
