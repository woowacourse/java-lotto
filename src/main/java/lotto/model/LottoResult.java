package lotto.model;

import java.util.HashMap;
import java.util.Map;

public class LottoResult {
    public static final Map<RankType, Integer> lottoResultCount = new HashMap<>();

    static {
        for (RankType rankType : RankType.values()) {
            lottoResultCount.put(rankType, 0);
        }
    }

    public static void updateCount(RankType rankType) {
        lottoResultCount.put(rankType, lottoResultCount.get(rankType) + 1);
    }
}
