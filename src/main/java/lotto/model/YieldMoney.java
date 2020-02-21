package lotto.model;

public class YieldMoney {
    private static final int PERCENT = 100;

    public static int countYieldMoney(Payment payment, double i) {
        return (int)(i / payment.getPayment() * PERCENT);
    }
}
