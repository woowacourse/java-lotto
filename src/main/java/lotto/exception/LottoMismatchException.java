package lotto.exception;

public class LottoMismatchException extends RuntimeException{
    public LottoMismatchException() {
        super();
    }

    public LottoMismatchException(String message) {
        super(message);
    }
}
