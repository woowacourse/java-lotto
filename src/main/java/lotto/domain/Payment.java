package lotto.domain;

public class Payment {
    private static final String ERROR_ONLY_NATURAL_NUMBER = "자연수를 입력해주세요!";
    public static final int MONEY_UNIT = 10;
    private static final String ERROR_MONEY_UNIT = "10원 단위로 입력해주세요.";
    private static final String ERROR_LOTTO_PRICE = "1,000원 이상을 넣어주세요.";

    private final int payment;

    public Payment(final int payment) {
        validatePayment(payment);
        this.payment = payment;
    }

    public int getPayment() {
        return payment;
    }

    private void validatePayment(final int payment) {
        validateNaturalNumber(payment);
        validateMoneyUnit(payment);
        validateLottoPrice(payment);
    }

    private void validateNaturalNumber(final int number) {
        if (number <= 0) {
            throw new IllegalArgumentException(ERROR_ONLY_NATURAL_NUMBER);
        }
    }

    private void validateMoneyUnit(final int number) {
        if ((number % MONEY_UNIT) != 0) {
            throw new IllegalArgumentException(ERROR_MONEY_UNIT);
        }
    }

    private void validateLottoPrice(final int number) {
        if (number < Lotto.LOTTO_PRICE) {
            throw new IllegalArgumentException(ERROR_LOTTO_PRICE);
        }
    }
}
