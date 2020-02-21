package lotto.domain;

import java.util.Map;

public class TotalResult {

    private static final int PERCENTAGE = 100;

    private final LottoResult lottoResult;
    private final LottoCount count;

    public TotalResult(LottoResult lottoResult, LottoCount count) {
        this.lottoResult = lottoResult;
        this.count = count;
    }

    public long getProfitRate() {
        long totalPrize = lottoResult.calculateTotalPrize();
        int buyMoney = Money.getBuyMoney(count);
        return totalPrize * PERCENTAGE / buyMoney;
    }

    public Map<LottoRank, Integer> getLottoResult() {
        return lottoResult.getRankResult();
    }
}
