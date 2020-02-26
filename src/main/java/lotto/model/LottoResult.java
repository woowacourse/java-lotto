package lotto.model;

import java.util.HashMap;
import java.util.Map;

public class LottoResult {
    private final Map<RankType, Integer> lottoResultCount = new HashMap<>();

    {
        for (RankType rankType : RankType.values()) {
            lottoResultCount.put(rankType, 0);
        }
    }

    public void updateResultCount(RankType rankType) {
        lottoResultCount.put(rankType, lottoResultCount.get(rankType) + 1);
    }

    public int getResultCount(RankType rankType) {
        return lottoResultCount.get(rankType);
    }
}
