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
        for (LottoRank lottoRank : LottoRank.values()) {
            moneySpent += lottoResult.get(lottoRank) * GameMoney.getSingleLottoPrice();
            moneyGain += lottoResult.get(lottoRank) * lottoRank.getPrizeMoney();
        }

        return ((double) moneyGain / (double) moneySpent);
    }

    public void combineResult(final LottoResult otherResult) {
        for (LottoRank lottoRank : LottoRank.values()) {
            this.lottoResult.put(lottoRank, this.lottoResult.get(lottoRank) + otherResult.lottoResult.get(lottoRank));
        }
    }

    public Map<LottoRank, Integer> getLottoResult() {
        return Collections.unmodifiableMap(this.lottoResult);
    }
}
