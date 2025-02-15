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
        long totalPrize = 0;
        for (LottoRanking ranking : result.keySet()) {
            totalPrize += (long) ranking.getPrize() * result.get(ranking);
        }
        return totalPrize;
    }

    public int getCount() {
        return result.size();
    }
}
