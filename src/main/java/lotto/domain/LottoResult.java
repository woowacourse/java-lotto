package lotto.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

public class LottoResult {
    private final Map<Rank, Long> rankCount;

    public LottoResult(Map<Rank, Long> rankCount) {
        this.rankCount = rankCount;
    }

    public BigDecimal calculateRateOfReturn(int payment) {
        return new BigDecimal(calculateTotalPrice())
                .divide(BigDecimal.valueOf(payment), 10, RoundingMode.HALF_UP);
    }

    private long calculateTotalPrice() {
        return rankCount.entrySet().stream()
                .mapToLong(set -> set.getKey().multiplyPrice(set.getValue()))
                .sum();
    }
}
