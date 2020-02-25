package lotto.domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Results {
    private static final int PERCENT = 100;
    private static final int INITIAL_COUNT = 0;

    private final Map<MatchResult, Integer> lottoResults;

    public Results(Lottos lottos, WinningBalls winningBalls, BonusBall bonusBall) {
        this.lottoResults = createMatchResults(lottos, winningBalls, bonusBall);
    }

    public static Map<MatchResult, Integer> createMatchResults(Lottos lottos, WinningBalls winningBalls, BonusBall bonusBall) {
        Map<MatchResult, Integer> matchResults = setUpMatchResults();
        for (Lotto lotto : lottos.getLottos()) {
            MatchResult lottoMatchResult = MatchResult.findMatchResult(lotto, winningBalls, bonusBall);
            updateMatchResults(matchResults, lottoMatchResult);
        }
        return matchResults;
    }

    private static Map<MatchResult, Integer> setUpMatchResults() {
        Map<MatchResult, Integer> matchResults = new HashMap<>();
        MatchResult[] results = MatchResult.values();
        for (MatchResult result : results) {
            matchResults.put(result, INITIAL_COUNT);
        }
        return matchResults;
    }

    private static void updateMatchResults(Map<MatchResult, Integer> matchResults, MatchResult lottoMatchResult) {
        if (lottoMatchResult == null) {
            return;
        }
        int matchCount = matchResults.get(lottoMatchResult);
        matchResults.put(lottoMatchResult, ++matchCount);
    }

    public int calculateEarningRate(PurchasePrice purchasePrice) {
        double total = 0;
        MatchResult[] matchResults = MatchResult.values();
        for (MatchResult matchResult : matchResults) {
            total += lottoResults.get(matchResult) * matchResult.getPrize();
        }
        return (int) Math.round(total / purchasePrice.getPrice() * PERCENT);
    }

    public Map<MatchResult, Integer> getLottoResults() {
        return Collections.unmodifiableMap(lottoResults);
    }
}
