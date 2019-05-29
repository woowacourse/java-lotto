package lotto.domain.domainexception;

public class InvalidLottoNumberException extends RuntimeException {
    public InvalidLottoNumberException(String message) {
        super(message);
    }
}