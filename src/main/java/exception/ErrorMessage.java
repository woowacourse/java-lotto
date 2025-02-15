package exception;

public enum ErrorMessage {
    INVALID_MONEY_FORMAT("구입 금액은 숫자여야 합니다."),
    INVALID_BONUS_NUMBER_FORMAT("보너스 넘버는 숫자여야 합니다."),
    INVALID_BONUS_NUMBER_RANGE("보너스 번호는 1부터 45 사이 숫자여야 합니다."),
    INVALID_WINNING_NUMBERS_FORMAT("당첨 번호들은 숫자여야 합니다."),
    INVALID_LOTTO_NUMBER_RANGE("숫자는 1부터 45 사이여야 합니다."),
    INVALID_LOTTO_NUMBER_SIZE("당첨번호는 6개여야 합니다."),
    INVALID_LOTTO_NUMBER_DUPLICATE("중복된 숫자가 없어야 합니다."),
    INVALID_MONEY_UNIT("구입 금액은 1000 단위 숫자로 입력해야 합니다."),
    INVALID_MONEY_RANGE("구입 금액은 1000원 이상이어야 합니다."),
    INVALID_WINNING_NUMBER_DUPLICATE("보너스 번호는 로또 번호와 중복될 수 없습니다.");

    private static final String PREFIX = "[ERROR] ";
    private final String message;

    ErrorMessage(final String message) {
        this.message = PREFIX + message;
    }

    public String getMessage() {
        return message;
    }
}
