package lotto.domain;

public class LottoMachine {

    private static final int LOTTO_PRICE = 1000;

    public int getLottoCount(Payment payment) {
        return payment.getPayment() / LOTTO_PRICE;
    }
}
