package lotto.exception;

public class InputTypeException extends IllegalArgumentException {

    public static final String INPUT_ONLY_INTEGER_ERROR_MESSAGE = "정수를 입력해주세요.";

    public InputTypeException(String message) {
        super(message);
    }
}
