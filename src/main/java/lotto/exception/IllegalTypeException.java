package lotto.exception;

public class IllegalTypeException extends RuntimeException {

    private static final String ERROR_MESSAGE = "[ERROR] 잘못된 타입 입력입니다. 숫자를 넣어주세요";

    public IllegalTypeException() {
        super(ERROR_MESSAGE);
    }
}
