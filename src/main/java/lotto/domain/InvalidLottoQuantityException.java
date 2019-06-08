package lotto.domain;

public class InvalidLottoQuantityException extends RuntimeException {
    public InvalidLottoQuantityException(String message) {
        super(message);
    }
}
