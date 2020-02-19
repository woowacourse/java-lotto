package domain;

import org.apache.commons.lang3.ObjectUtils;

public enum LottoResult {
    FIRST(6, 0, 2_000_000_000, "6개 일치(2000000000원) - "),
    SECOND(5, 1, 30_000_000, "5개 일치, 보너스볼 일치(30000000원) - "),
    THIRD(5, 0, 1_500_000, "5개 일치(1500000원) - "),
    FOURTH(4, 0, 50_000, "4개 일치(50000원) - "),
    FIFTH(3, 0, 5000, "3개 일치(5000원) - ");

    private int winningMatchCount;
    private int bonusMatchCount;
    private int winningMoney;
    private String resultMessage;

    LottoResult (int winningMatchCount, int bonusMatchCount, int winningMoney, String resultMessage){
        this.winningMatchCount = winningMatchCount;
        this.bonusMatchCount = bonusMatchCount;
        this.winningMoney = winningMoney;
        this.resultMessage = resultMessage;
    }



    public static LottoResult findResult(int winningMatchCount, int bonusMatchCount) {
        for (LottoResult result : LottoResult.values()) {
            if (result.winningMatchCount == winningMatchCount && result.bonusMatchCount == bonusMatchCount) {
                return result;
            }
        }
        return null;
    }
}
