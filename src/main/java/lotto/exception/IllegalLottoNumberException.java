package lotto.exception;

public class IllegalLottoNumberException extends IllegalArgumentException {
    public IllegalLottoNumberException() {
        super();
    }

    public IllegalLottoNumberException(String s) {
        super(s);
    }
}
