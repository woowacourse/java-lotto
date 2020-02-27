package domain;

import java.util.Arrays;
import java.util.Map;

public enum LottoRank {
    FIRST(6, BonusNeed.DONT_CARE, 2_000_000_000, "6개 일치(2000000000원) - "),
    SECOND(5, BonusNeed.NEED, 30_000_000, "5개 일치, 보너스볼 일치(30000000원) - "),
    THIRD(5, BonusNeed.NOT_NEED, 1_500_000, "5개 일치(1500000원) - "),
    FOURTH(4, BonusNeed.DONT_CARE, 50_000, "4개 일치(50000원) - "),
    FIFTH(3, BonusNeed.DONT_CARE, 5000, "3개 일치(5000원) - ");

    private int winningMatchCount;
    private BonusNeed bonusNeed;
    private int winningMoney;
    private String resultMessage;

    LottoRank(int winningMatchCount, BonusNeed bonusNeed, int winningMoney, String resultMessage){
        this.winningMatchCount = winningMatchCount;
        this.bonusNeed = bonusNeed;
        this.winningMoney = winningMoney;
        this.resultMessage = resultMessage;
    }

    public static void countRank(int winningBalls, boolean isBonusMatch, Map<LottoRank, Integer> rankCount) {
        LottoRank rank = Arrays.stream(LottoRank.values())
                            .filter(o -> o.winningMatchCount == winningBalls)
                            .filter(o -> o.bonusNeed.isNeedBonus(isBonusMatch))
                            .findFirst()
                            .orElse(null);
        if (rank != null) {
            rankCount.put(rank, rankCount.get(rank) + 1);
        }
    }

    public String getResultMessage(){
        return resultMessage;
    }

    public int getWinningMoney(){
        return winningMoney;
    }
}
