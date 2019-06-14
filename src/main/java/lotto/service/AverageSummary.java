package lotto.service;

import lotto.domain.LottoCreator;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class AverageSummary implements ResultSummary {
    private static final int DECIMAL_POINT = 3;
    private final BigDecimal summary;

    AverageSummary(int numerator, int denominator) {
        summary = validate(numerator, denominator);
    }

    private BigDecimal validate(int numerator, int denominator) {
        if (denominator == 0) {
            return new BigDecimal(numerator);
        }
        return new BigDecimal(numerator)
                .divide(new BigDecimal(denominator), DECIMAL_POINT, RoundingMode.CEILING)
                .divide(new BigDecimal(LottoCreator.LOTTO_PRICE), DECIMAL_POINT, RoundingMode.CEILING);
    }

    @Override
    public BigDecimal summary() {
        return summary;
    }
}
