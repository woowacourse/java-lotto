package lotto.domain;

public class Profit {

    private double profitRate = 0;

    public double calculateRate(final int totalMoney, final Payment payment) {
        int money = payment.getPayment();
        profitRate = (double) totalMoney / money;
        return profitRate;
    }
}
