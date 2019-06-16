package lotto.domain.exception;

public class DuplicateLottoException extends RuntimeException {
    public DuplicateLottoException(final String message) {
        super(message);
    }
}
