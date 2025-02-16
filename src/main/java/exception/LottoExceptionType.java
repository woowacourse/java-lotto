package exception;

public enum LottoExceptionType implements ExceptionMessage {

    INVALID_LOTTO_RANGE("로또 번호는 %d부터 %d 사이여야 합니다."),
    LOTTO_DUPLICATE("로또 번호는 중복되지 않아야 합니다.");

    private final String message;

    LottoExceptionType(String message) {
        this.message = message;
    }

    @Override
    public String getMessage(Object... args) {
        return PREFIX + String.format(message, args);
    }
}
