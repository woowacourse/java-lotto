package domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class ResultStatistics {

    private final TreeMap<LottoResult, Integer> resultStatistics = Arrays
            .stream(LottoResult.values())
            .collect(Collectors.toMap(key -> key, value -> 0, (o1, o2) -> o1, TreeMap::new));

    public Set<LottoResult> getLottoResultKeys() {
        return Collections.unmodifiableSet(resultStatistics.keySet());
    }

    public void collectAndUpdateStats(List<LottoResult> results, LottoResult key) {
        resultStatistics.put(key, collectPrizeCount(results, key));
    }

    private int collectPrizeCount(List<LottoResult> results, LottoResult key) {
        return (int) results.stream()
                .filter(result -> result == key)
                .count();
    }

    public int calculateTotalPrize() {
        return resultStatistics.keySet()
                .stream()
                .mapToInt(result -> sumOfLottoResult(result, resultStatistics.get(result)))
                .sum();
    }

    private int sumOfLottoResult(LottoResult lottoResult, int count) {
        return lottoResult.getPrize() * count;
    }

    public Map<LottoResult, Integer> getResultStatistics() {
        return Collections.unmodifiableMap(resultStatistics);
    }

    @Override
    public String toString() {
        return "ResultStatistics{" + "resultStatistics=" + resultStatistics + '}';
    }
}
