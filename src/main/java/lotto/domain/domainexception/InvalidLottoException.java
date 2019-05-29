package lotto.domain.domainexception;

public class InvalidLottoException extends RuntimeException {
    public InvalidLottoException(String message) {
        super(message);
    }
}
