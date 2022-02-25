package lotto.domain;

public class Profit {

    private double profitRate = 0;

    public double calculateRate(int totalMoney, Payment payment) {
        int money = payment.getPayment();
        profitRate = (double) totalMoney / money;
        return profitRate;
    }
}
