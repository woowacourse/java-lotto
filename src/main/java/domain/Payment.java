package domain;

import exception.ExceptionMessage;

public class Payment {
    private static final int LOTTO_PRICE = 1000;
    private static final int PAYMENT_LIMIT_PRICE = 100000;

    private final int payment;

    public Payment(int payment) {
        checkGreaterThanLottoPrice(payment);
        checkPaymentLimit(payment);
        this.payment = payment;
    }

    private void checkGreaterThanLottoPrice(int payment) {
        if (payment < LOTTO_PRICE) {
            throw new IllegalArgumentException(ExceptionMessage.BELOW_RANGE_PAYMENT.getMessage());
        }
    }

    private void checkPaymentLimit(int payment) {
        if (payment > PAYMENT_LIMIT_PRICE) {
            throw new IllegalArgumentException(ExceptionMessage.OVER_RANGE_PAYMENT.getMessage());
        }
    }

    public int calculateLottoCount() {
        return payment / LOTTO_PRICE;
    }

    public int calculateTicketPayment() {
        return LOTTO_PRICE * calculateLottoCount();
    }
}
