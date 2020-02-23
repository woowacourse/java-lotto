package lotto.domain;

public class WrongMoneyForLottoException extends IllegalArgumentException {
    public WrongMoneyForLottoException() {
        super();
    }

    public WrongMoneyForLottoException(final String message) {
        super(message);
    }
}
