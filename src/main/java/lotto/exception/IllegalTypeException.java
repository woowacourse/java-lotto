package lotto.exception;

public class IllegalTypeException extends RuntimeException {

    private static final String ERROR_MESSAGE = "[ERROR] 올바르지 않은 타입을 입력했습니다.";

    public IllegalTypeException() {
        super(ERROR_MESSAGE);
    }
}
