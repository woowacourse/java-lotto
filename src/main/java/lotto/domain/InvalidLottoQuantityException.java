package lotto.domain;

public class InvalidLottoQuantityException extends RuntimeException {
    InvalidLottoQuantityException(String message) {
        super(message);
    }
}
