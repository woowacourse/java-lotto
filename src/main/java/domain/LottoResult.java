package domain;

import java.util.LinkedHashMap;
import java.util.Map;

public record LottoResult(
        Map<LottoRanking, Integer> result
) {
    public static LottoResult initialize() {
        Map<LottoRanking, Integer> initialResult = new LinkedHashMap<>();
        for (LottoRanking ranking : LottoRanking.values()) {
            initialResult.put(ranking, 0);
        }
        return new LottoResult(initialResult);
    }

    public void add(LottoRanking lottoRanking) {
        result.merge(lottoRanking, 1, Integer::sum);
    }

    public long getTotalPrize() {

        return result.entrySet().stream()
                .mapToLong(entry -> (long) entry.getKey().getPrize() * entry.getValue())
                .sum();
    }

    public int getCount() {
        return result.size();
    }
}
