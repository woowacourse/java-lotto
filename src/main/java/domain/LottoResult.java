package domain;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

public class LottoResult {

    private Map<LottoRank, Integer> result;

    public LottoResult(Map<LottoRank, Integer> result) {
        this.result = Collections.unmodifiableMap(result);
    }

    public int count(LottoRank rank) {
        return result.get(rank);
    }

    public Set<LottoRank> keySet() {
        return result.keySet();
    }
}
