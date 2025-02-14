package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class LottoRankResult {

    private final Map<LottoRank, Integer> rankCount;

    public LottoRankResult() {
        this.rankCount = new HashMap<>();
    }

    public void updateRankCount(LottoRank lottoRank) {
        rankCount.merge(lottoRank, 1, Integer::sum);
    }

    public Set<LottoRank> getRanks() {
        return rankCount.keySet();
    }

    public int getCountByRank(LottoRank rank) {
        return rankCount.getOrDefault(rank, 0);
    }
}
