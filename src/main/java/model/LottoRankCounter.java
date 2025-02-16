package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LottoRankCounter {

    private static final int COUNT_DEFAULT_VALUE = 0;
    private static final int INCREMENT = 1;

    public Map<LottoRank, Integer> countLottoRanks(List<LottoRank> lottoRanks) {
        Map<LottoRank, Integer> lottoRankCounter = new HashMap<>();
        for (LottoRank lottoRank : lottoRanks) {
            lottoRankCounter.put(lottoRank, lottoRankCounter.getOrDefault(lottoRank, COUNT_DEFAULT_VALUE) + INCREMENT);
        }
        return lottoRankCounter;
    }
}
