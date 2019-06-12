package lotto.dto;

import lotto.domain.Rank;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

public class LottoResultDto {
    private static final int LOTTO_UNIT_PRICE = 1000;
    private static final int DECIMAL_POINT = 3;
    private final Map<Rank, Integer> results;

    public LottoResultDto(Map<Rank, Integer> results) {
        this.results = results;
    }

    private BigDecimal summary() {
        int sumOfRank = 0;
        int sumOfTickets = 0;
        for (Map.Entry<Rank, Integer> entry : results.entrySet()) {
            sumOfRank += entry.getKey().money() * entry.getValue();
            sumOfTickets += entry.getValue();
        }
        return toBigDecimal(sumOfRank, sumOfTickets);
    }

    private BigDecimal toBigDecimal(int numerator, int denominator) {
        if (denominator == 0) {
            return new BigDecimal(numerator);
        }
        return new BigDecimal(numerator)
                .divide(new BigDecimal(denominator), DECIMAL_POINT, RoundingMode.CEILING)
                .divide(new BigDecimal(LOTTO_UNIT_PRICE), DECIMAL_POINT, RoundingMode.CEILING);
    }

    public Map<Rank, Integer> getResults() {
        return results;
    }

    public BigDecimal getSummary() {
        return summary();
    }
}