package lotto.domain;

import java.util.HashMap;
import java.util.Map;

public class LottoResult {
    private final Map<Prize, Long> lottoResult;

    public LottoResult(Map<Prize, Long> lottoResult) {
        this.lottoResult = new HashMap<>(lottoResult);
    }

    public Long calculatePrizeMoney() {
        return lottoResult.keySet().stream()
            .mapToLong(key -> key.getPrizeMoney() * lottoResult.get(key))
            .sum();
    }

    public Map<Prize, Long> getLottoResult() {
        return new HashMap<>(lottoResult);
    }
}
