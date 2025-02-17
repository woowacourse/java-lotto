package domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class LottoStatistics {

    private final Map<LottoRank, Integer> lottoCounter;

    public LottoStatistics(Map<LottoRank, Integer> lottoCounter) {
        this.lottoCounter = new LinkedHashMap<>(lottoCounter);
    }

    public Map<LottoRank, Integer> getLottoCounter() {
        return new LinkedHashMap<>(lottoCounter);
    }

    public int calculateProfit() {
        int sum = 0;
        for (LottoRank lottoRank : lottoCounter.keySet()) {
            sum += lottoRank.multiplyPrice(lottoCounter.get(lottoRank));
        }
        return sum;
    }
}
