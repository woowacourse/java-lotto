package lotto.domain;

import lotto.exception.LowBalanceException;

import java.util.Objects;

public class TicketCount {

    private final int manual;
    private final int auto;

    public TicketCount(final Payment payment, final int count) {
        validate(payment, count);
        this.auto = payment.count() - count;
        this.manual = count;
    }

    private void validate(Payment payment, int count) {
        if (payment.count() < count) {
            throw new LowBalanceException();
        }
    }

    public int manual() {
        return manual;
    }

    public int auto() {
        return auto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketCount that = (TicketCount) o;
        return manual == that.manual && auto == that.auto;
    }

    @Override
    public int hashCode() {
        return Objects.hash(manual, auto);
    }
}
