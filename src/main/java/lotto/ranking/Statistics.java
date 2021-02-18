package lotto.ranking;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static lotto.game.LottoCount.ZERO;

public class Statistics {
    private static final int STATISTIC_CAPACITY = 6;

    private final Map<Ranking, Integer> statistics;

    public Statistics(List<Ranking> rankings) {
        statistics = new HashMap<>(STATISTIC_CAPACITY);
        statistics.put(Ranking.FIRST, ZERO);
        statistics.put(Ranking.SECOND, ZERO);
        statistics.put(Ranking.THIRD, ZERO);
        statistics.put(Ranking.FORTH, ZERO);
        statistics.put(Ranking.FIFTH, ZERO);
        statistics.put(Ranking.NOTHING, ZERO);
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
