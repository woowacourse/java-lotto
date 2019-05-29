package lotto.domain.domainexception;

public class InvalidWinningLottoException extends RuntimeException {
    public InvalidWinningLottoException(String message) {
        super(message);
    }
}
