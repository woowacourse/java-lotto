package lotto.domain.exception;

public class LottoNumberOutOfBoundsException extends IllegalArgumentException {
    public LottoNumberOutOfBoundsException(String message) {
        super(message);
    }
}
