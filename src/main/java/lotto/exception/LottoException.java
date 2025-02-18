package lotto.exception;

public class LottoException extends IllegalArgumentException {

    public LottoException(ExceptionMessage message) {
        super(message.getMessage());
    }
}
