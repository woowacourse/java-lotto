package lotto.model.exceptions;

public class IllegalCountException extends RuntimeException {
    private static String MESSAGE = "잘못된 입력입니다!";

    public IllegalCountException() {
        super(MESSAGE);
    }
}
