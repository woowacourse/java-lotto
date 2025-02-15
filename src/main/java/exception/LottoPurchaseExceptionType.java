package exception;

public enum LottoPurchaseExceptionType implements ExceptionMessage{


    INVALID_LOTTO_PURCHASE_TYPE("로또 구입금액은 숫자여야 합니다."),
    INVALID_LOTTO_MIN_PURCHASE("로또 구입 최소 금액은 %d원 입니다."),
    INVALID_LOTTO_PURCHASE_UNIT("로또 구입금액은 %d원 단위여야 합니다.");

    private final String message;

    LottoPurchaseExceptionType(String message) {
        this.message = message;
    }

    @Override
    public String getMessage(Object... args) {
        return PREFIX + String.format(message, args);
    }
}
