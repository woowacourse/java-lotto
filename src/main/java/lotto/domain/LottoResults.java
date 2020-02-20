package lotto.domain;

import java.util.Collections;
import java.util.Map;

public class LottoResults {

    private final Map<MatchResult, Integer> lottoResults;

    public LottoResults(Map<MatchResult, Integer> matchResults) {
        lottoResults = matchResults;
    }

    public int calculateEarningRate(PurchasePrice purchasePrice) {
        double total = 0;
        MatchResult[] matchResults = MatchResult.values();
        for (MatchResult matchResult : matchResults) {
            total += lottoResults.get(matchResult) * matchResult.getPrize();
        }
        return (int) Math.round(total / purchasePrice.getPrice() * 100);
    }

    public Map<MatchResult, Integer> getLottoResults() {
        return Collections.unmodifiableMap(lottoResults);
    }

}
