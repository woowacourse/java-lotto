package lotto.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LottoResults {

    private final List<LottoResult> results;

    private LottoResults(List<LottoResult> results) {
        this.results = results;
    }

    public static LottoResults of(LottoResult firstArgs, LottoResult... remainingArgs) {
        List<LottoResult> lottoResults = new ArrayList<>();
        lottoResults.add(firstArgs);
        for (LottoResult result : remainingArgs) {
            lottoResults.add(result);
        }
        return new LottoResults(lottoResults);
    }

    public Map<LottoRank, Integer> toMap() {
        LottoResult wholeResult = mergeWholeResult();
        return wholeResult.getResult();
    }

    private LottoResult mergeWholeResult() {
        LottoResult lottoResult = new LottoResult();
        for (LottoResult result : results) {
            lottoResult.mergeResult(result);
        }
        return lottoResult;
    }

    public int findNumOfMatchByKey(LottoRank lottoRank) {
        LottoResult wholeResult = mergeWholeResult();
        return wholeResult.findNumOfMatchByKey(lottoRank);
    }

    public double getEarningsRate() {
        return (double) getTotalPrize() / (double) getTotalPurchaseAmount();
    }

    private int getTotalPrize() {
        LottoResult wholeResult = mergeWholeResult();
        return wholeResult.getTotalPrize();
    }

    private int getTotalPurchaseAmount() {
        return results.stream()
            .map(result -> result.getTotalPurchaseAmount())
            .reduce((a, b) -> a + b)
            .orElse(0);
    }
}
