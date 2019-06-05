package lotto.domain;

import lotto.exception.PaymentOutOfBoundsException;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public class Payment {
    private static final int LOTTO_PRICE = 1_000;

    private int payment;

    public Payment(String payment) {
        if (StringUtils.isBlank(payment)) {
            throw new NullPointerException();
        }

        if (!StringUtils.isNumeric(payment)) {
            throw new ClassCastException("구입금액은 숫자를 입력해야합니다");
        }

        if (Integer.parseInt(payment) < LOTTO_PRICE) {
            throw new PaymentOutOfBoundsException(String.format("로또가격(%d)보다 높은 금액을 입력하세요", LOTTO_PRICE));
        }
        this.payment = Integer.parseInt(payment);
    }

    public int calculateCountOfLotto() {
        return payment / LOTTO_PRICE;
    }

    public double calculateEarningsRate(long totalWinningMoney) {
        return (double) totalWinningMoney / payment;
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
