package lotto.constant;

public enum ErrorMessage {
    NOT_NUMBER("숫자가 아닌 값을 입력할 수 없습니다."),
    ;

    private static final String PREFIX = "[ERROR] ";

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return PREFIX + message;
    }
}
