package lotto.domain;

import java.util.HashMap;
import java.util.Map;

import static lotto.domain.Rank.*;

public class LottoResult {
    private static final Map<Rank, Counter> lottoResult;

    static {
        lottoResult = new HashMap<>();
        lottoResult.put(FIRST, Counter.create());
        lottoResult.put(THIRD, Counter.create());
        lottoResult.put(FOURTH, Counter.create());
        lottoResult.put(FIFTH, Counter.create());
    }

    public static void increase(Rank rank) {
        lottoResult.put(rank, Counter.increase(lottoResult.get(rank)));
    }
}
