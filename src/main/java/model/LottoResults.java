package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LottoResults {
    private final List<LottoResult> lottoResults;

    public LottoResults(List<LottoResult> lottoResults) {
        this.lottoResults = lottoResults;
    }

    public Map<PrizeTier, Integer> getPrizeTierCounts() {
        Map<PrizeTier, Integer> prizeCounts = new HashMap<>();
        for (PrizeTier prizeTier : PrizeTier.values()) {
            checkPrizeTierMatched(prizeTier, prizeCounts);
        }
        return prizeCounts;
    }

    private void checkPrizeTierMatched(PrizeTier prizeTier, Map<PrizeTier, Integer> prizeCounts) {
        int count = (int) lottoResults.stream()
                .filter(lottoResult -> lottoResult.isTierMatched(prizeTier))
                .count();

        prizeCounts.put(prizeTier, count);
    }

    public long getTotalPrize() {
        long totalPrize = 0;
        for (LottoResult lottoResult : lottoResults) {
            totalPrize += lottoResult.getPrize();
        }
        return totalPrize;
    }

    public int getResultCount() {
        return lottoResults.size();
    }
}
