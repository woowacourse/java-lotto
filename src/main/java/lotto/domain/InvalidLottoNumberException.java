package lotto.domain;

public class InvalidLottoNumberException extends RuntimeException {
    InvalidLottoNumberException(String message) {
        super(message);
    }
}
