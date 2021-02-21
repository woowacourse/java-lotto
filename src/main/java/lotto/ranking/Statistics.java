package lotto.ranking;

import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import static lotto.game.LottoCount.ZERO;

public class Statistics {
    private final Map<Ranking, Integer> statistics;

    public Statistics(List<Ranking> rankings) {
        statistics = new EnumMap<>(Ranking.class);
        for (Ranking ranking : Ranking.values()) {
            statistics.put(ranking, ZERO);
        }
        calculateStatistics(rankings);
    }

    private void calculateStatistics(List<Ranking> rankings) {
        for (Ranking ranking : rankings) {
            statistics.computeIfPresent(ranking, (Ranking key, Integer value) -> ++value);
        }
    }

    public int findRankingCount(Ranking ranking) {
        return statistics.get(ranking);
    }

    public Map<Ranking, Integer> getStatistics() {
        return Collections.unmodifiableMap(statistics);
    }
}
