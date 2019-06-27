package lotto.domain.exception;

public class InvalidCountOfLottoNumberException extends IllegalArgumentException {
    public InvalidCountOfLottoNumberException(String message) {
        super(message);
    }
}
