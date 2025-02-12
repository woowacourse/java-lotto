package exception;

public enum ExceptionMessage {
    INVALID_POSITIVE("구매 금액은 양수여야 합니다."),
    INVALID_INTEGER("구매 금액은 정수여야 합니다."),
    INVALID_UNIT_PRICE("구매 금액 단위로 입력해 주세요."),
    INVALID_RANGE("범위에 맞는 로또 번호를 입력하세요."),

    PREFIX("[ERROR] ");

    private String message;

    ExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return PREFIX.getMessage() + message;
    }
}
