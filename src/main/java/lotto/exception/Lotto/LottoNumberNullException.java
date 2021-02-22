package lotto.exception.Lotto;

public class LottoNumberNullException extends LottoException {
    public static final String ERROR_MESSAGE = "null 값이 들어와서는 안됩니다.";

    public LottoNumberNullException() {
        super(ERROR_MESSAGE);
    }
}
