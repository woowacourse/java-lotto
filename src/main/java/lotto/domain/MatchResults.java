package lotto.domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MatchResults {
    private static final int INITIAL_COUNT = 0;

    private final Map<MatchResult, Integer> matchResultCounter;

    MatchResults(List<MatchResult> matchResults) {
        matchResultCounter = createMatchResultCounter(matchResults);
    }

    private static Map<MatchResult, Integer> createMatchResultCounter(List<MatchResult> matchResults) {
        Map<MatchResult, Integer> matchResultCounter = new HashMap<>();
        for (MatchResult matchResult : matchResults) {
            int matchCount = matchResultCounter.getOrDefault(matchResult, INITIAL_COUNT);
            matchResultCounter.put(matchResult, ++matchCount);
        }
        return matchResultCounter;
    }

    public int calculateTotalEarnings() {
        return this.matchResultCounter.keySet().stream()
                .mapToInt(this::computeTotalPrize)
                .sum();
    }

    private int computeTotalPrize(MatchResult matchResult) {
        return matchResultCounter.get(matchResult) * matchResult.getPrize();
    }

    public Map<MatchResult, Integer> get() {
        return Collections.unmodifiableMap(matchResultCounter);
    }
}
