package domain;

import java.util.HashMap;
import java.util.Map;

public class LottoResults {

    private final Map<LottoRank, Long> lottoResults;

    public LottoResults(final Map<LottoRank, Long> results) {
        lottoResults = new HashMap<>(results);
    }
}
