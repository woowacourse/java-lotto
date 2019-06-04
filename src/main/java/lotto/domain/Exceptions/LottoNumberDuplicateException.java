package lotto.domain.Exceptions;

public class LottoNumberDuplicateException extends LottoException {
    public LottoNumberDuplicateException(String message) {
        super(message);
    }

    public LottoNumberDuplicateException() {
        super();
    }
}
