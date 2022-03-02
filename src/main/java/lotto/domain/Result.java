package lotto.domain;

import java.util.EnumMap;
import java.util.Map;

public class Result {

    private static final int INIT_COUNT = 0;
    private static final int ADD_NUMBER = 1;

    private final Map<LottoRanking, Integer> result = new EnumMap<LottoRanking, Integer>(LottoRanking.class);

    {
        for (LottoRanking value : LottoRanking.values()) {
            result.put(value, INIT_COUNT);
        }
    }

    public Result() {
    }

    public void add(LottoRanking lottoRanking) {
        result.put(lottoRanking, result.get(lottoRanking) + ADD_NUMBER);
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
