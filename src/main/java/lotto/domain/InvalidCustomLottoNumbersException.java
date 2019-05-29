package lotto.domain;

public class InvalidCustomLottoNumbersException extends RuntimeException {
    public InvalidCustomLottoNumbersException(String message) {
        super(message);
    }
}
