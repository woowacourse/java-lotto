package error;

public enum ErrorMessage {
    WINNING_NUMBERS_ALREADY_DRAWN("이미 당첨번호가 추첨되었습니다."),
    WINNING_NUMBERS_NOT_DRAWN_YET("아직 당첨번호가 추첨되지 않았습니다."),
    WINNING_AND_BONUS_NUMBER_DUPLICATE("당첨 번호와 보너스 번호는 중복될 수 없습니다."),
    NUMBER_OUT_OF_RANGE("숫자 범위가 벗어났습니다."),
    INVALID_LOTTO_COUNT("로또 숫자는 6개여야 합니다."),
    DUPLICATE_NUMBERS_FOUND("중복된 숫자가 있습니다."),
    PURCHASE_AMOUNT_INVALID("구입 금액은 1000원 단위로만 가능합니다."),
    INPUT_MUST_BE_NUMERIC("숫자만 입력할 수 있습니다."),
    NEGATIVE_NUMBER_NOT_ALLOWED("음수는 입력할 수 없습니다."),
    PRIZE_NOT_DETERMINED("아직 당첨번호가 추첨되지 않아 상금이 결정되지 않았습니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
