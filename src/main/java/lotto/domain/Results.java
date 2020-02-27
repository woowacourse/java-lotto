package lotto.domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Results {
    private static final int PERCENT = 100;
    private static final int INITIAL_COUNT = 0;

    private final Map<MatchResult, Integer> lottoResults;

    public Results(Lottos lottos, WinningLotto winningLotto) {
        this.lottoResults = createMatchResults(lottos, winningLotto);
    }

    public static Map<MatchResult, Integer> createMatchResults(Lottos lottos, WinningLotto winningLotto) {
        Map<MatchResult, Integer> matchResults = setUpMatchResults();
        for (Lotto lotto : lottos.getLottos()) {
            MatchResult lottoMatchResult = findMatchResult(lotto, winningLotto);
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

    public static MatchResult findMatchResult(Lotto lotto, WinningLotto winningLotto) {
        int sameNumberCount = Lotto.countsOfDuplicates(lotto, winningLotto.getWinningBalls());
        boolean isBonus = isFiveMatchWithBonusBall(sameNumberCount, lotto, winningLotto.getBonusBall());
        return MatchResult.of(sameNumberCount, isBonus);
    }

    private static boolean isFiveMatchWithBonusBall(int sameNumberCount, Lotto lotto, BonusBall bonusBall) {
        return sameNumberCount == MatchResult.FIVE_MATCH.getMatchCount() && bonusBall.hasIncluded(lotto.getBalls());
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
