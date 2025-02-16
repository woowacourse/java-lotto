package global.constant;

public enum ErrorMessage {
    NUMERIC_INPUT_ONLY_MESSAGE("숫자만 입력할 수 있습니다."),
    RANGE_INPUT_ONLY_MESSAGE("범위 내의 값만 입력할 수 있습니다."),
    LOTTO_NUMBER_DUPLICATE_MESSAGE("로또 번호는 중복될 수 없습니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
