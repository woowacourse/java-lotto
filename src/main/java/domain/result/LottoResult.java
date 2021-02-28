package domain.result;

import domain.money.GameMoney;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class LottoResult {
    private final Map<LottoRank, Integer> lottoResult;

    public LottoResult(final Map<LottoRank, Integer> lottoResult) {
        this.lottoResult = new HashMap<>(lottoResult);
    }

    public double checkProfitRate() {
        int moneySpent = 0;
        int moneyGain = 0;
        for (LottoRank lottoRank : this.lottoResult.keySet()) {
            moneySpent += this.lottoResult.get(lottoRank) * GameMoney.getSingleLottoPrice();
            moneyGain += this.lottoResult.get(lottoRank) * lottoRank.getPrizeMoney();
        }

        return ((double) moneyGain / (double) moneySpent);
    }

    public void combineResult(final LottoResult otherResult) {
        for (LottoRank lottoRank : otherResult.lottoResult.keySet()) {
            this.lottoResult.put(lottoRank,
                    this.lottoResult.getOrDefault(lottoRank, 0) + otherResult.lottoResult.get(lottoRank));
        }
    }

    public Map<LottoRank, Integer> getLottoResult() {
        return Collections.unmodifiableMap(this.lottoResult);
    }
}