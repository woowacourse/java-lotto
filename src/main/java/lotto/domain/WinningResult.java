package lotto.domain;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class WinningResult {
    private final Map<Ranking, Integer> winningResult = new LinkedHashMap<>();

    public WinningResult(List<Ranking> rankings) {
        initialMap();
        putValues(rankings);
    }

    public long calculatePrizeSum() {
        long sum = 0;
        for (Map.Entry<Ranking, Integer> entry : winningResult.entrySet()) {
            sum += entry.getKey().calculate(entry.getValue());
        }
        return sum;
    }

    public Map<Ranking, Integer> getWinningResult() {
        return winningResult;
    }

    private void initialMap() {
        Ranking[] rankings = Ranking.sortByPrize();
        for (Ranking ranking : rankings) {
            winningResult.put(ranking, 0);
        }
    }

    private void putValues(List<Ranking> rankings) {
        for (Ranking ranking : rankings) {
            winningResult.put(ranking, winningResult.get(ranking) + 1);
        }
    }
}
