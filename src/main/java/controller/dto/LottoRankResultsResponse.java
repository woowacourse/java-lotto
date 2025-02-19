package controller.dto;

import java.util.Map;
import java.util.Set;
import model.LottoRank;

public record LottoRankResultsResponse(
        Map<LottoRank, Integer> rankCount
) {

    public Set<LottoRank> getKeys() {
        return rankCount.keySet();
    }

    public int getValue(LottoRank rank) {
        return rankCount.get(rank);
    }
}
