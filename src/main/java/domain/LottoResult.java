package domain;

import enums.LottoRanking;

import java.util.LinkedHashMap;
import java.util.Map;

public record LottoResult(
        Map<LottoRanking, Integer> result
) {
    public void add(LottoRanking lottoRanking) {
        result.compute(lottoRanking, (key, value) -> value + 1);
    }

    public static LottoResult initialize() {
        Map<LottoRanking, Integer> initialResult = new LinkedHashMap<>();
        for (LottoRanking ranking : LottoRanking.values()) {
            initialResult.put(ranking, 0);
        }
        return new LottoResult(initialResult);
    }

    public long getTotalPrize() {
        long totalPrize = 0;
        for (LottoRanking ranking : result.keySet()) {
            totalPrize += (long) ranking.getPrize() * result.get(ranking);
        }
        return totalPrize;
    }

    public double getLottoRate(int price){
        return (double) getTotalPrize() / price;
    }
}
