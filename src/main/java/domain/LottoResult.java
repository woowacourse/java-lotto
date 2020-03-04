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
        if (result.get(rank) == null) {
            return 0;
        }
        return result.get(rank);
    }

    public long getTotalPrize() {
        long totalPrize = 0;
        for (LottoRank rank : result.keySet()) {
            long prize = rank.getPrize();
            long matchCount = count(rank);
            totalPrize += prize * matchCount;
        }
        return totalPrize;
    }
}
