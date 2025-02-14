package model;

public enum ExceptionMessage {
    INVALID_INPUT_NULL_OR_BLANK("공백이나 NULL은 입력할 수 없습니다."),
    INVALID_INPUT_TYPE("입력은 숫자여야 합니다."),

    INVALID_BONUS_TYPE("보너스 번호는 숫자여야 합니다."),
    INVALID_BONUS_RANGE("보너스 번호는 %d부터 %d사이여야 합니다."),
    BONUS_DUPLICATE("보너스 번호는 당첨 번호와 중복되지 말아야 합니다."),

    INVALID_LOTTO_SIZE("로또 번호는 %d개여야 합니다."),
    INVALID_LOTTO_RANGE("로또 번호는 %d부터 %d사이여야 합니다."),
    LOTTO_DUPLICATE("로또 번호는 중복되지 않아야 한다."),
    INVALID_LOTTO_TYPE("로또 번호는 숫자여야 합니다"),

    INVALID_LOTTO_PURCHASE_TYPE("로또 구입금액은 숫자여야 합니다."),
    INVALID_LOTTO_MIN_PURCHASE("로또 구입 최소 금액은 %d원 입니다."),
    INVALID_LOTTO_PURCHASE_UNIT("로또 구입금액은 %d원 단위여야 합니다."),
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
