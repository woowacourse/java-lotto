package lotto.exception;

public class IllegalTypeException extends RuntimeException {

    private static final String NEW_LINE = "\n";
    private static final String ERROR_MESSAGE = "[ERROR] 올바르지 않은 타입을 입력했습니다.";
    private static final String INPUT_TYPE = "입력한 타입: ";

    public IllegalTypeException(Object inputObject) {
        super(ERROR_MESSAGE + NEW_LINE + INPUT_TYPE + inputObject.getClass());
    }
}
