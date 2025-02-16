package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LottoRankCounter {

    public Map<LottoRank, Integer> countLottoRanks(List<LottoRank> lottoRanks) {
        Map<LottoRank, Integer> lottoRankCounter = new HashMap<>();
        for (LottoRank lottoRank : lottoRanks) {
            lottoRankCounter.put(lottoRank, lottoRankCounter.getOrDefault(lottoRank, 0) + 1);
        }
        return lottoRankCounter;
    }
}
