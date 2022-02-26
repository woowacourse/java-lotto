package lotto.domain;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class WinningResult {
    private final Map<Ranking, Integer> winningResult = new EnumMap<>(Ranking.class);

    public WinningResult(List<Ranking> rankings) {
        putValues(rankings);
    }

    public long calculatePrizeSum() {
        long sum = 0;
        for (Map.Entry<Ranking, Integer> entry : winningResult.entrySet()) {
            sum += entry.getKey().multiple(entry.getValue());
        }
        return sum;
    }

    public Map<Ranking, Integer> getWinningResult() {
        return winningResult;
    }

    private void putValues(List<Ranking> rankings) {
        for (Ranking ranking : rankings) {
            winningResult.put(ranking, winningResult.getOrDefault(ranking, 0) + 1);
        }
    }
}
