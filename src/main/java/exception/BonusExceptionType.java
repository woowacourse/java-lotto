package exception;

public enum BonusExceptionType implements ExceptionMessage {

    INVALID_BONUS_RANGE("보너스 번호는 %d부터 %d사이여야 합니다."),
    BONUS_DUPLICATE("보너스 번호는 당첨 번호와 중복되지 말아야 합니다.");

    private final String message;

    BonusExceptionType(String message) {
        this.message = message;
    }

    @Override
    public String getMessage(Object... args) {
        return PREFIX + String.format(message, args);
    }
}
