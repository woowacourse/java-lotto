package lotto.exception;

public class IllegalLottoFormatException extends IllegalArgumentException {
    public IllegalLottoFormatException() {
        super();
    }

    public IllegalLottoFormatException(String s) {
        super(s);
    }
}
