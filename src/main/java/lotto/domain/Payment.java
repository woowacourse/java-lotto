package lotto.domain;

public class Payment {
    private static final int ZERO = 0;
    private int payment;

    public Payment(int payment) {
        if (payment <= ZERO) {
            throw new IllegalArgumentException("금액을 지불해주시기 바랍니다");
        }
        this.payment = payment;
    }

    public int calculateNumberOfTickets(int lottoPrice) {
        return payment / lottoPrice;
    }

    public double calculateEarningsRate(long totalWinningMoney) {
        return (double) totalWinningMoney / payment;
    }
}
