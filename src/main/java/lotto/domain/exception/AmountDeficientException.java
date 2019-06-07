package lotto.domain.exception;

public class AmountDeficientException extends RuntimeException {
    public AmountDeficientException(String content) {
        super(content);
    }
}
