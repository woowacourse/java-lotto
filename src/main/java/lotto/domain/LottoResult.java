package lotto.domain;

import java.util.EnumMap;
import java.util.Map;

public class LottoResult {
    private Map<Rank, Integer> results = new EnumMap<>(Rank.class);

    public LottoResult() {
        initResults();
    }

    private void initResults() {
        for (Rank rank : Rank.values()) {
            results.put(rank, 0);
        }
    }

    public Integer increaseOneCountBy(Rank key) {
        return results.put(key, results.get(key) + 1);
    }

    public Integer getCountsBy(Rank key) {
        return results.get(key);
    }
}
