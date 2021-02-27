package lotto.exception.Lotto;

public class LottoNumberNullException extends LottoException {
    private static final String ERROR_MESSAGE = "[ERROR] null 값이 들어와서는 안됩니다.";

    public LottoNumberNullException() {
        super(ERROR_MESSAGE);
    }
}
