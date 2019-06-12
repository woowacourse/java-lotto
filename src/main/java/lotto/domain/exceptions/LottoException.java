package lotto.domain.exceptions;

public class LottoException extends RuntimeException {
    public LottoException(String message) {
        super(message);
    }

    public LottoException() {
        super();
    }
}
