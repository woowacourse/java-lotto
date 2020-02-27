package lotto.domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Results {
    private static final int PERCENT = 100;
    private static final int INITIAL_COUNT = 0;

    private final Map<MatchResult, Integer> lottoResults;

    Results(Map<MatchResult, Integer> lottoResults) {
        this.lottoResults = lottoResults;
    }

    public static Results createMatchResults(Lottos lottos, WinningLotto winningLotto) {
        Map<MatchResult, Integer> matchResults = setUpMatchResults();
        for (Lotto lotto : lottos.getLottos()) {
            MatchResult lottoMatchResult = findMatchResult(lotto, winningLotto);
            updateMatchResults(matchResults, lottoMatchResult);
        }
        return new Results(matchResults);
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
        int sameNumberCount = lotto.countsOfDuplicates(winningLotto.getWinningBalls());
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Results results = (Results) o;
        return Objects.equals(lottoResults, results.lottoResults);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoResults);
    }
}
