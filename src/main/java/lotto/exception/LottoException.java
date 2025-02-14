package lotto.exception;

public class LottoException extends IllegalArgumentException {

    public LottoException(ErrorMessage message) {
        super(message.toString());
    }
}
