package domain;

import java.util.Map;

public class LottoStatistics {

    private final Map<LottoRank, Integer> lottoCounter;

    public LottoStatistics(Map<LottoRank, Integer> lottoCounter) {
        this.lottoCounter = lottoCounter;
    }

    public int calculateProfit() {
        int sum = 0;
        for (LottoRank lottoRank : lottoCounter.keySet()) {
            sum += lottoRank.prize * lottoCounter.get(lottoRank);
        }
        return sum;
    }

}
