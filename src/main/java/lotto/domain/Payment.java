package lotto.domain;

public class Payment {
    private static final String ERROR_ONLY_NATURAL_NUMBER = "자연수를 입력해주세요!";
    private static final String ERROR_LOTTO_PRICE = "1,000원 단위로 입력해주세요!";

    private final int payment;

    public Payment(final int payment) {
        validatePayment(payment);
        this.payment = payment;
    }

    public int getPayment() {
        return payment;
    }

    public LottoCount getTotalLottoCount() {
        return new LottoCount(this.payment / Lotto.LOTTO_PRICE);
    }

    private void validatePayment(final int payment) {
        validateNaturalNumber(payment);
        validateLottoPrice(payment);
    }

    private void validateNaturalNumber(final int number) {
        if (number <= 0) {
            throw new IllegalArgumentException(ERROR_ONLY_NATURAL_NUMBER);
        }
    }

    private void validateLottoPrice(final int number) {
        if ((number % Lotto.LOTTO_PRICE) != 0) {
            throw new IllegalArgumentException(ERROR_LOTTO_PRICE);
        }
    }
}
