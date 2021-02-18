package domain;

import java.util.HashMap;
import java.util.Map;

public class LottoResults {

    private final Map<LottoRank, Long> lottoResults;

    public LottoResults(final Map<LottoRank, Long> results) {
        lottoResults = new HashMap<>(results);
    }

    public Money getTotalWinningMoney() {
        Money total = new Money(0);
        for (LottoRank lottoRank : lottoResults.keySet()) {
            Money money = lottoRank.getPrize().multiply(lottoResults.get(lottoRank));
            total = total.add(money);
        }

        return total;
    }

    public Map<LottoRank, Long> getValues() {
        return new HashMap<>(lottoResults);
    }
}
