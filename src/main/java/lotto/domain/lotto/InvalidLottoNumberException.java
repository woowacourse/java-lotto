package lotto.domain.lotto;

public class InvalidLottoNumberException extends RuntimeException {
    InvalidLottoNumberException(String message) {
        super(message);
    }
}
