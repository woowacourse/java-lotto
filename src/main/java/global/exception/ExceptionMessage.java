package global.exception;

public enum ExceptionMessage {
    INVALID_POSITIVE("구매 금액은 양수여야 합니다."),
    INVALID_INTEGER("구매 금액은 정수여야 합니다."),
    INVALID_UNIT_PRICE("구매 금액 단위로 입력해 주세요."),
    INVALID_RANGE("범위에 맞는 로또 번호를 입력하세요."),
    INVALID_FORMAT("쉼표로 구분하여 정수로 입력해 주세요."),
    INVALID_LENGTH("당첨 번호의 개수는 6개여야 합니다."),
    DUPLICATED_NUMBER("보너스 번호는 로또 번호와 중복될 수 없습니다.");

    private static String PREFIX = "[ERROR] ";

    private String message;

    ExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return PREFIX + message;
    }
}
