package lotto.domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Results {
    private static final int PERCENT = 100;
    private static final int INITIAL_COUNT = 0;

    private final Map<MatchResult, Integer> results;

    Results(Map<MatchResult, Integer> results) {
        this.results = results;
    }

    public static Results createMatchResults(Lottos lottos, WinningNumbers winningNumbers) {
        Map<MatchResult, Integer> matchResults = setUp();
        lottos.stream()
                .forEach(lotto -> updateMatchResults(matchResults, lotto.findMatchResult(winningNumbers)));
        return new Results(matchResults);
    }

    private static Map<MatchResult, Integer> setUp() {
        Map<MatchResult, Integer> matchResults = new HashMap<>();
        for (MatchResult result : MatchResult.values()) {
            matchResults.put(result, INITIAL_COUNT);
        }
        return matchResults;
    }

    private static void updateMatchResults(Map<MatchResult, Integer> matchResults, MatchResult matchResult) {
        int matchCount = matchResults.get(matchResult);
        matchResults.put(matchResult, ++matchCount);
    }

    public int calculateEarningRate(PurchasePrice purchasePrice) {
        double total = 0;
        for (MatchResult matchResult : MatchResult.values()) {
            total += results.get(matchResult) * matchResult.getPrize();
        }
        return (int) Math.round(total / purchasePrice.getPrice() * PERCENT);
    }

    public Map<MatchResult, Integer> getResults() {
        return Collections.unmodifiableMap(results);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Results results1 = (Results) o;
        return Objects.equals(results, results1.results);
    }

    @Override
    public int hashCode() {
        return Objects.hash(results);
    }
}
