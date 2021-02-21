package lotto.domain;

import java.util.EnumMap;
import java.util.Map;

public class LottoResult {
    private final Map<Prize, Long> lottoResult;
    private final Money lottoPrice;

    public LottoResult(Map<Prize, Long> lottoResult, Money lottoPrice) {
        this.lottoResult = new EnumMap<>(lottoResult);
        this.lottoPrice = lottoPrice;
    }

    public long calculatePrizeMoney() {
        return lottoResult.keySet().stream()
                .mapToLong(key -> key.getPrizeMoney() * lottoResult.get(key))
                .sum();
    }

    public long get(Prize prize) {
        return lottoResult.getOrDefault(prize, 0L);
    }

    public long calculateProfitPercent() {
        return (calculatePrizeMoney() * 100)
                / ((long) lottoResult.values().size() * lottoPrice.getMoney());
    }
}
