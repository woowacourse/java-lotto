package lotto.domain.paymentinfo;

import lotto.domain.exception.PaymentOutOfBoundsException;

import java.util.Objects;

public class Payment {
    private static final int LOTTO_PRICE = 1_000;

    private final int payment;

    public Payment(int payment) {
        if (payment < LOTTO_PRICE) {
            throw new PaymentOutOfBoundsException(String.format("로또가격(%d)보다 높은 금액을 입력하세요", LOTTO_PRICE));
        }
        this.payment = payment;
    }

    public int calculateCountOfLotto() {
        return payment / LOTTO_PRICE;
    }

    public double calculateEarningsRate(long totalWinningMoney) {
        return (double) totalWinningMoney / payment;
    }

    public int getPayment() {
        return payment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment1 = (Payment) o;
        return payment == payment1.payment;
    }

    @Override
    public int hashCode() {
        return Objects.hash(payment);
    }
}
