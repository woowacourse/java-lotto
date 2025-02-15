package exception;

public enum CommonExceptionType implements ExceptionMessage {
    INVALID_INPUT_NULL_OR_BLANK("입력은 공백이나 NULL 아니여야 합니다."),
    INVALID_NUMBER_FORMAT("입력은 숫자여야 합니다."),
    ;

    private final String message;

    CommonExceptionType(String message) {
        this.message = message;
    }

    @Override
    public String getMessage(Object... args) {
        return PREFIX + String.format(message, args);
    }
}
