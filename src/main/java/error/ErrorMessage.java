package error;

public enum ErrorMessage {
    INVALID_NUMBERS_SIZE("로또 번호 갯수가 일치하지 않습니다."),
    INVALID_DUPLICATE_NUMBER("로또 번호는 중복되어선 안 됩니다."),
    INVALID_NUMBER_RANGE("로또 번호는 1~45 사이의 숫자여야 합니다.");

    private final String message;
    private final String prefix="[ERROR] ";

    ErrorMessage(String message) {
        this.message = prefix+message;
    }

    public String getMessage() {
        return message;
    }
}
