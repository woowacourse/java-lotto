package lotto.domain.exception;

public class DuplicateLottoNumberException extends IllegalArgumentException {
    public DuplicateLottoNumberException(String message) {
        super(message);
    }
}
