package error;

public enum ErrorMessage {
    INVALID_MONEY_FORMAT("구입 금액은 숫자여야 합니다."),
    INVALID_BONUS_NUMBER_FORMAT("보너스 넘버는 숫자여야 합니다."),
    INVALID_WINNING_NUMBERS_FORMAT("당첨 번호들은 숫자여야 합니다.");

    private static final String PREFIX = "[ERROR] ";
    private final String message;
    ErrorMessage(String message) {
        this.message = PREFIX + message;
    }
    public String getMessage() {
        return message;
    }
}
