package lotto.domain.exceptions;

public class DuplicatedLottoNumbersException extends RuntimeException {
    public DuplicatedLottoNumbersException(String message) {
        super(message);
    }
}
