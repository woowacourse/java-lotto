package lotto.exception.money;

import lotto.exception.LottoExceptionStatus;

public enum MoneyExceptionStatus implements LottoExceptionStatus {

    MONEY_MUST_BE_NUMERIC("구입 금액은 숫자여야 합니다."),
    MONEY_CANNOT_BE_ZERO("구입 금액은 0원이 될 수 없습니다."),
    MONEY_MUST_BE_DIVISIBLE("구입 금액은 1000원으로 나누어 떨어져야 합니다.");

    private final String message;

    MoneyExceptionStatus(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

}
