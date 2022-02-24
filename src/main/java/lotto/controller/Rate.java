package lotto.controller;

import java.math.BigDecimal;
import java.util.Objects;

public class Rate {

    private final BigDecimal rate;

    public Rate(BigDecimal rate) {
        this.rate = rate;
    }

    public BigDecimal getRate() {
        return rate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Rate)) {
            return false;
        }
        Rate rate1 = (Rate)o;
        return Objects.equals(rate, rate1.rate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rate);
    }
}
