package model;

public enum ExceptionMessage {
    INVALID_BONUS_TYPE("보너스 번호는 숫자여야 합니다."),
    INVALID_BONUS_RANGE("보너스 번호는 %d부터 %d사이여야 합니다."),
    BONUS_DUPLICATE("보너스 번호는 당첨 번호와 중복되지 말아야 합니다."),
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
