package lotto.exception;

public class LottosException extends RuntimeException {
    public LottosException() {
        super("null이나 빈 값이 들어올 수 없습니다.");
    }
}