package lotto.model;

public class YieldMoney {
    public static final int PERCENT = 100;

    public static int countYieldMoney(double i) {
        return (int)(i / Payment.payment * PERCENT);
    }
}
