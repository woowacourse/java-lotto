package lotto.constant;

public enum ErrorMessage {
    NOT_NUMBER("숫자가 아닌 값을 입력할 수 없습니다."),
    INVALID_PRICE("구입 금액은 %,d원 단위로 입력해야 합니다."),
    INVALID_LOTTO_NUMBER_COUNT("로또 번호는 %d개를 입력해야 합니다."),
    OUT_OF_RANGE_LOTTO_NUMBER("로또 번호는 %d ~ %d 값을 입력해야 합니다."),
    WINNING_NUMBERS_CONTAIN_BONUS_NUMBER("당첨 번호와 보너스 번호는 중복될 수 없습니다."),
    DUPLICATE_LOTTO_NUMBER("로또 번호는 중복될 수 없습니다."),
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
