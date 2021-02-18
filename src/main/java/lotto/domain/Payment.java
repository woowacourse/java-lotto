package lotto.domain;

import java.util.Objects;
import lotto.exception.InvalidPaymentAmountException;

public class Payment {

    private static final int MIN_PAYMENT = 1000;

    private final int money;

    public Payment(final int value) {
        validate(value);
        this.money = convertPaymentAmount(value);
    }

    private void validate(final int value) {
        if (value < MIN_PAYMENT) {
            throw new InvalidPaymentAmountException();
        }
    }

    public int convertPaymentAmount(final int value) {
        return value - (value % MIN_PAYMENT);
    }

    public int count() {
        return money / MIN_PAYMENT;
    }

    public int getPayment() {
        return money;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Payment payment = (Payment) o;
        return money == payment.money;
    }

    @Override
    public int hashCode() {
        return Objects.hash(money);
    }
}