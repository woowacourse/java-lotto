package lotto.domain.exceptions;

public class InvalidCustomLottoNumbersException extends RuntimeException {
    public InvalidCustomLottoNumbersException(String message) {
        super(message);
    }
}
