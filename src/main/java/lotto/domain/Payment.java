package lotto.domain;

public class Payment {
    private static final int ZERO = 0;
    private int payment;

    public Payment(int payment) {
        if (payment < ZERO) {
            throw new IllegalArgumentException("지불 금액은 음수가 될 수 없습니다");
        }
        this.payment = payment;
    }

    public int calculateNumberOfTickets(int lottoPrice) {
        return payment / lottoPrice;
    }
}
