package lotto.ranking;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Statistics {
    private final Map<Ranking, Integer> statistics;

    public Statistics(List<Ranking> rankings) {
        statistics = new HashMap<>(6);
        statistics.put(Ranking.FIRST, 0);
        statistics.put(Ranking.SECOND, 0);
        statistics.put(Ranking.THIRD, 0);
        statistics.put(Ranking.FORTH, 0);
        statistics.put(Ranking.FIFTH, 0);
        statistics.put(Ranking.NOTHING, 0);

        for (Ranking ranking : rankings) {
            statistics.computeIfPresent(ranking, (Ranking key, Integer value) -> ++value);
        }
    }

    public int findRankingCount(Ranking ranking) {
        return statistics.get(ranking);
    }
}
