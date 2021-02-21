package lotto.domain.lotto;

import java.util.Arrays;
import java.util.List;

import lotto.domain.Rank;

public class LottoResult {
    private static final int PRICE_EACH_LOTTO = 1000;
    private final List<Rank> rankList;

    public LottoResult(List<Rank> rankList) {
        this.rankList = rankList;
    }

    public int findNumberOfRank(Rank wantedRank) {
        long count = rankList.stream().filter(rank -> rank.equals(wantedRank)).count();
        return (int) count;
    }

    public float calculateProfitRate() {
        float totalWinMoney = Arrays.stream(Rank.values())
                .mapToInt(rank -> findNumberOfRank(rank) * rank.getMoney()).sum();
        return totalWinMoney / getPurchaseTotalMoney();
    }

    private int getPurchaseTotalMoney() {
        return rankList.size() * PRICE_EACH_LOTTO;
    }
}
