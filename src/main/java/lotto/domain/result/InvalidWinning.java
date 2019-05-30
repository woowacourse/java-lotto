package lotto.domain.result;

public class InvalidWinning extends RuntimeException {
    public InvalidWinning(String msg) {
        super(msg);
    }
}
