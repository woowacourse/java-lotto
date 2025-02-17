package lotto.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

public class LottoResult {
    private final Map<Rank, Long> rankCount;

    public LottoResult(final Map<Rank, Long> rankCount) {
        this.rankCount = rankCount;
    }

    public BigDecimal calculateRateOfReturn(final int payment) {
        return calculateTotalPrice().divide(BigDecimal.valueOf(payment), 10, RoundingMode.HALF_UP);
    }

    private BigDecimal calculateTotalPrice() {
        long total = rankCount.entrySet().stream()
                .mapToLong(set -> set.getKey().multiplyPrice(set.getValue()))
                .sum();
        return new BigDecimal(total);
    }
}
