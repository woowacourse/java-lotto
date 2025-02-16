package constans;

public enum ErrorType {

    // RandomNumberGenerator
    NUMBER_GENERATOR_RANGE("시작값은 끝값을 초과할 수 없습니다."),
    RANDOM_NUMBER_GENERATOR_COUNT("뽑으려는 수의 개수가 유효하지 않습니다."),

    // PurchaseAmount
    PURCHASE_AMOUNT_POSITIVE("구입 금액은 양수여야합니다."),
    PURCHASE_AMOUNT_NOT_DIVIDE_LOTTO_PRICE("구입 금액은 로또 가격으로 나누어 떨어져야합니다."),

    // WinningNumbers
    WINNING_NUMBERS_IS_INVALID_SIZE("당첨 번호는 6개여야합니다."),
    WINNING_NUMBERS_IS_DUPLICATION("당첨 번호의 숫자들 중 중복되는 수가 있습니다."),
    BONUS_BALL_IS_DUPLICATION("보너스 번호는 당첨 번호와 중복될 수 없습니다."),

    // LottoNumber
    LOTTO_NUMBER_RANGE("로또 번호 범위는 1에서 45까지의 숫자여야합니다."),
    LOTTO_NUMBER_IS_INVALID_SIZE("당첨 번호는 6개여야합니다."),
    LOTTO_NUMBER_DUPLICATE("로또 번호들은 중복될 수 없습니다."),

    // WinningResult
    WINNING_RESULT_POSITIVE("당첨 결과는 음수일 수 없습니다."),

    // ConsoleInputView
    PURCHASE_AMOUNT_INVALID_INPUT("구입 금액의 입력값이 올바르지 않습니다."),
    WINNING_NUMBERS_INVALID_INPUT("당첨 번호의 입력값이 올바르지 않습니다."),
    BONUS_BALL_INVALID_INPUT("보너스 볼의 입력값이 올바르지 않습니다.");

    private final String message;

    ErrorType(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return "[ERROR] " + this.message;
    }
}
