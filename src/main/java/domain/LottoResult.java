package domain;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class LottoResult {

    private static final int DEFAULT_VALUE = 0;

    private final Map<LottoRank, Integer> resultCount = new LinkedHashMap<>();

    public long sumTotalPrice() {
        long totalPrice = DEFAULT_VALUE;
        for (LottoRank rank : resultCount.keySet()) {
            totalPrice += (long) rank.getPrice() * resultCount.get(rank);
        }

        return totalPrice;
    }

    public Map<LottoRank, Integer> getResultCount() {
        return Collections.unmodifiableMap(resultCount);
    }

    public void putLottoRank(LottoRank rank) {
        resultCount.put(rank, resultCount.getOrDefault(rank, DEFAULT_VALUE) + 1);
    }
}
