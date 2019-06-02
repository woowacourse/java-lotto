package lotto.domain.exception;

public class InvalidLottoNumberException extends IllegalArgumentException {
    public InvalidLottoNumberException(String s) {
        super(s);
    }
}
