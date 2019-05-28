package lotto.domain;

public class DuplicatedLottoNumbersException extends RuntimeException {
    public DuplicatedLottoNumbersException(String message) {
        super(message);
    }
}
