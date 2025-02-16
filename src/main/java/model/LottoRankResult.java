package model;

import java.util.Map;
import java.util.Set;

public class LottoRankResult {

    private final Map<LottoRank, Integer> rankCount;

    public LottoRankResult(Map<LottoRank, Integer> rankCount) {
        this.rankCount = rankCount;
        for(LottoRank lottoRank : LottoRank.values()){
            rankCount.put(lottoRank, rankCount.getOrDefault(lottoRank, 0));
        }
    }

    public Map<LottoRank, Integer> getRankCount() {
        return rankCount;
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
