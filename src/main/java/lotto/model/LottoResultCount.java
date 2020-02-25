package lotto.model;

import java.util.HashMap;
import java.util.Map;

public class LottoResultCount {
    public static final Map<LottoResult, Integer> lottoResultCount = new HashMap<>();

    static {
        for (LottoResult lottoResult : LottoResult.values()) {
            lottoResultCount.put(lottoResult, 0);
        }
    }

    public static void updateCount(LottoResult lottoResult) {
        lottoResultCount.put(lottoResult, lottoResultCount.get(lottoResult) + 1);
    }
}
