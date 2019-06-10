package lotto.domain.result;

public class InvalidWinningException extends RuntimeException {
    public InvalidWinningException(String msg) {
        super(msg);
    }
}
