package lotto.exception.money;

import lotto.exception.LottoExceptionStatus;

public enum MoneyExceptionStatus implements LottoExceptionStatus {

    MONEY_IS_NOT_NUMERIC("구입 금액은 숫자여야 합니다."),
    MONEY_IS_ZERO("구입 금액은 0원이 될 수 없습니다."),
    MONEY_IS_NOT_DIVISIBLE("구입 금액은 1000원으로 나누어 떨어져야 합니다.");

    private final String message;

    MoneyExceptionStatus(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

}
