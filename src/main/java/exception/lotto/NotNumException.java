package exception.lotto;

public class NotNumException extends IllegalArgumentException {
    private static final String ERROR_MESSAGE = "숫자를 입력해주세요.";

    public NotNumException() {
        super(ERROR_MESSAGE);
    }
}
