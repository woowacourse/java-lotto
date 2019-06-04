package lotto.domain.Exceptions;

public class LottoException extends RuntimeException {
    public LottoException(String message) {
        super(message);
    }

    public LottoException() {
        super();
    }
}
