package lotto.model;

import java.util.HashMap;
import java.util.Map;

public class LottoResult {
    Map<Rank, Integer> result;

    public LottoResult() {
        this.result = new HashMap<>();
    }

    public Map<Rank, Integer> getResult() {
        return result;
    }

    public Integer getOrDefault(Rank rank) {
        return result.getOrDefault(rank, 0);
    }
}
