package constant.message;

public enum ExceptionMessage {

    INVALID_INPUT_NULL_OR_BLANK("입력값은 공백이거나 NULL일 수 없습니다."),

    INVALID_LOTTO_FORMAT("로또 번호는 숫자 형식이어야 합니다."),
    INVALID_LOTTO_RANGE("로또 번호는 %d부터 %d 사이여야 합니다."),
    INVALID_LOTTO_SIZE("로또 번호는 %d개여야 합니다."),
    DUPLICATE_LOTTO_NUMBER("로또 번호는 중복될 수 없습니다."),

    INVALID_BONUS_FORMAT("보너스 번호는 숫자 형식이어야 합니다."),
    INVALID_BONUS_RANGE("보너스 번호는 %d부터 %d 사이여야 합니다."),
    DUPLICATE_BONUS_NUMBER("보너스 번호는 당첨 번호와 중복될 수 없습니다."),

    INVALID_LOTTO_PURCHASE_FORMAT("로또 구입 금액은 숫자 형식이어야 합니다."),
    INVALID_LOTTO_MIN_PURCHASE("로또 최소 구입 금액은 %d원입니다."),
    INVALID_LOTTO_PURCHASE_UNIT("로또 구입 금액은 %d원 단위로 입력해야 합니다."),
    ;

    private static final String PREFIX = "[ERROR] ";
    private final String message;

    ExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return PREFIX + message;
    }

    public String getMessage(int arg) {
        return PREFIX + String.format(message, arg);
    }

    public String getMessage(int arg1, int arg2) {
        return PREFIX + String.format(message, arg1, arg2);
    }
}
