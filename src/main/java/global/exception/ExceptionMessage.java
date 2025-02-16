package global.exception;

public enum ExceptionMessage {
    INVALID_POSITIVE("양수로 입력해 주세요."),
    INVALID_INTEGER("정수로 입력해 주세요."),
    INVALID_UNIT_PRICE("구매 금액 단위로 입력해 주세요."),
    INVALID_RANGE("범위에 맞는 로또 번호를 입력하세요."),
    INVALID_FORMAT("정해진 형식으로 입력해 주세요."),
    DUPLICATED_NUMBER("로또 번호는 중복될 수 없습니다.");

    private static String PREFIX = "[ERROR] ";

    private String message;

    ExceptionMessage(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return PREFIX + message;
    }
}
