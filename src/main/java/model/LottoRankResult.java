package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class LottoRankResult {

    private final Map<LottoRank, Integer> rankCount;

    public LottoRankResult() {
        this.rankCount = new HashMap<>();
        for (LottoRank lottoRank : LottoRank.values()) {
            rankCount.put(lottoRank, 0);
        }
    }

    public void updateRankCount(LottoRank lottoRank) {
        rankCount.merge(lottoRank, 1, Integer::sum);
    }

    public Set<LottoRank> getKeys() {
        return rankCount.keySet();
    }

    public int getValue(LottoRank rank) {
        return rankCount.get(rank);
    }
}
