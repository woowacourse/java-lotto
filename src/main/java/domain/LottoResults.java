package domain;

import java.util.HashMap;
import java.util.Map;

public class LottoResults {

    private final Map<LottoRank, Long> lottoResults;

    public LottoResults(final Map<LottoRank, Long> results) {
        lottoResults = new HashMap<>(results);
    }

    public Money getTotalWinningMoney() {
        Money total = Money.valueOf(0L);
        for (LottoRank lottoRank : lottoResults.keySet()) {
            Money money = lottoRank.getPrize().multiply(lottoResults.get(lottoRank)); //todo: Rank에게 메시지를 보내자!! (개수를 인자로 보내주면 될듯!)
            total = total.add(money);
        }

        return total;
    }

    public Map<LottoRank, Long> getValues() {
        return new HashMap<>(lottoResults);
    }
}
