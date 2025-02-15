package model;

public enum ExceptionMessage {

    INVALID_LOTTO_TYPE("로또 번호는 숫자여야 합니다"),

    ;

    private static final String PREFIX = "[ERROR] ";
    private final String message;

    ExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage(Object... args) {
        return PREFIX + String.format(message, args);
    }
}
