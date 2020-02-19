package lotto.model;

public class YieldMoney {
    public static int yieldMoney = 0;

    public static int countYieldMoney(double i) {
        return (int)(i / Payment.payment * 100);
    }
}
