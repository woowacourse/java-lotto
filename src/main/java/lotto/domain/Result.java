package lotto.domain;

import java.util.Map;

public class Result {

    private final Map<LottoRanking, Integer> result;

    public Result(Map<LottoRanking, Integer> result) {
        this.result = result;
    }

    public int getCount(LottoRanking key) {
        return result.get(key);
    }

    public long getTotalProfit() {
        return result.entrySet()
            .stream()
            .mapToLong(entry -> entry.getKey().multiply(entry.getValue()))
            .sum();
    }
}
