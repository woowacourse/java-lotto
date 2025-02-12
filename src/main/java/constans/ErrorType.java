package constans;

public enum ErrorType {

    숫자오류("숫자여야합니다."),
    로또숫자범위오류("로또 번호 범위는 1에서 45까지의 숫자여야합니다."),
    로또숫자중복오류("로또 번호들은 중복될 수 없습니다."),

    // input view
    PURCHASE_AMOUNT_RANGE_INVALID("구입 금액의 범위가 유효하지 않습니다."),
    PURCHASE_AMOUNT_IS_NOT_NUMBER("구입 금액이 숫자가 아닙니다."),
    WINNING_NUMBERS_FORMAT_INVALID("당첨 번호의 형식이 올바르지 않습니다."),
    WINNING_NUMBERS_IS_NOT_NUMBER("당첨 번호의 숫자가 올바르지 않습니다."),
    WINNING_NUMBERS_IS_DUPLICATION("당첨 번호의 숫자들 중 중복되는 수가 있습니다.");

    private final String message;

    ErrorType(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return "[ERROR]" + this.message;
    }
}
