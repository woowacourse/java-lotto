package lotto.exception;

public class WinningNumberException extends RuntimeException {
    public static final String ERROR_MESSAGE = "당첨 번호는 중복되지 않는 6개의 숫자여야 합니다.";

    public WinningNumberException() {
        super(ERROR_MESSAGE);
    }
}
